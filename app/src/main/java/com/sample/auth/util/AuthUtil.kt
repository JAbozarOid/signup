package com.sample.auth.util

import java.util.regex.Pattern

enum class AuthCheckBoxState {
    CHECKED, UNCHECKED, ERROR
}

/**
 * it's singleton
 * we don't need create an instance of that class to use the function that is in it
 */
object AuthUtil {
    /**
     * the input is not valid if ...
     * ...the username is empty -> ok
     * ...the email is empty -> ok
     * ...the password is empty -> ok
     * ...the confirmed password is empty -> ok
     * ...the username is already taken -> ok
     * ...the username is not base on regex -> TODO
     * ...the email is already taken -> ok
     * ...the email is not base on regx -> ok
     * ...the password is not base on regx -> ok
     * ...the confirmed password is not the same as the real password -> ok
     */

    // this method calls in DeleteAccountFragment
    fun isFieldFilled(
        checkboxState: AuthCheckBoxState
    ): Boolean {
        return checkboxState != AuthCheckBoxState.UNCHECKED
    }

    // this method calls in SignupFragment
    fun isFieldsFilled(
        //username: String,
        email: String,
        password: String,
        confirmedPassword: String,
        checkboxState: AuthCheckBoxState
    ): Boolean {
        return !(
                //username.isEmpty()||
                email.isEmpty()
                        || password.isEmpty()
                        || confirmedPassword.isEmpty()
                        || checkboxState == AuthCheckBoxState.UNCHECKED)
    }

    fun isEmailAndPasswordFilled(email: String, password: String): Boolean {
        return !(email.isEmpty() || password.isEmpty())
    }

    fun isUsernameNotEmpty(username : String) : Boolean {
        return username.isNotEmpty()
    }

    // this method just check emptiness
    fun isUsernameValid(username: String): Boolean {
        return Pattern.compile(
            "^[a-zA-Z0-9\\S]{6,}\$"
        ).matcher(username).matches()
    }

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    /**
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
     * (?=\\S+$)          # no whitespace allowed in the entire string
     * .{6,}             # anything, at least six places though
     * $                 # end-of-string
     */
    // if the one of the above inputs doesn't meet we can show a message to user that a password is weak
    fun isPasswordValid(password: String): Boolean {
        return Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[!@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$"
        ).matcher(password).matches()
    }

    fun isConfirmPasswordValid(password: String, confirmedPassword: String): Boolean {
        return password == confirmedPassword
    }

    /**
     * username
     */
    fun String.isLengthLessThanEightCharacters(): Boolean {
        return  this.length<8 && this.isNotEmpty()
    }

    fun String.containsWhiteSpace(): Boolean {
        return this.contains(" ")
    }
}


