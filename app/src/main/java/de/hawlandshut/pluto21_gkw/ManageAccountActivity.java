package de.hawlandshut.pluto21_gkw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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


        //TODO: Nur zum Testen - später löschen!
        mEmail.setText("dietergreipl@gmail.com");
        mPassword.setText("123456");
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Disable Send Activation, if already verifiednot yet enabled
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return; // This should never happen
        }
        ((Button) findViewById( R.id.manageAccountButtonSendActivationMail)).setEnabled( ! user.isEmailVerified() );

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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
           Log.e(TAG, "Schwerer Fehler: Nicht angmeldeter User in SignOut");
        } else {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(), "Your are signed out.",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void doSendActivationMail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            Log.e(TAG, "Schwerer Fehler: Nicht angmeldeter User in SendActivationMail");
        } else {
            user.sendEmailVerification()
                    .addOnCompleteListener(
                            new OnCompleteListener<Void>() {
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

    private void doDeleteAccount() {
        // TODO: Test: was passiert, wenn user vor dem Delete-Call am Server gelöscht wird.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Log.e(TAG, "Schwerer Fehler: Nicht angmeldeter User in DeleteAccount");
        } else {
            user.delete()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Account deleted.",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Account deletion failed.",
                                        Toast.LENGTH_SHORT).show();
                                Log.w(TAG, "deleteUser:failure", task.getException());
                            }
                        }
                    });

        }
    }
}