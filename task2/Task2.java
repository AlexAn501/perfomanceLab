package com.perfomanceLab.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public static void main(String[] args) {
        List<String> coordinates = new ArrayList<>();
        List<String> points = new ArrayList<>();

        readFiles(args[0], args[1], coordinates, points);

        Pattern pattern = Pattern.compile("(?<X>(-)?\\w*(\\.\\w+)?)( (?<Y>(-)?\\w*(\\.\\w+)?))?");

        Matcher coordinateMatcher = pattern.matcher(coordinates.get(0));
        Matcher radiusMatcher = pattern.matcher(coordinates.get(1));

        if (!coordinateMatcher.find() || !radiusMatcher.find()) {
            System.err.println("Pattern exception");
        }

        float coordinateX = Float.parseFloat(coordinateMatcher.group("X"));
        float coordinateY = Float.parseFloat(coordinateMatcher.group("Y"));

        float radius = Float.parseFloat(radiusMatcher.group("X"));

        for (String point : points) {
            Matcher matcher = pattern.matcher(point);
            if (!matcher.find()) {
                System.err.println("Pattern exception");
            }

            float pointX = Float.parseFloat(matcher.group("X"));
            float pointY = Float.parseFloat(matcher.group("Y"));

            double distance = Math.hypot(coordinateX - pointX, coordinateY - pointY);

            if (distance == radius) {
                System.out.print(0 + "\n");
            } else if (distance > radius) {
                System.out.print(2 + "\n");
            } else {
                System.out.print(1 + "\n");
            }
        }
    }

    private static void readFiles(String file1, String file2, List<String> coordinates, List<String> points) {
        try {
            Files.lines(Path.of(file1))
                    .forEach(coordinates::add);
            Files.lines(Path.of(file2))
                    .forEach(points::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}