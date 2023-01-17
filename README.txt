Jiayi Gu
31526890 
This project is used to plot a map and find the shortest routh path from two points by given data using Dijkstra’s algorithm. This project includes 11 files, each use is stated as following: 
Edge.java / Node.java/ Road.java/ Intersection.Java: A node contain an intersection and an edge. An intersection contains longitude, latitude (is given in the data), a unique Intersection ID, reach, intersection and distance(above three are used in Dijkstra's algorithm). 
LinkedList.java: It is used to implement the graph, used to contains intersections. 
Graph.java: Within the graph, there are two main parts: 
	- Implement the graph: Using hashmap, each intersection id has a coresponding linked list for all the intersections it is connected. 
	- Find the shortest path: Using Dijkstra’s algorithm for the start node, and then find the path from the start node to the end node. 
StreetMap.java: Contain the main method. You should first input the file name. And if you input "--show", it will shows the map, if you input "--directions" and two intersections, it will find the shortest path. It will returns the route, and also the path marks in red on the map. Also, it will show the runtime for plotting the map and finding the shortest path separately. 
In.java: From Lab 2, used for input the file in StreetMap.java. 
Stopwatch.java/ StdOut.java: From lab 2, used for calculating the runtime in StreetMap.java. 
Within the graph, there are several methods to help implement the graph and the Dijkstra's algorithm, including findDist, Vertex_Smallest, etc. After using Dijkstra to mark the distance and the intersection for each node, we use shortestpath to find the route from the start point to the end point. And finally calculuate the distance of the path. 
For calculating the distance between two intersection, we use the ‘haversine’ formula, where the code is from online, the reference is here: https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
Here is the runtime analysis: 
When process the ur.txt, monroe.txt and nys.txt, we have the following runtime: 
For the ur.txt(7KB), the runtime for finding the shortest path is 0.0 seconds. 
The runtime for mapping is 0.496 seconds. 
For the monroe.txt(5.3MB), the runtime for finding the shortest path is 0.099 seconds. 
The runtime for mapping is 1.013 seconds. 
For the nys.txt(35MB), the runtime for finding the shortest path is 14.982 seconds. 
The runtime for mapping is 0.453 seconds.
Therefore,  we can see that the runtime for the shortest path should be superlinearly, and actually it is. (it should be O(ElogV)). 
Moreover, we can see that the runtime for the mapping is similar for three files. The runtime for the mapping should be linearly to the number of the road since it just plot each road on the graph. Since it takes a really short time, the runtime here is similar for three files. 
