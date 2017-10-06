package com.jbd.authorization;

/**
 * Created by Admin on 25.12.2016.
 */
public enum Privilege {
    ADMIN("Administrator"), LOCAL("Local User");

    Privilege(String privileges) {
        String type = privileges;
    }
}
