package com.caavo.assignment.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.caavo.assignment.R;
import com.caavo.assignment.database.BaseDatabase;

public class SplashActivity extends AppCompatActivity {

    private Button register, login;
    private EditText username, password;
    private BaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        db = new BaseDatabase(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty()) {
                    username.setError(null);
                    return;
                }

                if(password.getText().toString().isEmpty()) {
                    password.setError(null);
                    return;
                }

                loginUser();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty()) {
                    username.setError(null);
                    return;
                }

                if(password.getText().toString().isEmpty()) {
                    password.setError(null);
                    return;
                }

                registerUser();
            }
        });
    }

    private void registerUser() {
        if(db.insertPerson(username.getText().toString()
                    , password.getText().toString())) {
            loginUser();
        }
    }

    private void loginUser() {
        try {
            Cursor rs = db.getPerson(username.getText().toString()
                    , password.getText().toString());

            if(rs != null) {
                rs.moveToFirst();
                String id = rs.getString(rs.getColumnIndex(BaseDatabase.PERSON_COLUMN_UUID));
                Intent intent = new Intent(this, BaseActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

                if (!rs.isClosed()) {
                    rs.close();
                }

            } else {
                Toast.makeText(this, "No record found", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {

        }
    }


}
