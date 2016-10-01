package com.jbd;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by marcinb on 18.09.16.
 */
public class ContentmentVerification {

    private String example = "X-Mailer: Apple Mail (2.553)\n" +
            "Subject: [Testlist] test\n" +
            "Sender: testlist-admin@lists.malcolmhardie.com";
    private String pattern = "(Subject:)(.*)";
    private List<String> keywordsList = new ArrayList<>(SearchCriteria.getKEYWORDS());
    public List<String> foundTitle = new ArrayList<>();


    public void searchTitle(List<String> keywordsList){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(example);   // get a matcher object
        int count = 0;
        if (m.find()){
            boolean check = false;
            for (String item: keywordsList)
            {
                if (m.group(2).contains(item)){
                check = true;
                foundTitle.add(m.group(2));
                    break;

            }
                else {
                    System.out.println("Nie znaleziono!");

            }

            }
            if(check){
            for (String title: foundTitle) {
                System.out.println("tytul: " + title);
                }
            }
        }

    }
    


}
