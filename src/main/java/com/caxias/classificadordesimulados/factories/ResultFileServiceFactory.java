package com.caxias.classificadordesimulados.factories;

import com.caxias.classificadordesimulados.enums.ResultFileTypeEnum;
import com.caxias.classificadordesimulados.services.ResultFileService;

public interface ResultFileServiceFactory {
    ResultFileService create(ResultFileTypeEnum resultFileTypeEnum);
}
