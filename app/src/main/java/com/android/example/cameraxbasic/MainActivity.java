package com.android.example.cameraxbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
//部分代码省略
public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    // opencv相关功能实现所需要的so库
    static {
        System.loadLibrary("opencv_java4");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mImageView = findViewById(R.id.iv_image);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.v2_r9haow);
        mImageView.setImageBitmap(bitmap);

        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blurImage(bitmap);
                mImageView.setImageBitmap(bitmap);
            }
        });
    }
    //传入任意bitmap, 返回模糊过后的bitmap
    private Bitmap blurImage(Bitmap origin) {
        Mat mat = new Mat();
        Utils.bitmapToMat(origin, mat);
        Imgproc.GaussianBlur(mat, mat, new Size(15, 15), 0);
        Utils.matToBitmap(mat, origin);
        return origin;
    }
}