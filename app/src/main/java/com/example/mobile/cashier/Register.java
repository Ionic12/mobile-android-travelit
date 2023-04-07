package com.example.mobile.cashier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobile.cashier.data.DTSAppDatabase;
import com.example.mobile.cashier.data.DatabaseTask;
import com.example.mobile.cashier.data.DatabaseTaskEventListener;
import com.example.mobile.cashier.data.User;

public class Register extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtEmail;
    ProgressDialog loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
    }

    private void initComponents()
    {
        this.edtUsername = this.findViewById(R.id.edt_username);
        this.edtPassword = this.findViewById(R.id.edt_password);
        this.edtEmail = this.findViewById(R.id.edt_email);
    }

    public void LoginPage(View view) {
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }

    private User makeUser()
    {
        User u = new User();
        u.username = this.edtUsername.getText().toString();
        u.password = this.edtPassword.getText().toString();
        u.email = this.edtEmail.getText().toString();

        return u;
    }

    public void Register(View view)
    {
        this.showLoadingIndicator();

        new DatabaseTask(this, new DatabaseTaskEventListener() {

            @Override
            public Object runDatabaseOperation(RoomDatabase database, Object... params) {

                // Mengambil Entity dari params
                User user = (User) params[0];

                // Mendapatkan DAO dari object database, dan memanggil method operasi INSERT
                ((DTSAppDatabase)database).userDao().insertAll(user);

                return null;
            }
            @Override
            public void onDatabaseOperationFinished(Object... results)
            {
                // Delay eksekusi program agar nampak agak lama seolah-olah datanya sedang diunggah
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        // Tutup loading indicator & tampilkan Toast
                        loadingIndicator.dismiss();
                        Toast.makeText(getApplicationContext(), "Registration success!", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        }).execute(this.makeUser());
    }

    private void showLoadingIndicator()
    {
        loadingIndicator = new ProgressDialog(this);
        loadingIndicator.setMessage("Uploading user data to server...");
        loadingIndicator.setIndeterminate(false);
        loadingIndicator.setCancelable(false);
        loadingIndicator.show();
    }

}