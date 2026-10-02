# SmartPantryManager

Android app to reduce food waste by tracking pantry ingredients and suggesting recipes you can make with only what you have.

Technologies
Language: Kotlin/Java
Platform: Android
IDE: Android Studio
Database: SQLite

I chose SQLite because it works offline, requires no internet or server, persists data after app closes and is for standard local storage. It is perfect for pantry items and pre-loaded recipes.


Features:
Add, Edit, Delete Pantry ingredients (CRUD)
List all pantry items via RecyclerView
20 pre-loaded recipes seeded on first run
Suggested recipes screen with strict matching rule - only shows recipes where All ingredients are in pantry with required quantity
Recipe detail screen
Setting screen
handles singular/plural e.g. tomato/tomatoes

How to run
Clone repo: https://github.com/NonzwakaziDev/SmartPantryManager.git
Open in Android Studio
Sync Gradle
Run on emulator or physical device (android 7.0+)

Structure:
/app: main android app module
/database: SQLite helper & Room entities
/recipes: 20 Seeded recipes
/ui: screen: Pantry List, Add Item, Recipes and settings



Video Demo: https://drive.google.com/file/d/1UbdIC7-Wz7k4vnJCRocMF4Y2PIwRZq3L/view?usp=drive_link
