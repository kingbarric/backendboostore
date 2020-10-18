package com.bookstore.bookstorebackend.entities;

public enum Category {
    	Books("Books"),
        Audio_Books("Audio_Books"),
         Videos("Videos"),
        Music("Music");
    private final String enumValue;

    private  Category(String enumValue){
        this.enumValue =enumValue;
    }

    public String getEnumValue(){
        return this.enumValue;
    }

}
