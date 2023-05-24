# android-starter

android-starter is a starter project for Android development with a modular architecture. It consists of three modules: `app`, `DataModule`, and `UiModule`. The `DataModule` handles network-related tasks and database string operations, while the `UiModule` contains all the Compose components that can be used in the `app` module.

## Modules

---

### app

The `app` module is the main module of the project written in Compose. It contains the user interface and business logic of the application.

---

## DataModule

- The `DataModule` module is responsible for handling network-related tasks and database string operations. It provides the necessary data for the `app` module.

#### Logger Class

- The `Logger` class in the `DataModule` is an object class that provides logging functionality for the module.

#### Internet Connectivity Check

- The `DataModule` also includes an extension function for checking internet connectivity.

### Dependencies

The DataModule module includes the following dependencies:

```
// Hilt
implementation 'com.google.dagger:hilt-android:2.44.2'
kapt 'com.google.dagger:hilt-android-compiler:2.44.2'
implementation 'com.google.dagger:hilt-android-gradle-plugin:2.45'

// Retrofit
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.squareup.okhttp3:okhttp:4.10.0'

// Logging interceptor
implementation 'com.squareup.okhttp3:logging-interceptor:4.10.0'

// Encrypted shared preference
implementation 'androidx.security:security-crypto:1.0.0'
```

---

## UiModule

- The UiModule module holds all the Compose components that can be used in the app module. It provides reusable UI components and layouts.

---

## Requirements

- Minimum Android version: 12 or K 33.

---

## Getting Started

To get started with the android-starter project, follow these steps:

1. Clone the repository: `git clone https://github.com/coditas-llp/android-starter.git`
2. Open the project in Android Studio.
3. Build and run the `app` module on an emulator or physical device with the required Android version.
