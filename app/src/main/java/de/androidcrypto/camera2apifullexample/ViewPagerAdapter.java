package de.androidcrypto.camera2apifullexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {
    // Context object
    Context context;
    // Array of images
    ArrayList<String> imagePaths = new ArrayList<>();
    // Layout inflater
    LayoutInflater mLayoutInflater;

    public ViewPagerAdapter(Context context, ArrayList<String> imagePaths) {
        this.context = context;
        this.imagePaths = imagePaths;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    // todo video 31:13
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.galleryitem, container, false);

        /// referencing the image view from the item.xml file
        ImageView imageView = itemView.findViewById(R.id.imageViewMain);

        // setting the image in the imageView
        Bitmap mBitmap = BitmapFactory.decodeFile(imagePaths.get(position));
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        imageView.setImageBitmap(rotatedBitmap);


        if(imageView.getParent() != null) {
            System.out.println("*** imageView.getParent is NOT null");
            ((ViewGroup) imageView.getParent()).removeView(imageView); // <- fix
            ViewParent parentRemoved = imageView.getParent();
            System.out.println("*** removed parent: " + parentRemoved);
        }


        //ViewGroup parent = (ViewGroup) container;
        //parent.removeView(imageView);

        // adding the view
        Objects.requireNonNull(container).addView(imageView);
        return imageView;
        /**
         * Here an error is thrown
         * E/AndroidRuntime: FATAL EXCEPTION: main
         *     Process: de.androidcrypto.camera2apifullexample, PID: 25375
         *     java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent first.
         *         at android.view.ViewGroup.addViewInner(ViewGroup.java:5122)
         *         at android.view.ViewGroup.addView(ViewGroup.java:4953)
         *         at androidx.viewpager.widget.ViewPager.addView(ViewPager.java:1485)
         *         at android.view.ViewGroup.addView(ViewGroup.java:4893)
         *         at android.view.ViewGroup.addView(ViewGroup.java:4866)
         *         at de.androidcrypto.camera2apifullexample.ViewPagerAdapter.instantiateItem(ViewPagerAdapter.java:67)
         *         at androidx.viewpager.widget.ViewPager.addNewItem(ViewPager.java:1010)
         *         at androidx.viewpager.widget.ViewPager.populate(ViewPager.java:1224)
         *         at androidx.viewpager.widget.ViewPager.populate(ViewPager.java:1092)
         *         at androidx.viewpager.widget.ViewPager$3.run(ViewPager.java:273)
         *         at android.view.Choreographer$CallbackRecord.run(Choreographer.java:911)
         *         at android.view.Choreographer.doCallbacks(Choreographer.java:723)
         *         at android.view.Choreographer.doFrame(Choreographer.java:655)
         *         at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:897)
         *         at android.os.Handler.handleCallback(Handler.java:789)
         *         at android.os.Handler.dispatchMessage(Handler.java:98)
         *         at android.os.Looper.loop(Looper.java:164)
         *         at android.app.ActivityThread.main(ActivityThread.java:6944)
         *         at java.lang.reflect.Method.invoke(Native Method)
         *         at com.android.internal.os.Zygote$MethodAndArgsCaller.run(Zygote.java:327)
         *         at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1374)
         */
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        // todo This line causes a following error
        //container.removeView((LinearLayout) object);
        // changed to from LinearLayout to ImageView
        container.removeView((ImageView) object);
    }

}
