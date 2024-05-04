package com.example.project02;



import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project02.DB.AppDatabase;
import com.example.project02.DB.UserDAO;

public class LandingPage extends AppCompatActivity {

    private static final String PREFERENCES_KEY = "com.example.project02.preferencesKey";

    TextView user;
    EditText loggedIn;
    Button admin;

    Button delete;
    Button marketPage;
    Button logout;

    public boolean isAdmin = true;

    private UserDAO mUserDAO;   // DAO to access database
    private int mUserId = -1;   // Id of currently logged-in User
    private User mUser;         // Our currently Logged-in User


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        makeUserDAO();
        assignDisplays();

        //Get the calling intent, retrieve the stored extra "userId"
        mUserId = getIntent().getIntExtra("user id", 0);
        mUser = mUserDAO.getUserByUserId(mUserId);

        Toast.makeText(LandingPage.this, "UserId: " + mUser.getLogID() + " Name: " + mUser.getUser(), Toast.LENGTH_SHORT).show();
        loggedIn.setText("User: " + mUser.getUser());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LandingPage.this, "deleting: " + mUser.getUser(), Toast.LENGTH_SHORT).show();
                mUserDAO.delete(mUser);

                switchToLogin();
            }
        });

        if(mUser.isAdmin()){
            admin.setVisibility(VISIBLE);
        }else{
            admin.setVisibility(INVISIBLE);
        }
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToEditItems();
            }
        });


        marketPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToMarket();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void assignDisplays() {
        delete = findViewById(R.id.deleteBtn);
        marketPage = findViewById(R.id.marketBtn);
        user = findViewById(R.id.user_display);
        loggedIn = findViewById(R.id.username);
        logout = findViewById(R.id.logout_landing);
        admin = findViewById(R.id.admin_button);


        //loggedIn.setText("UserId: " + mUser.getLogID());


    }

    private void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout?").
                setMessage("Are you sure that you want to logout?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getApplicationContext(),
                                MainActivity.class);
                        startActivity(i);
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder.create();
        alert11.show();
         }


    private void makeUserDAO(){
        mUserDAO= Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .UserDAO();

    }

    private void switchToLogin(){
        Intent switchToLoginintent = new Intent(this, LoginActivity.class);
        startActivity(switchToLoginintent);
    }

    private void switchToMarket(){
        Intent switchToMarketintent = new Intent(this, MarketPage.class);
        startActivity(switchToMarketintent);
    }

    private void switchToEditItems(){
        Intent switchToEditItemintent = new Intent(this, EditItemActivity.class);
        startActivity(switchToEditItemintent);
    }
}
