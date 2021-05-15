package com.example.gymbuddiesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper DB;
    TextView userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);

        userdata = (TextView) findViewById(R.id.userdata);

        Cursor cursor = DB.getdata();
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        }
        else{
            StringBuffer buffer = new StringBuffer();
            while(cursor.moveToNext()){
                buffer.append("Welcome "+cursor.getString(0)+"\n\n");
                buffer.append("\nEmail : " + cursor.getString(1)+"\n\n");
                buffer.append("\nGender : "+cursor.getString(3)+"\n\n");
                buffer.append("\nGym Membership : "+cursor.getString(4)+"\n\n");
            }

            userdata.setText(buffer.toString());
        }

        Button acc=(Button)findViewById(R.id.account);
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), myaccount.class);
                startActivity(intent);
            }
        });
    }
}