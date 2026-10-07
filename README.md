# Recipe App

A Java Android app for exploring recipes, viewing recipe details, managing favorites, adding recipes, and maintaining a shopping list.

## Open and run

1. Open the repository root in Android Studio.
2. Install Android SDK Platform 36 when prompted and let Gradle sync finish.
3. Use the JDK required by the Android Gradle Plugin, normally Android Studio's bundled Gradle JDK. The app's Java source compatibility is 11.
4. Select the `app` configuration and run on an emulator or device running Android 7.0 / API 24 or newer.

## Build from a terminal

On Windows:

```powershell
.\gradlew.bat assembleDebug
.\gradlew.bat testDebugUnitTest
```

On macOS/Linux, use `./gradlew` instead. Connected device tests run with `connectedDebugAndroidTest`. The debug APK is generated under `app/build/outputs/apk/debug/`.

## Code map

- `app/src/main/java/com/example/recipeapp/`: activity, fragments, and data managers.
- `app/src/main/res/`: layouts, drawables, strings, and themes.
- `FavoritesManager` and `ShoppingManager`: in-memory lists.
- `ProfileManager`: name and email stored with SharedPreferences.

## Data behavior

Favorites and shopping items currently live in static lists: they are lost when Android terminates the app process. Profile name and email persist locally through SharedPreferences. This is a local app prototype, with no cloud synchronization guarantee. Existing example tests are scaffolding rather than full feature coverage.
