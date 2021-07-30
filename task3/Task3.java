package com.perfomanceLab.task3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        File testsFile = new File(args[0]);
        File valuesFile = new File(args[1]);
        File reportFile = new File("report.json");

        Values values = null;

        try {
            values = mapper.readValue(valuesFile, Values.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(testsFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {

            String line = null;
            String value = null;
            int id = 0;

            while ((line = reader.readLine()) != null) {
                if (isId(line)) {
                    id = getId(line);
                    value = getValue(id, values).orElse("");
                }
                if (isValueLine(line)) {
                    if (getValueLine(line).contains(",")) {
                        String valueReport = getValueLine(line).substring(0, getValueLine(line).length() - 2);
                        valueReport += value + getValueLine(line).substring(getValueLine(line).length() - 2);
                        writer.write(valueReport + "\n");
                    } else {
                        String valueReport = getValueLine(line).substring(0, getValueLine(line).length() - 1);
                        valueReport += value + getValueLine(line).substring(getValueLine(line).length() - 1);
                        writer.write(valueReport + "\n");
                    }
                } else {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getValueLine(String line) {
        Pattern pattern = Pattern.compile("(?<v>\\s+\"value\": \"\"(,)?)");
        Matcher matcher = pattern.matcher(line);
        return (matcher.find()) ? matcher.group("v") : "";
    }

    private static boolean isValueLine(String line) {
        Pattern pattern = Pattern.compile("\"value\": \"\"");
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    private static Optional<String> getValue(int id, Values values) {
        return values.values.stream()
                .filter(entity -> entity.id == id)
                .map(entity -> entity.value)
                .findAny();
    }

    private static boolean isId(String line) {
        Pattern pattern = Pattern.compile("\"id\": (?<id>\\d+)");
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    private static int getId(String idString) {
        Pattern pattern = Pattern.compile("\"id\": (?<id>\\d+)");
        Matcher matcher = pattern.matcher(idString);
        return (matcher.find()) ? Integer.parseInt(matcher.group("id")) : 0;
    }
}