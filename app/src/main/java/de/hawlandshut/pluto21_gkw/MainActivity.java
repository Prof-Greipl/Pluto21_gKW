package de.hawlandshut.pluto21_gkw;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hawlandshut.pluto21_gkw.model.Post;
import de.hawlandshut.pluto21_gkw.test.PostTestData;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "xx MainActivity";

    // TODO: Only for testing - remove later
    private static final String TEST_MAIL = "dietergreipl@gmail.com";
    private static final String TEST_PASSWORD = "123456";


    // The place to store posts, after received from server
    ArrayList<Post> mPostList = new ArrayList<Post>();

    // Adapter between ListView and mPostList
    ArrayAdapter<Post> mAdapter;

    // UI-Element deklarieren
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Später ändern, nur zum Test
        Log.d(TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: For testing only, remove later;
        PostTestData.createTestData();
        mPostList = (ArrayList<Post>) PostTestData.postTestList;

        // Initialisieren des Apdapter
        mAdapter = new ArrayAdapter<Post>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                mPostList
        ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                // Hole eine leere simple_list_item_2 view
                View view = super.getView(position, convertView, parent);

                // Erzeuge zwei TextViews für die beiden Zeilen
                TextView text1, text2;
                text1 = view.findViewById(android.R.id.text1);
                text2 = view.findViewById(android.R.id.text2);

                Post p = getItem(position);
                text1.setText(p.title);
                text2.setText(p.body);

                return view;
            }
        };

        // Initialisieren der Listview...
        mListView = (ListView) findViewById(R.id.listViewMessages);
        //... und mit dem Adapter verbinden
        mListView.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Ist ein User angemeldet.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if( user == null){
            Log.d(TAG, "Kein user, also ab zu Sign In... ");
            Intent intent = new Intent( getApplicationContext(), SignInActivity.class);
            startActivity( intent );
        } else {
            ;
        }


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
        // TODO: Clean up!
        Intent intent;
        switch (item.getItemId()) {

            case R.id.mainMenuManageAccount:
                // TODO: Check auf user == null?
                intent = new Intent( getApplicationContext(), ManageAccountActivity.class);
                startActivity( intent );
                return true;

            case R.id.mainMenuTestDatabase:
                FirebaseDatabase.getInstance().getReference("App/Version").setValue("V1.0");
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