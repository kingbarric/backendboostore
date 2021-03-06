package com.bookstore.bookstorebackend.entities;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class SearchObject {
    private String publisher;
    private String category;
    private String lowerPrice;
    private String upperPrice;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(String lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public String getUpperPrice() {
        return upperPrice;
    }

    public void setUpperPrice(String upperPrice) {
        this.upperPrice = upperPrice;
    }

    @Override
    public String toString() {
        return "SearchObject{" +
                "publisher='" + publisher + '\'' +
                ", category='" + category + '\'' +
                ", lowerPrice='" + lowerPrice + '\'' +
                ", upperPrice='" + upperPrice + '\'' +
                '}';
    }
}
