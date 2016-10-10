package com.jbd;

import java.util.Scanner;

public class UserCommunication {

    public void sendUserMessage(String message) {
        System.out.println(message);
    }

    public String getUserResonse() {
        Scanner userResponse = new Scanner(System.in);
        return userResponse.nextLine();
    }
}
