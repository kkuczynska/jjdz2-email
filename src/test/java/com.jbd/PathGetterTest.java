package com.jbd;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class PathGetterTest {
    PathGetter pg = new PathGetter();
    String absolutePath = new File("").getAbsolutePath();
    String pathToFile = absolutePath + "/src/main/resources/testlist.mbox";
    String pathToDirectory = absolutePath + "/src/main/resources";

    @Test
    public void emptyInputReturnsAbsolutePath() {
        String input = "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(absolutePath, pg.askUserAboutInputPath());
        System.out.println(absolutePath);
    }

    @Test
    public void pathToFileReturnsThatFile() {
        assertEquals(pathToFile, pg.createFileListFromPath(pathToFile).get(0));
    }

    @Test
    public void pathToDirectoryReturnsAllFilesInIt() {
        assertEquals(pathToFile, pg.createFileListFromPath(pathToDirectory).get(0));
    }
}