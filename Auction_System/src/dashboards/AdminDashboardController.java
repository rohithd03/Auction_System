package dashboards;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AdminDashboardController implements Initializable {
	
    @FXML
    private Pane holderpane;
    
    AnchorPane Adminhome;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	private void SetNode(Node node) {
		holderpane.getChildren().clear();
		holderpane.getChildren().add((Node) node);
		
		FadeTransition ft = new FadeTransition(Duration.millis(1500));
		ft.setNode(node);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}

	private void createPage() throws IOException {
		Adminhome = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
		SetNode(Adminhome);
		
	}
	
    @FXML
    void openhome(ActionEvent event) {
    	try {
			createPage();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    @FXML
    void logout(ActionEvent event) {
    	System.out.println("Admin Logged out!!");
    	System.exit(0);
    }
    
    @FXML
    void openmanageusers(ActionEvent event) throws IOException {
    	if(Adminhome == null) {
        	Adminhome = FXMLLoader.load(getClass().getResource("AdminManageUsers.fxml"));
    	}else {
    		Adminhome.getChildren().clear();
        	Adminhome = FXMLLoader.load(getClass().getResource("AdminManageUsers.fxml"));
    	}
		holderpane.getChildren().add((Node) Adminhome);
    }
    
    @FXML
    void openViewItems(ActionEvent event) throws IOException {
    	if(Adminhome == null) {
    		Adminhome = FXMLLoader.load(getClass().getResource("ViewItems.fxml"));
    	}else {
    		Adminhome.getChildren().clear();
    		Adminhome = FXMLLoader.load(getClass().getResource("ViewItems.fxml"));
    	}
		holderpane.getChildren().add((Node) Adminhome);
    }
	
    @FXML
    void openmanageitems(ActionEvent event) throws IOException {
    	if(Adminhome == null) {
    		Adminhome = FXMLLoader.load(getClass().getResource("ManageItems.fxml"));
    	}else {
    		Adminhome.getChildren().clear();
    		Adminhome = FXMLLoader.load(getClass().getResource("ManageItems.fxml"));
    	}
		holderpane.getChildren().add((Node) Adminhome);
    }

}
