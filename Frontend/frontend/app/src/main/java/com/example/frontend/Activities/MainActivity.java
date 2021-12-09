package com.example.frontend.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.R;
import com.example.frontend.api.SharedUserData;
import com.example.frontend.model.User;

/** 
 * This is the Main Activity for our app, it calls the other activities during different stages within the app.  
 * These activities can be divided up into in-game and navigation. Drawing Activity covers much of the in-game activitiy,
 * While login covers much of the user accessibility and usability. 
 *  @author Cole Hunt and Jose Carlos Garcia
*/
public class MainActivity extends AppCompatActivity {

    public ProfileActivity profileActivity;
    public LoginActivity loginActivity;
    User currentUser;

    /**
     * This onCreate() method doesn't differ much from the standard one, main difference is that this calls and instantiates
     * each activity based on User input with the exclusion of the UserLogin as that comes up frist when the application is launched.
    * @param savedInstanceState - used in conjunction with our XML files corresponding to each activity 
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        ImageButton loginButton = findViewById(R.id.profileButton);
        Button doodleButton = findViewById(R.id.Doodle);


        currentUser = SharedUserData.getInstance().GetSharedUser();

        Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        Intent lobby = new Intent(MainActivity.this, LobbyActivity.class);



        profileActivity = new ProfileActivity();
        loginActivity = new LoginActivity();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser = SharedUserData.getInstance().GetSharedUser();
                if(currentUser != null){
                    startActivity(profile);
                    } else{
                    startActivity(login);
                }
            }
        });

        doodleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser = SharedUserData.getInstance().GetSharedUser();
                if(currentUser != null){
                    startActivity(lobby);
                }
                else{
                    startActivity(login);
                }
            }
        });
    }
}