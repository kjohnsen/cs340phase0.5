package com.kajohnsen.client;

import com.kajohnsen.shared.IStringProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        int input = -1;
        while (input != 3) {
            System.out.println("");
            System.out.println("Welcome to the string processor! How would you like your strings processed?");
            System.out.println("1. Without command pattern");
            System.out.println("2. With command pattern");
            System.out.println("3. Exit the program");
            System.out.println("");
            System.out.print("Please select an option from 1-3: ");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            try {
                input = Integer.parseInt(br.readLine());

                if(input < 0 || input > 3) {
                    System.out.println("You have entered an invalid selection, please try again\r\n");
                } else if(input == 3) {
                    System.out.println("You have quit the program\r\n");
                    System.exit(1);
                } else {
                    String methodId = getUserMethodId();
                    String inString = getUserString();
                    IStringProcessor processor = null;
                    if (input == 1) processor = StringProcessorProxy.getInstance();
                    else if (input == 2) processor = StringProcessorCmdProxy.getInstance();
                    Method method = processor.getClass().getMethod(methodId, String.class);
                    Object result = method.invoke(processor, inString);
                    System.out.println("Your string after processing is:");
                    System.out.println(result.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private static String getUserString() throws IOException {
        System.out.println("What string would you like to process? ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    private static String getUserMethodId() throws IOException {
        System.out.println("What would you like to do with your string? ");
        System.out.println("1. Make it lower-case");
        System.out.println("2. Trim it");
        System.out.println("3. Parse a double from it");
        System.out.println("");
        System.out.print("Please select an option from 1-3: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("");
        int input = Integer.parseInt(br.readLine());
        if (input == 1) return "toLowerCase";
        else if (input == 2) return "trim";
        else if (input == 3) return "parseDouble";
        else return null;
    }
}
