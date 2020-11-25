package de.hawlandshut.pluto21_gkw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG ="xx SignInActivity";

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonSignIn;
    private Button mButtonResetPassword;
    private Button mButtonCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Log.d(TAG,"in onCreate");

        // Initialisieren der UI Variablen
        mEditTextEmail = (EditText) findViewById( R.id.signInEmail);
        mEditTextPassword = (EditText) findViewById( R.id.signInPassword);
        mButtonCreateAccount = (Button) findViewById( R.id.signInButtonCreateAccount);
        mButtonResetPassword = (Button) findViewById( R.id.signInButtonResetPassword);
        mButtonSignIn = (Button) findViewById( R.id.signInButtonSignIn);

        // Anhängen (auch: Registrieren der Listener) an die Buttons
        mButtonCreateAccount.setOnClickListener( this );
        mButtonResetPassword.setOnClickListener( this );
        mButtonSignIn.setOnClickListener( this );

        // TODO: Nur zum testen - später löschen
        mEditTextEmail.setText("dietergreipl@gmail.com");
        mEditTextPassword.setText("123456");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch( v.getId()){
            case R.id.signInButtonCreateAccount:
                Intent intent = new Intent( getApplicationContext(), CreateAccountActivity.class);
                startActivity( intent );
                return;

            case R.id.signInButtonResetPassword:
                doResetPassword();
                return;

            case R.id.signInButtonSignIn:
                doSignIn();
                return;

            default:
                return;
        }
    }

    private void doSignIn() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user==null) {

            String email = mEditTextEmail.getText().toString();
            String password = mEditTextPassword.getText().toString();

            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInUserWithEmail:success");
                                Toast.makeText(getApplicationContext(), "You are signed in.",
                                        Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "SignIn failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            // This should never happen
            Log.e(TAG,"Angemeldeter User in Sign In. Sollte nicht sein...");
        }
    }

    private void doResetPassword() {

        String email = mEditTextEmail.getText().toString();
        Toast.makeText(getApplicationContext(), "Pressed ResetPassword", Toast.LENGTH_LONG).show();
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "We sent you a link to your e-mail account.",
                                    Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Email sent.");
                        } else {
                            Toast.makeText(getApplicationContext(), "Could not send mail. Correct e-mail?",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

}