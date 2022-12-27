package br.univali.computacao.trabalhom1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Franchise implements Serializable {
    private List<Restaurant> listRestaurants;
    private String name;
    private String description;
    private Integer image;

    public Franchise (String name, String description, Integer image){
        this.name = name;
        this.description = description;
        this.image = image;
        listRestaurants = new ArrayList<>();
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getImage() {
        return image;
    }

    public void addRestaurant (Restaurant restaurant){
        listRestaurants.add(restaurant);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public List<Restaurant> getListRestaurants() {
        return listRestaurants;
    }

}
