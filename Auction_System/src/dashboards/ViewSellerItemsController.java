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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class ViewSellerItemsController implements Initializable {
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
    private TableColumn<Item, Integer> bidid;

    @FXML
    private TableColumn<Item, Integer> bidp;

    @FXML
    private TableColumn<Item, Integer> stat;
    
    @FXML
    private Label msg;
    
    public static String winner, itemname;
    public static int amt;

    @FXML
    void refresh(ActionEvent event) {
    	viewTable();
    }
	public void viewTable() {
		id.setCellValueFactory(new PropertyValueFactory<Item, Integer>("item_id"));
		img.setCellValueFactory(new PropertyValueFactory<Item, String>("photo"));
		name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		startp.setCellValueFactory(new PropertyValueFactory<Item, Integer>("starting_price"));
		bidid.setCellValueFactory(new PropertyValueFactory<Item, Integer>("bidder_id"));
		bidp.setCellValueFactory(new PropertyValueFactory<Item, Integer>("bidprice"));
		stat.setCellValueFactory(new PropertyValueFactory<Item, Integer>("status"));
		this.itemtable.setItems(getitemlist());
		
	}
	
	public ObservableList<Item> getitemlist() {
		int b_id = 0;
		String uname = SellerDashboardController.seller_temp;
    	members user = new members();
    	for(members i : user.getmemberslist()) {
    		if(i.getEmail().equals(uname)) {
    			b_id = i.getId();
    		}
    	}
		Item item = new Item();
		ArrayList<Item> itemlist = item.getsellerItemlist(b_id);
		ObservableList<Item> data = FXCollections.observableArrayList(itemlist);
		return data;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		viewTable();
		
	}
    @FXML
    void endbidding(ActionEvent event) throws SQLException {
    	Item item = itemtable.getSelectionModel().getSelectedItem();
    	if(item == null) {
    		msg.setText("Please Select the Item!!");
    		msg.setTextFill(Color.RED);
    	}else {
    		int bidderid = item.getBidder_id();
    		int bidamount = item.getBidprice();
    		members member = new members();
    		for(members i : member.getmemberslist()) {
    			if(i.getId() == bidderid) {
    				winner = i.getFname();
    				itemname = item.getName();
    				amt = bidamount;
    				System.out.println("The Winner is " + i.getFname() + " And Bid Amount is " + bidamount);
    			}
    		}
            item.ChangeStatus(-1, item.getItem_id());
            msg.setText("Bidding for Selected Item ended");
            msg.setTextFill(Color.ORANGE);
        	viewTable();
    	}
    }
}
