package com.diamoncode.diamonbank.accounts.aplication.port.out.dto;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int port;

    // Constructors, getters, and setters

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }


}
