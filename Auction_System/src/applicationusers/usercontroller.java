package applicationusers;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class usercontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView adminic;

    @FXML
    private ImageView buyeric;

    @FXML
    private ImageView selleric;

    @FXML
    private Button adminlogin;

    @FXML
    private Button buyerlogin;

    @FXML
    private Button sellerlogin;
    
    public usercontroller() {
    	
    }
    
    public void changeToAdminLoginPage (ActionEvent event) throws IOException {
    	Parent adminLoginPage = FXMLLoader.load(getClass().getResource("/application/Sample.fxml"));
    	Scene adminLoginScene = new Scene(adminLoginPage);
    	
    	adminLoginScene.getStylesheets().add(getClass().getResource("user.css").toExternalForm());
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	
    	window.setScene(adminLoginScene);
    	window.show();
    }
    
    public void changeToRegisterPage (ActionEvent event) throws IOException {
    	Parent adminRegPage = FXMLLoader.load(getClass().getResource("/application/Register.fxml"));
    	Scene adminRegScene = new Scene(adminRegPage);
    	
    	adminRegScene.getStylesheets().add(getClass().getResource("user.css").toExternalForm());
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	
    	window.setScene(adminRegScene);
    	window.show();
    }  
    
/*
    @FXML
    void initialize() {
        assert adminic != null : "fx:id=\"adminic\" was not injected: check your FXML file 'users.fxml'.";
        assert buyeric != null : "fx:id=\"buyeric\" was not injected: check your FXML file 'users.fxml'.";
        assert selleric != null : "fx:id=\"selleric\" was not injected: check your FXML file 'users.fxml'.";
        assert adminlogin != null : "fx:id=\"adminlogin\" was not injected: check your FXML file 'users.fxml'.";
        assert buyerlogin != null : "fx:id=\"buyerlogin\" was not injected: check your FXML file 'users.fxml'.";
        assert sellerlogin != null : "fx:id=\"sellerlogin\" was not injected: check your FXML file 'users.fxml'.";

    }
    */
}
