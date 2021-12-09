package com.example.frontend.Activities;

import static com.example.frontend.api.ApiClientFactory.GetUserApi;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.R;
import com.example.frontend.api.SharedUserData;
import com.example.frontend.api.SlimCallback;
import com.example.frontend.model.FriendRequest;
import com.example.frontend.model.User;

import java.util.List;

/**
 * Instantiates a new Profile Activity which is used view current user info and maintain .
 * This Activity will also allow for user creation and registering in the database.
 * @author : Cole Hunt
 */
public class ProfileActivity extends AppCompatActivity {

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proflie_page);

        currentUser = SharedUserData.getInstance().GetSharedUser();

        Button manageFriendButton = findViewById(R.id.manageFriendButton);
        Button signOutButton = findViewById(R.id.signOutButton);
        ImageButton returnButton = findViewById(R.id.returnProfileButton);
        Button adminButtom = findViewById(R.id.ADMIN);


        TextView display_name = findViewById(R.id.screen_name);
        TextView user_id = findViewById(R.id.friendCode);
        TextView friendList = findViewById(R.id.friendListView);

        friendList.setMovementMethod(new ScrollingMovementMethod());
        friendList.setHeight(500);

        display_name.setText(currentUser.getDisplayname());
        user_id.setText(currentUser.getIdNum() + "");

        Intent friendManage = new Intent(ProfileActivity.this, FriendManageActivity.class);
        Intent admin = new Intent(ProfileActivity.this, AdminActivity.class);


        adminButtom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(admin);
             }});

        returnButton.setOnClickListener(v -> finish());

        displayFriends(friendList);

        manageFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(friendManage);
            }
        });


        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedUserData.getInstance().SetSharedUser(null);
                finish();
            }
        });
    }

    private void displayFriends(TextView friendList){
        GetUserApi().getFriendsById(currentUser.getIdNum()).enqueue(new SlimCallback<List<User>>(friends -> {
            for (int i = friends.size()-1; i>=0; i--){
                    friendList.append(friends.get(i).getDisplayname() + "\n");
            }
        }, "DisplayFriends"));
    }
}