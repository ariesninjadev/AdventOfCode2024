package helpers;

import java.io.File;

public class Data {

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

}
