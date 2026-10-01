package com.example.smartpantrymanager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerPantry;
    DatabaseHelper dbHelper;
    IngredientAdapter adapter;
    Button btnAddIngredient, btnSuggested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        btnAddIngredient = findViewById(R.id.btnAddIngredient);
        btnSuggested = findViewById(R.id.btnSuggestedRecipes);
        recyclerPantry = findViewById(R.id.RecyclerPantry);
        recyclerPantry.setLayoutManager(new LinearLayoutManager(this));

        btnAddIngredient.setOnClickListener(v -> {
            startActivity(new Intent(this, AddIngredientActivity.class));
        });
        btnSuggested.setOnClickListener(v -> {
            startActivity(new Intent(this, SuggestedRecipesActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Ingredient> list = dbHelper.getAllIngredients();
        adapter = new IngredientAdapter(list);
        recyclerPantry.setAdapter(adapter);
    }
}
