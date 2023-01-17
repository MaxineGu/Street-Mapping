// Jiayi Gu
//31526890
public class LinkedList {
	public int size;
	public Node head;
	
	//constructor
	public LinkedList() {
		head = new Node();
		size = 0;
	}

	public int size() {
		return size;
	}
	public double findDist(Intersection int2) {
		Edge temp2 = head.edge;
		while(temp2 != null) {
			if(temp2.road.IntersectionID1.equals(int2.IntersectionID) || temp2.road.intersectionID2.equals(int2.IntersectionID)) {
				return temp2.road.distance;
			}
			temp2 = temp2.edge;
		}
		return -1;
	}
	public void insert(Intersection intersect) {
		if(head.intersection == null) {
			head.intersection = intersect;
		}
		
		size++;
	}

	public void insert(Road road) {
		
		Edge tempEdge = new Edge();
		tempEdge.road = road;
		tempEdge.edge = head.edge;
		head.edge = tempEdge;

	}

}
