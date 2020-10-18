package com.bookstore.bookstorebackend.entities;


import java.math.BigDecimal;

public class BookDto {
    private String isbn;

    private String title;
    private String category;
    private String publisher;
    private BigDecimal price;
    private Integer quantityOnHand;
    private String image;

    public BookDto() {
    }

    public BookDto(String isbn, String title, String category, String publisher, BigDecimal price, Integer quantityOnHand, String image) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.publisher = publisher;
        this.price = price;
        this.quantityOnHand = quantityOnHand;
        this.image = image;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(Integer quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
