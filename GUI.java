// Jiayi Gu
//31526890
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;
public class GUI extends JPanel{

	
	public static ArrayList<Road> roads;
	public static HashMap<String, Intersection> Map_Intersection;
	public static boolean thickLines = false;
	public static double minLat, minLong, maxLat, maxLong;
	public static double xScale, yScale;
	
	public GUI(ArrayList<Road> roads, HashMap<String, Intersection> intersectMap, double min_lat, double max_lat, double min_long, double max_long) {

		GUI.roads = roads;
		GUI.Map_Intersection = intersectMap;
		
		minLat = min_lat;
		maxLat = max_lat;
		minLong = min_long;
		maxLong = max_long;
		setPreferredSize(new Dimension(800, 800));
	}
	public void paintComponent(Graphics page) {
		Graphics2D page2 = (Graphics2D) page;
		super.paintComponent(page2);
		page2.setColor(Color.BLACK);
		xScale = this.getWidth() / (maxLong - minLong);
		yScale = this.getHeight() / (maxLat - minLat);
		Intersection int1, int2;
		double x1, y1, x2, y2;
		for(Road r : roads) {
			int1 = Map_Intersection.get(r.IntersectionID1);
			int2 = Map_Intersection.get(r.intersectionID2);
			x1 = int1.longitude;
			y1 = int1.latitude;
			x2 = int2.longitude;
			y2 = int2.latitude;
			page2.draw(new Line2D.Double((x1-minLong) * xScale, getHeight() - ((y1 - minLat) * yScale), 
					(x2-minLong) * xScale, getHeight() - ((y2 - minLat) * yScale)));
		}
		if(Graph.path_dij != null) {
			
			page2.setColor(Color.RED);
			
			for(int i = 0; i < Graph.path_dij.length - 1; i++) {
				x1 = Graph.path_dij[i].longitude;
				y1 = Graph.path_dij[i].latitude;
				x2 = Graph.path_dij[i+1].longitude;
				y2 = Graph.path_dij[i+1].latitude;
				page2.draw(new Line2D.Double((x1-minLong) * xScale, getHeight() - ((y1 - minLat) * yScale), 
						(x2-minLong) * xScale, getHeight() - ((y2 - minLat) * yScale)));
			}
			
		}


	}
		
	}
	
	


