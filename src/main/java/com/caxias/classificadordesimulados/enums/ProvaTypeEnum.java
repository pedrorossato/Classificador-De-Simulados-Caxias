package com.caxias.classificadordesimulados.enums;

public enum ProvaTypeEnum {
    CMSM ("CMSM"),
    EEAR ("EEAR"),
    ESA ("ESA"),
    ESPCEX ("ESPCEX"),
    ANO_4 ("4º Ano"),
    ANO_8 ("8º Ano");
    
    private final String description;
    
    ProvaTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
