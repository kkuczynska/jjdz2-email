package com.jbd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathGetter {
    private static final String ACCEPTED_EXTENSIONS = ".*(.eml|.mbox)";
    private List<String> fileList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(PathGetter.class);
    private static final Marker PG_MARKER = MarkerFactory.getMarker("PathGetter");

    public List<String> getFileList() {
        return fileList;
    }

    public String askUserAboutInputPath(){
        Scanner scanner = new Scanner(System.in);
        String inputPath;
        do{
            LOGGER.info(PG_MARKER, "Waiting for user to type path.");
            System.out.println("Type correct path to file or directory:");
            inputPath = scanner.nextLine();
            if (inputPath.equals("")) {
                inputPath = new File("").getAbsolutePath();
            }
        } while (Files.notExists(Paths.get(inputPath)));
        LOGGER.info(PG_MARKER, "Scanned correct path.");
        return inputPath;
    }

    public List<String> createFileListFromPath(String inputPath) {
        Pattern pattern = Pattern.compile(ACCEPTED_EXTENSIONS);
        Matcher matcher = pattern.matcher(inputPath);
        fileList.clear();
        LOGGER.info(PG_MARKER, "Looking for files in given path.");
        if(matcher.matches()){
            fileList.add(inputPath);
        } else{
            File[] files = new File(inputPath).listFiles();
            for (File file : files){
                if(file.isFile() && (file.getAbsolutePath().toLowerCase().endsWith(".mbox")
                        || file.getAbsolutePath().toLowerCase().endsWith(".eml"))){
                    fileList.add(file.getAbsolutePath());
                }
            }
        }
        return fileList;
    }
}
