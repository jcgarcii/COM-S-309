package test.connect.myapplication;

import static test.connect.myapplication.api.ApiClientFactory.GetUserApi;
import static test.connect.myapplication.api.ApiClientFactory.GetUserApi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import test.connect.myapplication.api.SlimCallback;
import test.connect.myapplication.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView apiText1 = findViewById(R.id.activity_main_textView1);
        apiText1.setMovementMethod(new ScrollingMovementMethod());
        apiText1.setHeight(350);

        Button postByPathBtn = findViewById(R.id.activity_main_post_by_path_button);
        EditText userIn = findViewById(R.id.activity_main_user_editText);
        EditText passwordIn = findViewById(R.id.activity_main_password_editText);

        RegenerateAllUsersOnscreen(apiText1);


        postByPathBtn.setOnClickListener(view -> { GetUserApi().PostUserByPath(userIn.getText().toString(), passwordIn.getText().toString(), userIn.getText().toString()).enqueue(new SlimCallback<User>(users->{}));
        RegenerateAllUsersOnscreen(apiText1);
        userIn.setText("");
        passwordIn.setText("");
    });


    }


    void RegenerateAllUsersOnscreen(TextView apitext)
    {GetUserApi().GetAllUsers().enqueue(new SlimCallback<List<User>>(users ->{
        apitext.setText("");
        for (int i = users.size()-1; i>=0; i--)
        {
            apitext.append(users.get(i).printable());
        }
    }, "GetAllUsers")); } }

