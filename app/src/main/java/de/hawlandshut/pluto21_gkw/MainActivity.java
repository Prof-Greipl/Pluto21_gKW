package de.hawlandshut.pluto21_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="xx MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Später ändern, nur zum Test
        Log.d(TAG, "in onCreate1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}