package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerPantry;
    DatabaseHelper dbHelper;
    IngredientAdapter adapter;
    Button btnAddIngredient, btnSuggested, btnDelete, btnUpdate;
    EditText edtActionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        btnAddIngredient = findViewById(R.id.btnAddIngredient);
        btnSuggested = findViewById(R.id.btnSuggestedRecipes);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtActionName = findViewById(R.id.edtActionName);
        recyclerPantry = findViewById(R.id.RecyclerPantry);
        recyclerPantry.setLayoutManager(new LinearLayoutManager(this));

        btnAddIngredient.setOnClickListener(v -> {
            startActivity(new Intent(this, AddIngredientActivity.class));
        });

        btnSuggested.setOnClickListener(v -> {
            startActivity(new Intent(this, SuggestedRecipesActivity.class));
        });
        findViewById(R.id.btnSettings).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });


        // DELETE WORKS NOW
        btnDelete.setOnClickListener(v -> {
            String name = edtActionName.getText().toString().trim();
            if(name.isEmpty()){
                Toast.makeText(this, "Type name to delete", Toast.LENGTH_SHORT).show();
                return;
            }
            dbHelper.deleteIngredient(name);
            Toast.makeText(this, name + " deleted", Toast.LENGTH_SHORT).show();
            edtActionName.setText("");
            refreshPantry();
        });

        // UPDATE WORKS NOW
        btnUpdate.setOnClickListener(v -> {
            String oldName = edtActionName.getText().toString().trim();
            if(oldName.isEmpty()){
                Toast.makeText(this, "Type name to update", Toast.LENGTH_SHORT).show();
                return;
            }
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_ingredient, null);
            EditText edtNewName = dialogView.findViewById(R.id.edtEditName);
            EditText edtNewQty = dialogView.findViewById(R.id.edtEditQty);
            EditText edtNewUnit = dialogView.findViewById(R.id.edtEditUnit);
            edtNewName.setText(oldName);

            new AlertDialog.Builder(this)
                    .setTitle("Update " + oldName)
                    .setView(dialogView)
                    .setPositiveButton("Save", (d,w) -> {
                        String newName = edtNewName.getText().toString().trim();
                        String newQty = edtNewQty.getText().toString().trim();
                        String newUnit = edtNewUnit.getText().toString().trim();
                        dbHelper.updateIngredient(oldName, newName, newQty, newUnit);
                        Toast.makeText(this, "Updated to " + newName, Toast.LENGTH_SHORT).show();
                        edtActionName.setText("");
                        refreshPantry();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshPantry();
    }

    private void refreshPantry(){
        List<Ingredient> list = dbHelper.getAllIngredients();
        adapter = new IngredientAdapter(list);
        recyclerPantry.setAdapter(adapter);
    }
}
