package com.smartroute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private final Map<Integer, Location> locations;
    private final Map<Integer, List<Edge>> roads;

    public Graph() {
        locations = new HashMap<>();
        roads = new HashMap<>();
    }

    public void addLocation(int id, String name) {

        Location location = new Location(id, name);

        locations.put(id, location);
        roads.put(id, new ArrayList<>());
    }

    public void addRoad(
            int from,
            int to,
            double distance,
            double travelTime) {

        if (!locations.containsKey(from) ||
                !locations.containsKey(to)) {

            return;
        }

        Edge forward =
                new Edge(to, distance, travelTime);

        Edge backward =
                new Edge(from, distance, travelTime);

        roads.get(from).add(forward);
        roads.get(to).add(backward);
    }

    public Location getLocation(int id) {
        return locations.get(id);
    }

    public List<Edge> getRoads(int id) {
        return roads.getOrDefault(
                id,
                new ArrayList<>()
        );
    }

    public Map<Integer, Location> getLocations() {
        return locations;
    }

    public boolean blockRoad(int from, int to) {

        boolean found = false;

        for (Edge edge : roads.getOrDefault(
                from,
                new ArrayList<>())) {

            if (edge.destination == to) {
                edge.blocked = true;
                found = true;
            }
        }

        for (Edge edge : roads.getOrDefault(
                to,
                new ArrayList<>())) {

            if (edge.destination == from) {
                edge.blocked = true;
            }
        }

        return found;
    }

    public boolean unblockRoad(int from, int to) {

        boolean found = false;

        for (Edge edge : roads.getOrDefault(
                from,
                new ArrayList<>())) {

            if (edge.destination == to) {
                edge.blocked = false;
                found = true;
            }
        }

        for (Edge edge : roads.getOrDefault(
                to,
                new ArrayList<>())) {

            if (edge.destination == from) {
                edge.blocked = false;
            }
        }

        return found;
    }

    public int getTotalLocations() {
        return locations.size();
    }

    public int getTotalRoads() {

        int total = 0;

        for (List<Edge> edges : roads.values()) {
            total += edges.size();
        }

        return total / 2;
    }

    public int getBlockedRoads() {

        int total = 0;

        for (List<Edge> edges : roads.values()) {

            for (Edge edge : edges) {

                if (edge.blocked) {
                    total++;
                }
            }
        }

        return total / 2;
    }

    public int getActiveRoads() {
        return getTotalRoads() - getBlockedRoads();
    }

    public void displayGraph() {

        System.out.println();
        System.out.println("========== ROAD NETWORK ==========");

        for (Map.Entry<Integer, Location> entry
                : locations.entrySet()) {

            int id = entry.getKey();

            Location location = entry.getValue();

            System.out.print(
                    location.name + " -> "
            );

            List<Edge> edges = roads.get(id);

            for (Edge edge : edges) {

                Location destination =
                        locations.get(edge.destination);

                System.out.print(
                        destination.name
                                + " ["
                                + edge.distance
                                + " km, "
                                + edge.travelTime
                                + " min"
                                + (
                                edge.blocked
                                        ? ", BLOCKED"
                                        : ""
                        )
                                + "] "
                );
            }

            System.out.println();
        }

        System.out.println("==================================");
    }
}