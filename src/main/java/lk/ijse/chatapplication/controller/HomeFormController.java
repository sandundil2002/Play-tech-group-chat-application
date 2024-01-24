package lk.ijse.chatapplication.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lk.ijse.chatapplication.HelloApplication;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class HomeFormController {

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TextField txtName;

    static String name;

    public void initialize(){
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh : mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String formattedTime = time.format(timeFormatter);
        String formattedDate = date.format(dateFormatter);
        lblTime.setText(formattedTime);
        lblDate.setText(formattedDate);
        loadServerForm();
    }

    @FXML
    private void btnJoinOnAction() {
        if (validateName()){
            loadClientForm();
            clearField();
        } else {
            new Alert(Alert.AlertType.WARNING,"Please enter a valid name").show();
            clearField();
        }
    }

    private boolean validateName() {
        name = txtName.getText();
        boolean isNameValidated = Pattern.compile("^[A-z]{1,20}$").matcher(name).matches();

        if (!isNameValidated) {
            txtName.setStyle("-fx-border-color:#ff0000;");
            txtName.requestFocus();
            return false;
        } else
            return true;
    }

    private void loadClientForm(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/view/client_form.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(Event::consume);
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double screenWidth = screenBounds.getWidth();
            double screenHeight = screenBounds.getHeight();
            double rightEdge = screenWidth - screenHeight;
            stage.setX(rightEdge);
            stage.setTitle(txtName.getText()+" Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadServerForm(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/view/server_form.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(Event::consume);
            double leftX = 0;
            double topY = 100;
            stage.setX(leftX);
            stage.setY(topY);
            stage.setTitle("Server Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearField(){
        txtName.clear();
        txtName.requestFocus();
    }

    @FXML
    public void enterKeyLogin(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            System.out.println("if");
        }
    }
}