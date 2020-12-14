package dashboards;

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

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.members;

public class ManageUserController implements Initializable{
	
    @FXML
    private TableView<members> usertable;
    
    @FXML
    private TableColumn<members, Integer> id;

    @FXML
    private TableColumn<members, String> fname;

    @FXML
    private TableColumn<members, String> lname;

    @FXML
    private TableColumn<members, String> email;

    @FXML
    private TableColumn<members, Integer> phone;
    
    @FXML
    private Label msg;
    
    @FXML
    void deleteuser(ActionEvent event) throws SQLException {
    	//System.out.println("Clicked Delete btn!!");
    	members member = usertable.getSelectionModel().getSelectedItem();
    	if(member == null) {
    		msg.setText("Please Select the Item!");
    		msg.setTextFill(Color.RED);
    	}else {
    		System.out.println(member.getEmail());
    		member.delete(member.getEmail());
    		msg.setText("Deletion Successful!!");
    		msg.setTextFill(Color.GREEN);
    		System.out.println("Deletion Successful!!");
    	}
    	viewTable();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	System.out.println("in manage users!");
    	
    	viewTable();
	}
	
	public void viewTable() {
		id.setCellValueFactory(new PropertyValueFactory<members, Integer>("id"));
    	fname.setCellValueFactory(new PropertyValueFactory<members, String>("Fname"));
    	lname.setCellValueFactory(new PropertyValueFactory<members, String>("Lname"));
    	email.setCellValueFactory(new PropertyValueFactory<members, String>("email"));
    	phone.setCellValueFactory(new PropertyValueFactory<members, Integer>("phone"));
    	this.usertable.setItems(getmembers());
	}
	
	public ObservableList<members> getmembers(){
	
		members member = new members();
		ArrayList<members> memlist = member.getmemberslist();
		ObservableList<members> data = FXCollections.observableArrayList(memlist);
	    return data;
	}

}
