package com.smartroute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {

    private final Graph graph;

    private final Map<Integer, Double> distances;
    private final Map<Integer, Integer> previous;

    public Dijkstra(Graph graph) {

        this.graph = graph;

        distances = new HashMap<>();
        previous = new HashMap<>();
    }

    // Node used by the PriorityQueue
    static class Node {

        int id;
        double distance;

        Node(int id, double distance) {

            this.id = id;
            this.distance = distance;
        }
    }

    public void calculateShortestPath(
            int start,
            boolean fastestRoute) {

        distances.clear();
        previous.clear();

        // Initially, all locations have infinite distance
        for (int id : graph.getLocations().keySet()) {

            distances.put(
                    id,
                    Double.POSITIVE_INFINITY
            );
        }

        // Check whether starting location exists
        if (!distances.containsKey(start)) {
            return;
        }

        // Distance from start to itself is zero
        distances.put(start, 0.0);

        PriorityQueue<Node> queue =
                new PriorityQueue<>(
                        Comparator.comparingDouble(
                                node -> node.distance
                        )
                );

        queue.add(
                new Node(start, 0.0)
        );

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            int currentId = current.id;

            double currentDistance =
                    current.distance;

            // Ignore outdated queue entries
            if (currentDistance >
                    distances.get(currentId)) {

                continue;
            }

            // Check all roads from current location
            for (Edge edge :
                    graph.getRoads(currentId)) {

                // Ignore blocked roads
                if (edge.blocked) {
                    continue;
                }

                double weight;

                // Choose distance or travel time
                if (fastestRoute) {

                    weight = edge.travelTime;

                } else {

                    weight = edge.distance;
                }

                double newDistance =
                        currentDistance + weight;

                // Found a better route
                if (newDistance <
                        distances.get(
                                edge.destination
                        )) {

                    distances.put(
                            edge.destination,
                            newDistance
                    );

                    previous.put(
                            edge.destination,
                            currentId
                    );

                    queue.add(
                            new Node(
                                    edge.destination,
                                    newDistance
                            )
                    );
                }
            }
        }
    }

    public double getDistance(int destination) {

        return distances.getOrDefault(
                destination,
                Double.POSITIVE_INFINITY
        );
    }

    public List<Integer> getPath(
            int start,
            int end) {

        List<Integer> path =
                new ArrayList<>();

        // Destination does not exist
        if (!distances.containsKey(end)) {
            return path;
        }

        // No route exists
        if (distances.get(end) ==
                Double.POSITIVE_INFINITY) {

            return path;
        }

        int current = end;

        // Trace backwards from destination
        while (current != start) {

            path.add(current);

            Integer parent =
                    previous.get(current);

            // Safety check
            if (parent == null) {

                return new ArrayList<>();
            }

            current = parent;
        }

        // Add starting location
        path.add(start);

        // Reverse path to get start -> destination
        Collections.reverse(path);

        return path;
    }
}