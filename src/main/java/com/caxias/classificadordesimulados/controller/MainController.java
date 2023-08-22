package com.caxias.classificadordesimulados.controller;

import com.caxias.classificadordesimulados.MainApplication;
import com.caxias.classificadordesimulados.enums.ProvaTypeEnum;
import com.caxias.classificadordesimulados.enums.ResultFileTypeEnum;
import com.caxias.classificadordesimulados.factories.ResultFileServiceFactory;
import com.caxias.classificadordesimulados.factories.ResultFileServiceFactoryImpl;
import com.caxias.classificadordesimulados.modelviews.ResultLine;
import com.caxias.classificadordesimulados.services.ResultFileService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MainController {
    
    private Stage stage;
    private File resultFile;
    private final ResultFileServiceFactory resultFileServiceFactory;
    public List<ResultLine> resultLines;
    
    @FXML
    private ComboBox<String> chooseProva;
    
    @FXML
    private ComboBox<String> chooseResultFile;
    
    @FXML
    private Button chooseResultFileButton;
    
    @FXML
    private Label step1Label;
    
    @FXML
    private Button avancarButton;

    
    public MainController() {
        resultFileServiceFactory = new ResultFileServiceFactoryImpl();
    }

    @FXML
    public void initialize() {
        chooseProva.getItems().addAll(Arrays.stream(ProvaTypeEnum.values()).map(ProvaTypeEnum::getDescription).toList());
        chooseProva.setValue(ProvaTypeEnum.CMSM.getDescription());
        chooseResultFile.getItems().addAll(Arrays.stream(ResultFileTypeEnum.values()).map(ResultFileTypeEnum::getDescription).toList());
        chooseResultFile.setValue(ResultFileTypeEnum.GIP.getDescription());
        avancarButton.setDisable(true);
    }
    
    @FXML
    protected void chooseResultFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha o arquivo de resultados");
        fileChooser.getExtensionFilters()
            .addAll(new FileChooser.ExtensionFilter("Arquivos de texto", "*.txt")
        );
        resultFile = fileChooser.showOpenDialog(stage);
        
        if(resultFile == null) {
            return;
        }
        
        ResultFileService resultFileService =
                resultFileServiceFactory.create(ResultFileTypeEnum.valueOf(chooseResultFile.getValue()));
        
        try {
            resultLines = resultFileService.parseResultFileToResultLines(resultFile);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao ler arquivo");
            alert.setContentText("Ocorreu um erro ao ler o arquivo de resultados. Por favor, tente novamente.");
            alert.showAndWait();
            return;
        }
        step1Label.setText("Arquivo escolhido: ".concat(resultFile.getName()));
        chooseResultFileButton.setDisable(true);
        chooseResultFile.setDisable(true);
        avancarButton.setDisable(false);
    }

    public void next() {
        try {
            switch (ProvaTypeEnum.valueOf(chooseProva.getValue())) {
                case CMSM -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cmsm-view.fxml"));
                    Parent load = fxmlLoader.load();
                    CmsmController cmsmController = fxmlLoader.getController();
                    cmsmController.setStage(stage);
                    cmsmController.setResultLines(resultLines);
                    stage.setScene(new Scene(load, 800, 600));
                }
                case EEAR, ESA, ESPCEX, ANO_4, ANO_8 -> {
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao abrir avançar");
            alert.setContentText("Confira o preenchimento dos campos e tente novamente.");
            alert.showAndWait();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}