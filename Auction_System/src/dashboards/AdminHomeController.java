package dashboards;

import java.net.URL;
import java.util.ResourceBundle;

import applicationusers.Admin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AdminHomeController implements Initializable {

    @FXML
    private Label bidders;

    @FXML
    private Label sellers;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("In Adminhome!!");
		Admin admin = new Admin();
		int sum = admin.getbiddercount();
		
		String temp = Integer.toString(sum);
		
		this.bidders.setText(temp);
		
		int sellersum = admin.getsellercount();
		temp = Integer.toString(sellersum);
		this.sellers.setText(temp);
	}

}
