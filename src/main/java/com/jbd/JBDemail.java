package com.jbd;

public class JBDemail {
    public static void main(String[] args) {
        PathGetter pG = new PathGetter();
        pG.createFileListFromPath(pG.askUserAboutInputPath());

    }
}
