# **android-starter**

## Starter Kit to be used for any Android Project

<br />

---

## **Prerequisite**

`minSdk` and `targetSdk` for this project is 33 ([Android 13](https://developer.android.com/guide/topics/manifest/uses-sdk-element)).

Please add you base url in :- `app/src/main/java/com/coditas/example/utils/AppConstants.kt`.

---

<br />

## **Code folder structure**

```
|-- app
|   `-- src
|       |-- androidTest
|       |   `-- java
|       |       `-- com
|       |           `-- coditas
|       |               `-- example
|       |                   `-- ExampleInstrumentedTest.kt
|       |-- main
|       |   |-- AndroidManifest.xml
|       |   |-- java
|       |   |   `-- com
|       |   |       `-- coditas
|       |   |           `-- example
|       |   |               |-- data
|       |   |               |   |-- dto
|       |   |               |   |   `-- GenericResponse.kt
|       |   |               |   |-- local
|       |   |               |   |   `-- AccessTokenSharedPreference.kt
|       |   |               |   `-- remote
|       |   |               |       |-- APIService.kt
|       |   |               |       |-- CustomInterceptor.kt
|       |   |               |       `-- NetworkResult.kt
|       |   |               |-- di
|       |   |               |   |-- App.kt
|       |   |               |   `-- NetworkModule.kt
|       |   |               |-- repository
|       |   |               |   `-- UserRepository.kt
|       |   |               |-- ui
|       |   |               |   |-- MainActivity.kt
|       |   |               |   |-- dashboard
|       |   |               |   |   |-- DashboardFragment.kt
|       |   |               |   |   `-- DashboardViewModel.kt
|       |   |               |   `-- login
|       |   |               |       |-- LoginFragment.kt
|       |   |               |       `-- LoginViewModel.kt
|       |   |               `-- utils
|       |   |                   |-- AppConstants.kt
|       |   |                   |-- CustomExtensions.kt
|       |   |                   |-- InternetConnection.kt
|       |   |                   `-- Logger.kt
|       |   `-- res
|       |       |-- drawable
|       |       |   |-- ic_btn_login_background.xml
|       |       |   `-- ic_launcher_background.xml
|       |       |-- drawable-v24
|       |       |   `-- ic_launcher_foreground.xml
|       |       |-- font
|       |       |   |-- montserratregular.otf
|       |       |   `-- montserratsemibold.otf
|       |       |-- layout
|       |       |   |-- activity_main.xml
|       |       |   |-- fragment_dashboard.xml
|       |       |   `-- fragment_login.xml
|       |       |-- mipmap-anydpi-v26
|       |       |   |-- ic_launcher.xml
|       |       |   `-- ic_launcher_round.xml
|       |       |-- mipmap-hdpi
|       |       |   |-- ic_coditas.png
|       |       |   |-- ic_coditas_logo.png
|       |       |   |-- ic_launcher.webp
|       |       |   `-- ic_launcher_round.webp
|       |       |-- mipmap-mdpi
|       |       |   |-- ic_coditas.png
|       |       |   |-- ic_coditas_logo.png
|       |       |   |-- ic_launcher.webp
|       |       |   `-- ic_launcher_round.webp
|       |       |-- mipmap-xhdpi
|       |       |   |-- ic_coditas.png
|       |       |   |-- ic_coditas_logo.png
|       |       |   |-- ic_launcher.webp
|       |       |   `-- ic_launcher_round.webp
|       |       |-- mipmap-xxhdpi
|       |       |   |-- ic_coditas.png
|       |       |   |-- ic_coditas_logo.png
|       |       |   |-- ic_launcher.webp
|       |       |   `-- ic_launcher_round.webp
|       |       |-- mipmap-xxxhdpi
|       |       |   |-- ic_coditas.png
|       |       |   |-- ic_coditas_logo.png
|       |       |   |-- ic_launcher.webp
|       |       |   `-- ic_launcher_round.webp
|       |       |-- navigation
|       |       |   `-- nav_graph.xml
|       |       |-- values
|       |       |   |-- colors.xml
|       |       |   |-- dimens.xml
|       |       |   |-- strings.xml
|       |       |   |-- style.xml
|       |       |   `-- themes.xml
|       |       |-- values-night
|       |       |   `-- themes.xml
|       |       |-- values-night-v31
|       |       |   `-- themes.xml
|       |       |-- values-v31
|       |       |   `-- themes.xml
|       |       `-- xml
|       |           |-- backup_rules.xml
|       |           `-- data_extraction_rules.xml
|       `-- test
|           `-- java
|               `-- com
|                   `-- coditas
|                       `-- example
|                           `-- ExampleUnitTest.kt
|-- build.gradle
|-- gradle
|   `-- wrapper
|       |-- gradle-wrapper.jar
|       `-- gradle-wrapper.properties
|-- gradle.properties
|-- gradlew
|-- gradlew.bat
|-- local.properties
`-- settings.gradle

```
---
<br />

### **data/dto :-**
1. `GenericResponse.kt` :- Contains GenericResponse for generic type.

<br />

### **data/local :-**
1. `AccessTokenSharedPreference.kt` :- Access shared preference to store or read data.

<br />

### **data/remote :-**
1. `APIService.kt` :- Use REST API in this file.
2. `CustomInterceptor.kt` :- Custom interceptor to intermediate api responses with custom error handeling including internet connection check.
3. `NetworkResult.kt` :- Network response handeling for api calls [Success,Error,Loading].

<br />

### **utils :-**
1. `AppConstants.kt` :- Store all app constants in this file including base url.Please add your base url in this file.
2. `CustomExtensions.kt` :- Custome [extensions](https://kotlinlang.org/docs/extensions.html#extensions-are-resolved-statically) for application.
3. `InternetConnection.kt` :- Returns true if internet connection is available.
4. `Logger.kt` :- Extension for different log messages

---

## **Dependencies**

1. [Navigation](https://developer.android.com/guide/navigation) :-
```
implementation 'androidx.navigation:navigation-fragment-ktx:2.5.3'
implementation 'androidx.navigation:navigation-ui-ktx:2.5.3'
```

- Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app. Android Jetpack's Navigation component helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.

<br />

2.  [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) :-

```
implementation 'com.google.dagger:hilt-android:2.44.2'
kapt 'com.google.dagger:hilt-android-compiler:2.44.2'
```

- Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Doing manual dependency injection requires you to construct every class and its dependencies by hand, and to use containers to reuse and manage dependencies.

- Hilt provides a standard way to use DI in your application by providing containers for every Android class in your project and managing their lifecycles automatically. Hilt is built on top of the popular DI library Dagger to benefit from the compile-time correctness, runtime performance, scalability, and Android Studio support that Dagger provides. For more information, see Hilt and Dagger.

<br />

3. [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) :-

```
implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.5.1'
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'
implementation 'androidx.fragment:fragment-ktx:1.5.5'
```

- LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

<br />

4. [Gson](https://github.com/google/gson) :-

```
implementation 'com.google.code.gson:gson:2.9.1'
```

- Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. Gson can work with arbitrary Java objects including pre-existing objects that you do not have source-code of.

<br />

5. [Retrofit](https://square.github.io/retrofit/) :-

```
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.squareup.okhttp3:okhttp:4.10.0'
```

- Retrofit is an easy and fast library to retrieve and upload data via a REST-based web service. Retrofit manages the process of receiving, sending, and creating HTTP requests and responses. It resolves issues before sending an error and crashing the application. It pools connections to reduce latency.

<br />

6. [Coroutine](https://developer.android.com/kotlin/coroutines)

```
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
```

- Coroutines are computer program components that allow execution to be suspended and resumed, generalizing subroutines for cooperative multitasking. Coroutines are well-suited for implementing familiar program components such as cooperative tasks, exceptions, event loops, iterators, infinite lists and pipes.

7. [Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) :-

```
implementation 'com.squareup.okhttp3:logging-interceptor:4.10.0'
```

- An [OkHttp interceptor](https://square.github.io/okhttp/features/interceptors/) which logs HTTP request and response data.

<br />

8. [Encrypted shared preference](https://developer.android.com/jetpack/androidx/releases/security) :-

```
implementation 'androidx.security:security-crypto:1.0.0'
```

- Safely manage keys and encrypt files and sharedpreferences.

<br />

9. [Splash Screen](https://developer.android.com/develop/ui/views/launch/splash-screen) :-

```
implementation 'androidx.core:core-splashscreen:1.0.0'
```

- Starting in Android 12, the SplashScreen API enables a new app launch animation for all apps when running on a device with Android 12 or higher. This includes an into-app motion at launch, a splash screen showing your app icon, and a transition to your app itself. A SplashScreen is a Window and therefore occludes an Activity.