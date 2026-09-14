📌 About the Project

Project Name-Find Shortest Way

Overview:
Shortest-Path Application which is build based on java and DSA that models locations and roads as a weighted graph and uses Dijkstra's Algorithm to find the shortest route between two locations,
demonstrate how graph-based algorithms can be applied to a real-world route-planning problem.
The application represents each location as a vertex (node) and each road as a weighted edge, where the weight represents the 
distance between locations. Given a starting point and a destination, the application calculates the shortest available route using Dijkstra's Shortest Path Algorithm.

Features:
-Create and manage multiple locations
-Connect locations through weighted roads
-Represent the road network using a graph
-Find the shortest path between two locations
-Calculate the minimum total distance
-Display the shortest route in the console
-Uses an efficient priority-based approach for shortest-path calculation

Technologies & Concepts covered:
Java and DSA:
object-oriented programming, collections framework, graphs, algorithm etc..... many interrelated subtopics are used.

Class Responsibilities-                                        

Location.java         Represents a location/node in the graph                 
Edge.java             Represents a road connection and its distance           
Graph.java            Stores locations and their road connections             
Dijkstra.java         Calculates the shortest path using Dijkstra's Algorithm 
Main.java             Creates the graph and runs the application              |

Project Architecture and Working:

The application follows these steps:

Locations
    ↓
Road Connections
    ↓
Weighted Graph
    ↓
Starting Location + Destination
    ↓
Dijkstra's Algorithm
    ↓
Shortest Path
    ↓
Minimum Total Distance

-Create the graph
The program creates a Graph object.
-Add locations
Locations are added as vertices/nodes.
-Add roads
Roads are added between locations.
Each road has a distance/weight.
-Build the graph
Graph.java stores the locations and their connections using an adjacency list.
-Choose source and destination
The program identifies the starting location and destination.
-Run Dijkstra's Algorithm
Dijkstra.java searches for the shortest route between them.
-Use PriorityQueue
The closest location with the smallest known distance is processed first.
-Compare distances
The algorithm checks neighbouring locations and updates the distance when a shorter route is found.
-Track the route
Previous locations are stored so the final shortest path can be reconstructed.
-Display the result
The program displays:
Shortest route
Total distance


Dijkstra's Algorithm:

Dijkstra's Algorithm is a shortest-path algorithm used for finding the minimum distance between nodes in a weighted graph

The general process is:

1. Assign a distance of 0 to the starting location.
2. Assign infinity to all other locations.
3. Select the unvisited location with the smallest known distance.
4. Examine its neighboring locations.
5. Update their distances if a shorter route is found.
6. Continue until the destination has been reached or all reachable locations have been processed.
7. Reconstruct the shortest route using the stored path information.

Complexity:
When Dijkstra's Algorithm is implemented using an adjacency list and a priority queue, the typical time complexity is:

O((V + E) log V)

Where:

V = number of vertices (locations)
E = number of edges (roads)

The space complexity is approximately:

O(V + E)
This includes the graph representation and supporting data structures.

How to Run:
Prerequisites

-Java Development Kit (JDK)
-IntelliJ IDEA or another Java IDE
-Git (optional, for cloning the repository)

Run Using IntelliJ IDEA-

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA.
3. Make sure the Java source files are inside the correct package (Edge.java, Location.java, Graph.java, Dijkstra.java and Main.java).
4. Open Main.java
5. Run the main() method.
6. View the shortest route and distance in the console.

Clone the Repository

git clone https://github.com/MohammedShariqHussain/FindShortestWay.git

Then open the project in your preferred Java IDE(Integrated Development Environment — software where you can write, run, debug, and manage Java programs)

Example Output:
Shortest Path:
A -> B -> C -> D

Total Distance:
15 km.


-Author : Mohammed Shariq Hussain
