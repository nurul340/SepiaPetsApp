# SepiaPetsApp

This project is an Android application that displays a list of pets during working hour.
else shows an dialog with message with (app) exit button. 
On clicking a pet, a detailed view of pet is displayed. 

Requirements
Android Studio 4.1 or later
Android SDK version 21 or later

Setup
Clone the repository to your local machine:
$ git clone https://github.com/nurul340/SepiaPetsApp.git


Open the project in Android Studio.

Build the project by clicking on the "Run" button or by using the Shift + F10 keyboard shortcut.

Features
-> Displays a list of pets during working hour
-> The list is displayed in a fragment inside the PetListActivity
-> Uses the Android Jetpack libraries, such as ViewModel, LiveData, and Navigation, to implement the application's logic and navigation
-> Uses the Android KTX library to make the code more concise and readable
-> Uses the AndroidX library to provide backwards compatibility with previous versions of Android
   Testing
-> This project includes a some  UI test cases that test the functionality of the PetListActivity and the PetListFragment. 
   The tests are written using the Espresso library and are located in the app/src/androidTest directory. To run the tests, use the following command:
    $ ./gradlew connectedAndroidTest