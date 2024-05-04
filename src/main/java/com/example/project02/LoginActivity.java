package com.example.project02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project02.DB.AppDatabase;
import com.example.project02.DB.UserDAO;


public class LoginActivity extends AppCompatActivity {


    private int mUserId = -1;
    private User mUser;

    private UserDAO mUserDAO;


    private EditText user;
    private EditText pass;
    private Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //Setup all the fields and display boxes
        assignDisplays();

        //Init DAO
        makeUserDAO();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Check if login info is valid
                String username = user.getText().toString();
                String password = pass.getText().toString();



                //Database Usernames are currently unique (Fix later)
                if(mUserDAO.getUserByUsername(username) != null){
                    User loggedUser = mUserDAO.getUserByUsername(username);
                    if (password.equals(loggedUser.getPass())) { //If Matching Info!
                        //Store mUserId
                        Toast.makeText(LoginActivity.this, "Matched!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this, "UserId: " + loggedUser.getLogID() + " Name: " + loggedUser.getUser(), Toast.LENGTH_SHORT).show();

                        switchToLanding(loggedUser.getLogID());

                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "No such user", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void makeUserDAO(){
        mUserDAO= Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .UserDAO();

    }

    private void assignDisplays(){
        loginBtn = findViewById(R.id.login);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
    }

    private void switchToLanding(int id) {
        Intent switchToLandingIntent = new Intent(this, LandingPage.class);
        //stores int inside extra of intent
        switchToLandingIntent.putExtra("user id", id);

        startActivity(switchToLandingIntent);
    }
}
