package com.arithefirst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Birp {
    public static void main(String[] args) {
        ArrayList<Integer> feed = new ArrayList<Integer>(0);
        feed.add(0); // Inatlize the array to avoid Index Out of Bounds
        int pointer = 0;

        String source = readFile(args[0]);
        for (int i = 1; i != source.length() + 1; i++) {
            // Print start value the pointer (for debugging)
            System.out.print(String.format("Pointer: %d", pointer));
            try {
                switch (source.charAt(i - 1)) {
                    case '>':
                        // Increment pointer, add 0 to feed if pointer is out of bounds
                        if (i + 1 > feed.size()) {
                            feed.add(0);
                        }
                        pointer += 1;
                        break;
                    case '<':
                        // Decrement pointer, throw error if pointer is out of bounds
                        if (i - 1 < 0) {
                            throw new IndexOutOfBoundsException("Memory error: -1");
                        } else {
                            pointer -= 1;
                        }
                        break;
                    case '+':
                        // Increment value at pointer, reset to 0 if value is 255
                        if (feed.get(pointer) == 255) {
                            feed.set(pointer, 0);
                        } else {
                            feed.set(pointer, feed.get(pointer) + 1);
                        }
                        break;
                    case '-':
                        // Decrement value at pointer, reset to 255 if value is 0
                        if (feed.get(pointer) == 0) {
                            feed.set(pointer, 255);
                        } else {
                            feed.set(pointer, feed.get(pointer) - 1);
                        }
                        break;
                    default:
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            // Print the end value of the pointer (for debugging)
            System.out.println(String.format(" %d (%d)", pointer, feed.get(pointer)));
        }
        System.out.println(feed);
    }

    public static String readFile(String path) {
        String data = "";
        try {
            File bfile = new File(path);
            Scanner reader = new Scanner(bfile);
            while (reader.hasNextLine()) {
                String read = reader.nextLine();
                data += read;
            }
            reader.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println(String.format("No such file exists: \"%s\".\n", path));
            e.printStackTrace();
            System.exit(2);
            return "";
        }
    }
}
