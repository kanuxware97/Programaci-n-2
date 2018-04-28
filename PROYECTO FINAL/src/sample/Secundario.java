package sample;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Secundario {

    int conteo = 0;

    @FXML
    ImageView TRUMP;

    public void contar(MouseEvent mouseEvent) {
        conteo++;
        if (conteo == 10) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Vas pa lante!");
            alerta.setHeaderText("Intento de Fuga");
            alerta.setContentText("Estas seguro?");
            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.get() == ButtonType.OK) {
                Platform.exit();
            }
            JFXPanel button1 = null;
            Stage stage = (Stage) button1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (Exception e) {
                Alert Alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Aplicación");
                alerta.setContentText("Llama al lapecillo de sistemas.");
                alerta.showAndWait();
                Platform.exit();
            }
            FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            Controller controller = fxmlLoader.<Controller>getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        double x = (Math.random() * (500 - 10)) + 10;
        double y = (Math.random() * (300 - 10)) + 10;
        TRUMP.setLayoutX(x);
        TRUMP.setLayoutY(y);
    }

    public void punto (ActionEvent actionEvent) {
        File archivo = new File("lineas.txt");
        System.out.print(conteo);
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea, ultimalinea = "";
            while ((linea = br.readLine()) != null) {
                ultimalinea = linea;
            }
            System.out.println(ultimalinea);
        } catch (FileNotFoundException e) {
            System.out.println("Problema con el archivo!");
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo!");
        }
    }

    public void ordenar (ActionEvent actionEvent) {
        File archivo = new File("lineas.txt");
        File archivo2 = new File("score.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            ArrayList<Integer> rank = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                rank.add(Integer.parseInt(linea));
            }
            Collections.sort(rank);
            Collections.reverse(rank);
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo2));
            int para = 1;
            for (int r : rank) {
                if (para == 6) {
                    break;
                }
                bw.write(r + "\n");
                para++;
            }
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problema con el archivo!");
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo!");
        }
    }
}






