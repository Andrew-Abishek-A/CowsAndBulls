package com.example.cowsandbulls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class PlayActivity extends AppCompatActivity {

    public static String CodeText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.play_activity);
    }

    public void Submit(View V) {
        EditText et = findViewById(R.id.editText);
        CodeText = et.getText().toString();
        startActivity(new Intent(PlayActivity.this, FindCodeActivity.class));
    }

}
