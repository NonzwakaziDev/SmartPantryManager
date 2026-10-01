package com.example.smartpantrymanager;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
//class
public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get data
        String name = getIntent().getStringExtra("NAME");
        String ing = getIntent().getStringExtra("ING");
        String steps = getIntent().getStringExtra("STEPS");

        //Screen scroll view
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.parseColor("#121212"));

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        //text views
        TextView t1 = new TextView(this);
        t1.setText(name);
        t1.setTextSize(28);
        t1.setTextColor(Color.WHITE); 
        t1.setPadding(0,0,0,30);

        TextView t2 = new TextView(this);
        t2.setText("Ingredients:\n" + ing);
        t2.setTextSize(18);
        t2.setTextColor(Color.BLACK);
        t2.setBackgroundColor(Color.WHITE);
        t2.setPadding(40,40,40,40);

        TextView t3 = new TextView(this);
        t3.setText("\nSteps:\n" + steps);
        t3.setTextSize(18);
        t3.setTextColor(Color.parseColor("#E0E0E0")); 
        t3.setPadding(0,40,0,0);

        layout.addView(t1);
        layout.addView(t2);
        layout.addView(t3);
        scroll.addView(layout);

        setContentView(scroll);
    }
}
