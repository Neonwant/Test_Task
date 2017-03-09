package com.softgroup.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter number or word: ");
            String str = inputStream.readLine();
            System.out.println(isPalindrom(str) ? "It's palindrom" : "It's not palindroms");

        }
        catch (IOException except) {
            System.err.println("Error");
        }
    }

    public static boolean isPalindrom(String source) {
        boolean result = true;
        source = source.replaceAll("[\\s]", "").toLowerCase();
        for (int i = 0; i < source.length() / 2; ++i) {
            if (source.charAt(i) != source.charAt(source.length() - i - 1)) {
                result = false;
                break;
            }
        }
        return result;
    }
}