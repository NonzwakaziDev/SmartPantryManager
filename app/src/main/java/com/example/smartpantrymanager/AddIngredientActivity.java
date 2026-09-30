package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddIngredientActivity extends AppCompatActivity {
    EditText editName, editQuantity, editUnit;
    Button btnAdd;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_ingredient);

        editName =findViewById (R.id.edtName);
        editQuantity = findViewById (R.id.edtQuantity);
        editUnit =  findViewById (R.id.edtUnit);
        btnAdd = findViewById (R.id.btnSave);

        btnAdd.setOnClickListener (v->{
            String name = editName.getText().toString().trim();
            String qty = editQuantity.getText().toString().trim();
            String unit = editUnit.getText().toString().trim();

            if (name.isEmpty() || qty.isEmpty() || unit.isEmpty()){
                Toast.makeText (this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;

            }
            Toast.makeText (this, name + " - " + qty + " " + unit + " Added!", Toast.LENGTH_LONG).show();

            editName.setText("");
            editQuantity.setText("");
            editUnit.setText("");
        });

    }


}

