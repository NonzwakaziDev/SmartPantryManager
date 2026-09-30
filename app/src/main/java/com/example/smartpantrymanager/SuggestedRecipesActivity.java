package com.example.smartpantrymanager;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;



public class SuggestedRecipesActivity extends AppCompatActivity {
    ListView listRecipes;
    Button btnBack;
    DatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        listRecipes = findViewById (R.id.listRecipes);
        btnBack = findViewById (R.id.btnBack);

        dbHelper = new DatabaseHelper(this);

        ArrayList<String> recipes = new ArrayList<>();

        if (dbHelper.ingredientExists("Rice")
                && dbHelper.ingredientExists("Eggs")) {

            recipes.add("Egg Fried Rice");
        }

        if (dbHelper.ingredientExists("Beans")) {

            recipes.add("Bean Salad");
        }

        if (dbHelper.ingredientExists("Bread")
                && dbHelper.ingredientExists("Eggs")) {

            recipes.add("French Toast");
        }

        if (dbHelper.ingredientExists("Vegetables")) {

            recipes.add("Vegetable Soup");
            recipes.add("Vegetable Stir Fry");
        }

        if (dbHelper.ingredientExists("Potato")) {
            recipes.add("Potato Bake");
        }

        if (dbHelper.ingredientExists("Cheese")
                && dbHelper.ingredientExists("Bread")) {

            recipes.add("Cheese Sandwich");
        }

        if (dbHelper.ingredientExists("Tuna")) {
            recipes.add("Tuna Salad");
        }

        if (dbHelper.ingredientExists("Pumpkin")) {
            recipes.add("Pumpkin Soup");
        }

        if (dbHelper.ingredientExists("Chicken")) {
            recipes.add("Chicken Curry");
            recipes.add("Chicken Wrap");
        }

        if (dbHelper.ingredientExists("Rice")
                && dbHelper.ingredientExists("Beans")) {

            recipes.add("Rice & Beans Stew");
        }

        if (dbHelper.ingredientExists("Flour")) {

            recipes.add("Flour Pancakes");
        }

        if (dbHelper.ingredientExists("Sugar")
                && dbHelper.ingredientExists("Tea")
                && dbHelper.ingredientExists("Bread")) {

            recipes.add("Sugar Tea + Bread");
        }

        if (dbHelper.ingredientExists("Maize Meal")) {

            recipes.add("Maize Porridge");
        }

        if (dbHelper.ingredientExists("Tomato")) {

            recipes.add("Tomato Pasta");
        }

        if (dbHelper.ingredientExists("Eggs")) {

            recipes.add("Omelette");
            recipes.add("Fried Eggs");
        }

        if (recipes.isEmpty()) {

            recipes.add(
                    "No recipes match your pantry yet. Add more ingredients."
            );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipes);
        listRecipes.setAdapter (adapter);

        listRecipes.setOnItemClickListener((parent, view, position, id) -> {

            String selectedRecipe = recipes.get(position);

            if(selectedRecipe.equals(
                    "No recipes match your pantry yet. Add more ingredients.")){

                return;
            }

            Intent intent = new Intent(
                    SuggestedRecipesActivity.this,
                    RecipeDetailActivity.class
            );

            intent.putExtra("recipe_name", selectedRecipe);

            startActivity(intent);
        });
        btnBack.setOnClickListener (v-> finish());
    }


}
