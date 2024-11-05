# device_screen_recorder

A Flutter plugin for record the screen based on [HBRecorder](https://github.com/HBiSoft/HBRecorder). This plug-in requires Android SDK 21+

[Donnations](https://www.tinkoff.ru/cf/6xz8n4h0LzO)

## Getting Started

This plugin can be used for record the screen on Android and iOS devices.

Start the recording:

```dart
bool started = DeviceScreenRecorder.startRecordScreen(name: 'example');
```
Or

```dart
bool started = DeviceScreenRecorder.startRecordScreen();
```

Stop the recording:

```dart
String path = DeviceScreenRecorder.stopRecordScreen();
```

## Android

Require add the following permissions in your manifest:

```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_INTERNAL_STORAGE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
```
Add the following in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Implement library in your app level build.gradle:

```groovy
dependencies {
    implementation 'com.github.HBiSoft:HBRecorder:2.0.0'
}
```

## iOS

In progress
