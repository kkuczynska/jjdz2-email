package com.jbd;

/**
 * Created by Admin on 25.12.2016.
 */
public enum Privilege {
    ADMIN("Administrator"), LOCAL("Local User");

    private String type;
    Privilege(String privileges) {
        this.type = privileges;
    }
}
