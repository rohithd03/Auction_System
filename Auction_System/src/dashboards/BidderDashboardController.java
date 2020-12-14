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

public class BidderDashboardController implements Initializable{
    @FXML
    private AnchorPane holderpane;
    
    AnchorPane bidderpane;
    
    @FXML
    private Label welcome;
    
    public String uname;
    public static String temp;

    @FXML
    void logout(ActionEvent event) {
    	System.out.println("Bidder logged out!");
    	System.exit(0);
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
    void openparticipate(ActionEvent event) throws IOException {
    	if(bidderpane == null) {
    		bidderpane = FXMLLoader.load(getClass().getResource("BidderParticipate.fxml"));
    	}else {
    		bidderpane.getChildren().clear();
    		bidderpane = FXMLLoader.load(getClass().getResource("BidderParticipate.fxml"));
    	}
		holderpane.getChildren().add((Node) bidderpane);
    }

    @FXML
    void openviewitems(ActionEvent event) throws IOException {
    	if(bidderpane == null) {
    		bidderpane = FXMLLoader.load(getClass().getResource("ViewItems.fxml"));
    	}else {
    		bidderpane.getChildren().clear();
    		bidderpane = FXMLLoader.load(getClass().getResource("ViewItems.fxml"));
    	}
		holderpane.getChildren().add((Node) bidderpane);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		members user = new members();
		for(members i : user.getmemberslist()) {
			if(i.getEmail().equals(temp)) {
				temp = i.getFname();
				welcome.setText("Welcome " + i.getFname() + "!");
			}
		}
	}
	public BidderDashboardController() {
	}
	public void setuname(String user) {
		temp = user;
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
		bidderpane = FXMLLoader.load(getClass().getResource("BidderHome.fxml"));
		SetNode(bidderpane);
		
	}
}
