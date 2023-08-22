package com.caxias.classificadordesimulados.modelviews;

import java.time.LocalDate;

public class BirthLine {
    private int matriculaAluno;
    private LocalDate dataNascimentoAluno;

    public int getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(int matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public LocalDate getDataNascimentoAluno() {
        return dataNascimentoAluno;
    }

    public void setDataNascimentoAluno(LocalDate dataNascimentoAluno) {
        this.dataNascimentoAluno = dataNascimentoAluno;
    }
}
