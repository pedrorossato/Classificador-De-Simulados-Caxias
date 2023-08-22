package com.caxias.classificadordesimulados.services;

import com.caxias.classificadordesimulados.modelviews.BirthLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BirthFileServiceCSVImpl implements BirthFileService {
    @Override
    public List<BirthLine> parseBirthFileToBirthLines(File birthFile) throws IOException {
        List<BirthLine> birthLines = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(birthFile, StandardCharsets.ISO_8859_1));

        BirthLine birthLine;
        String line;
        while ((line = br.readLine()) != null) {
            birthLine = new BirthLine();
            String[] values = line.split(";\\s*");
            birthLine.setMatriculaAluno(Integer.parseInt(values[0]));
            birthLine.setDataNascimentoAluno(LocalDate.parse(values[1], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            birthLines.add(birthLine);
        }
        return birthLines;
    }
}
