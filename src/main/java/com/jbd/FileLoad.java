package com.jbd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class FileLoad {

    FileReader filer = null;
    String line = "";
    BufferedReader bfr = new BufferedReader(filer);

    public void fileLoad(Path inputPath) {
        try {
            filer = new FileReader(String.valueOf(inputPath));
        } catch (FileNotFoundException e) {
            System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
            System.exit(1);
        }
    }
    public void fileRead() {
        try {
            while ((line = bfr.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("BŁĄD ODCZYTU Z PLIKU!");
            System.exit(2);
        }
    }
}
