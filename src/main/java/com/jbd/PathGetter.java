package com.jbd;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathGetter {
    private String inputPath;
    private List<String> fileList;

    public String getInputPath() {
        return inputPath;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void askUserAboutInputPath() {
        Pattern pattern = Pattern.compile(".*\\.[\\w]{1,5}");
        Matcher matcher;
        fileList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println("Type correct path to file or directory:");
            inputPath = scanner.nextLine();
            if (inputPath.equals("")) {
                inputPath = new java.io.File("").getAbsolutePath();
            }
        } while (Files.notExists(Paths.get(inputPath)));

        matcher = pattern.matcher(inputPath);

        if(matcher.matches()){
            fileList.add(inputPath);
        }
        else{
            File[] files = new File(inputPath).listFiles();
            for (File file : files){
                if(file.isFile() && (file.getAbsolutePath().toLowerCase().endsWith(".mbox")
                        || file.getAbsolutePath().toLowerCase().endsWith(".eml"))){
                    fileList.add(file.getAbsolutePath());
                }
            }
        }
    }
}
