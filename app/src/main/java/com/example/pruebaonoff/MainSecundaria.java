package com.example.pruebaonoff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainSecundaria extends AppCompatActivity {

    TextView title, id, iduser, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        title = (TextView) findViewById(R.id.idTitle);
        id = (TextView) findViewById(R.id.idUser_ID);
        body = (TextView) findViewById(R.id.idBody);

        String IdPost = getIntent().getStringExtra("IdPost");
        String IdUser = getIntent().getStringExtra("IdUser");
        String Title = getIntent().getStringExtra("Title");
        String Body = getIntent().getStringExtra("Body");

        title.setText(Title);
        body.setText(Body);
        id.setText(IdPost + " - " + IdUser);
    }
}