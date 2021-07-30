package com.perfomanceLab.task1;

public class Task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int countStep = 0;
        int[] step = new int[m];
        int[] numbs = new int[n];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numbs.length; i++) {
            numbs[i] = i + 1;
        }

        for (int i = 0; i < numbs.length; i++, countStep++) {
            step[countStep] = numbs[i];

            if (countStep == step.length - 1) {
                result.append(step[0]);
                step[0] = numbs[i];
                countStep = 0;
            }

            if (i == numbs.length - 1) {
                i = -1;
            }
            if (numbs[0] == step[step.length - 1]) {
                break;
            }
        }
        System.out.print(result + "\n");
    }
}