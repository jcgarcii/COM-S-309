package com.example.frontend.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.R;
import com.example.frontend.api.ApiClientFactory;

public class AdminActivity extends AppCompatActivity {

    /**
     * This onCreate() method doesn't differ much from the standard one, main difference is that this calls and instantiates
     * each activity based on User input with the exclusion of the UserLogin as that comes up frist when the application is launched.
     * @param savedInstanceState - used in conjunction with our XML files corresponding to each activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button addW = findViewById(R.id.addWord);
        Button subW = findViewById(R.id.delWord);
        EditText addWords = findViewById(R.id.addWordText);
        EditText remWords = findViewById(R.id.removeWordText);

        //Add Word
        addW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClientFactory.GetWordApi().PostWordByPath(addWords.getText().toString());
            }
        });

        //Remove Word
        subW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClientFactory.GetWordApi().DeleteWordByPath(remWords.getId());
            }
        });



    }
}
