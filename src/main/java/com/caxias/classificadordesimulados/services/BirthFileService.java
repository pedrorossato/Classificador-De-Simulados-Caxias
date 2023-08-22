package com.caxias.classificadordesimulados.services;

import com.caxias.classificadordesimulados.modelviews.BirthLine;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface BirthFileService {
    List<BirthLine> parseBirthFileToBirthLines(File birthFile) throws IOException;
}
