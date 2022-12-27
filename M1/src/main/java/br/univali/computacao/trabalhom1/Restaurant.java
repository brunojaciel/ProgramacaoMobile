package br.univali.computacao.trabalhom1;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String name;
    private String andress;
    private String city;
    private Integer image;

    public Restaurant (String name, String andress, String city, Integer image){
        this.name = name;
        this.andress = andress;
        this.city= city;
        this.image = image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getAndress() {
        return andress;
    }

    public String getCity() {
        return city;
    }

    public Object getImage() {
        return image;
    }

}
