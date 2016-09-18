package com.jbd;

public class JBDemail {
    public static void main(String[] args) {
        PathGetter pG = new PathGetter();
        //don't tell anyone it's a secret -> click on "Run" window on bottom (default layout) to type input
        pG.askUserAboutInputPath();
        System.out.println(pG.getInputPath()); //delete this line
    }
}
