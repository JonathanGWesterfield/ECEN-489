package com.memoryboi.jonathanwesterfield.memoryboi;

public class MemoryObj
{
    String image;
    double latitude;
    double longitude;

    public MemoryObj() { /* Empty Construcotr */ }
    public MemoryObj(String image, double latitude, double longitude)
    {
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getImage()
    {
        return this.image;
    }

    public double getLatitude()
    {
        return this.latitude;
    }

    public double getLongitude()
    {
        return this.longitude;
    }
}
