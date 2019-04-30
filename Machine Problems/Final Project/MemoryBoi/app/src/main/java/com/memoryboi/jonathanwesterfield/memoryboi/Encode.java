package com.memoryboi.jonathanwesterfield.memoryboi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import static java.lang.System.err;


/**
 * This class is for encoding and decoding the images taken by the camera as well as
 * uploading and downloading the images from firebase
 */
public class Encode
{
    private DatabaseReference dbRef;
    private DatabaseReference dbTable;
    private FirebaseDatabase fireDB;
    private FirebaseAuth mAuth;
    private FirebaseUser fUser;
    private ArrayList<MemoryObj> retMemoryList;

    public Encode()
    {
        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        fireDB = FirebaseDatabase.getInstance();
        dbRef = fireDB.getReference();
        dbTable = dbRef.child("memories/");
    }

    /**
     * Putting blobs into databases is never a good idea especially with bitmaps
     * so we are going to encode it into a base64 string so it is more stable
     * @param bmp
     * @return
     */
    public static String encodeToBase64(Bitmap bmp)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos); // Could be Bitmap.CompressFormat.PNG or Bitmap.CompressFormat.WEBP
        byte[] bai = baos.toByteArray();

        String base64Image = Base64.encodeToString(bai, Base64.DEFAULT);
        return base64Image;
    }

    /**
     * Decode the base64 string back into the original image bitmap
     * @param base64Img
     * @return
     */
    public static Bitmap decodeFromBase64(String base64Img)
    {
        byte[] data = Base64.decode(base64Img, Base64.DEFAULT);
        Bitmap bm;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;
        bm = BitmapFactory.decodeByteArray(data, 0, data.length, opt);
        return bm;
    }

    public void push(String imgStr, double latitude, double longitude)
    {
        MemoryObj mem = new MemoryObj(imgStr, latitude, longitude);

        DatabaseReference rankey = dbTable.push();
        rankey.setValue(mem, new DatabaseReference.CompletionListener()
        {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference)
            {
                // no error
                if (databaseError == null)
                    System.out.println("DONE");
                else
                    System.out.println("UPLOAD FAILED");
            }
        });

        return;
    }

    public void pull()
    {
        Query query = dbTable.orderByKey();
        query.addListenerForSingleValueEvent(getValueEventListener());
    }

    /**
     * Event listener for both queries. Since it just fills up the GradeObj arraylist,
     * can be used for both query 1 and 2.
     * @return
     */
    public ValueEventListener getValueEventListener()
    {
        ValueEventListener valueEventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    // clear the arraylist to refill it
                    retMemoryList = new ArrayList<>();
                    //Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_SHORT).show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        MemoryObj memory = snapshot.getValue(MemoryObj.class);

                        retMemoryList.add(memory);
                    }
                    System.out.println("Query Finished");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                System.out.println("ERROR: query failed");
            }
        };

        return valueEventListener;
    }


}
