package com.caxias.classificadordesimulados.modelviews;

import java.time.LocalDate;

public class ResultLine {
    private String nomeProva;

    private String codigoProva;

    private LocalDate dataProva;

    private int numeroModelo;

    private String nomeModelo;

    private int questaoInicial;

    private int questaoFinal;

    private double totalPontos;

    private String gabarito;

    private String respostas;

    private double resultadoPontos;

    private double resultadoNota;

    private String disciplina;

    private String codigoDisciplina;

    private String nomeAluno;

    private int matriculaAluno;

    private int numeroAluno;

    private String turmaAluno;

    public String getNomeProva() {
        return nomeProva;
    }

    public void setNomeProva(String nomeProva) {
        this.nomeProva = nomeProva;
    }

    public String getCodigoProva() {
        return codigoProva;
    }

    public void setCodigoProva(String codigoProva) {
        this.codigoProva = codigoProva;
    }

    public LocalDate getDataProva() {
        return dataProva;
    }

    public void setDataProva(LocalDate dataProva) {
        this.dataProva = dataProva;
    }

    public int getNumeroModelo() {
        return numeroModelo;
    }

    public void setNumeroModelo(int numeroModelo) {
        this.numeroModelo = numeroModelo;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    public int getQuestaoInicial() {
        return questaoInicial;
    }

    public void setQuestaoInicial(int questaoInicial) {
        this.questaoInicial = questaoInicial;
    }

    public int getQuestaoFinal() {
        return questaoFinal;
    }

    public void setQuestaoFinal(int questaoFinal) {
        this.questaoFinal = questaoFinal;
    }

    public double getTotalPontos() {
        return totalPontos;
    }

    public void setTotalPontos(double totalPontos) {
        this.totalPontos = totalPontos;
    }

    public String getGabarito() {
        return gabarito;
    }

    public void setGabarito(String gabarito) {
        this.gabarito = gabarito;
    }

    public String getRespostas() {
        return respostas;
    }

    public void setRespostas(String respostas) {
        this.respostas = respostas;
    }

    public double getResultadoPontos() {
        return resultadoPontos;
    }

    public void setResultadoPontos(double resultadoPontos) {
        this.resultadoPontos = resultadoPontos;
    }

    public double getResultadoNota() {
        return resultadoNota;
    }

    public void setResultadoNota(double resultadoNota) {
        this.resultadoNota = resultadoNota;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(int matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public int getNumeroAluno() {
        return numeroAluno;
    }

    public void setNumeroAluno(int numeroAluno) {
        this.numeroAluno = numeroAluno;
    }

    public String getTurmaAluno() {
        return turmaAluno;
    }

    public void setTurmaAluno(String turmaAluno) {
        this.turmaAluno = turmaAluno;
    }
}
