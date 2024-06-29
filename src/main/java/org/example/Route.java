package org.example;

import lombok.Getter;

@Getter
public class Route {

    // Название достопримечательности
    private final String name;

    // Затраты времени на посещение (в часах)
    private final double spentTime;

    // Важность
    private final int priority;

    // Ценность одного часа (отношение важности к затратам времени на посещение)
    private final double hourValue;

    public Route(String name, double spentTime, int priority) {
        this.name = name;
        this.spentTime = spentTime;
        this.priority = priority;
        this.hourValue = priority / spentTime;
    }

    @Override
    public String toString() {
        return "Время на посещение: " + spentTime + " ч.\t" + "Важность: " + priority + " \t" + name;
    }
}