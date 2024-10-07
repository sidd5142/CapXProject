# CapXProject

CapXProject is an Android application designed to show the crypto coins and apply search on coins

## Table of Contents
- [Installation](#installation)
- [Prerequisites](#prerequisites)
- [Running the App](#running-the-app)
- [APK File](#apk-file)
- [Tech Stack](#tech-stack)
- [Screenshots]
- (![Screenshot 2024-10-07 220645](https://github.com/user-attachments/assets/2b51b208-60ef-4065-94d3-3c9d97566331)  ![image](https://github.com/user-attachments/assets/aac7639d-f8ce-444f-8102-4c64e4219e26)  ![image](https://github.com/user-attachments/assets/257811bf-3520-4a36-9042-10b695eaff48)

)

## Installation

To get started with CapXProject, clone this repository to your local machine:

```bash
git clone https://github.com/sidd5142/CapXProject.git
```
## Prerequisites
Before you run the project, ensure you have the following tools installed:
```bash
Android Studio Koala (version 2024.1 or higher)
Gradle (comes pre-bundled with Android Studio)
JDK 8 or higher (Ensure that the JAVA_HOME environment variable is set)
```

### Steps to Set Up:
Open Android Studio:
```bash
Go to File -> Open... and navigate to the directory where the project was cloned/downloaded.
Select the project folder and click OK.
Sync Gradle:

Once the project opens, Android Studio Koala will automatically attempt to sync Gradle. If it does not, go to File -> Sync Project with Gradle Files.
Download Dependencies:

Allow Gradle to finish downloading all the necessary dependencies for the project.
Run the Project:

Once the build is successful, click the Run button (green play icon) in Android Studio.
Choose a connected device or emulator to run the application.
```

### Build APK:

If you'd like to generate an APK, go to Build -> Build Bundle(s) / APK(s) -> Build APK(s).
Running the App
Run on a Physical Device:
``` bash
Connect your Android device via USB.
Enable developer mode and USB debugging on your device.
Select your device in Android Studio Koala's target device dropdown, then click Run.
Run on an Emulator:

Create an Android Virtual Device (AVD) by going to Tools -> AVD Manager.
Select the created virtual device in Android Studio Koala's target device dropdown, then click Run.
APK File
You can find the latest APK of the working CapXProject here.
```

### Tech Stack
CapXProject uses the following technologies:
```bash
Kotlin
MVVM Architecture
Android Jetpack Components
Retrofit
Coroutines
Jetpack Compose
```
