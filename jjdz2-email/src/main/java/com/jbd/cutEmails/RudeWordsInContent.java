package com.jbd.cutEmails;

import com.jbd.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Stateless
public class RudeWordsInContent {
    private static final Logger LOGGER = LoggerFactory.getLogger(RudeWordsInContent.class);
    private static final Marker RUDEWORDSINCONTENT_MARKER = MarkerFactory.getMarker("RudeWordsInContent");
    public boolean found;

    ArrayList<String> content = new ArrayList<>();
    ArrayList<String> content1 = new ArrayList<>();

    String regex = "Micha";
    public void iteratingThroughList(List<Email> eMailKeeper) {

        for (Email email : eMailKeeper) {
            content.add(email.getContent());
            content1.addAll(content);
        }
    }

    public boolean ifRudeWord(String content){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }

    public boolean ifRudeWord(ArrayList<String> content) {


        Pattern pattern = Pattern.compile(regex);

        for (String var : content1) {
            Matcher matcher = pattern.matcher(var);
            found = matcher.find();
            if (found) {
                LOGGER.info(RUDEWORDSINCONTENT_MARKER, "if true that means there were swearings in message   :   " + found);
            }

        }

        return true;
    }
}

