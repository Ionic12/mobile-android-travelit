package com.example.mobile.cashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobile.cashier.data.AppDbProvider;
import com.example.mobile.cashier.data.User;
import com.example.mobile.cashier.data.UserDao;

import java.util.Objects;

public class Login extends AppCompatActivity {
    private static final String DUMMY_USERNAME = "sad";
    private static final String DUMMY_PASSWORD = "sad";
    private EditText edtUsername;
    private EditText edtPassword;
    private User loginUser;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.initComponents();
    }

    private void initComponents()
    {
        // Init components
        this.edtUsername = this.findViewById(R.id.edt_username);
        this.edtPassword = this.findViewById(R.id.edt_password);
    }

    public void HomePage(View view)
    {
        boolean valid = this.validateCredential();

        if(valid)
        {
            Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "Invalid username and/or password!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateCredential()
    {
        String currentUsername = this.edtUsername.getText().toString();
        String currentPassword = this.edtPassword.getText().toString();
        UserDao daoUser = AppDbProvider.getInstance(this).userDao();
        this.loginUser = daoUser.findByUsernameAndPassword(currentUsername,currentPassword);

        if (this.loginUser != null){
            return true;
        }else {
            return false;
        }
    }

    public void RegisterPage(View view) {
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }
}