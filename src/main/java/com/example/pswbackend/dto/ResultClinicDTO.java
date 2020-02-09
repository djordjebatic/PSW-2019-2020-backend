package com.example.pswbackend.dto;

public class ResultClinicDTO {

    private String id;
    private String name;
    private String description;
    private String address;
    private String city;
    private String stars;
    private String num_votes;
    private String price;

    public ResultClinicDTO() {
    }

    public ResultClinicDTO(String id, String name, String description, String address, String city, String stars, String num_votes, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.stars = stars;
        this.num_votes = num_votes;
        this.price= price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getNum_votes() {
        return num_votes;
    }

    public void setNum_votes(String num_votes) {
        this.num_votes = num_votes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
