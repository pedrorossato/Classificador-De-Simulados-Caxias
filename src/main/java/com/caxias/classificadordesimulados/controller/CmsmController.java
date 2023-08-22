package com.caxias.classificadordesimulados.controller;

import com.caxias.classificadordesimulados.enums.BirthFileTypeEnum;
import com.caxias.classificadordesimulados.factories.BirthFileServiceFactory;
import com.caxias.classificadordesimulados.factories.BirthFileServiceFactoryImpl;
import com.caxias.classificadordesimulados.models.DisciplineResult;
import com.caxias.classificadordesimulados.models.Student;
import com.caxias.classificadordesimulados.modelviews.BirthLine;
import com.caxias.classificadordesimulados.modelviews.ResultLine;
import com.caxias.classificadordesimulados.services.BirthFileService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CmsmController {
    private final BirthFileServiceFactory birthFileServiceFactory;
    private List<ResultLine> resultLines;
    private List<BirthLine> birthLines;
    
    @FXML
    public TextField className;
    @FXML
    public TextField minResultPoints;
    @FXML
    public Button classifyButton;
    @FXML
    public Label step3Label;
    @FXML
    public Button chooseBirthFileButton;
    @FXML
    public Stage stage;
    

    public CmsmController() {
        this.birthFileServiceFactory = new BirthFileServiceFactoryImpl();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setResultLines(List<ResultLine> resultLines) {
        this.resultLines = resultLines;
    }

    public void chooseBirthFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha o arquivo de datas de nascimento");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivos CSV", "*.csv")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file == null) 
            return;
        BirthFileService birthFileService = birthFileServiceFactory.create(BirthFileTypeEnum.valueOf(file.getName().split("\\.")[1].toUpperCase()));
        try {
            birthLines = birthFileService.parseBirthFileToBirthLines(file);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao ler arquivo");
            alert.setContentText("Ocorreu um erro ao ler o arquivo de resultados. Por favor, tente novamente.");
            alert.showAndWait();
            return;
        }

        step3Label.setText("Arquivo escolhido: ".concat(file.getName()));
        chooseBirthFileButton.setDisable(true);
        classifyButton.setDisable(false);
    }

    public void classify() {
        resultLines = filterResultLinesByClassName();
        int minResultPoints = Integer.parseInt(this.minResultPoints.getText());
        List<Student> students = new ArrayList<>();
        try {
            for (ResultLine resultLine : resultLines) {
                Optional<Student> studentByRegistration = students.stream().filter(student -> student.getRegistration() == resultLine.getMatriculaAluno()).findFirst();
                if(studentByRegistration.isPresent()) {
                    DisciplineResult disciplineResult = getDisciplineResultByResultLine(resultLine);
                    studentByRegistration.get().getDisciplineResults().add(disciplineResult);
                    if(disciplineResult.getResultPoints() < minResultPoints)
                        studentByRegistration.get().setSituation("DESCLASSIFICADO");
                    continue;
                }
                
                Student student = new Student();
                student.setName(resultLine.getNomeAluno());
                student.setRegistration(resultLine.getMatriculaAluno());
                student.setBirthDate(birthLines.stream()
                        .filter(birthLine -> birthLine.getMatriculaAluno() == resultLine.getMatriculaAluno())
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("Não foi possível encontrar a data de nascimento do aluno com matrícula " + resultLine.getMatriculaAluno()))
                        .getDataNascimentoAluno()
                );
                student.setDisciplineResults(new ArrayList<>());
                student.setSituation("CLASSIFICADO");
                DisciplineResult disciplineResult = getDisciplineResultByResultLine(resultLine);
                if(disciplineResult.getResultPoints() < minResultPoints)
                    student.setSituation("DESCLASSIFICADO");
                student.getDisciplineResults().add(disciplineResult);
                students.add(student);
            }
        } catch (NoSuchElementException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao classificar.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }

        sortStudentsByCmsmRules(students);

        try {
            writeToResultOfClassificationCSV(students);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao escrever no arquivo de resultados.");
            alert.setContentText("Tente novamente.");
            alert.showAndWait();
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Classificação gerada");
        alert.setHeaderText("Um arquivo de classificação foi gerado com sucesso.");
        alert.setContentText("Confira o arquivo classificacao.csv no diretório do programa.");
        alert.showAndWait();
    }

    private static DisciplineResult getDisciplineResultByResultLine(ResultLine resultLine) {
        DisciplineResult disciplineResult = new DisciplineResult();
        disciplineResult.setDisciplineName(resultLine.getDisciplina());
        disciplineResult.setResultPoints(resultLine.getResultadoPontos());
        disciplineResult.setResultGrade(resultLine.getResultadoNota());
        disciplineResult.setTotalPoints(resultLine.getTotalPontos());
        return disciplineResult;
    }

    private static void sortStudentsByCmsmRules(List<Student> students) {
        students.sort((s1, s2) -> {
            double resultPointsStudent1 = s1.getDisciplineResults().stream()
                    .mapToDouble(DisciplineResult::getResultPoints)
                    .sum();
            double resultPointsStudent2 = s2.getDisciplineResults().stream()
                    .mapToDouble(DisciplineResult::getResultPoints)
                    .sum();

            int resultPointsComparison = Double.compare(resultPointsStudent2, resultPointsStudent1);
            if (resultPointsComparison != 0) {
                return resultPointsComparison;
            }

            double resultPointsMathStudent1 = s1.getDisciplineResults().stream()
                    .filter(disciplineResult -> disciplineResult.getDisciplineName().equals("MATEMÁTICA"))
                    .mapToDouble(DisciplineResult::getResultPoints)
                    .sum();
            double resultPointsMathStudent2 = s2.getDisciplineResults().stream()
                    .filter(disciplineResult -> disciplineResult.getDisciplineName().equals("MATEMÁTICA"))
                    .mapToDouble(DisciplineResult::getResultPoints)
                    .sum();

            int mathResultComparison = Double.compare(resultPointsMathStudent2, resultPointsMathStudent1);
            if (mathResultComparison != 0) {
                return mathResultComparison;
            }

            int birthDateComparison = s1.getBirthDate().compareTo(s2.getBirthDate());
            return birthDateComparison;
        });
    }

    private List<ResultLine> filterResultLinesByClassName() {
        String className = this.className.getText();
        if (!className.isBlank()) {
            return resultLines.stream().filter(resultLine -> resultLine.getTurmaAluno().equals(className)).collect(Collectors.toList());
        }
        return resultLines;
    }

    private static void writeToResultOfClassificationCSV(List<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("classificacao.csv"));
        writer.write("Posição;Matrícula;Nome;Situação;Pontuaçao total;Pontuação matemática; Pontuação português\n");
        int position = 1;
        for (Student student : students) {
            writer.write(
            position++ + ";" +
                student.getRegistration() + ";" +
                student.getName() + ";" + 
                student.getSituation() + ";" +
                (int)student.getDisciplineResults().stream().mapToDouble(DisciplineResult::getResultPoints).sum() + ";" +
                (int)student.getDisciplineResults().stream().filter(disciplineResult -> disciplineResult.getDisciplineName().equals("MATEMÁTICA")).mapToDouble(DisciplineResult::getResultPoints).sum() + ";" +
                (int)student.getDisciplineResults().stream().filter(disciplineResult -> disciplineResult.getDisciplineName().equals("PORTUGUÊS")).mapToDouble(DisciplineResult::getResultPoints).sum() + "\n"
            );
        }
        writer.close();
    }
}
