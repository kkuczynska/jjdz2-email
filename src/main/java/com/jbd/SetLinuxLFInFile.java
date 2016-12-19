package com.jbd;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.*;

public class SetLinuxLFInFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(SetLinuxLFInFile.class);
    private static final Marker FL_MARKER = MarkerFactory.getMarker("SetLinuxLFInFile");
    private StringBuilder builder;

    public File RewriteFile(File file){
        LOGGER.info(FL_MARKER, "File loading started.");
        builder = new StringBuilder();
        FileReader fr = null;
        String line;

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            LOGGER.error(FL_MARKER, "Failed during opening.");
            System.exit(1);
        }

        BufferedReader bfr = new BufferedReader(fr);
        try {
            while((line = bfr.readLine()) != null){
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            LOGGER.error(FL_MARKER, "Failed during reading.");
            System.exit(2);
        }

        try {
            fr.close();
        } catch (IOException e) {
            LOGGER.error(FL_MARKER, "Failed during closing.");
            System.exit(3);
        }

        LOGGER.info(FL_MARKER, "File loading finished.");
        try {
            FileUtils.writeStringToFile(file, builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}