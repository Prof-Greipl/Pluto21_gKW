package de.hawlandshut.pluto21_gkw;

import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import de.hawlandshut.pluto21_gkw.model.Post;


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

    // Query und ChildEventlistener mit running Indikator deklarieren
    Query mQuery;
    ChildEventListener mChildEventListener;
    boolean mListenerIsRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Später ändern, nur zum Test
        Log.d(TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  FirebaseDatabase.getInstance().setPersistenceEnabled(true);

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

                Post p = getItem(getCount() - 1 - position);
                text1.setText(p.title);
                text2.setText(p.body);

                return view;
            }
        };

        // Initialisieren der Listview...
        mListView = (ListView) findViewById(R.id.listViewMessages);
        //... und mit dem Adapter verbinden
        mListView.setAdapter(mAdapter);

        // Query  und ChildEvenlistener initialisieren
        mQuery = FirebaseDatabase.getInstance().getReference().child("Posts/").limitToLast(3);
        mChildEventListener = getChildEventListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Ist ein User angemeldet.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if( user == null){
            resetApp();
            Intent intent = new Intent( getApplicationContext(), SignInActivity.class);
            startActivity( intent );
        } else {
            Log.d(TAG,"Added CEL");
             if( !mListenerIsRunning ) {
                 mPostList.clear();
                 mQuery.addChildEventListener(mChildEventListener);
                 mListenerIsRunning = true;
                 mAdapter.notifyDataSetChanged();
             }
        }

        // TODO: Remove later
        Log.d(TAG, "in onStart");
    }

    private void resetApp(){
        mPostList.clear();
        mAdapter.notifyDataSetChanged();
        if (mListenerIsRunning) {
            mQuery.removeEventListener(mChildEventListener);
            mListenerIsRunning = false;
        }
    }

    private ChildEventListener getChildEventListener(){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildAdded  Key = "+dataSnapshot.getKey());
                Post post = Post.getPostFromSnapShot( dataSnapshot );
                mPostList.add( post );
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               // Wird nicht benötigt
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                for (int i = 0; i < mPostList.size(); i++){
                    if (key.equals( mPostList.get(i).firebaseKey)){
                        mPostList.remove(i);
                        break;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildMoved  Key = "+dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled  ");
            }
        };
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

            case R.id.mainMenuPost:
                intent = new Intent( getApplicationContext(), PostActivity.class);
                startActivity( intent );
                return true;

            case R.id.mainMenuCrash:
                throw new RuntimeException("Test Crash");


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