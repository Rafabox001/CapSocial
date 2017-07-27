package com.example.rafaeldomingo.testapp.data;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Salvador Elizarraras on 18/06/2017.
 */

public class ItemData implements Serializable{



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    private String title;
    private String description;
    private transient Bitmap imageBitmap;
    private transient Bitmap imageHBitmap;
    private byte[] image;
    private byte[] imageHresolution;

    public Bitmap getImageHBitmap() {
        return imageHBitmap;
    }

    public void setImageHBitmap(Bitmap imageHBitmap) {
        this.imageHBitmap = imageHBitmap;
    }



    public byte[] getImageHresolution() {
        return imageHresolution;
    }

    public void setImageHresolution(byte[] imageHresolution) {
        this.imageHresolution = imageHresolution;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
