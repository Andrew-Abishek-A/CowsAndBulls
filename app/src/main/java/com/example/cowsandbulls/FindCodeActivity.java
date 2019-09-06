package com.example.cowsandbulls;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class FindCodeActivity extends AppCompatActivity {

    public String s,word;
    public int len,tries,cows,bulls,t1=0,array[];
    public LinearLayout ll;
    public TextView tvs[],tv1;
    public ScrollingMovementMethod sc1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.find_code_layout);


        s = PlayActivity.CodeText;
        len = s.length();
        array = new int[len];
        String show = "The size of the word is:\t";
        String show1 = Integer.toString(len);
        TextView tv = findViewById(R.id.textView2);
        if(tv != null)
            tv.setText(show + show1);
        tries = (len-4)*5 + 10;
        tvs = new TextView[tries*3];
        tv1 = findViewById(R.id.textView3);
        if(tv1 != null)
            tv1.setText("Remaining tries: "+tries);
        //ll = (LinearLayout) findViewById(R.id.parent_layout);
        sc1 = new ScrollingMovementMethod();
    }

    public void OK(View V) {
        EditText et = findViewById(R.id.editText3);
        word = et.getText().toString();
        et.setText("");
        if (word.length() > len) {
            Toast.makeText(this, "Word length is larger than required", Toast.LENGTH_LONG).show();
            et.setText("");
            findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OK(v);
                }
            });
        } else if (word.length() < len) {
            Toast.makeText(this, "Word length is smaller than required", Toast.LENGTH_LONG).show();
            et.setText("");
            findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OK(v);
                }
            });
        } else if (word.equalsIgnoreCase(s)) {
            startActivity(new Intent(FindCodeActivity.this, EndGameWin.class));
        } else {
            for (int i = 0; i < len; i++) {
                array[i] = (int) word.charAt(i);
                for (int j = 0; j < len; j++) {
                    if ((int) word.charAt(i) == (int) s.charAt(j)) {
                        if (i == j) {
                            bulls++;
                            break;
                        } else if (iselementof(i)) {
                            cows++;
                            break;
                        }
                    }
                }
            }
            inflate();
            inflatecows();
            inflatebulls();
            tries--;
            if(tv1 != null)
                tv1.setText("Remaining tries: "+tries);
            cows=bulls=0;
            if(tries == 0)
                startActivity(new Intent(FindCodeActivity.this, EndGameLose.class));
        }
    }

    public void inflate() {
        ll = (LinearLayout) findViewById(R.id.text_layout);

        tvs[t1] = new TextView(this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.LEFT;
        tvs[t1].setLayoutParams(layoutParams);
        tvs[t1].setText(word);
        tvs[t1].setPadding(20, 20, 20, 20);
        tvs[t1].setTextColor(Color.parseColor("#FFFFFF"));
        ll.addView(tvs[t1]);
        t1++;

    }

    public void inflatecows() {
        ll = (LinearLayout) findViewById(R.id.cows_layout);

        tvs[t1] = new TextView(this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        tvs[t1].setLayoutParams(layoutParams);
        tvs[t1].setText("Cows......"+cows);
        tvs[t1].setPadding(20, 20, 20, 20);
        tvs[t1].setTextColor(Color.parseColor("#FFFFFF"));
        ll.addView(tvs[t1]);
        t1++;
    }

    public void inflatebulls() {
        ll = (LinearLayout) findViewById(R.id.bulls_layout);

        tvs[t1] = new TextView(this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.RIGHT;
        tvs[t1].setLayoutParams(layoutParams);
        tvs[t1].setText("Bulls......"+bulls);
        tvs[t1].setPadding(20, 20, 20, 20);
        tvs[t1].setTextColor(Color.parseColor("#FFFFFF"));
        ll.addView(tvs[t1]);
        t1++;
    }

    public boolean iselementof(int j){
        for(int i=0;i<j;i++){
            if(array[i] == word.charAt(j))
                return false;
        }
        return true;
    }

}
