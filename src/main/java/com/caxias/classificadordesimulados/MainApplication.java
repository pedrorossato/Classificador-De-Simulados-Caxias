package com.caxias.classificadordesimulados;

import com.caxias.classificadordesimulados.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Parent parent = fxmlLoader.load();
        MainController controller = fxmlLoader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(parent, 800, 600);
        stage.setTitle("Classificador de Simulados Caxias");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}