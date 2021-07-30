package com.perfomanceLab.task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        int[] numbers = readFile(args[0]);

        float sum = 0;
        int max = numbers[0];
        int min = numbers[0];
        int indexMax = 0;
        int indexMin = 0;
        int countOperation = 0;

        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];

            if(numbers[i] > max){
                max = numbers[i];
                indexMax = i;
            }
            if(numbers[i] < min){
                min = numbers[i];
                indexMin = i;
            }
        }

        int average = Math.round(sum / numbers.length);

        while (numbers[indexMax] != average | numbers[indexMin] != average) {
            for (int i = 0; i < numbers.length; i++) {
                if(numbers[i] == average){
                    continue;
                }else if (numbers[i] > average) {
                    numbers[i] = numbers[i] - 1;
                    countOperation++;
                }else {
                    numbers[i] = numbers[i] + 1;
                    countOperation++;
                }
            }
        }
        System.out.print(countOperation + "\n");
    }


    private static int[] readFile(String path) {
        List<Integer> numbList = new ArrayList<>();
        try {
            Files.lines(Path.of(path))
                    .forEach(number -> numbList.add(Integer.parseInt(number)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] numbArr = new int[numbList.size()];
        for (int i = 0; i < numbList.size(); i++) {
            numbArr[i] = numbList.get(i);
        }
        return numbArr;
    }
}