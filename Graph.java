// Jiayi Gu
//31526890
import java.util.*;
public class Graph {
	public static boolean connected = true;
	public HashMap<String, LinkedList> graph;
	public static ArrayList<Road> roads;
	public static HashMap<String, Intersection> Map_Intersection;
	public static PriorityQueue<Intersection> Heap_Intersection;
	public static Intersection [] path_dij;
	public static double minLat, maxLat, minLong, maxLong;
	//constructor
	public Graph(int num) {
		minLat = Integer.MAX_VALUE;
		minLong = Integer.MAX_VALUE;
		maxLat = Integer.MIN_VALUE;
		maxLong = Integer.MIN_VALUE;
		graph = new HashMap<String, LinkedList>();
		roads = new ArrayList<Road>();
		Map_Intersection = new HashMap<String, Intersection>();
		Comparator<Intersection> comparator = new Comparator<Intersection>() {
        	@Override
        	public int compare(Intersection i1, Intersection i2) {
            	if(i1.distance < i2.distance) {
            		return -1;
            	}
            	else {
            		return 1;
            	}
            }
		};
		Heap_Intersection = new PriorityQueue<Intersection>(num, comparator);
	}
	public int size() {
		return graph.size();
	}
	public void insert(Intersection i) {
		if(i.latitude < minLat) {
			minLat = i.latitude;
		}
		if(i.latitude > maxLat) {
			maxLat = i.latitude;
		}
		if(i.longitude < minLong) {
			minLong = i.longitude;
		}
		if(i.longitude > maxLong) {
			maxLong = i.longitude;
		}
		Map_Intersection.put(i.IntersectionID, i);
		Heap_Intersection.add(i);
		LinkedList temp = new LinkedList();
		temp.insert(i);
		graph.put(i.IntersectionID, temp);
	}
	public void insert(Road e) {
		LinkedList int1 = graph.get(e.IntersectionID1);
		LinkedList int2 = graph.get(e.intersectionID2);
		int1.insert(e);
		int2.insert(e);
		roads.add(e);
	}
	public static Intersection get_intersect(String intersectID) {
		return Map_Intersection.get(intersectID);
	}
	public static String shortestpath(String endID) {
		Intersection temp = Map_Intersection.get(endID);
		String [] path = new String[Map_Intersection.size()];
		int count = 0;
		while(temp.node != null) {
			path[count] = temp.IntersectionID;
			temp = temp.node;
			count++;
		}
		if(count == 0){
			connected = false;
			return ("Sorry, these two points are not connected");
		}
		path[count] = temp.IntersectionID;
		int totalroad = 0;
		for(int i = 0; i < path.length; i++) {
			if(path[i] == null) {
				totalroad = i;
				break;
			}
		}
		path_dij = new Intersection [totalroad];
		for(int i = 0; i < totalroad; i++) {
			path_dij[i] = Map_Intersection.get(path[i]);
		}
		String output = "The path is" + "\n";
		for(int i = count ; i > -1; i--) {
			output = output + path[i] + "\n";
		}
		return output;
	}
	public static double pathLength_miles() {
		//transfer form meters to miles
		return path_dij[0].distance * 0.000621371;
	}
	public static Intersection Vertex_Smallest() {
		Intersection temp = Heap_Intersection.remove();
		return Map_Intersection.get(temp.IntersectionID);
	}
	public void dijkstra(String intersectionID) {
		Intersection start = Map_Intersection.get(intersectionID);
		Heap_Intersection.remove(start);
		start.distance = 0;
		Heap_Intersection.add(start);
		double dis;
		int num_intersects = Map_Intersection.size();
		while(num_intersects > 0) {
			Intersection temp = Vertex_Smallest();
			temp.reach = true;
			num_intersects = num_intersects - 1;
			LinkedList currentIntersect = graph.get(temp.IntersectionID);
			Edge currentRoad = currentIntersect.head.edge;
			Intersection currentIntersection;
			while(currentRoad != null) {
				if(currentRoad.road.IntersectionID1.equals(temp.IntersectionID)) {
					currentIntersection = Map_Intersection.get(currentRoad.road.intersectionID2);
				}
				else {
					currentIntersection = Map_Intersection.get(currentRoad.road.IntersectionID1);
				}
				if(currentIntersection.reach == false) {
					dis = findDist(temp, currentIntersection);
					if(temp.distance + dis < currentIntersection.distance) {
						Heap_Intersection.remove(currentIntersection);
						currentIntersection.distance = temp.distance + dis;
						currentIntersection.node = temp;
						Heap_Intersection.add(currentIntersection);
					}
				}
				currentRoad = currentRoad.edge;
			}
		}
	}
	public double findDist(Intersection int1, Intersection int2) {
		LinkedList temp = graph.get(int1.IntersectionID);
		return temp.findDist(int2);
	}
	public static double calcdist(Intersection int1, Intersection int2) {
		return calcDist(int1.latitude, int1.longitude, int2.latitude, int2.longitude);
	}

	//reference: https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
	public static double calcDist(double lat1, double lon1,double lat2, double lon2) {
		final int R = 6371;
		double lat = Math.toRadians(lat2 - lat1);
		double lon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(lat / 2) * Math.sin(lat / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.sin(lon / 2) * Math.sin(lon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000;

		double height = 0;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}

}
