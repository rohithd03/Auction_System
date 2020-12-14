package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import applicationusers.Admin;
import dashboards.BidderDashboardController;
import dashboards.SellerDashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField uname;

    @FXML
    private PasswordField pword;
    
    private int temp;
    
    @FXML
    void login(ActionEvent event) throws IOException {
    	printstatement(event);

    }
    
    public SampleController() {
    	
    }
    public int getid() {
    	return this.temp;
    }
    
    
    public void printstatement (ActionEvent event) throws IOException {
    	Admin admin = new Admin();
    	members user = new members();
    	
    	if(admin.getusername().equals(uname.getText()) && admin.getpassword().equals(pword.getText())) {
    		
    		OpenAdminDashboard(event);
    		
    		System.out.println("Admin logged in Successful!!");
    	}else {
    		if(user.Login(uname.getText(), pword.getText()) == 0) {
    			if(user.getUsertype() == 1) {
    				BidderDashboardController bdc = new BidderDashboardController();
    				bdc.setuname(uname.getText());
    				OpenBidderDashboard(event);
    			}else {
    				SellerDashboardController sdc = new SellerDashboardController();
    				sdc.setuname(uname.getText());
    				OpenSellerDashboard(event);
    				
    			}
    			
    		}else {
    		System.out.println("Login Failed :(!!");
    		}
    	}
    }

	public void OpenAdminDashboard(ActionEvent event) throws IOException {
		Parent admindash = FXMLLoader.load(getClass().getResource("/dashboards/AdminDashboard.fxml"));
       	Scene adminDashScene = new Scene(admindash);
       	
       	adminDashScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        	
       	window.setScene(adminDashScene);
       	window.show();
		
	}
	
	public void OpenBidderDashboard(ActionEvent event) throws IOException {
		Parent admindash = FXMLLoader.load(getClass().getResource("/dashboards/BidderDashboard.fxml"));
       	Scene adminDashScene = new Scene(admindash);
       	
       	adminDashScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        	
       	window.setScene(adminDashScene);
       	window.show();
		
	}
	
	public void OpenSellerDashboard(ActionEvent event) throws IOException {
		Parent admindash = FXMLLoader.load(getClass().getResource("/dashboards/SellerDashboard.fxml"));
       	Scene adminDashScene = new Scene(admindash);
       	
       	adminDashScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        	
       	window.setScene(adminDashScene);
       	window.show();
		
	}
    
   
/*
    @FXML
    void initialize() {
        assert uname != null : "fx:id=\"uname\" was not injected: check your FXML file 'Sample.fxml'.";
        assert pword != null : "fx:id=\"pword\" was not injected: check your FXML file 'Sample.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'Sample.fxml'.";

    }*/
}
