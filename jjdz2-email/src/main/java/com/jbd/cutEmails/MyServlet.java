package com.jbd.cutEmails;

import com.jbd.Email;
import com.jbd.MailHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@WebServlet(urlPatterns = "weirdcutemails")
public class MyServlet extends HttpServlet {

    public LocalDateTime data6 = LocalDateTime.of(2015, Month.DECEMBER, 05, 23, 59, 59);

    private static final Logger LOGGER = LoggerFactory.getLogger(MyServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("SearchKeywordsServlet");
    private int questionnaireCounter = 1;
    private List<Email> recivedEamils = new ArrayList<>();

    private List<Email> fdnaMails = new ArrayList<>();
@EJB
RudeWordsInContent rudeWordsInContent;
    @EJB
    FiveDaysNoAnswer fiveDaysNoAnswer;
    @Inject
    MailHolder mailHolder;


    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        List<Email> emails = new ArrayList<>();
        recivedEamils = mailHolder.getMails();

        Map<String,String> parsedQueryString = splitQuery(req.getQueryString());
        List<Email> outputEmails = new ArrayList<>();

        switch (parsedQueryString.get("type")){
            case "rudewords":{
                for (Email email : recivedEamils)
                {
                    if(rudeWordsInContent.ifRudeWord(email.getContent())){
                        outputEmails.add(email);
                    }
                }
            }
            case "fivedays":{
                for (Email email : recivedEamils){
                    if(fiveDaysNoAnswer.checkIfWasAnswer(email.getData())){
                        outputEmails.add(email);
                    }
                }
            }
        }

        req.setAttribute("displayMails", outputEmails);

        LOGGER.info(MARKER, "Displaying: " + recivedEamils.size() + " records.");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/weirdcutemails.jsp");
        try {
            dispatcher.forward(req, response);
        } catch (ServletException e) {
            LOGGER.debug(MARKER, "Caught ServletException " + e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.debug(MARKER, "Caught IOException " + e);
            e.printStackTrace();
        }
    }

    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) {
    }
}
