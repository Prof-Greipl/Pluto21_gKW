package de.hawlandshut.pluto21_gkw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "xx CreateAccountAct.";

    private EditText mEditTextEMail;
    private EditText mEditTextPassword1;
    private EditText mEditTextPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mEditTextEMail =  ((EditText) findViewById(R.id.createAccountEmail));
        mEditTextPassword1 =  ((EditText) findViewById(R.id.createAccountPassword1));
        mEditTextPassword2 =  ((EditText) findViewById(R.id.createAccountPassword2));

        // TODO: Remove presets later
        mEditTextEMail.setText("dietergreipl@gmail.com");
        mEditTextPassword1.setText("123456");
        mEditTextPassword2.setText("123456");

        // Add Listener
        ((Button) findViewById(R.id.createAccountButtonCreateAccount)).setOnClickListener( this );

    }

    @Override
    public void onClick( View v){
        switch( v.getId() ){

            case R.id.createAccountButtonCreateAccount:
                doCreateAccount();
                return;

            default:
                return;
        }
    }

    private void doCreateAccount() {

        String email =  mEditTextEMail.getText().toString();
        String password1 = mEditTextPassword1.getText().toString();
        String password2 = mEditTextPassword2.getText().toString();

        // Übung: Identität der beiden Passwörter prüfen.

        // https://firebase.google.com/docs/auth/android/manage-users#create_a_user
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User created.",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "User creation failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Create Account Fehler: " + task.getException());
                        }
                    }
                });
    }
}