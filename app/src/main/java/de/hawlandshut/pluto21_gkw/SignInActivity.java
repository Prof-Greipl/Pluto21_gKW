package de.hawlandshut.pluto21_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    }

    @Override
    public void onClick(View v) {
        switch( v.getId()){
            case R.id.signInButtonCreateAccount:
                doCreateAccount();
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
        Toast.makeText(getApplicationContext(), "Pressed SignIn", Toast.LENGTH_LONG).show();

    }

    private void doResetPassword() {
        Toast.makeText(getApplicationContext(), "Pressed ResetPassword", Toast.LENGTH_LONG).show();

    }

    private void doCreateAccount() {
        Toast.makeText(getApplicationContext(), "Pressed CreateAccount ", Toast.LENGTH_LONG).show();

    }
}