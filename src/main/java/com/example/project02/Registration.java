package com.example.project02;

import static com.example.project02.DB.AppDatabase.DATABASE_NAME;
import static com.example.project02.DB.AppDatabase.USER_TABLE;

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

import java.util.List;

public class Registration extends AppCompatActivity {

    private UserDAO mUserDAO;

    private Button create;
    private EditText user;
    private EditText pass;

    private List<User> users;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        //Link XML objects
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        create = findViewById(R.id.create_account);

        //Create DAO of Database
        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .UserDAO();



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user.getText().toString();
                String password = pass.getText().toString();



                users = mUserDAO.getAllUsers();
                Toast.makeText(Registration.this, "Retrieved Table", Toast.LENGTH_SHORT).show();

                if (users.isEmpty()){
                    Toast.makeText(Registration.this, "Empty Table", Toast.LENGTH_SHORT).show();

                    mUserDAO.insert(new User(username, password, false));
                    switchToLogin();
                }else{
                    boolean available = true;
                    for (User u :users) {
                        if((u.getUser().equals(username))){
                            Toast.makeText(Registration.this, "Username already exists", Toast.LENGTH_SHORT).show();
                            available = false;
                        }
                    }
                    if(available){
                        mUserDAO.insert(new User(username, password, false));
                        Toast.makeText(Registration.this, "Good Job, Now Login", Toast.LENGTH_SHORT).show();
                        switchToLogin();
                    }
                }

            }
        });
    }

    private void switchToLogin(){
        Intent switchToLoginintent = new Intent(this, LoginActivity.class);
        startActivity(switchToLoginintent);
    }
}
