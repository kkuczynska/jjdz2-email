package com.jbd;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileParserTest {
    private FileParser fP = new FileParser();
    private String absolutePath = new File("").getAbsolutePath();
    private String pathToEMLFile = absolutePath + File.separator + "src" + File.separator + "main"
            + File.separator + "resources" + File.separator + "sample.eml";
    private String pathToMBOXFile = absolutePath + File.separator + "src" + File.separator + "main"
            + File.separator + "resources" + File.separator + "testlist.mbox";

    @Test
    public void parseEmlFile() throws Exception {
        List<Email> e = fP.parseEmails(Arrays.asList(pathToEMLFile));
        assertThat(e.get(0).getFrom(), is ("yolo@exampleeml.com"));
    }

    @Test
    public void parseMbox() throws Exception {
        List<Email> e = fP.parseEmails(Arrays.asList(pathToMBOXFile));
        assertThat(e.size(), is (7));
    }
}