package com.planatechnologies.androidchallenge.Helpers;

import com.google.android.material.textfield.TextInputLayout;
import com.planatechnologies.androidchallenge.R;

public class PasswordFieldValidator extends BaseValidator {
    private int mMinLength;

    // This is a constructor for the PasswordFieldValidator class. It is initializing the
    // errorContainer, length, and mErrorMessage.
    public PasswordFieldValidator(TextInputLayout errorContainer, int length) {
        super(errorContainer);
        mMinLength = length;
        mErrorMessage = mErrorContainer.getResources().getQuantityString(R.plurals.error_week_password, mMinLength, mMinLength);
    }

    /**
     * If the length of the CharSequence is greater than or equal to the minimum length, return true.
     *
     * @param charSequence The text that the user has entered
     * @return The length of the CharSequence.
     */
    @Override
    protected boolean isValid(CharSequence charSequence) {
        return charSequence.length() >= mMinLength;
    }
}
