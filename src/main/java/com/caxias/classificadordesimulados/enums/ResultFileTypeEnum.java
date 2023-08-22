package com.caxias.classificadordesimulados.enums;

public enum ResultFileTypeEnum {
    GIP("GIP");
    
    private String description;
    
    ResultFileTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
