package com.bookstore.bookstorebackend.entities;

public enum Publisher {
    Harper_Collins("Harper Collins"),
     Penguins("Penguins"),
     St_Pauls_Publication("St Pauls Publication"),
     Franciscan_Media("Franciscan Media"),
    Orbis("Orbis");
    private final String enumValue;

    private  Publisher(String enumValue){
        this.enumValue =enumValue;
    }

    public String getEnumValue(){
        return this.enumValue;
    }
}
