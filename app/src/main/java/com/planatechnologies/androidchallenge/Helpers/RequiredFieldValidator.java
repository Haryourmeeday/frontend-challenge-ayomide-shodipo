package com.planatechnologies.androidchallenge.Helpers;

import com.google.android.material.textfield.TextInputLayout;
import com.planatechnologies.androidchallenge.R;

public class RequiredFieldValidator extends BaseValidator {

    // This is the constructor of the class. It is calling the constructor of the super class and
    // setting the error message.
    public RequiredFieldValidator(TextInputLayout errorContainer) {
        super(errorContainer);
        mEmptyMessage =  mErrorContainer.getResources().getString(R.string.required);
    }

    /**
     * If the CharSequence is not null and has a length greater than 0, return true.
     *
     * @param charSequence The text that the user has entered into the EditText
     * @return A boolean value.
     */
    @Override
    protected boolean isValid(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0;
    }
}
