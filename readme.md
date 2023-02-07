# Android Camera2 API full example

Source: Youtube https://www.youtube.com/watch?v=MhsG3jYEsek

Author: Sandip Bhattacharya

Description: Take photos, save to external storage and display using Camera2 API

Runs on Android 8, open gallery is very slow

Android 13: error on asking for permissions ("only 1 permission at a time"), 
Camera permission is given but not Read/Write external storage, so no photos
are taken.


AndroidManifest.xml:
```plaintext
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

