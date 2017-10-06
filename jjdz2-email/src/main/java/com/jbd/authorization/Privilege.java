package com.jbd.authorization;

public enum Privilege {
    ADMIN("Administrator"), LOCAL("Local User");

    Privilege(String privileges) {
        String type = privileges;
    }
}
