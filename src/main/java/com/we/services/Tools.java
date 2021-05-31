package com.we.services;

import java.util.Random;

public class Tools {

    private static int valLenStatic = 0;
    static String outputString = "";

    public static String convertToSonkha(String number) {
        int staticLen = number.length() - 1;
        if (number != null && number != "") {
        }
        if (valLenStatic > staticLen) {
            return "";
        }
        Character character = number.charAt(valLenStatic);
        ++valLenStatic;
        return switchConverter(character) + convertToSonkha(number);
    }

    private static String switchConverter(Character character) {
        switch (character) {
            case '@':
                outputString = "@";
                break;
            case '$':
                outputString = "$";
                break;
            case '#':
                outputString = "#";
                break;
            case '/':
                outputString = "/";
                break;
            case '+':
                outputString = "+";
                break;
            case '-':
                outputString = "-";
                break;

            case '.':
                outputString = ".";
                break;

            case '0':
                outputString = "০";
                break;

            case '1':
                outputString = "১";
                break;

            case '2':
                outputString = "২";
                break;

            case '3':
                outputString = "৩";
                break;

            case '4':
                outputString = "৪";
                break;

            case '5':
                outputString = "৫";
                break;

            case '6':
                outputString = "৬";
                break;

            case '7':
                outputString = "৭";
                break;

            case '8':
                outputString = "৮";
                break;

            case '9':
                outputString = "৯";
                break;

            default:
                outputString = "";
        }
        return outputString;
    }

    public static String randGen() {
        final String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        final String numbers = "0123456789";
        final String speecialChars = "({[]})";
        long current = System.currentTimeMillis();
        int len = 6;

        String randChars = String.valueOf(current);
//        String randChars = current + upperAlphabet + lowerAlphabet + numbers;
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        stringBuilder.setLength(0);

        for (int i = 0; i < len; i++) {
            int index = random.nextInt(randChars.length());
            stringBuilder.append(randChars.charAt(index));
        }
        return stringBuilder.toString();
    }
}
