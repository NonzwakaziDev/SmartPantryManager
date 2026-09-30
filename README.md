# SmartPantryManager

Android app to reduce food waste by tracking pantry ingredients and suggesting recipes you can make with only what you have.

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

Video Demo: Link will be added
