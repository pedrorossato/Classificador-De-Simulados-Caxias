package com.caxias.classificadordesimulados.factories;

import com.caxias.classificadordesimulados.enums.BirthFileTypeEnum;
import com.caxias.classificadordesimulados.services.BirthFileService;
import com.caxias.classificadordesimulados.services.BirthFileServiceCSVImpl;

public class BirthFileServiceFactoryImpl implements BirthFileServiceFactory {
    @Override
    public BirthFileService create(BirthFileTypeEnum birthFileTypeEnum) {
        return switch (birthFileTypeEnum) {
            case CSV -> new BirthFileServiceCSVImpl();
        };
    }
}
