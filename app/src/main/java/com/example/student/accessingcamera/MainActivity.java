package com.example.student.accessingcamera;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.provider.MediaStore;

public class MainActivity extends AppCompatActivity {
static final int REQUEST_IMAGE_CAPTURE=1;
    ImageView buckysImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    buckysImageView=(ImageView)findViewById(R.id.buckysImageView);
        Button buckysButton=(Button) findViewById(R.id.buckysButton);

        //disable button if user has no camera
        if(!hasCamera())
            buckysButton.setEnabled(false);
    }
    public boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
    //launching the camera
    public void launchCamera(View view)
    {
      Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);    //launch camera
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //image was clicked withour any errors
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK)
        { //get photo
            Bundle extra=data.getExtras();
            Bitmap pic=(Bitmap)extra.get("data");
            buckysImageView.setImageBitmap(pic);
            //Displaying the photo


        }
    }
}
