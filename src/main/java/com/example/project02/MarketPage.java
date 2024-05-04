package com.example.project02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project02.DB.AppDatabase;
import com.example.project02.DB.ItemDAO;
import com.example.project02.DB.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class MarketPage extends AppCompatActivity {

    private Item mItem;
    private int mItemId = -1;

    private ItemDAO mItemDAO;

    private Button logout2;
    private Button cart;
    Button cartAdd;
    Button cart2;
    Button cart3;

    private List<Item> list;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_page);

        makeItemDAO(); //Make the DAO
        logout2 = findViewById(R.id.logoutBtn);
        cart = findViewById(R.id.cartBtn);
        cartAdd = findViewById(R.id.button);
        cart2 = findViewById(R.id.button2);
        cart3 = findViewById(R.id.button3);
        //recyclerView = findViewById(R.id.marketRecyclerView);

        //list = new ArrayList<>();
        //list.add(new Item("Guitar", 200, 20));
        //list.add(new Item("Not", 14, 2));

        //setAdapter();
//
        logout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToCart();
            }
        });

        cartAdd.setOnClickListener(new View.OnClickListener() {
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

//        mItemDAO.insert(new Item("Guitar", 200, 20));
//        mItemDAO.insert(new Item("Piano", 250, 10));
//        mItemDAO.insert(new Item("Banjo", 5, 1));
//
//        List<Item> list = mItemDAO.getAllItems();

    }

    private void setAdapter(){
        InstrumentAdapter adapter = new InstrumentAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
    }

    private void makeItemDAO(){
        mItemDAO= Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .ItemDAO();

    }

    private Item getItemsFromExtras(){
        //Returns the user stored in the Extras (Or NULL, if nothing stored)
        // Break up into: int userID = getIntent().getIntExtra("user id", -1)
        //  and: mUserDAO.getUserByUserId(userID)
        return mItemDAO.getItembyItemId(getIntent().getIntExtra("item id", -1));
    }

    private void switchToCart(){
        //Make sure to rename
        Intent switchToCart = new Intent(this, Cart.class);
        startActivity(switchToCart);
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

    private void addToCart(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add to cart?").
                setMessage("Are you sure that you want to add to cart?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getApplicationContext(),
                                MarketPage.class);
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