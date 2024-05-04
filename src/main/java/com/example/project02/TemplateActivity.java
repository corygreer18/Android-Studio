//package com.example.project02;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;
//import com.example.project02.DB.AppDatabase;
//import com.example.project02.DB.UserDAO;
//
//import android.view.View;
//import android.content.Intent;
//import android.os.Bundle;
//
////Import Boxes in XML
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//
//public class Template extends AppCompatActivity {
//    //Init Activity.XML Objects
//    //  EditText pass;
//    Button exampleBtn;
//
//    private int mUserId = -1;
//    private User mUser;
//    private UserDAO mUserDAO;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.landing_page);
//
//        makeUserDAO(); //Make the DAO
//        mUser = getUserFromExtras(); //Get User from ID in Extras
//
//        assignDisplays(); // Link Ids to Vars
//
//        mUserId = mUser.getLogID(); //This can be done inside getUser...() but this will break easily
//
//        exampleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switchToNextActivity();
//            }
//        });
//    }
//
//    private void assignDisplays() {
//        exampleBtn = findViewById(R.id.exampleBtn);
//    }
//
//    private void makeUserDAO(){
//        mUserDAO= Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
//                .allowMainThreadQueries()
//                .build()
//                .UserDAO();
//
//    }
//
//    private User getUserFromExtras(){
//        //Returns the user stored in the Extras (Or NULL, if nothing stored)
//        // Break up into: int userID = getIntent().getIntExtra("user id", -1)
//        //  and: mUserDAO.getUserByUserId(userID)
//        return mUserDAO.getUserByUserId(getIntent().getIntExtra("user id", -1));
//    }
//
//    private void switchToNextActivity(){
//        //Make sure to rename
//        Intent switchToNextActivity = new Intent(this, NextActivity.class);
//        startActivity(switchToNextActivity);
//    }
//
//}
