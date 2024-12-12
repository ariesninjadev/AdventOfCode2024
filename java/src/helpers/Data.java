package helpers;

import java.io.File;

public class Data {

    private String path;
    private int cols;
    private int rows;
    private char[][] data;

    public Data(String path) {
        this.path = path;
        this.cols = Data.getFromFileByLines(path)[0].length();

        String d = Data.getFromFile(path);
        this.rows = d.length() / cols;

        char[][] a = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            a[i] = d.substring(i * cols, (i + 1) * cols).toCharArray();
        }
        this.data = a;
    }

    public char[][] getAs2dArray() {
        return this.data;
    }

    public int getCols() {
        return this.cols;
    }

    public int getRows() {
        return this.rows;
    }

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
        // Remove newlines
        content = content.replace("\n", "");
        // Remove carriage returns
        content = content.replace("\r", "");
        // Remove EOF
        content = content.replace("\u001a", "");
        // Return the string
        return content;
    }

    public static String getFromFileRaw(String path) {
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
