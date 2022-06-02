package com.planatechnologies.androidchallenge.Helpers;

import android.util.Patterns;

import com.google.android.material.textfield.TextInputLayout;
import com.planatechnologies.androidchallenge.R;

public class EmailFieldValidator extends BaseValidator {

    // This is the constructor of the class. It is called when an object of the class is created.
    public EmailFieldValidator(TextInputLayout errorContainer) {
        super(errorContainer);
        mErrorMessage = mErrorContainer.getResources().getString(R.string.invalidEmail);
        mEmptyMessage =  mErrorContainer.getResources().getString(R.string.emptyEmail);
    }

    /**
     * If the email address matches the pattern, return true, otherwise return false.
     *
     * @param charSequence The text to be validated.
     * @return A boolean value.
     */
    @Override
    protected boolean isValid(CharSequence charSequence) {
        return Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }
}
