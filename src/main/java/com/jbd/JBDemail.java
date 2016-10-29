package com.jbd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JBDemail {
    private static final Logger LOGGER = LogManager.getLogger(JBDemail.class);

    public static void main(String[] args) {
        List<String> filesInStrings = new ArrayList<>();
        List<Email> eMailKeeper = new ArrayList<>();

        QuestionForm askQuestionsAndQuestionForm = new QuestionForm();
        askQuestionsAndQuestionForm.askQuestionsAndSetAnswers();

        PathGetter pG = new PathGetter();
        pG.createFileListFromPath(pG.askUserAboutInputPath());
        LOGGER.info("Found: " + pG.getFileList().size() + " files.");

        FileLoad fL = new FileLoad();
        filesInStrings.addAll(pG.getFileList().stream().map(fL::fileLoad).collect(Collectors.toList()));

        MakeEmailsFromString makeEmails = new MakeEmailsFromString();
        for (String s : filesInStrings) {
            eMailKeeper.addAll(makeEmails.makeEmailList(s));
        }

        ContentmentVerification cV = new ContentmentVerification();

        List<String> eMailToLookFor = new ArrayList<>();
        eMailToLookFor.add(SearchCriteria.getEMAIL());
        String dateOfEmailToLookFor = SearchCriteria.getSTARTDATE();
        //eMailToLookFor.add(SearchCriteria.getSTARTDATE());
        List<Email> result1;
        List<Email> result2;
        List<Email> result3;
        result1 = cV.searchEmailByName(eMailToLookFor, eMailKeeper);
        result2 = cV.searchEmailByTitleWithKeyWords(SearchCriteria.getKEYWORDS(), eMailKeeper);
        result3 = cV.searchEmailByDate(dateOfEmailToLookFor,eMailKeeper);

        // awh@malcolmhardie.com
        // 12/12/2004
        // 12/12/2006
        // test

        System.out.println("\n" + result1.size() + "\n");
        if(result1.size() == 0){
            System.out.println("Nie znaleziono wyników odpowiadająych podanemu krytetrium email");
        }
        else {
            System.out.println("Wyniki po nazwie maila: ");
        }
        System.out.println(result1);

        System.out.println("\n" + result2.size() + "\n");
        if(result2.size() == 0){
            System.out.println("Nie znaleziono wyników odpowiadająych podanemu krytetrium (Slowa kluczowe w tytule: )" + SearchCriteria.getKEYWORDS());
        }
        else {
            System.out.println("Wyniki po słowach kluczowych w tytule: ");
        }
        System.out.println(result2);

        System.out.println("\n" + result3.size() + "\n");
        if(result3.size() == 0){
            System.out.println("Nie znaleziono wyników odpowiadająych podanemu krytetrium (Data początkowa: )" + SearchCriteria.getSTARTDATE());
        }
        else {
            System.out.println("Wyniki po dacie: " + SearchCriteria.getSTARTDATE());
        }
        System.out.println(result3);

        System.out.println("\n" + eMailKeeper.size() + "\n");
        System.out.println(eMailKeeper);
        System.out.println(eMailKeeper.get(1).getData());

    }
}
