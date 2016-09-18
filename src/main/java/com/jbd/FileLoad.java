package com.jbd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileLoad {

    void metoda(){
        FileReader filer = null;
        String linia = "";
        try {
            filer = new FileReader("nazwa pliku");
        } catch (FileNotFoundException e) {
            System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
            System.exit(1);
        }

        BufferedReader bfr = new BufferedReader(filer);
    }


    public static void main(String[] inputPath) {





    }






}
