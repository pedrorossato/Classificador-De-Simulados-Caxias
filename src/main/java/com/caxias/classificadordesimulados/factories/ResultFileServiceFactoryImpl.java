package com.caxias.classificadordesimulados.factories;

import com.caxias.classificadordesimulados.enums.ResultFileTypeEnum;
import com.caxias.classificadordesimulados.services.ResultFileService;
import com.caxias.classificadordesimulados.services.ResultFileServiceGIPImpl;

public class ResultFileServiceFactoryImpl implements ResultFileServiceFactory {
    @Override
    public ResultFileService create(ResultFileTypeEnum resultFileTypeEnum) {
        return switch (resultFileTypeEnum) {
            case GIP -> new ResultFileServiceGIPImpl();
        };
    }
}
