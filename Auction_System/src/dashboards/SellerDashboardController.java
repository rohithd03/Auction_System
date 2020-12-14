package dashboards;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.members;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SellerDashboardController implements Initializable {
    @FXML
    private AnchorPane holderpane;

    @FXML
    private Label welcome;
    
    AnchorPane sellerpane;
    
    public static String seller_temp;

    @FXML
    void logout(ActionEvent event) {
    	System.out.println("Seller logged out!");
    	System.exit(0);
    }

    @FXML
    void openaddnewitem(ActionEvent event) throws IOException {
    	if(sellerpane == null) {
    		sellerpane = FXMLLoader.load(getClass().getResource("AddNewItems.fxml"));
    	}else {
    		sellerpane.getChildren().clear();
    		sellerpane = FXMLLoader.load(getClass().getResource("AddNewItems.fxml"));
    	}
		holderpane.getChildren().add((Node) sellerpane);
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
    void openviewyouritems(ActionEvent event) throws IOException {
    	if(sellerpane == null) {
    		sellerpane = FXMLLoader.load(getClass().getResource("ViewSellerItems.fxml"));
    	}else {
    		sellerpane.getChildren().clear();
    		sellerpane = FXMLLoader.load(getClass().getResource("ViewSellerItems.fxml"));
    	}
		holderpane.getChildren().add((Node) sellerpane);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		members user = new members();
		for(members i : user.getmemberslist()) {
			if(i.getEmail().equals(seller_temp)) {
				welcome.setText("Welcome " + i.getFname() + "!");
			}
		}
	}
	
	public SellerDashboardController() {
	}
	public void setuname(String user) {
		seller_temp = user;
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
		sellerpane = FXMLLoader.load(getClass().getResource("SellerHome.fxml"));
		SetNode(sellerpane);
		
	}
}
