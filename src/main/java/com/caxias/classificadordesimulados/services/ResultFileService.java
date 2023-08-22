package com.caxias.classificadordesimulados.services;

import com.caxias.classificadordesimulados.modelviews.ResultLine;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ResultFileService {
    List<ResultLine> parseResultFileToResultLines(File resultFile) throws IOException, ParseException;
}
