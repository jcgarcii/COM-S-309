package com.example.frontend.Activities;

import static com.example.frontend.api.ApiClientFactory.GetUserApi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.R;
import com.example.frontend.api.SharedUserData;
import com.example.frontend.api.SlimCallback;
import com.example.frontend.model.User;

import java.util.List;

/**
 * Instantiates a new Login Activity which is used to login in and maintain the currently active user.
 * This Activity will also allow for user creation and registering in the database.
 * @author : Cole Hunt
 */
public class LoginActivity extends AppCompatActivity {


    boolean[] usernameAvaiable = {false};
    boolean[] usernameCorrect = {false};
    boolean[] passwordCorrect = {false};

    User currentUser = null;

    @Override
    /**
     * Overrides AppCompatActivity's onCreate method, this creates content and context for the XML counterpart
     * @param savedInstanceState used to pass in a currently existing activity for reloading past states
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Intent mainPage = new Intent(LoginActivity.this, MainActivity.class);

        TextView userMsg = findViewById(R.id.userMsg);

        Button postByPathBtn = findViewById(R.id.buttonRegister);
        Button userAuth = findViewById(R.id.buttonSignIn);
        ImageButton returnPageButton = findViewById(R.id.returnButtonLogin);

        EditText usernameIn = findViewById(R.id.editUsernameField);
        EditText passwordIn = findViewById(R.id.editPasswordField);

        postByPathBtn.setOnClickListener(view -> {
            RegisterUser(userMsg, usernameIn, passwordIn);
        });
        userAuth.setOnClickListener(view -> {
            LoginUser(userMsg, usernameIn, passwordIn);
        });

        returnPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Adds a new user to the database after confirming that they do not already exist
     * @param userMsg Text field that displays login info to inform user of errors
     * @param username Text field that holds the text within the user box
     * @param password Text field that holds the text within the password box
     */
      void RegisterUser(TextView userMsg, EditText username, EditText password){
        GetUserApi().GetAllUsers().enqueue(new SlimCallback<List<User>>(users-> {
            usernameAvaiable[0] = true;
            for (int i = users.size() - 1; i >= 0; i--) {
                currentUser = users.get(i);
                if (currentUser.getLoginname().compareTo(username.getText().toString()) == 0){
                 userMsg.setText("Username is taken");
                 usernameAvaiable[0] = false;
                 break;
             }
            }
            if( usernameAvaiable[0] == true){
                GetUserApi().PostUserByPath(username.getText().toString(), password.getText().toString(), username.getText().toString()).enqueue(new SlimCallback<User>(user -> {
                }));
                username.setText("");
                password.setText("");
                userMsg.setText("Account Created");
                SharedUserData.getInstance().SetSharedUser(currentUser);
            }
        }, "ConfirmUsernameAvaiable"));
      }

    /**
     * Logins in and updates the current user after checking database for existing users
     * @param userMsg Text field that displays login info to inform user of errors
     * @param username Text field that holds the text within the user box
     * @param password Text field that holds the text within the password box
     */
      void LoginUser(TextView userMsg, TextView username, TextView password){
        userMsg.setText("");
        usernameCorrect[0] = false;
        passwordCorrect[0] = false;
        GetUserApi().GetAllUsers().enqueue(new SlimCallback<List<User>>(users-> {
            for (int i = users.size() - 1; i >= 0; i--) {
             if (users.get(i).getLoginname().compareTo(username.getText().toString()) == 0){
                 usernameCorrect[0] = true;
                 if(users.get(i).getPassword().compareTo(password.getText().toString()) == 0) {
                     passwordCorrect[0] = true;
                     currentUser = users.get(i);
                     SharedUserData.getInstance().SetSharedUser(currentUser);
                     break;
                 }
             }
            }
            if (passwordCorrect[0] == true) {
                username.setText("");
                password.setText("");
                userMsg.setText("Login Success");
            }
            else if(usernameCorrect[0] == true){
                password.setText("");
                userMsg.setText("Incorrect Password");
            }
            else{
                password.setText("");
                userMsg.setText("Invalid Login");
            }
        }, "LoginUser"));
      }
}