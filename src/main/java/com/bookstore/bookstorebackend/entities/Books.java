package com.bookstore.bookstorebackend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Books  implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @Basic(optional = false)
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    private Category category;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private Publisher publisher;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity_on_hand")
    private Integer quantityOnHand;
    @Column(name = "image")
    private String image;

    public Books() {

    }

    public Books(String isbn, String title, Category category, String author, Publisher publisher, BigDecimal price, Integer quantityOnHand, String image) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.author = author;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
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
