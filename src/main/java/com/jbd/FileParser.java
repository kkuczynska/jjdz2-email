package com.jbd;

import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.dom.*;
import org.apache.james.mime4j.mboxiterator.CharBufferWrapper;
import org.apache.james.mime4j.mboxiterator.MboxIterator;
import org.apache.james.mime4j.message.BodyPart;
import org.apache.james.mime4j.message.DefaultMessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.*;

public class FileParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileParser.class);
    private static final Marker FP_MARKER = MarkerFactory.getMarker("FileParser");
    private List<Email> emailsFromFiles = new ArrayList<>();

    private final static CharsetEncoder ENCODER = Charset.forName("UTF-8").newEncoder();

    public List<Email> parseEmails (List<String> fileList) throws Exception {
        LOGGER.info(FP_MARKER, "Parsing Started");
        emailsFromFiles.clear();

        for (String file : fileList) {
            if (file.endsWith(".eml")){
                LOGGER.info(FP_MARKER, "Found eml file.");
                emailsFromFiles.add(parseEML(new File(file)));
                LOGGER.info(FP_MARKER, "Parsing of single eml file finished.");
            } else if (file.endsWith(".mbox")){
                LOGGER.info(FP_MARKER, "Found mbox file.");
                emailsFromFiles.addAll(parseMbox(new File(file)));
                LOGGER.info(FP_MARKER, "Parsing of single mbox file finished.");
            }
        }
        LOGGER.info(FP_MARKER, "Parsing finished");
        System.gc();
        return emailsFromFiles;
    }

    public Email parseEML(File emlFile) throws Exception {
        LOGGER.info(FP_MARKER, "Parsing eml file.");
        Session mailSession = Session.getDefaultInstance(new Properties());
        InputStream source = new FileInputStream(emlFile);
        MimeMessage message = new MimeMessage(mailSession, source);

        return new Email(message.getFrom()[0].toString(), message.getSubject(), message.getSentDate(),  message.getContent().toString());
    }

    public List<Email> parseMbox(File mboxFile) throws IOException, MimeException {
        SetLinuxLFInFile s = new SetLinuxLFInFile();
        mboxFile = s.RewriteFile(mboxFile);

        LOGGER.info(FP_MARKER, "Parsing mbox file.");
        List<Email> emails = new ArrayList<>();

        for (CharBufferWrapper message : MboxIterator.fromFile(mboxFile).charset(ENCODER.charset()).build()) {
            DefaultMessageBuilder builder = new DefaultMessageBuilder();
            Message mess = builder.parseMessage(message.asInputStream(ENCODER.charset()));
            String messageBody;

            if (mess.isMultipart()) {
                Multipart multipart = (Multipart) mess.getBody();
                messageBody = parseBodyParts(multipart);

            } else {
                messageBody = getTxtPart(mess);
            }
            Email e = new Email(mess.getFrom().get(0).getAddress(),
                    mess.getSubject(),
                    mess.getDate(),
                    messageBody);
            emails.add(e);
        }

        LOGGER.info(FP_MARKER, "Found " + emails.size() + " email(s).");
        return emails;
    }

    private String getTxtPart(Entity part) throws IOException {
        TextBody body = (TextBody) part.getBody();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        body.writeTo(byteArrayOutputStream);
        return new String(byteArrayOutputStream.toByteArray());
    }

    private String parseBodyParts(Multipart multipart) throws IOException {
        StringBuilder txtBody = new StringBuilder();
        StringBuilder htmlBody = new StringBuilder();
        ArrayList<BodyPart> attachments = new ArrayList<>();

        for (Entity part : multipart.getBodyParts()) {
            if (part.getMimeType().equals("text/plain")) {
                String txt = getTxtPart(part);
                txtBody.append(txt);
            } else if (part.getMimeType().equals("text/html")) {
                String html = getTxtPart(part);
                htmlBody.append(html);
            } else if (part.getDispositionType() != null && !part.getDispositionType().equals("")) {
                attachments.add((BodyPart) part);
            }
            if (part.isMultipart()) {
                parseBodyParts((Multipart) part.getBody());
            }
        }
        return txtBody.toString();
    }
}
