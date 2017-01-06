package com.jbd;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class PathGetterTest {
    private PathGetter pg = new PathGetter();
    private String absolutePath = new File("").getAbsolutePath();
    private String pathToFile = absolutePath + File.separator + "src" + File.separator + "main"
            + File.separator + "resources" + File.separator + "testlist.mbox";
    private String pathToDirectory = absolutePath + File.separator + "src" + File.separator + "main"
            + File.separator + "resources";

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
        assertEquals(pathToFile, pg.createFileListFromPath(pathToDirectory).get(2));
    }
}