package helpers;

import java.io.File;

public class Data {

    // Get the content of a file as an array of lines
    public static String[] getFromFileByLines(String path) {
        // Get the file
        File file = new File(path);
        // Build the array
        String[] lines = new String[0];
        // Read the file
        try {
            lines = java.nio.file.Files.readAllLines(file.toPath()).toArray(new String[0]);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        // Return the array
        return lines;
    }

    // Get the content of a file as a string with newlines removed
    public static String getFromFile(String path) {
        // Get the file
        File file = new File(path);
        // Build the string
        String content = "";
        // Read the file
        try {
            content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        // Return the string
        return content;
    }

}
