package dashboards;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewItemsController implements Initializable{
	
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
