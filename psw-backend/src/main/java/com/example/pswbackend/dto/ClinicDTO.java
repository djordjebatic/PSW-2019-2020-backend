package com.example.pswbackend.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ClinicDTO {

    private Long id;

    @NotEmpty(message = "Name empty.")
    @Size(message = "Max name size  is 40.", max = 40)
    private String name;

    private String description;

    @NotEmpty(message = "Address is empty.")
    private String address;

    @NotEmpty(message = "City is empty.")
    private String city;

    public ClinicDTO() {
    }

    public ClinicDTO(Long id, @NotEmpty(message = "Name empty.") @Size(message = "Max name size  is 40.", max = 40) String name, String description, @NotEmpty(message = "Address is empty.") String address, @NotEmpty(message = "City is empty.") String city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
