module com.caxias.corretordesimulados {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.caxias.classificadordesimulados to javafx.fxml;
    opens com.caxias.classificadordesimulados.controller to javafx.fxml;
    exports com.caxias.classificadordesimulados;
}