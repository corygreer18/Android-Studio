package com.daclink.gymlog_v_sp22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daclink.gymlog_v_sp22.DB.AppDatabase;
import com.daclink.gymlog_v_sp22.DB.GymLogDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;

    private Button mButton;


    private GymLogDAO mGymLogDAO;

    private String mUsernameString;
    private String mPasswordString;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wireupDisplay();

        getDatabase();


    }


    private void wireupDisplay(){
        mUsername = findViewById(R.id.editTextLoginName);
        mPassword = findViewById(R.id.editTextLoginPassword);

        mButton = findViewById(R.id.loginButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if(checkForUserInDB());{
                    if(validatePassword()){
                       Toast.makeText(LoginActivity.this, "Invalid password ", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = MainActivity.intentFactory(getApplicationContext(), mUser.getUserId());
                        startActivity(intent);
                    }
                }



            }
        });


    }

    private void getValuesFromDisplay(){
        mUsernameString = mUsername.getText().toString();
        mPasswordString = mPassword.getText().toString();
    }

    private boolean checkForUserInDB(){
        mUser = mGymLogDAO.getUserByUsername(mUsernameString);
        if(mUser == null){
            Toast.makeText(this, "no user" + mUsernameString + " found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validatePassword(){
        return mUser.getPassword().equals(mPasswordString);
    }

    private

    private void getDatabase(){
        mGymLogDAO= Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .getGymLogDAO();

    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LoginActivity.class);

        return intent;
    }
}