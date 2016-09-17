package com.jbd;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PathGetter {
    private Path inputPath;

    public Path getInputPath() {
        return inputPath;
    }

    public Path askUserAboutInputPath() {
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("Type correct path:");
            inputPath = Paths.get(scanner.nextLine());
            if (inputPath.toString().equals("")) {
                inputPath = Paths.get(new java.io.File("").getAbsolutePath());
            }
        } while (Files.notExists(inputPath));
        return inputPath;
    }
}
