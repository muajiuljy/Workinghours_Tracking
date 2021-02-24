package com.example.working_hours2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    EditText email, password, cpassword;
    Button btnsignup;
    TextView tvlogin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        cpassword = findViewById(R.id.editTextCPassword);
        btnsignup = findViewById(R.id.button);
        tvlogin = findViewById(R.id.textViewLogin);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email.getText().toString();
                String userpassword = password.getText().toString();
                String usercpassword = cpassword.getText().toString();

                if(!useremail.isEmpty() && !userpassword.isEmpty() && !usercpassword.isEmpty() && userpassword.equals(usercpassword)) {
                    mFirebaseAuth.createUserWithEmailAndPassword(useremail, userpassword).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                updateUI(user);
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            }
                            else{
                                updateUI(null);
                            }
                        }
                    });

                }
                else if(!(userpassword.equals(usercpassword)) && !userpassword.isEmpty() && !usercpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password should be the same!", Toast.LENGTH_SHORT ).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT ).show();
                }

            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });



        }

    public void updateUI(FirebaseUser account){
        if(account != null){
            Toast.makeText(SignUpActivity.this, "Create User With Email:success", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(SignUpActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
        }

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        updateUI(currentUser);

    }*/
}
