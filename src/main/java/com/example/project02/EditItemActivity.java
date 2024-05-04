package com.example.project02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project02.DB.AppDatabase;
import com.example.project02.DB.ItemDAO;
import com.example.project02.DB.UserDAO;
import com.example.project02.R;
import com.example.project02.User;

public class EditItemActivity extends AppCompatActivity {
    EditText price;
    EditText quantity;
    Button itemBtn;

    private int mUserId = -1;
    private User mUser;
    private Item mItem;

    private UserDAO mUserDAO;
    private ItemDAO mItemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edititem_activity);

        makeUserDAO(); //Make the DAO
        makeItemDAO();

        mUser = getUserFromExtras(); //Get User from ID in Extras

        assignDisplays(); // Link Ids to Vars

        mUserId = mUser.getLogID(); //This can be done inside getUser...() but this will break easily

        itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLanding();
            }
        });
    }

    private void assignDisplays() {
        itemBtn = findViewById(R.id.editItemBtn);
        price = findViewById(R.id.editTextTextPrice);
        quantity = findViewById(R.id.editTextTextQuantity);

    }

    private void makeUserDAO(){
        mUserDAO= Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .UserDAO();

    }

    private void makeItemDAO(){
        mItemDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .ItemDAO();

    }
    private void switchToLanding(){
        Intent switchToLandingIntent = new Intent(this, LandingPage.class);
        startActivity(switchToLandingIntent);
    }


    private User getUserFromExtras(){
        //Returns the user stored in the Extras (Or NULL, if nothing stored)
        // Break up into: int userID = getIntent().getIntExtra("user id", -1)
        //  and: mUserDAO.getUserByUserId(userID)
        return mUserDAO.getUserByUserId(getIntent().getIntExtra("user id", -1));
    }

}

