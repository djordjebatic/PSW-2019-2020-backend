package com.example.pswbackend.dto;

public class DrugDTO {
    private Long id;

    private String name;

    private String ingredient;

    private String description;

    public DrugDTO(){
        super();
    }

    public DrugDTO(String name, String ingredient, String description) {
        this.name = name;
        this.ingredient = ingredient;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
