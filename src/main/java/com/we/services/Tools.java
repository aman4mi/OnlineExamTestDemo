package com.we.services;

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
}
