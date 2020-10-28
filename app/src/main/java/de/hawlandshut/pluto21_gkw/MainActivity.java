package de.hawlandshut.pluto21_gkw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="xx MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Später ändern, nur zum Test
        Log.d(TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: Remove later
        Log.d(TAG, "in onStart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId()){

            case R.id.mainMenuGotoCreateAccount:
                Toast.makeText(getApplicationContext(), "Pressed CreateAccount ", Toast.LENGTH_LONG).show();
                return true;

            case R.id.mainMenuGotoManageAccount:
                Toast.makeText(getApplicationContext(), "Pressed ManageAccount ", Toast.LENGTH_LONG).show();
                return true;

            case R.id.mainMenuGotoPost:
                Toast.makeText(getApplicationContext(), "Pressed Post ", Toast.LENGTH_LONG).show();
                return true;

            case R.id.mainMenuGotoSignIn:
                Intent intent = new Intent(getApplication(), SignInActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // TODO:  Methoden ab hier brauchen wir nicht; löschen!
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "in onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "in onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "in onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "in onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "in onRestart");
    }
}