package com.smartroute;

public class Edge {

    int destination;
    double distance;
    double travelTime;
    boolean blocked;

    public Edge(int destination, double distance, double travelTime) {
        this.destination = destination;
        this.distance = distance;
        this.travelTime = travelTime;
        this.blocked = false;
    }
}