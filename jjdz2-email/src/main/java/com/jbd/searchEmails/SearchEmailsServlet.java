package com.jbd.searchEmails;

import com.jbd.database.MngEmailsAddrSearch;
import com.jbd.fileManagement.FileParser;
import com.jbd.fileManagement.PathGetter;
import com.jbd.phoneNumbersSearch.PhoneNumbers;
import com.jbd.phoneNumbersSearch.SearchPhoneNumbersInEmails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.jbd.searchEmails.SearchEmailsConstants.*;

@WebServlet(urlPatterns = URL_PATTERN)
@MultipartConfig
public class SearchEmailsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchEmailsServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker(LOGGING_MARKER);

    @EJB
    EmailSearchForm emailSearchForm;
    @EJB
    PathGetter pathGetter;
    @EJB
    FileParser fileParser;
    @EJB
    FinalEmailsSet finalEmailsSet;
    @EJB
    SearchPhoneNumbersInEmails searchPhoneNumbersInEmails;
    @EJB
    PhoneNumbers phoneNumbers;

    @Inject
    MailHolder mailHolder;
    @Inject
    MngEmailsAddrSearch mngEmailsAddrSearch;

    private List<Email> emails = new ArrayList<>();
    private String message = "";
    private File file = null;
    private String fileName = "";
    private Set<Email> emailSet = null;
    private String emailPath;

    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        emailSearchForm.setEmail(req.getParameter(EMAIL_PARAM));
        LOGGER.info(MARKER, "Set value for email field.");
        emailSearchForm.setStartDate(req.getParameter(START_DATE_PARAM));
        LOGGER.info(MARKER, "Set value for start date field.");
        emailSearchForm.setEndDate(req.getParameter(END_DATE_PARAM));
        LOGGER.info(MARKER, "Set value for end date field.");
        emailSearchForm.setKeywords(req.getParameter(KEYWORDS_PARAM));
        LOGGER.info(MARKER, "Set value for keywords field.");

        new File(FILE_UPLOAD_PATH).mkdir();
        File uploads = new File(FILE_UPLOAD_PATH);

        LOGGER.info(MARKER, "Set directory for uploads");
        Part filePart = null;
        try {
            filePart = req.getPart(FILE_PATH_PARAM);
        } catch (IOException e) {
            LOGGER.debug(MARKER, "Caught IOException: " + e);
            e.printStackTrace();
        } catch (ServletException e) {
            LOGGER.debug(MARKER, "Caught ServletException: " + e);
            e.printStackTrace();
        }

        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        LOGGER.info(MARKER, "Name of the uploaded file has been saved: " + fileName);

        try {
            file = new File(uploads, fileName);
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, file.toPath());
                LOGGER.info(MARKER, "File has been saved to default directory: " + FILE_UPLOAD_PATH);
            }
        } catch (IOException e) {
            LOGGER.debug(MARKER, "Caught IOException: " + e);
            e.printStackTrace();
        }

        emailPath = FILE_UPLOAD_PATH.concat(BACKSLASH).concat(fileName);
        LOGGER.info(MARKER, "Set value for emailPath.");
        if (("".equals(fileName))) {
            req.setAttribute(EMAILS_FOUND_ATTRIBUTE, ERROR_HTML_SPAN);
        } else {
            try {
                emails = fileParser.parseEmails(pathGetter.createFileListFromPath(emailPath));
            } catch (Exception e) {
                LOGGER.debug(MARKER, "Wrong path has been given by user.");
                e.printStackTrace();
            }
            emailSet = finalEmailsSet.createUniqueEmailsSet(emails);
            req.setAttribute(EMAILS_FOUND_ATTRIBUTE, setEmailsHtmlHeaderAttr());

            Map<String, List<String>> resultMap = searchPhoneNumbersInEmails.searchPhoneNumbers(emails);
            message = phoneNumbers.setPhoneNumbersMessage(req.getParameter(PHONE_NUMBERS_ATTRIBUTE), resultMap, req);
        }

        saveEmailsToDb();

        setEncoding(req);

        mailHolder.setMails(emails);

        req.setAttribute(PHONE_NUMBERS_FOUND_ATTRIBUTE, message);
        req.setAttribute(FINAL_EMAILS_SET_ATTRIBUTE, emailSet);
        req.setAttribute(EMAIL_FILE_ATTRIBUTE, emailPath);
        req.setAttribute(EMAILS_ATTRIBUTE, finalEmailsSet.joinStringsWithComma(emailSearchForm.getEmail()));
        req.setAttribute(START_DATE_ATTRIBUTE, emailSearchForm.dateToDisplayInFrontEnd(emailSearchForm.getStartDate()));
        req.setAttribute(END_DATE_ATTRIBUTE, emailSearchForm.dateToDisplayInFrontEnd(emailSearchForm.getEndDate()));
        req.setAttribute(KEYWORDS_ATTRIBUTE, finalEmailsSet.joinStringsWithComma(emailSearchForm.getKeywords()));

        RequestDispatcher dispatcher = req.getRequestDispatcher(EMAIL_DISPATCHER);
        LOGGER.info(MARKER, "Dispatcher to emails.jsp");
        try {
            dispatcher.forward(req, response);
        } catch (ServletException e) {
            LOGGER.debug(MARKER, "Caught ServletException " + e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.debug(MARKER, "Caught IOException " + e);
            e.printStackTrace();
        }

        deleteFile();
    }

    private String setEmailsHtmlHeaderAttr() {
        LOGGER.info(MARKER, "Set JSP attribute \"finalEmailSet\".");
        if (emailSet.size() > ZERO_EMAILS) {
            return EMAILS_MATCHING_YOUR_CRITERIA;
        }
        return NO_EMAILS_MATCHING_YOUR_CRITERIA;
    }

    private void saveEmailsToDb() {
        mngEmailsAddrSearch.saveEmailsToDb(emailSearchForm.getEmail());
    }

    private void setEncoding(HttpServletRequest req) {
        try {
            req.setCharacterEncoding(ENCODING);
            LOGGER.info(MARKER, "Encoding Set.");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(MARKER, "Encoding fail.");
            e.printStackTrace();
        }
    }

    private void deleteFile() {
        file.delete();
        if (file.exists()) {
            LOGGER.warn(MARKER, "File " + emailPath + " was not deleted.");
        }
    }
}
