package com.example.codetesting.programmers;

public class Dart {
    public static int solution(String dartResult) {
        double result[] = new double[3];

        String[] array = dartResult.split("");
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            switch (array[i]) {
                case "S":
                case "D":
                case "T":
                    result[index] = Math.pow(result[index], get(array[i]));
                    if(i+1 < array.length){
                        if(!array[i+1].equals("*") && !array[i+1].equals("#")){
                            index++;
                        }
                    }
                    break;
                case "*":
                    if (index != 0) {
                        result[index - 1] = result[index-1] * 2;
                    }
                    result[index] = result[index] * 2;
                    index++;
                    break;
                case "#":
                    result[index] = result[index] * -1;
                    index++;
                    break;
                default:
                    if(i+1 < array.length){
                        if(array[i+1].equals("0")){
                            result[index] = Double.valueOf(10);
                            i++;
                            break;
                        }
                        result[index] = Double.valueOf(array[i]);
                    }
                    break;
            }
        }

        double sum = 0;
        for (double d : result) {
            sum += d;
        }
        return (int) sum;
    }

    private static int get(String special){
        switch (special){
            case "S":
                return 1;
            case "D":
                return 2;
            case "T":
                return 3;
        }
        throw new IllegalArgumentException();
    }
}
