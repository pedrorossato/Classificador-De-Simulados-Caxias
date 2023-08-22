package com.caxias.classificadordesimulados.models;

public class DisciplineResult {
    private String disciplineName;
    
    private double totalPoints;
    private double totalGrade;
    
    private double resultPoints;
    private double resultGrade;

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public double getResultPoints() {
        return resultPoints;
    }

    public void setResultPoints(double resultPoints) {
        this.resultPoints = resultPoints;
    }

    public double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(double totalGrade) {
        this.totalGrade = totalGrade;
    }

    public double getResultGrade() {
        return resultGrade;
    }

    public void setResultGrade(double resultGrade) {
        this.resultGrade = resultGrade;
    }
}
