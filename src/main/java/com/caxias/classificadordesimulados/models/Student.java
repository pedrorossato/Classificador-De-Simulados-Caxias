package com.caxias.classificadordesimulados.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Student {
    private String name;
    private LocalDate birthDate;
    private int registration;
    private String situation;
    List<DisciplineResult> disciplineResults;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public List<DisciplineResult> getDisciplineResults() {
        return disciplineResults;
    }

    public void setDisciplineResults(List<DisciplineResult> disciplineResults) {
        this.disciplineResults = disciplineResults;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
