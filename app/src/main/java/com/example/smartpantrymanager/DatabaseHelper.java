package com.example.smartpantrymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PantryDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_INGREDIENTS_TABLE =
                "CREATE TABLE ingredients (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "quantity TEXT," +
                        "unit TEXT)";

        db.execSQL(CREATE_INGREDIENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS ingredients");
        onCreate(db);
    }
    public void addIngredient(
            String name,
            String quantity,
            String unit) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        android.content.ContentValues values =
                new android.content.ContentValues();

        values.put("name", name);
        values.put("quantity", quantity);
        values.put("unit", unit);

        db.insert("ingredients", null, values);

        db.close();
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        android.database.Cursor cursor =
                db.rawQuery("SELECT * FROM ingredients", null);

        StringBuilder ingredients = new StringBuilder();

        while(cursor.moveToNext()){

            String name =
                    cursor.getString(1);

            String quantity =
                    cursor.getString(2);

            String unit =
                    cursor.getString(3);

            ingredients.append(name)
                    .append(" - ")
                    .append(quantity)
                    .append(" ")
                    .append(unit)
                    .append("\n");
        }

        cursor.close();
        db.close();

        return list;
    }

    public void deleteIngredient(String name){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                "ingredients",
                "name=?",
                new String[]{name}
        );

        db.close();
    }

    public void updateIngredient(
            String oldName,
            String newName,
            String quantity,
            String unit){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("name", newName);
        values.put("quantity", quantity);
        values.put("unit", unit);

        db.update(
                "ingredients",
                values,
                "name=?",
                new String[]{oldName}
        );

        db.close();
    }

    public boolean ingredientExists(String ingredientName){

        SQLiteDatabase db =
                this.getReadableDatabase();

        android.database.Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM ingredients WHERE name=?",
                        new String[]{ingredientName}
                );

        boolean exists =
                cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }
    public void addIngredient(Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", ingredient.getName());
        values.put("quantity", ingredient.getQuantity());
        values.put("unit", ingredient.getUnit());
        db.insert("ingredients", null, values);
        db.close();
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ingredients", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String qty = cursor.getString(2);
                String unit = cursor.getString(3);
                list.add(new Ingredient(id, name, qty, unit));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

}
