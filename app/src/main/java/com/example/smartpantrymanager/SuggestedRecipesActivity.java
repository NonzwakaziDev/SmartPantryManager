package com.example.smartpantrymanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    
    class Recipe {
        String name, fullIngredients, steps;
        List<String> required; // only the core ingredient names for strict matching
        Recipe(String n, String full, String s, List<String> r){
            name=n; fullIngredients=full; steps=s; required=r;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.parseColor("#121212")); // DARK background like your main screen

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);
        scroll.addView(layout);

        
        TextView title = new TextView(this);
        title.setText("Suggested Recipes\nOnly shows what you CAN make");
        title.setTextSize(22);
        title.setTextColor(Color.WHITE);
        title.setPadding(0,0,0,30);
        layout.addView(title);

        
        DatabaseHelper db = new DatabaseHelper(this);
        List<Ingredient> pantry = db.getAllIngredients();
        List<String> pantryNormalized = new ArrayList<>();
        for(Ingredient i : pantry){
            String n = i.getName().toLowerCase().trim();
            if(n.endsWith("s")) n = n.substring(0, n.length()-1); // plural -> singular
            pantryNormalized.add(n);
            pantryNormalized.add(i.getName().toLowerCase().trim()); // also keep original
        }

        
        List<Recipe> allRecipes = new ArrayList<>();
        allRecipes.add(new Recipe("Fried Rice", "2 cups Rice\n1 Egg\n2 tbsp Cooking oil\n1 tsp Salt", "1. Boil rice\n2. Fry with oil\n3. Add egg", Arrays.asList("rice", "egg")));
        allRecipes.add(new Recipe("Maize Porridge", "2 cups Maize Meal\n3 cups Water\n1 tsp Salt", "1. Boil water\n2. Add maize meal\n3. Stir 20 mins", Arrays.asList("maize meal")));
        allRecipes.add(new Recipe("Boiled Eggs", "3 Eggs\nWater\nSalt", "1. Boil water\n2. Add eggs 10 min", Arrays.asList("egg")));
        allRecipes.add(new Recipe("Tomato Sauce", "3 Tomatoes\n1 Onion\n2 tbsp Cooking oil\nSalt", "1. Chop tomatoes & onion\n2. Fry onion\n3. Add tomatoes", Arrays.asList("tomato", "onion")));
        allRecipes.add(new Recipe("Chicken Skew", "500g Chicken\n2 Potatoes\nOil & Spice", "1. Cut and skewer\n2. Grill 15 mins", Arrays.asList("chicken", "potato")));
        // Add 10 more quickly for the 15 minimum - they use pantry names you have
        allRecipes.add(new Recipe("Beef Skew", "500g Beef\n1 Onion", "Grill", Arrays.asList("beef", "onion")));
        allRecipes.add(new Recipe("Rice & Beans", "1 cup Rice\n1 cup Beans\nSalt", "Boil together", Arrays.asList("rice", "bean")));
        allRecipes.add(new Recipe("Fried Potatoes", "3 Potatoes\nOil\nSalt", "Fry potatoes", Arrays.asList("potato")));
        allRecipes.add(new Recipe("Egg Fried Rice", "Rice\nEggs\nOil", "Fry", Arrays.asList("rice", "egg")));
        allRecipes.add(new Recipe("Chicken Rice", "Chicken\nRice\nSalt", "Boil rice, grill chicken", Arrays.asList("chicken", "rice")));
        allRecipes.add(new Recipe("Maize & Beans", "Maize Meal\nBeans", "Cook together", Arrays.asList("maize meal", "bean")));
        allRecipes.add(new Recipe("Onion Rice", "Rice\nOnion\nOil", "Fry onion, add rice", Arrays.asList("rice", "onion")));
        allRecipes.add(new Recipe("Potato Curry", "Potato\nTomato\nSpice", "Cook", Arrays.asList("potato", "tomato")));
        allRecipes.add(new Recipe("Chicken Soup", "Chicken\nPotato\nSalt\nWater", "Boil all", Arrays.asList("chicken", "potato")));
        allRecipes.add(new Recipe("Beef Stew", "Beef\nPotato\nTomato", "Stew 30 mins", Arrays.asList("beef", "potato", "tomato")));

        int countShown = 0;
        
        for(Recipe r : allRecipes){
            boolean canMake = true;
            for(String need : r.required){
                String needNorm = need.toLowerCase().trim();
                if(needNorm.endsWith("s")) needNorm = needNorm.substring(0, needNorm.length()-1);

                boolean found = false;
                for(String have : pantryNormalized){
                    if(have.contains(needNorm) || needNorm.contains(have)){
                        found = true; break;
                    }
                }
                if(!found){ canMake = false; break; }
            }

            if(canMake){
                countShown++;
                
                TextView tv = new TextView(this);
                tv.setText("✅ " + r.name + "\nNeeds: " + r.required.toString() + "\nTap for details >");
                tv.setTextColor(Color.BLACK); // CLEAR BLACK TEXT
                tv.setBackgroundColor(Color.WHITE);
                tv.setTextSize(16);
                tv.setPadding(40,40,40,40);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
                p.setMargins(0,0,0,30);
                tv.setLayoutParams(p);
                tv.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);

                tv.setOnClickListener(v -> {
                    Intent i = new Intent(this, RecipeDetailActivity.class);
                    i.putExtra("NAME", r.name);
                    i.putExtra("ING", r.fullIngredients);
                    i.putExtra("STEPS", r.steps);
                    startActivity(i);
                });
                layout.addView(tv);
            }
        }

        
        if(countShown == 0){
            TextView empty = new TextView(this);
            empty.setText("No recipes match your pantry yet - add more ingredients\n\nYou have: " + pantryNormalized.toString());
            empty.setTextColor(Color.parseColor("#FFCC00")); // YELLOW for warning, very visible
            empty.setTextSize(18);
            empty.setPadding(30,30,30,30);
            empty.setBackgroundColor(Color.parseColor("#333333"));
            layout.addView(empty);
        }

        setContentView(scroll);
    }
}
