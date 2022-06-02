package com.planatechnologies.androidchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.planatechnologies.androidchallenge.Helpers.EmailFieldValidator;
import com.planatechnologies.androidchallenge.Helpers.Helper;
import com.planatechnologies.androidchallenge.Helpers.PasswordFieldValidator;
import com.planatechnologies.androidchallenge.Helpers.RequiredFieldValidator;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener{
    View container;
    MaterialButton btnSignin;
    TextInputLayout Lemail,Lpassword;
    TextInputEditText email, password;
    RequiredFieldValidator LpasswordValidator;
    PasswordFieldValidator LpasswordLength;
    EmailFieldValidator LemailValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // This is a method that hides the system UI.
        Helper.hideSystemUI(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // This are the code that are used to link the variables to the xml file.
        container = findViewById(R.id.layout);
        btnSignin = findViewById(R.id.btnSignin);
        Lemail = findViewById(R.id.Lemail);
        Lpassword = findViewById(R.id.Lpassword);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        // This is a method that is used to validate the password field.
        LpasswordValidator = new RequiredFieldValidator(Lpassword);
        password.setOnFocusChangeListener(this);

        // This is a method that is used to validate the email field.
        LemailValidator = new EmailFieldValidator(Lemail);
        email.setOnFocusChangeListener(this);

        // This is a method that is used to validate the password field.
        LpasswordLength = new PasswordFieldValidator(Lpassword,6);


        // This is a lambda expression that is used to hide the keyboard when the user clicks outside
        // the keyboard.
        container.setOnClickListener(v -> Helper.hideSoftKeyboard(this));

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This is a method that is used to validate the password field.
                LpasswordValidator.validate(password.getText());
                LpasswordLength.validate(password.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This is a method that is used to validate the password and email field and enable
                // the sign in button.
                btnSignin.setEnabled(LemailValidator.validate(email.getText()) && LpasswordValidator.validate(password.getText()) && LpasswordLength.validate(password.getText()));
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This is a method that is used to validate the email field.
                LemailValidator.validate(email.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This is a method that is used to validate the password and email field and enable
                // the sign in button.
                btnSignin.setEnabled(LemailValidator.validate(email.getText()) && LpasswordValidator.validate(password.getText()) && LpasswordLength.validate(password.getText()));
            }
        });

        btnSignin.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            return; //we want to validate only fields loosing focus and not fields gaining focus
        }

        int id = v.getId();

        // This is a method that is used to validate the password and email field.
        if (id == R.id.password){
            LpasswordValidator.validate(password.getText());
            LpasswordLength.validate(password.getText());
        }
        else if(id == R.id.email)
            LemailValidator.validate(email.getText());

        // This is a method that is used to validate the password and email field and enable the sign in button.
        btnSignin.setEnabled(LemailValidator.validate(email.getText()) && LpasswordValidator.validate(password.getText()) && LpasswordLength.validate(password.getText()));
    }
}