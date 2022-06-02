package com.example.bonnie.ecommerce.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bonnie.ecommerce.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SellerLoginActivity extends AppCompatActivity
{
    private Button loginSellerBtn;
    private EditText emailInput, passwordInput;

    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);


        loadingBar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();


        emailInput = findViewById(R.id.seller_login_email);
        passwordInput = findViewById(R.id.seller_login_password);
        loginSellerBtn = findViewById(R.id.seller_login_btn);


        loginSellerBtn.setOnClickListener((View v) -> {
                loginSeller();
        });
    }

    private void loginSeller()
    {
        final String email = emailInput.getText().toString();
        final String password = passwordInput.getText().toString();


        if (!email.equals("") && !password.equals("")) {

            loadingBar.setTitle("Logging in Seller Account");
            loadingBar.setMessage("Please wait while we are checking credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((@NonNull Task<AuthResult> task) ->
                        {
                            if (task.isSuccessful())
                            {
                                Intent intent = new Intent(SellerLoginActivity.this, SellerHomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }

                    });
        }
        else
        {
            Toast.makeText(this, "Please complete the login form.", Toast.LENGTH_SHORT).show();
        }
    }
}