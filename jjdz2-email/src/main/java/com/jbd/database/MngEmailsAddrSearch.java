package com.jbd.database;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.jbd.searchEmails.SearchEmailsConstants.*;

@SessionScoped
public class MngEmailsAddrSearch implements Serializable {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MngEmailsAddrSearch.class);
    private static final Marker MARKER = MarkerFactory.getMarker("MngEmailsAddrSearch");

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    private void saveAddress(Address address) {
        entityManager.persist(address);
        LOGGER.info(MARKER, "Collected data address");
    }

    public List<String> getAllEmails() {
        List<String> emailsList = new ArrayList<>();
        TypedQuery<String> query = entityManager.createNamedQuery("Address.getAll", String.class);
        emailsList = query.getResultList();
        LOGGER.debug(MARKER, "All emails searched: " + emailsList);
        return emailsList;
    }

    @Transactional
    public void saveEmailsToDb(ArrayList<String> emails) {
        emails.stream()
                .filter(el -> el.length() > MINIMUM_EMAIL_ADDRESS_LENGTH)
                .filter(el -> el.contains(EMAIL_SPEC_CHAR_AT))
                .filter(el -> el.contains(EMAIL_SPEC_CHAR_DOT))
                .forEach(add -> {
                    saveAddress(new Address(add));
                });
    }
}
