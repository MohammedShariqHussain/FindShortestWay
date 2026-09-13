package com.smartroute;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Graph graph = new Graph();

        // Locations
        graph.addLocation(1, "Home");
        graph.addLocation(2, "College");
        graph.addLocation(3, "Market");
        graph.addLocation(4, "Hospital");
        graph.addLocation(5, "Airport");

        // Roads
        graph.addRoad(1, 2, 5.0, 12.0);
        graph.addRoad(1, 3, 2.0, 5.0);
        graph.addRoad(3, 2, 1.0, 3.0);
        graph.addRoad(2, 4, 3.0, 8.0);
        graph.addRoad(3, 4, 7.0, 15.0);
        graph.addRoad(4, 5, 4.0, 10.0);

        while (true) {

            showMenu();

            System.out.print("Enter choice: ");

            int choice = readInt(scanner);

            switch (choice) {

                case 1:
                    graph.displayGraph();
                    break;

                case 2:
                    findRoute(graph, scanner);
                    break;

                case 3:
                    blockRoad(graph, scanner);
                    break;

                case 4:
                    unblockRoad(graph, scanner);
                    break;

                case 5:
                    addLocation(graph, scanner);
                    break;

                case 6:
                    addRoad(graph, scanner);
                    break;

                case 7:
                    showSummary(graph);
                    break;

                case 8:

                    System.out.println();
                    System.out.println(
                            "Thank you for using Smart Route Planner."
                    );

                    scanner.close();

                    return;

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    public static void showMenu() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("       SMART ROUTE PLANNER");
        System.out.println("=================================");
        System.out.println("1. View all roads");
        System.out.println("2. Find shortest route");
        System.out.println("3. Block a road");
        System.out.println("4. Unblock a road");
        System.out.println("5. Add a location");
        System.out.println("6. Add a road");
        System.out.println("7. Network summary");
        System.out.println("8. Exit");
        System.out.println("=================================");
    }

    public static void showLocations(Graph graph) {

        System.out.println();
        System.out.println("Available Locations:");

        for (Map.Entry<Integer, Location> entry
                : graph.getLocations().entrySet()) {

            System.out.println(
                    entry.getKey()
                            + " - "
                            + entry.getValue().name
            );
        }

        System.out.println();
    }

    public static void findRoute(
            Graph graph,
            Scanner scanner) {

        System.out.println();
        System.out.println("========== FIND ROUTE ==========");

        showLocations(graph);

        System.out.print("Enter start ID: ");
        int start = readInt(scanner);

        System.out.print("Enter destination ID: ");
        int end = readInt(scanner);

        if (graph.getLocation(start) == null ||
                graph.getLocation(end) == null) {

            System.out.println(
                    "Invalid location ID."
            );

            return;
        }

        if (start == end) {

            System.out.println(
                    "Start and destination cannot be the same."
            );

            return;
        }

        System.out.println();
        System.out.println("1. Shortest Distance");
        System.out.println("2. Fastest Time");

        System.out.print("Choose option: ");

        int option = readInt(scanner);

        if (option != 1 && option != 2) {

            System.out.println(
                    "Invalid option."
            );

            return;
        }

        boolean fastestRoute = option == 2;

        Dijkstra dijkstra = new Dijkstra(graph);

        dijkstra.calculateShortestPath(
                start,
                fastestRoute
        );

        List<Integer> path =
                dijkstra.getPath(start, end);

        if (path.isEmpty()) {

            System.out.println();
            System.out.println(
                    "No route available."
            );

            return;
        }

        double distance =
                calculateDistance(graph, path);

        double time =
                calculateTime(graph, path);

        System.out.println();
        System.out.println("=================================");
        System.out.println("           ROUTE FOUND");
        System.out.println("=================================");

        System.out.print("Path: ");

        printPath(graph, path);

        System.out.printf(
                "Distance: %.2f km%n",
                distance
        );

        System.out.printf(
                "Travel Time: %.2f minutes%n",
                time
        );

        if (fastestRoute) {

            System.out.println(
                    "Route Type: Fastest Time"
            );

        } else {

            System.out.println(
                    "Route Type: Shortest Distance"
            );
        }

        System.out.println("=================================");
    }

    public static double calculateDistance(
            Graph graph,
            List<Integer> path) {

        double total = 0.0;

        for (int i = 0;
             i < path.size() - 1;
             i++) {

            int from = path.get(i);
            int to = path.get(i + 1);

            for (Edge edge :
                    graph.getRoads(from)) {

                if (edge.destination == to) {

                    total += edge.distance;

                    break;
                }
            }
        }

        return total;
    }

    public static double calculateTime(
            Graph graph,
            List<Integer> path) {

        double total = 0.0;

        for (int i = 0;
             i < path.size() - 1;
             i++) {

            int from = path.get(i);
            int to = path.get(i + 1);

            for (Edge edge :
                    graph.getRoads(from)) {

                if (edge.destination == to) {

                    total += edge.travelTime;

                    break;
                }
            }
        }

        return total;
    }

    public static void printPath(
            Graph graph,
            List<Integer> path) {

        for (int i = 0;
             i < path.size();
             i++) {

            Location location =
                    graph.getLocation(path.get(i));

            System.out.print(location.name);

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }

    public static void blockRoad(
            Graph graph,
            Scanner scanner) {

        System.out.println();
        System.out.println("========== BLOCK ROAD ==========");

        showLocations(graph);

        System.out.print("Enter first location ID: ");
        int from = readInt(scanner);

        System.out.print("Enter second location ID: ");
        int to = readInt(scanner);

        if (graph.blockRoad(from, to)) {

            System.out.println(
                    "Road blocked successfully."
            );

        } else {

            System.out.println(
                    "Road not found."
            );
        }
    }

    public static void unblockRoad(
            Graph graph,
            Scanner scanner) {

        System.out.println();
        System.out.println("========= UNBLOCK ROAD =========");

        showLocations(graph);

        System.out.print("Enter first location ID: ");
        int from = readInt(scanner);

        System.out.print("Enter second location ID: ");
        int to = readInt(scanner);

        if (graph.unblockRoad(from, to)) {

            System.out.println(
                    "Road unblocked successfully."
            );

        } else {

            System.out.println(
                    "Road not found."
            );
        }
    }

    public static void addLocation(
            Graph graph,
            Scanner scanner) {

        System.out.println();
        System.out.println("========= ADD LOCATION =========");

        System.out.print("Enter location ID: ");

        int id = readInt(scanner);

        if (graph.getLocation(id) != null) {

            System.out.println(
                    "This ID already exists."
            );

            return;
        }

        System.out.print("Enter location name: ");

        String name = scanner.nextLine();

        if (name.trim().isEmpty()) {

            System.out.println(
                    "Location name cannot be empty."
            );

            return;
        }

        graph.addLocation(id, name);

        System.out.println(
                "Location added successfully."
        );
    }

    public static void addRoad(
            Graph graph,
            Scanner scanner) {

        System.out.println();
        System.out.println("=========== ADD ROAD ===========");

        showLocations(graph);

        System.out.print("Enter first location ID: ");

        int from = readInt(scanner);

        System.out.print("Enter second location ID: ");

        int to = readInt(scanner);

        if (graph.getLocation(from) == null ||
                graph.getLocation(to) == null) {

            System.out.println(
                    "Invalid location ID."
            );

            return;
        }

        if (from == to) {

            System.out.println(
                    "A road cannot connect a location to itself."
            );

            return;
        }

        System.out.print("Enter distance in km: ");

        double distance = readDouble(scanner);

        System.out.print(
                "Enter travel time in minutes: "
        );

        double travelTime = readDouble(scanner);

        if (distance <= 0 ||
                travelTime <= 0) {

            System.out.println(
                    "Distance and travel time must be greater than zero."
            );

            return;
        }

        graph.addRoad(
                from,
                to,
                distance,
                travelTime
        );

        System.out.println(
                "Road added successfully."
        );
    }

    public static void showSummary(Graph graph) {

        System.out.println();
        System.out.println("======== NETWORK SUMMARY ========");

        System.out.println(
                "Locations: "
                        + graph.getTotalLocations()
        );

        System.out.println(
                "Total Roads: "
                        + graph.getTotalRoads()
        );

        System.out.println(
                "Active Roads: "
                        + graph.getActiveRoads()
        );

        System.out.println(
                "Blocked Roads: "
                        + graph.getBlockedRoads()
        );

        System.out.println("=================================");
    }

    public static int readInt(Scanner scanner) {

        while (true) {

            try {

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Enter a valid integer: "
                );
            }
        }
    }

    public static double readDouble(Scanner scanner) {

        while (true) {

            try {

                return Double.parseDouble(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Enter a valid number: "
                );
            }
        }
    }
}