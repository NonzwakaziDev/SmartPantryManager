package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
//class
public class AddIngredientActivity extends AppCompatActivity {

    EditText editName, editQuantity, editUnit;
    Button btnSave;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        dbHelper = new DatabaseHelper(this);
        editName = findViewById(R.id.edtName);
        editQuantity = findViewById(R.id.edtQuantity);
        editUnit = findViewById(R.id.edtUnit);
        btnSave = findViewById(R.id.btnSave);
//buttons
        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String qty = editQuantity.getText().toString().trim();
            String unit = editUnit.getText().toString().trim();

            if(name.isEmpty()){
                Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHelper.addIngredient(new Ingredient(name, qty, unit));
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
