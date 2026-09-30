
package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editItemName;
    EditText editQuantity;
    EditText editUnits;

    Button btnAdd;
    Button btnDelete;
    Button btnUpdate;

    TextView txtPantryList;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        editItemName = findViewById(R.id.editItemName);
        editQuantity = findViewById(R.id.editQuantity);
        editUnits = findViewById(R.id.units);

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);


        txtPantryList = findViewById(R.id.txtPantryList);
        txtPantryList.setText(
                dbHelper.getAllIngredients()
        );




        btnAdd.setOnClickListener(v -> {
            String item = editItemName.getText().toString().trim();
            String quantity = editQuantity.getText().toString().trim();
            String unit = editUnits.getText().toString().trim();



            if (item.isEmpty() || quantity.isEmpty() || unit.isEmpty()) {
                txtPantryList.setText("Please fill in all fields");
                return;

            }
            dbHelper.addIngredient(item, quantity, unit);

            txtPantryList.setText(
                    dbHelper.getAllIngredients()
            );

            editItemName.setText("");
            editQuantity.setText("");
            editUnits.setText("");
        });

        btnDelete.setOnClickListener(v -> {
            String item = editItemName.getText().toString().trim();


            if (item.isEmpty()) {
                txtPantryList.setText("Enter the ingredient name to delete");
                return;

            }
            dbHelper.deleteIngredient(item);

            txtPantryList.setText(
                    dbHelper.getAllIngredients()
            );

            editItemName.setText("");
            editQuantity.setText("");
            editUnits.setText("");
        });

        btnUpdate.setOnClickListener(v -> {
            String item = editItemName.getText().toString().trim();
            String quantity = editQuantity.getText().toString().trim();
            String unit = editUnits.getText().toString().trim();



            if (item.isEmpty() || quantity.isEmpty() || unit.isEmpty()) {
                txtPantryList.setText("Please fill in all fields");
                return;

            }
            dbHelper.updateIngredient(item, item, quantity, unit);

            txtPantryList.setText(
                    dbHelper.getAllIngredients()
            );

            editItemName.setText("");
            editQuantity.setText("");
            editUnits.setText("");
        });


    }
}
