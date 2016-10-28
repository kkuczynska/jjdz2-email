package com.jbd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileLoad {
    private StringBuilder builder;

    public String fileLoad(String file){
        builder = new StringBuilder();
        FileReader fr = null;
        String line;

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("error during opening");
            System.exit(1);
        }

        BufferedReader bfr = new BufferedReader(fr);
        try {
            while((line = bfr.readLine()) != null){
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("error during reading");
            System.exit(2);
        }

        try {
            fr.close();
        } catch (IOException e) {
            System.out.println("error during closing");
            System.exit(3);
        }

        return builder.toString();
    }

}