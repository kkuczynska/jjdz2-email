package com.jbd;


public class SearchCriteriaValidator {

    public static void validateEmail(String email) {
        String[] unacceptableChars = {"!", "#", "$", "%", "^", "&", "*", "(", ")", "+",
                "=", "[", "]", "{", "}", "~", "`", "\\", "|", ":", ";", "\"", "'", ",", "<", ">", "?", "/"};

        int validationBinary = 0;

        if(!email.contains("@") || !email.contains(".")) {
            validationBinary = 1;
        }

        validateUnacceptableCharsExisting(email,unacceptableChars);

        if(validationBinary==1) {
            System.out.println("Podany adres jest niepoprawny.");
        }
    }




    private static int validateUnacceptableCharsExisting (String validateInput, String[] unacceptableChars) {
        int validationBinary = 0;

        for(String unacceptableChar : unacceptableChars) {
            if (validateInput.contains(unacceptableChar)) {
                validationBinary = 1;
            }
        }

        return validationBinary;

    }






}
