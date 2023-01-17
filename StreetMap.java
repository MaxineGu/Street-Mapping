// Jiayi Gu
//31526890
import javax.swing.JFrame;
public class StreetMap {
	public static void main(String [] args) {
		In in = new In(args[0]);
		int num = 0;
		if (in.hasNextLine()){
			while(in.readLine().startsWith("i")) {
				num++;
			}
		}
		String intersectionID;
		double lat, longitude;
		Intersection x;
		In in2 = new In(args[0]);
		Graph map = new Graph(num);
		String currentLine = in2.readLine();
		String [] inter;
		while(currentLine.startsWith("i")) {
			inter = currentLine.split("\t");
			intersectionID = inter[1];
			lat = Double.parseDouble(inter[2]);
			longitude = Double.parseDouble(inter[3]);
			x = new Intersection();
			x.distance = Integer.MAX_VALUE;
			x.IntersectionID = intersectionID;
			x.latitude = lat;
			x.longitude = longitude;
			x.reach = false;
			map.insert(x);
			if (in2.hasNextLine()) {
				currentLine = in2.readLine();
			} else break;
		}
		String roadID, int1, int2;
		Intersection a, b;
		double distance;

		while(currentLine.startsWith("r")) {
			inter = currentLine.split("\t");
			roadID = inter[1];
			int1 = inter[2];
			int2 = inter[3];
			a = Graph.get_intersect(int1);
			b = Graph.get_intersect(int2);
			distance = Graph.calcdist(a, b);
			map.insert(new Road(roadID, int1, int2, distance));
			if(in2.hasNextLine()){
				currentLine = in2.readLine();
			}
			else break;
		}
		boolean Map = false;
		boolean dijkstras = false;

		String Start = null;
		String End = null;

		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("--show")) {
				Map = true;
			}
			if(args[i].equals("--directions")) {
				dijkstras = true;
				Start = args[i+1];
				End = args[i+2];
			}
		}
		if(dijkstras == true) {
			Stopwatch timer = new Stopwatch();
			map.dijkstra(Start);
			System.out.println(Graph.shortestpath(End));
			if (Graph.connected == true) {
				System.out.println("Length of the path is "  + Graph.pathLength_miles() + " miles.");
			}
			double time = timer.elapsedTime();
			System.out.println("The total runtime for finding the shortest path is " + time + "seconds") ;
			}
		if(Map == true) {
			Stopwatch timer2 = new Stopwatch();
			JFrame frame = new JFrame("Map");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(new GUI(Graph.roads, Graph.Map_Intersection, Graph.minLat, Graph.maxLat, Graph.minLong, Graph.maxLong));
			frame.pack();
			frame.setVisible(true);
			double time = timer2.elapsedTime();
			System.out.println("The total runtime for mapping is " + time + "seconds") ;
		}
	}
}
