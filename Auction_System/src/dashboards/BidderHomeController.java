package dashboards;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class BidderHomeController implements Initializable {

    @FXML
    private Label msg;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String wname = ViewSellerItemsController.winner;
		String itemname = ViewSellerItemsController.itemname;
		int amount = ViewSellerItemsController.amt;
		String pname = BidderDashboardController.temp;
		if(wname != null) {
			if(wname.equals(pname)) {
				msg.setText("Congrats! You won " + itemname + " For " + amount + " bucks!!");
			}
		}
	}
    
    
}
