package de.androidcrypto.camera2apifullexample;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.util.ArrayList;

public class CustomGalleryActivity extends AppCompatActivity {

    ArrayList<String> f = new ArrayList<>(); // list of file paths
    File[] listFile;
    private String folderName = "MyPhotoDir";
    // creating object of ViewPager
    ViewPager mViewPager;
    // creating object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getFromSdCard();
        // init the ViewPager object
        mViewPager = findViewById(R.id.viewPagerMain);
        // init the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(this, f);
        // adding the adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);

    }

    public void getFromSdCard() {
        File file = new File(getExternalFilesDir(folderName), "/");
        if (file.isDirectory()) {
            listFile = file.listFiles();
            for (int i = 0; i < listFile.length; i++) {
                f.add(listFile[i].getAbsolutePath());
            }
        }
    }
}
