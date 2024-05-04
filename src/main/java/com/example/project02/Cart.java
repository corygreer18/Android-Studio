package com.example.project02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cart extends AppCompatActivity {

   Button back;
   Button checkout;
   Button cart;
   Button cart2;
   Button cart3;

//
//    private int mUserId = -1;
//    private User mUser;
//    private UserDAO mUserDAO;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//
//        makeUserDAO(); //Make the DAO
//        mUser = getUserFromExtras(); //Get User from ID in Extras
//
        assignDisplays(); // Link Ids to Vars

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToPrevActivity();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToCheckout();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
        cart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
        cart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });

//
//        mUserId = mUser.getLogID(); //This can be done inside getUser...() but this will break easily
//
//        exampleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switchToNextActivity();

    }

//
    private void assignDisplays() {
        back = findViewById(R.id.back_cart);
        checkout = findViewById(R.id.cartBtn);
        cart = findViewById(R.id.cartBtn);
        cart2 = findViewById(R.id.button);
        cart3 = findViewById(R.id.button2);
    }

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
    private void switchToPrevActivity(){
        //Make sure to rename
        Intent switchToPrevActivity = new Intent(this, MarketPage.class);
        startActivity(switchToPrevActivity);
    }
    private void switchToCheckout(){
        //Make sure to rename
        Intent switchToCheckout = new Intent(this, Checkout.class);
        startActivity(switchToCheckout);
    }

    private void addToCart(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add to cart?").
                setMessage("Are you sure that you want to logout?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getApplicationContext(),
                                Cart.class);
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
}
