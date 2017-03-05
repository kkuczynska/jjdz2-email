package com.jbd;
import com.jbd.cutEmails.RudeWordsInContent;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class RudeWordsInContentTest {
    RudeWordsInContent rudeWordsInContent = new RudeWordsInContent();

    public String przekle1 = "rudeWord1";
    String przekle2 = "rudeWord2";
    ArrayList<String> content1 = new ArrayList<>();

    @Test
    public void shouldBeTrueIfRudeWordsInContent(){
        content1.add(przekle1);
        content1.add(przekle2);
        for (String st : content1){
          rudeWordsInContent.ifRudeWord(content1);
            assertTrue(rudeWordsInContent.ifRudeWord(content1));
        }
    }
}

