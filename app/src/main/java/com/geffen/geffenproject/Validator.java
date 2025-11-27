package com.geffen.geffenproject;

public class Validator {

    public static boolean isEmailValid(String email) {
        return email != null && email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d).{8,}$");
    }

    public static boolean isNameValid(String name) {
        return name != null && name.matches("^[A-Za-z]{2,}$");
    }

    public static boolean isPhoneValid(String phone) {
        return phone != null && phone.matches("^[0-9]{7,15}$");
    }

    public static boolean isAllValid(
            String email,
            String password,
            String firstName,
            String lastName,
            String phone
    ) {
        return isEmailValid(email) &&
                isPasswordValid(password) &&
                isNameValid(firstName) &&
                isNameValid(lastName) &&
                isPhoneValid(phone);
    }
}
