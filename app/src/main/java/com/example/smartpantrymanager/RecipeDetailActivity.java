package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {
    TextView txtName, txtDetail;
    Button btnDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

       txtName=findViewById(R.id.txtRecipeName);
       txtDetail=findViewById (R.id.txtRecipeDetail);
       btnDone=findViewById(R.id.btnDone);

        String name = getIntent().getStringExtra("recipe_name");

        if (name != null) {

            txtName.setText(name);

            if (name.equals("Rice & Beans Stew")) {

                txtDetail.setText(
                        "Ingredients:\nRice\nBeans\n\nMethod:\n1. Boil rice.\n2. Cook beans.\n3. Mix and serve."
                );

            } else if (name.equals("Flour Pancakes")) {

                txtDetail.setText(
                        "Ingredients:\nFlour\nEggs\n\nMethod:\n1. Mix ingredients.\n2. Pour into a pan.\n3. Cook until golden."
                );

            } else if (name.equals("Sugar Tea + Bread")) {

                txtDetail.setText(
                        "Ingredients:\nTea\nSugar\nBread\n\nMethod:\n1. Boil water.\n2. Add tea and sugar.\n3. Serve with bread."
                );

            } else if (name.equals("Maize Porridge")) {

                txtDetail.setText(
                        "Ingredients:\nMaize Meal\nWater\n\nMethod:\n1. Boil water.\n2. Stir in maize meal.\n3. Cook until thick."
                );

            } else if (name.equals("Tomato Pasta")) {

                txtDetail.setText(
                        "Ingredients:\nTomato\nPasta\n\nMethod:\n1. Boil pasta.\n2. Prepare tomato sauce.\n3. Mix and serve."
                );

            } else if (name.equals("Omelette")) {

                txtDetail.setText(
                        "Ingredients:\nEggs\n\nMethod:\n1. Beat eggs.\n2. Pour into a pan.\n3. Fold and serve."
                );

            } else if (name.equals("Fried Eggs")) {

                txtDetail.setText(
                        "Ingredients:\nEggs\n\nMethod:\n1. Heat pan.\n2. Fry eggs.\n3. Serve hot."
                );

            } else if (name.equals("Potato Bake")) {

                txtDetail.setText(
                        "Ingredients:\nPotatoes\n\nMethod:\n1. Slice potatoes.\n2. Bake until soft.\n3. Serve."
                );

            } else if (name.equals("Cheese Sandwich")) {

                txtDetail.setText(
                        "Ingredients:\nCheese\nBread\n\nMethod:\n1. Place cheese between bread slices.\n2. Toast lightly.\n3. Serve."
                );

            } else if (name.equals("Tuna Salad")) {

                txtDetail.setText(
                        "Ingredients:\nTuna\nVegetables\n\nMethod:\n1. Mix ingredients.\n2. Season.\n3. Serve chilled."
                );

            } else if (name.equals("Pumpkin Soup")) {

                txtDetail.setText(
                        "Ingredients:\nPumpkin\n\nMethod:\n1. Boil pumpkin.\n2. Blend until smooth.\n3. Serve warm."
                );

            } else if (name.equals("Chicken Curry")) {

                txtDetail.setText(
                        "Ingredients:\nChicken\nCurry Powder\n\nMethod:\n1. Cook chicken.\n2. Add curry.\n3. Simmer and serve."
                );

            } else if (name.equals("Chicken Wrap")) {

                txtDetail.setText(
                        "Ingredients:\nChicken\nWrap\n\nMethod:\n1. Cook chicken.\n2. Fill wrap.\n3. Roll and serve."
                );

            } else if (name.equals("Egg Fried Rice")) {

                txtDetail.setText(
                        "Ingredients:\nRice\nEggs\n\nMethod:\n1. Fry eggs.\n2. Add cooked rice.\n3. Stir and serve."
                );

            } else if (name.equals("Bean Salad")) {

                txtDetail.setText(
                        "Ingredients:\nBeans\nVegetables\n\nMethod:\n1. Mix ingredients.\n2. Season.\n3. Serve."
                );

            } else if (name.equals("French Toast")) {

                txtDetail.setText(
                        "Ingredients:\nBread\nEggs\n\nMethod:\n1. Dip bread in egg.\n2. Fry lightly.\n3. Serve."
                );

            } else if (name.equals("Vegetable Soup")) {

                txtDetail.setText(
                        "Ingredients:\nVegetables\n\nMethod:\n1. Boil vegetables.\n2. Season.\n3. Serve hot."
                );

            } else if (name.equals("Rice Pudding")) {

                txtDetail.setText(
                        "Ingredients:\nRice\nMilk\nSugar\n\nMethod:\n1. Cook rice.\n2. Add milk and sugar.\n3. Simmer and serve."
                );

            } else if (name.equals("Beef Stew")) {

                txtDetail.setText(
                        "Ingredients:\nBeef\nVegetables\n\nMethod:\n1. Brown beef.\n2. Add vegetables.\n3. Simmer until tender."
                );

            } else if (name.equals("Vegetable Stir Fry")) {

                txtDetail.setText(
                        "Ingredients:\nVegetables\n\nMethod:\n1. Heat oil.\n2. Stir-fry vegetables.\n3. Serve."
                );
            }
        }

        btnDone.setOnClickListener(v -> finish());
    }
}
