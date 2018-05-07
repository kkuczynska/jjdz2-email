package com.jbd.searchEmails;

public class SearchEmailsConstants {

    public static final String FILE_UPLOAD_PATH = "src/main/resources/temporary";
    public static final int MINIMUM_EMAIL_ADDRESS_LENGTH = 3;

    public static final String LOGGING_MARKER = "SearchEmailsServlet";
    public static final String EMAIL_DISPATCHER = "/emails.jsp";
    public static final String URL_PATTERN = "emails";

    public static final String PHONE_NUMBERS_FOUND_ATTRIBUTE = "phoneNumbersFound";
    public static final String FINAL_EMAILS_SET_ATTRIBUTE = "finalEmailSet";
    public static final String EMAIL_FILE_ATTRIBUTE = "emailFile";
    public static final String EMAILS_ATTRIBUTE = "emails";
    public static final String START_DATE_ATTRIBUTE = "startDate";
    public static final String END_DATE_ATTRIBUTE = "endDate";
    public static final String KEYWORDS_ATTRIBUTE = "keywords";
    public static final String EMAILS_FOUND_ATTRIBUTE = "emailsFound";
    public static final String PHONE_NUMBERS_ATTRIBUTE = "phoneNumbers";

    public static final String FILE_PATH_PARAM = "emailPath";
    public static final String EMAIL_PARAM = "email";
    public static final String START_DATE_PARAM = "startDate";
    public static final String END_DATE_PARAM = "endDate";
    public static final String KEYWORDS_PARAM = "keywords";

    public static final String ENCODING = "UTF-8";
    public static final String ERROR_HTML_SPAN =
            "<span class=\"glyphicon glyphicon-exclamation-sign\" id=\"noPathMsg\"></span>\n" +
                    "No path to email file given.\n This field is required. \n";
    public static final String BACKSLASH = "/";
}
