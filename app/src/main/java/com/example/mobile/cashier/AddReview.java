package com.example.mobile.cashier;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddReview extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button send;
    EditText edt_username,edt_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        dbHelper = new DataHelper(this);
        edt_review = (EditText) findViewById(R.id.edt_review);
        edt_username = (EditText) findViewById(R.id.edt_username);
        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into review(username,review) values('"+edt_username.getText().toString()+"','"+ edt_review.getText().toString() + "');");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG);
                Review.ma.RefreshList();
                finish();
            }
        });
    }
}