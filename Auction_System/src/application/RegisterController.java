package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;

public class RegisterController implements Initializable {
	
	ObservableList<String> typeofuser = FXCollections.observableArrayList("Bidder", "Seller");
	
	@FXML
	private TextField fname;
	
	@FXML
	private TextField lname;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField phone;
	
    @FXML
    private ComboBox<String> usertype;
	
	@FXML
	private PasswordField pass;
	
	@FXML
	private PasswordField repass;
	
	private boolean check_all = false;
	private boolean check_email = false;
	private boolean check_pass = false;
	private int id = 0;
	private int type;
	
    @FXML
    void login(ActionEvent event) throws IOException {
    	
    	ToLoginPage(event);

    }
	
	private void ToLoginPage(ActionEvent event) throws IOException {
    	Parent userLoginPage = FXMLLoader.load(getClass().getResource("Sample.fxml"));
    	Scene userLoginScene = new Scene(userLoginPage);
    	
    	userLoginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	
    	window.setScene(userLoginScene);
    	window.show();
	}
	
    @FXML
    void register(ActionEvent event) {
    	if(!fname.getText().isEmpty()) {
    		this.check_all = true;
    	}else {
    		this.check_all = false;
    		System.out.println("Enter First Name!");
    	}
    	if(!lname.getText().isEmpty()) {
    		this.check_all = true;
    	}else {
    		this.check_all = false;
    		System.out.println("Enter Last Name!");
    	}
    	if(!email.getText().isEmpty()) {
    		this.check_all = true;
    		validate_email();
    	}
    	if(!pass.getText().isEmpty()) {
    		this.check_all = true;
    		validate_pass();
    	}
    	if(!phone.getText().isEmpty()) {
    		this.check_all = true;
    	}else {
    		this.check_all = false;
    		System.out.println("Enter Phone Number!");
    	}
    	if(usertype.getValue() != null) {
    		this.check_all = true;
    		if(usertype.getValue().equals("Bidder")) {
    			type = 1;
    		}
    		if(usertype.getValue().equals("Seller")) {
    			type = 0;
    		}
    	}else {
    		this.check_all = false;
    		System.out.println("Select Usertype!");
    	}
    	
    	if(this.check_all && this.check_email && this.check_pass) {
    		System.out.println("here");
    		members user = new members();
    		int num = Integer.parseInt(phone.getText());
    		
    		user.Register(id, fname.getText(), lname.getText(), email.getText(), pass.getText(), num, type);
    		
    		try {
				ToLoginPage(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		usertype.setItems(typeofuser);
		
	}
	
	private void validate_pass() {
		if(pass.getText().equals(repass.getText())) {
			this.check_pass = true;
		}else {
			this.check_pass = false;
		}
	}
	
	private void validate_email() {
		Pattern pattern_mail;
		Matcher matcher;
		
		String mail_pattern = "^[A-Za-z0-9]+@(.+)$";
		
		pattern_mail = Pattern.compile(mail_pattern);
		matcher = pattern_mail.matcher(email.getText());
		
		if(matcher.matches()) {
			this.check_email = true;
		}else {
			this.check_email = false;
		}
	}
	
}
