package sample;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class Controller {

    @FXML
    Button button1;
    @FXML
    Button button2;

    public void iniciar(ActionEvent actionEvent) {
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("ARMA SECRETA");
        dialogo.setHeaderText("Secreto...");
        dialogo.setContentText("INTRODUCIR CODIGO MORZE");
        Optional<String> resultado = dialogo.showAndWait();
        if (resultado.isPresent()) {
            if (resultado.get().equals("proyinf")) {
                Stage stage = (Stage) button1.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Secundario.fxml"));
                Parent root = null;
                try {
                    root = fxmlLoader.load();
                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Aplicación");
                    alerta.setContentText("Llama al lapecillo de sistemas.");
                    alerta.showAndWait();
                    Platform.exit();
                }
                FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();
                Secundario controller = fxmlLoader.<Secundario>getController();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    public void salir(ActionEvent actionEvent) {
        Platform.exit();
    }
}
