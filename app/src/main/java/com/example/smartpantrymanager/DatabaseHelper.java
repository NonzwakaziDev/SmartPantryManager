package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PantryDB";
    private static final int DATABASE_VERSION = 6;
    private static final String TABLE_NAME = "ingredients";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT, "
                + "quantity TEXT, "
                + "unit TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addIngredient(Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", ingredient.getName());
        values.put("quantity", ingredient.getQuantity());
        values.put("unit", ingredient.getUnit());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            String qty = cursor.getString(2);
            String unit = cursor.getString(3);
            list.add(new Ingredient(name, qty, unit));
        }

        cursor.close();
        db.close();
        return list;
    }
    public boolean ingredientExists(String ingredientName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE name = ?", new String[]{ingredientName});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }
    public void deleteIngredient(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("ingredients", "name = ?", new String[]{name});
        db.close();
    }

    public void updateIngredient(String oldName, String newName, String newQty, String newUnit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("quantity", newQty);
        values.put("unit", newUnit);
        db.update("ingredients", values, "name = ?", new String[]{oldName});
        db.close();
    }


}
