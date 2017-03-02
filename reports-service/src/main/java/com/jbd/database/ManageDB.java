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

@SessionScoped
public class ManageDB implements Serializable {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ManageDB.class);
    private static final Marker MARKER = MarkerFactory.getMarker("ManageDB");

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void  saveUser(User User){
        entityManager.persist(User);
    }

    public List<User> searchForAll() {
        List<User> userList = new ArrayList<>();
        TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
        userList = query.getResultList();
        LOGGER.debug(MARKER, "All user list: " + userList);
        return userList;
    }

}
