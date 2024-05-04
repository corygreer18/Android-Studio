package com.daclink.gymlog_v_sp22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daclink.gymlog_v_sp22.DB.GymLogDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.daclink.gymlog_v_sp22.userIdKey";
    private static final String PREFERENCES_KEY = "com.daclink.gymlog_v_sp22.preferencesKey";

    TextView mMainDisplay;

    private EditText mExercise;
    private EditText mWeights;
    private EditText mReps;

    private Button mSubmitButton;

    private GymLogDAO mGymLogDAO;

    private List<GymLog> mGymLogs;

    private int mUserId = -1;
    private User mUser;

    private SharedPreferences mPreference = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkForUser();
        addUserToPreference(mUserId);

        loginUser(mUserId);
    }

    private void loginUser(int userId) {
        mUser = mGymLogDAO.getUserByUserId(userId);
        invalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(mUser != null){
            MenuItem = menu.findItem(R.id.menu); //for the drop down add the id of the dropmenu
            item.setTitle(mUser.getUsername());
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void addUserToPreference(int userId) {
        if(mPreference== null){
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putInt(USER_ID_KEY, userId);

    }


    private void checkForUser() {
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);

        if(mUserId !=-1){
            return;
        }

        SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        if(mPreference == null) {
            getPrefs();
        }
        mUserId = preferences.getInt(USER_ID_KEY, -1);

        if(mUserId != -1){
            return;
        }

        List<User> users = mGymLogDAO.getAllUsers();

        if(users.size()<=0){
            User defaultUser = new User("testuser", "testuser");
            mGymLogDAO.insert(defaultUser);
        }

        Intent intent = LoginActivity.intentFactory(this);
        startActivity(intent);



    }

    private void getPrefs() {
        mPreference = this.getSharedPreferences((PREFERENCES_KEY, Context.MODE_PRIVATE))

    }


    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);


        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }

    private void clearUserFromPref(){
        addUserToPreference(-1);
    }

}