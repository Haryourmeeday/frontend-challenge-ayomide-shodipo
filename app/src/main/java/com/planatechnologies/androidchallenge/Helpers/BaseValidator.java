package com.planatechnologies.androidchallenge.Helpers;

import com.google.android.material.textfield.TextInputLayout;
import com.planatechnologies.androidchallenge.R;


public class BaseValidator {
    TextInputLayout mErrorContainer;
    String mErrorMessage = "";
    String mEmptyMessage = mErrorContainer != null ? mErrorContainer.getResources().getString(R.string.required) : null;

    // This is a constructor. It is called when an object of the class is created.
    public BaseValidator(TextInputLayout errorContainer) {
        mErrorContainer = errorContainer;
    }

    /**
     * If the CharSequence is valid, return true, otherwise return false.
     *
     * @param charSequence The text to validate.
     * @return The return value is a boolean.
     */
    protected boolean isValid(CharSequence charSequence) {
        return true;
    }

    /**
     * If the field is empty, set the error message to the empty message. If the field is not empty and
     * isValid returns true, set the error message to an empty string. If the field is not empty and
     * isValid returns false, set the error message to the error message
     *
     * @param charSequence The text that the user has entered into the EditText.
     * @return A boolean value.
     */
    public boolean validate(CharSequence charSequence) {
        if (mEmptyMessage != null && (charSequence == null || charSequence.length() == 0)) {
            //set Empty error message for any empty field
            mErrorContainer.setError(mEmptyMessage);
            return false;
        } else if (isValid(charSequence)) {
            //custom implementation of the isValid returns true
            mErrorContainer.setError("");
            return true;
        } else {
            //set error for any other case
            mErrorContainer.setError(mErrorMessage);
            return false;
        }
    }

    /**
     * This function returns true if the two strings are equal, and false otherwise.
     *
     * @param s1 The first string to compare.
     * @param s2 The string to compare to.
     * @return True or False
     */
    public boolean confirm(CharSequence s1, CharSequence s2) {
        return s1.equals(s2);
    }
}