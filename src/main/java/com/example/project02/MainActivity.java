/*
* so when i created this activity I was a little confused on how to use room database
* i spent a lot of time trying to figure it out but this is the best I could come up with.
* I think it runs I went to test it and my computer froze each time, i do not think it like android very much.
* I tried multiple emulators but that did not seem to help either.
* I was not really sure what to do, but I wanted to submit at least my best effort.
* */
package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project02.DB.AppDatabase;
import com.example.project02.DB.ItemDAO;
import com.example.project02.DB.UserDAO;
import com.example.project02.R;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button createAccount;
    private UserDAO mUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mUserDAO= Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .UserDAO();

        mUserDAO.insert(new User("admin", "admin", true));


        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLogin();
            }
        });

        createAccount = findViewById(R.id.create_button);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToRegistration();
            }
        });

    }

    private void switchToLogin(){
        Intent switchToLoginintent = new Intent(this, LoginActivity.class);
        startActivity(switchToLoginintent);
    }


    private void switchToRegistration(){
        Intent switchToRegintent = new Intent(this, Registration.class);
        startActivity(switchToRegintent);
    }
}

