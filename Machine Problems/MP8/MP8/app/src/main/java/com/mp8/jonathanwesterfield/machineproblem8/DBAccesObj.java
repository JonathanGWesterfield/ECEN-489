package com.mp8.jonathanwesterfield.machineproblem8;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class DBAccesObj implements Serializable
{
    private FirebaseDatabase fireDB;
    private DatabaseReference fireDBRef;
    private FirebaseUser fUser;
    private FirebaseAuth mAuth;
    private Context context;

    public DBAccesObj() { /* Empty Constructor */ }

    public DBAccesObj(FirebaseAuth auth)
    {
        this.mAuth = auth;
    }

    public DBAccesObj(FirebaseAuth auth, FirebaseUser user)
    {
        this.mAuth = auth;
        this.fUser = user;
    }

    public DBAccesObj(FirebaseAuth auth, FirebaseUser user, Context context)
    {
        this.mAuth = auth;
        this.fUser = user;
        this.context = context;
    }

    public DBAccesObj(FirebaseAuth auth, FirebaseUser user, FirebaseDatabase fireDB)
    {
        this.mAuth = auth;
        this.fUser = user;
        this.fireDB = fireDB;
    }

    public DBAccesObj(FirebaseAuth auth, FirebaseUser user, FirebaseDatabase fireDB, DatabaseReference dbRef)
    {
        this.mAuth = auth;
        this.fUser = user;
        this.fireDB = fireDB;
        this.fireDBRef = dbRef;

    }

    public DBAccesObj(FirebaseAuth auth, FirebaseUser user, FirebaseDatabase fireDB, DatabaseReference dbRef, Context context)
    {
        this.mAuth = auth;
        this.fUser = user;
        this.fireDB = fireDB;
        this.fireDBRef = dbRef;
        this.context = context;
    }

    public DBAccesObj(FirebaseDatabase fireDB, DatabaseReference dbRef)
    {
        this.fireDB = fireDB;
        this.fireDBRef = dbRef;
    }

    public void setFireDB(FirebaseDatabase fireDB)
    {
        this.fireDB = fireDB;
    }

    public void setFireDBRef(DatabaseReference fireDBRef)
    {
        this.fireDBRef = fireDBRef;
    }

    public void setUser(FirebaseUser fUser)
    {
        this.fUser = fUser;
    }

    public void setAuth(FirebaseAuth auth)
    {
        this.mAuth = auth;
    }

    public DatabaseReference getFireDBRef()
    {
        return fireDBRef;
    }

    public FirebaseAuth getmAuth()
    {
        return mAuth;
    }

    public FirebaseDatabase getFireDB()
    {
        return fireDB;
    }

    public FirebaseUser getfUser()
    {
        return fUser;
    }
}
