package com.example.frontend.Activities;

import static com.example.frontend.api.ApiClientFactory.GetUserApi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
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
public class FriendManageActivity extends AppCompatActivity {

    User currentUser;

    @Override
    /**
     * Overrides AppCompatActivity's onCreate method, this creates content and context for the XML counterpart
     * @param savedInstanceState used to pass in a currently existing activity for reloading past states
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_manage_page);

        currentUser = SharedUserData.getInstance().GetSharedUser();

        TextView friendRequestSendStatus = findViewById(R.id.friendRequestSendStatus);
        EditText friendRequestSendTo = findViewById(R.id.friendRequestSendTo);

        Button sendRequestButton = findViewById(R.id.sendRequestButton);
        Button acceptRequestButton = findViewById(R.id.acceptButton);
        Button denyRequestButton = findViewById(R.id.denyButton);
        ImageButton returnFriendManageButton = findViewById(R.id.returnFriendManageButton);

        TextView pendingRequests = findViewById(R.id.pendingRequests);

        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFriendRequest(Integer.valueOf(friendRequestSendTo.getText().toString()), friendRequestSendStatus);
            }
        });

        returnFriendManageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        acceptRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptFriendRequests(pendingRequests);
            }
        });

        denyRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                denyFriendRequests(pendingRequests);
            }
        });
    }

    private void sendFriendRequest(int friendRequestSendTo, TextView friendRequestSendStatus){
        GetUserApi().GetAllUsers().enqueue(new SlimCallback<List<User>>(users-> {
            boolean usernameFound = false;
            for (int i = users.size() - 1; i >= 0; i--) {
                if (users.get(i).getIdNum() == friendRequestSendTo){
                    GetUserApi().PostFriendRequestByPath(friendRequestSendTo, currentUser.getIdNum());
                    friendRequestSendStatus.setText("Friend Request Sent");
                    usernameFound = true;
                    break;
                }
            }
            if(usernameFound == false){
                friendRequestSendStatus.setText("Cannot Find User");
            }
        }, "SendFriendRequest"));
    }

    private void displayFriendRequests(TextView friendRequestList){
        GetUserApi().getRequestsById(currentUser.getIdNum()).enqueue(new SlimCallback<List<FriendRequest>>(requests -> {
            for (int i = requests.size()-1; i>=0; i--){
                FriendRequest currentRequest = requests.get(i);
                GetUserApi().getUserById(currentRequest.getUserFrom()).enqueue(new SlimCallback<User>(user -> {
                    if(currentRequest.getStatus().compareTo("PENDING") == 0){
                        friendRequestList.append(user.getDisplayname());
                    }
                }));
            }
        }, "DisplayFriendRequests"));
    }

    private void acceptFriendRequests(TextView friendRequestList){
        displayFriendRequests(friendRequestList);
    }

    private void denyFriendRequests(TextView friendRequestList){
        displayFriendRequests(friendRequestList);
    }

}