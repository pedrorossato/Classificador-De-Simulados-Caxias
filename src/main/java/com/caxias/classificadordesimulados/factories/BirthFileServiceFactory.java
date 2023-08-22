package com.caxias.classificadordesimulados.factories;


import com.caxias.classificadordesimulados.enums.BirthFileTypeEnum;
import com.caxias.classificadordesimulados.services.BirthFileService;

public interface BirthFileServiceFactory {
    BirthFileService create(BirthFileTypeEnum birthFileTypeEnum);
}
