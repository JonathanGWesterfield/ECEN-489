package com.memoryboi.jonathanwesterfield.memoryboi;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraButton extends Fragment
{
    private ImageButton captureBtn;
    static final int CAMERA_REQUEST_CODE = 10;
    //static final int CAMERA_REQUEST_CODE2 = 11;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_TAKE_PHOTO2 = 2;
    private Encode encode;

    public CameraButton()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera_button, container, false);
        this.captureBtn = (ImageButton) view.findViewById(R.id.captureBtn);
        this.encode = new Encode();

        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))
            showCameraPermissionsAlert(view);
        else
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_TAKE_PHOTO);

        (view.findViewById(R.id.captureBtn)).setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                openCamera();
            }
        });

        return view;
    }

    /**
     * Sometimes we have to ask the user for permission to use the camera
     * Shows OK/Cancel confirmation dialog about camera permission.
     * @param view
     */
    public void showCameraPermissionsAlert(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("We need help").
                setMessage("We need permission to use the camera")
                .setPositiveButton("Yeet", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Activity parent = getActivity();
                        parent.requestPermissions(new String[]{Manifest.permission.CAMERA},
                                REQUEST_TAKE_PHOTO);
                    }
                })
                .setNegativeButton("Hell Naw",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Activity activity = getActivity();
                                if (activity != null)
                                    activity.finish();
                            }
                        });


        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Open up the camera app to take a picture since I don't know the correct
     * REQ_CODE to take a picture. 90210 from the slides is bullshit and doesn't work.
     * It makes the app crash every time so REQ_CODE is set to be 1 since I couldn't find
     * any documentation that actually lists what the code numbers mean
     */
    public void openCamera()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
    }

    /**
     * Get the picture that was taken with the defualt camera app.
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(resultCode == RESULT_OK)
        {
            Bitmap bmp = (Bitmap) intent.getExtras().get("data");
            String base64str = Encode.encodeToBase64(bmp);

            LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null)
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            double latitude = 0.0, longitude = 0.0;

            if(location != null)
            {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

            this.encode.push(base64str, latitude, longitude);


        }
    }

}
