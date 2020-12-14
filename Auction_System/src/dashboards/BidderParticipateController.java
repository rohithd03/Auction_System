package dashboards;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Item;
import application.members;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class BidderParticipateController implements Initializable{

    @FXML
    private TableView<Item> itemtable;

    @FXML
    private TableColumn<Item, Integer> id;

    @FXML
    private TableColumn<Item, String> img;

    @FXML
    private TableColumn<Item, String> name;

    @FXML
    private TableColumn<Item, Integer> startp;

    @FXML
    private TableColumn<Item, Integer> bidp;
    
    @FXML
    private TextField amount;
    
    @FXML
    private Label msg;

    @FXML
    void submitbid(ActionEvent event) throws SQLException {
    	int b_id = 0;
    	String uname = BidderDashboardController.temp;
    	members user = new members();
    	for(members i : user.getmemberslist()) {
    		if(i.getEmail().equals(uname)) {
    			b_id = i.getId();
    		}
    	}
    	Item item = itemtable.getSelectionModel().getSelectedItem();
    	if(item == null) {
    		msg.setText("Please Select the Item");
    		msg.setTextFill(Color.RED);
    	}else {
    		System.out.println(item.getBidprice());
    		if(amount.getText().isEmpty()) {
    			msg.setText("Please Enter Bid Amount!!");
        		msg.setTextFill(Color.RED);
    		}
    		else {
    		int price = Integer.parseInt(amount.getText());
    		System.out.println(price);
    		System.out.println(item.getItem_id());
    		item.SubmitBidPrice(item.getItem_id(), price, b_id);
    		if(Item.label.equals("Enter Valid Bid Amount!")) {
    			msg.setText(Item.label);
    			msg.setTextFill(Color.RED);
    		}else {
    			msg.setText("Bid Amount Submitted!!");
        		msg.setTextFill(Color.GREEN);
    		}
    		amount.clear();
    		}
    	}
    	viewTable();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	 	viewTable();
	}
	public void viewTable() {
		id.setCellValueFactory(new PropertyValueFactory<Item, Integer>("item_id"));
		img.setCellValueFactory(new PropertyValueFactory<Item, String>("photo"));
		name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		startp.setCellValueFactory(new PropertyValueFactory<Item, Integer>("starting_price"));
		bidp.setCellValueFactory(new PropertyValueFactory<Item, Integer>("bidprice"));
		this.itemtable.setItems(getitemlist());
		
	}
	
	public ObservableList<Item> getitemlist() {
		Item item = new Item();
		ArrayList<Item> itemlist = item.getAcceptedItemlist();
		ObservableList<Item> data = FXCollections.observableArrayList(itemlist);
		return data;
	}
}
