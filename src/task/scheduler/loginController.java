package task.scheduler;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loginController implements Initializable{
    
    @FXML
    private Label  aPLbl1, aPLbl2;
  
    @FXML
    private Button loginBtn, registerLbl, loginLbl, loginLbl1, proceedBtn;

    @FXML
    private AnchorPane main_form;
    
    @FXML
    private AnchorPane anchorPane1, anchorPane2, anchorPane3;
    
    
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField, emailField, emailField1;

      public void switchForm(ActionEvent event){
          if(event.getSource() == registerLbl) {
              anchorPane2.setVisible(true);
              aPLbl1.setVisible(true);
              aPLbl2.setVisible(true);
              loginLbl.setVisible(true);
              anchorPane1.setVisible(false);
            } else if(event.getSource() == loginLbl && event.getSource() == loginLbl1){
          
             anchorPane2.setVisible(false);
              aPLbl1.setVisible(false);
              aPLbl2.setVisible(false);
              loginLbl.setVisible(false);
              anchorPane1.setVisible(true);
      }
      }
      
      public void proceed(ActionEvent event){
          if(event.getSource() == proceedBtn){
              anchorPane1.setVisible(false);
              anchorPane2.setVisible(false);
              anchorPane3.setVisible(true);
          }
      }
          
  
    
    
    // databse
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    public void loginAdmin(ActionEvent events){
        String sql = "SELECT * FROM login WHERE username = ? and password = ?";
        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, usernameField.getText());
            prepare.setString(2, passwordField.getText());
            
            result = prepare.executeQuery();
            Alert alert;
            if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Fill all the blank fields");
                alert.showAndWait();
            } else{
                if(result.next()){
                    // IF PASSWORD AND USERNAME IS CORRECT
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    
                    Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    stage.setScene(scene);
                    
                    stage.show();
                    
                } else{
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password");
                    alert.showAndWait();
                }
            }
            
            
        } catch(Exception e){ e.printStackTrace();}
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    
}
