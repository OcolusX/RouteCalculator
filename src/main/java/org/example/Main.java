package org.example;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        RouteCalculator routeCalculator = new RouteCalculator("src/main/resources/routes.xlsx");

        double routeTime = 48;
        double sleepTime = 8;
        int sleepNum = 2;

        try {
            List<Route> optimalRouteList = routeCalculator.calculateOptimalRouteList(routeTime, sleepTime, sleepNum);

            double spentTime = 0;
            for (Route route : optimalRouteList) {
                System.out.println(route);
                spentTime += route.getSpentTime();
            }
            System.out.println("\nОбщее время маршрута (без учёта сна): " + spentTime + " ч.");
            System.out.println("Оставшееся время (в том числе на сон): " + (routeTime - spentTime) + " ч.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
