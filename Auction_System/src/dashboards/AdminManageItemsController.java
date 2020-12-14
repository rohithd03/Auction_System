package dashboards;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Item;
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

public class AdminManageItemsController implements Initializable {
	
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
    private TableColumn<Item, Integer> stat;
    
    @FXML
    private Label msg;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("In manage items page!!");
		viewTable();
		
		
	}
	
	public void viewTable() {
		id.setCellValueFactory(new PropertyValueFactory<Item, Integer>("item_id"));
		img.setCellValueFactory(new PropertyValueFactory<Item, String>("photo"));
		name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		startp.setCellValueFactory(new PropertyValueFactory<Item, Integer>("starting_price"));
		stat.setCellValueFactory(new PropertyValueFactory<Item, Integer>("status"));
		this.itemtable.setItems(getitemlist());
		
	}
	
	public ObservableList<Item> getitemlist() {
		Item item = new Item();
		//ArrayList<Item> itemlist = item.getRejectedItemlist();
		ArrayList<Item> item2list = item.getPendingItemlist();
		ObservableList<Item> data = FXCollections.observableArrayList(item2list);
		return data;
	}
	
    @FXML
    void onaccept(ActionEvent event) throws SQLException {
    	Item item = itemtable.getSelectionModel().getSelectedItem();
    	if(item == null) {
    		msg.setText("Please Select an Item!");
    		msg.setTextFill(Color.RED);
    	}else {
    		item.ChangeStatus(1, item.getItem_id());
    		msg.setText("Item Accepted!!");
    		msg.setTextFill(Color.GREEN);
    		System.out.println("Status changed!!");
    	}
    	viewTable();
    }

    @FXML
    void onreject(ActionEvent event) throws SQLException {
    	Item item = itemtable.getSelectionModel().getSelectedItem();
    	if(item == null) {
    		msg.setText("Please Select an Item!");
    		msg.setTextFill(Color.RED);
    	}else {
        	item.ChangeStatus(-1, item.getItem_id());
        	msg.setText("Item Rejected!!");
        	msg.setTextFill(Color.RED);
        	System.out.println("Status changed!!");
    	}
    	viewTable();
    }
	
}
