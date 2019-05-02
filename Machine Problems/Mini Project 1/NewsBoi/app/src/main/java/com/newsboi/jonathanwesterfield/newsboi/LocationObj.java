package com.newsboi.jonathanwesterfield.newsboi;

public class LocationObj
{
    double latitude, longitude;

    public LocationObj(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }
}
