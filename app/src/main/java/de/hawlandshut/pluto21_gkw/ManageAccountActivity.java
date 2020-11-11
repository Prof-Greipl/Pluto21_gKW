package de.hawlandshut.pluto21_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManageAccountActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "xx ManageActivity";

    TextView mEmail, mAccountState, mTechnicalId;
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        mEmail = (TextView) findViewById(R.id.manageAccountEmail);
        mAccountState = (TextView) findViewById(R.id.manageAccountVerificationState);
        mTechnicalId = (TextView) findViewById(R.id.manageAccountTechnicalId);
        mPassword = (EditText) findViewById(R.id.manageAccountPassword);

        ((Button) findViewById( R.id.manageAccountButtonSignOut)).setOnClickListener( this );
        ((Button) findViewById( R.id.manageAccountButtonSendActivationMail)).setOnClickListener( this );
        ((Button) findViewById( R.id.manageAccountButtonDeleteAccount)).setOnClickListener( this );

    }

    @Override
    public void onClick( View v){
        switch( v.getId() ){

            case R.id.manageAccountButtonDeleteAccount:
                doDeleteAccount();
                return;

            case R.id.manageAccountButtonSendActivationMail:
                doSendActivationMail();
                return;

            case R.id.manageAccountButtonSignOut:
                doSignOut();
                return;

            default:
                return;
        }
    }

    private void doSignOut() {
        Toast.makeText(getApplicationContext(), "You pressed Sign Out (nyi).", Toast.LENGTH_LONG).show();
    }

    private void doSendActivationMail() {
        Toast.makeText(getApplicationContext(), "You pressed Send Act. Mail (nyi).", Toast.LENGTH_LONG).show();
    }

    private void doDeleteAccount() {
        Toast.makeText(getApplicationContext(), "You pressed Delete Account (nyi).", Toast.LENGTH_LONG).show();
    }
}