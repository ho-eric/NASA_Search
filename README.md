# NASA Search App

For this project, I used:
- [Retrofit](https://github.com/square/retrofit)
  - Used to make HTTP requests and API calls to the NASA API in order to get the data we need back as a JSON in the form of a string
- [Kotlin Serialization Converter](https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter#kotlin-serialization-converter)
  - Used to convert and parse the JSON response from the retrofit library into Kotlin objects in order to display the images and other bits of data
  - The serialization then matches the keys in the JSON response with properties in a data object I created that have the same name
- [Coil](https://github.com/coil-kt/coil)
  - Used to take the data of the images we got from the serialization and display the actual images
  - It had built in support for placeholder and error handling and integrated nicely into other Jetpack Compose components 
  - It also has support for image caching 
- [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation)
  - Helped make navigation within my app easier to manage
  - It provided a way to define my app's navigation graph, handle navigation actions, and pass data between screens
- [Material 3](https://m3.material.io/)
  - Helped make building out the UI a lot simpler
  - Included components that adheres to Google's material design standards such as search bar and card components


I attempted to architect my app in the MVVM format:

![app architecture](/image1.png?raw=true)

To build/run my app, open android studio, at the top menu bar, click build, then build bundle(s)/APK(s), build APK. The APK file will be located in \NASASearch\app\build\outputs\apk\debug.

Anything else important:
Repo Link: https://github.com/ho-eric/NASA_Search
