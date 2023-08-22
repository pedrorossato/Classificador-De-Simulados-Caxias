package com.caxias.classificadordesimulados.services;

import com.caxias.classificadordesimulados.modelviews.ResultLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResultFileServiceGIPImpl implements ResultFileService {
    @Override
    public List<ResultLine> parseResultFileToResultLines(File resultFile) throws IOException, ParseException {
        List<ResultLine> resultLines = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(resultFile, StandardCharsets.ISO_8859_1));
        String line = br.readLine();
        String[] headers = line.split(";\\s*");
        
        ResultLine resultLine;
        while ((line = br.readLine()) != null) {
            resultLine = new ResultLine();
            String[] values = line.split(";\\s*");
            for (int i = 0; i < headers.length; i++) {
                if(values[i].equals("null")) 
                    continue;
                switch (headers[i]) {
                    case "NOME_PROVA" -> resultLine.setNomeProva(values[i]);
                    case "CODIGO_PROVA" -> resultLine.setCodigoProva(values[i]);
                    case "DATA_PROVA" -> resultLine.setDataProva(LocalDate.parse(values[i], DateTimeFormatter.ofPattern("dd/MM/yy")));
                    case "NUMERO_MODELO" -> resultLine.setNumeroModelo(Integer.parseInt(values[i]));
                    case "NOME_MODELO" -> resultLine.setNomeModelo(values[i]);
                    case "QUESTAO_INICIAL" -> resultLine.setQuestaoInicial(Integer.parseInt(values[i]));
                    case "QUESTAO_FINAL" -> resultLine.setQuestaoFinal(Integer.parseInt(values[i]));
                    case "TOTAL_PONTOS" -> resultLine.setTotalPontos(NumberFormat.getInstance(Locale.getDefault()).parse(values[i]).doubleValue());
                    case "GABARITO" -> resultLine.setGabarito(values[i]);
                    case "RESPOSTAS" -> resultLine.setRespostas(values[i]);
                    case "RESULTADO_PONTOS" -> resultLine.setResultadoPontos(NumberFormat.getInstance(Locale.getDefault()).parse(values[i]).doubleValue());
                    case "RESULTADO_NOTA" -> resultLine.setResultadoNota(NumberFormat.getInstance(Locale.getDefault()).parse(values[i]).doubleValue());
                    case "DISCIPLINA" -> resultLine.setDisciplina(values[i]);
                    case "CODIGO_DISCIPLINA" -> resultLine.setCodigoDisciplina(values[i]);
                    case "NOME_ALUNO" -> resultLine.setNomeAluno(values[i]);
                    case "MATRICULA_ALUNO" -> resultLine.setMatriculaAluno(Integer.parseInt(values[i]));
                    case "NUMERO_ALUNO" -> resultLine.setNumeroAluno(Integer.parseInt(values[i]));
                    case "TURMA_ALUNO" -> resultLine.setTurmaAluno(values[i]);
                }
            }
            resultLines.add(resultLine);
        }
        
        return resultLines;
    }
}
