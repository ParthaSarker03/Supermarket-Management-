/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package suparmarket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane admin_form;
    @FXML
    private AnchorPane employee_form;

    @FXML
    private Hyperlink admin_hyperlink;

    @FXML
    private Button admin_loginBtn;

    @FXML
    private PasswordField admin_password;

    @FXML
    private TextField admin_username;

    @FXML
    private Hyperlink employee_hyperlink;

    @FXML
    private TextField employee_id;

    @FXML
    private Button employee_loginBtn;

    @FXML
    private PasswordField employee_password;

    @FXML
    private AnchorPane main_form;

    @FXML
    public void close() {
        System.exit(0);
    }

    //database tols
    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;
    
   
    

    public void employeeLogin() {
        String employeeData = "SELECT employee_id, password FROM employee WHERE employee_id=? and password = ?";
        connect = database.connectDB();
        try {
            Alert alert;
            if (employee_id.getText().isEmpty() || employee_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Blank Fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(employeeData);
                prepare.setString(1, employee_id.getText());
                prepare.setString(2, employee_password.getText());
                result = prepare.executeQuery();
            }
            if (result.next()) {
                //dashboard
                
                getData.employeeId=employee_id.getText();
                
                
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Login");
                alert.showAndWait();
                employee_loginBtn.getScene().getWindow().hide();//login page hide adminDashboard ulta hoiya geche
                Parent root = FXMLLoader.load(getClass().getResource("adminDashboard.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                
              
                
                
                stage.setScene(scene);
                stage.show();

            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong UserId Or Password");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adminLogin() {
        String adminData = "SELECT username, password FROM login WHERE username=? and password = ?";
        connect = database.connectDB();

        try {

            Alert alert;
            if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Blank Fields");
                alert.showAndWait();
            } else {

                prepare = connect.prepareStatement(adminData);
                prepare.setString(1, admin_username.getText());
                prepare.setString(2, admin_password.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    //ADMIN dashboard
                    
                    getData.username=admin_username.getText();
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();

                    admin_loginBtn.getScene().getWindow().hide();//login page hide employeeDashboard

                    Parent root = FXMLLoader.load(getClass().getResource("employeeDashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                   
                    
                    stage.setScene(scene);
                    stage.show();

                } else {
                    //errror msg
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username Or Password");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //hyperlink work
    @FXML
    public void switchFrom(ActionEvent event) {
        if (event.getSource() == admin_hyperlink) {
            admin_form.setVisible(false);
            employee_form.setVisible(true);
        } else if (event.getSource() == employee_hyperlink) {
            admin_form.setVisible(true);
            employee_form.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
