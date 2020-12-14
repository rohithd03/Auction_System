package dashboards;

import java.net.URL;
import java.util.ResourceBundle;

import application.Item;
import application.members;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AddNewItemsController implements Initializable{
    @FXML
    private TextField name;

    @FXML
    private TextField photo;

    @FXML
    private TextField startp;
    
    @FXML
    private Label msg;

    @FXML
    void clearselection(ActionEvent event) {
    	name.clear();
    	photo.clear();
    	startp.clear();
    }

    @FXML
    void submititem(ActionEvent event) {
    	if(name.getText().isEmpty()) {
    		msg.setText("Enter Name of the Item!");
    		msg.setTextFill(Color.RED);
    	}else if(photo.getText().isEmpty()) {
    		msg.setText("Enter Photo String of the Item!");
    		msg.setTextFill(Color.RED);
    	}else if(startp.getText().isEmpty()) {
    		msg.setText("Enter Starting Price of the Item!");
    		msg.setTextFill(Color.RED);
    	}else {
    		int b_id = 0;
    		String uname = SellerDashboardController.seller_temp;
    		members user = new members();
    		for(members i : user.getmemberslist()) {
    			if(i.getEmail().equals(uname)) {
    				b_id = i.getId();
    			}
    		}
    		Item item = new Item();
    		int price = Integer.parseInt(startp.getText());
    		item.SubmitItem(photo.getText(), name.getText(), b_id, price, 0);
    		System.out.println("Item Submitted Successfully!!");
    		clearselection(event);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
