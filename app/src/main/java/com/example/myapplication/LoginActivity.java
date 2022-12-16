package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText editText;
    private final String blockCharacterSet = "~#^|@$%&*!";

    private final InputFilter filter = (source, start, end, dest, dstart, dend) -> {

        if (source != null && blockCharacterSet.contains(("" + source))) {
            return "";
        }
        return null;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = this.findViewById(R.id.login);
        EditText account = this.findViewById(R.id.account);
        editText = (EditText) findViewById(R.id.account);
        editText.setFilters(new InputFilter[] { filter });

        login.setOnClickListener(view -> {
            EditText account1 = LoginActivity.this.findViewById(R.id.account);
            EditText password = LoginActivity.this.findViewById(R.id.password);
            if(1==1/*account.getText().toString().equals("user") && password.getText().toString().equals("123456")*/){
                //Toast.makeText(LoginActivity.this,"帳號密碼正確",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            else{
                Toast.makeText(LoginActivity.this,"帳號密碼錯誤",Toast.LENGTH_SHORT).show();
            }
        });
    }


}