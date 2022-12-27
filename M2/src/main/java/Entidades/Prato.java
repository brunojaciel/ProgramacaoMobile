package Entidades;

import com.google.gson.annotations.JsonAdapter;

import java.math.BigDecimal;

import Serializer.BooleanSerializer;

public class Prato {
    private int id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    @JsonAdapter(BooleanSerializer.class)
    private boolean gluten;
    private BigDecimal calorias;
    private String image;

    public Prato(int id, String name, String description, BigDecimal price, boolean gluten, BigDecimal calories, String image){
        this.id = id;
        this.nome = name;
        this.descricao = description;
        this.preco = price;
        this.gluten = gluten;
        this.calorias = calories;
        this.image = image;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getCalorias() {
        return calorias;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getGluten() { return gluten;}

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrice(BigDecimal price) {
        this.preco = price;
    }

    public void setHa_gluten(boolean ha_gluten) {
        this.gluten = ha_gluten;
    }

    public void setCalorias(BigDecimal calorias) {
        this.calorias = calorias;
    }

    public String getImageID() {
        return image;
    }

    public void setImageID(String imageID) {
        this.image = imageID;
    }

}
