package application;

import entities.CaesarCipher;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
	
	private static Text errorMsg = new Text();
	private static Text successMsg = new Text();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GridPane grid = addGridPane();
		HBox hbox = addHBox();
		
		BorderPane border = new BorderPane();
		border.setTop(hbox);	
		border.setCenter(grid);
		
		setOnActionButtonEncrypt(grid);  
		
		Scene scene = new Scene(border, 550, 390);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Caesar Cipher");
		primaryStage.show();
	}
	
	private HBox addHBox () {
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(20, 14, 20, 14));
		hbox.setStyle("-fx-background-color: #000000;");
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(100);
		
		Text textCaesarCipher = new Text("Caesar Cipher");
		textCaesarCipher.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 28));;
		textCaesarCipher.setFill(Color.web("#ffffff"));
	
		Image img = new Image("./icons/icon-caesar-cipher.png");
		ImageView imgView = new ImageView(img);
		imgView.setPreserveRatio(true);
		imgView.setFitHeight(80);
			  	   
		hbox.getChildren().addAll(textCaesarCipher, imgView);
		return hbox;
	}
	
	private GridPane addGridPane () {

	    Text input = new Text("Input text");       
	    Text output = new Text("Output text"); 
	  
	    Text key1 = new Text("Key 1");
	    Text key2 = new Text("Key 2");
	    
	    TextField textFieldInput = new TextField();           
	    TextField textFieldOutput = new TextField();  
	    TextField textFieldKey1 = new TextField();  
	    TextField textFieldKey2 = new TextField(); 
	    
	    textFieldInput.setMinWidth(400);
	    textFieldKey1.setMaxWidth(40);
	    textFieldKey2.setMaxWidth(40);
	    
	    Button buttonEncrypt = new Button("Encrypt"); 
	    Button buttonDecrypt = new Button("Decrypt");  
	  
	    GridPane gridPane = new GridPane();    
	 
	    gridPane.setMinSize(400, 210); 
	    gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	  
	    gridPane.setVgap(15); 
	    gridPane.setHgap(15);       
	  
	    gridPane.setAlignment(Pos.CENTER); 
	   
	    gridPane.add(input, 0, 0); 
	    gridPane.add(textFieldInput, 1, 0); 
	    gridPane.add(output, 0, 1);       
	    gridPane.add(textFieldOutput, 1, 1); 
	    gridPane.add(key1, 0, 2);
	    gridPane.add(textFieldKey1, 1, 2); 
	    gridPane.add(key2, 0, 3);
	    gridPane.add(textFieldKey2, 1, 3); 
	    
	    gridPane.add(buttonEncrypt, 0, 5); 
	    gridPane.add(buttonDecrypt, 1, 5); 
	    
	    errorMsg.setFill(Color.web("#ec2241"));
	    gridPane.add(errorMsg, 1, 6); 
	    
	    successMsg.setFill(Color.web("#39e225"));
	    gridPane.add(successMsg, 1, 6);
	    
	    final ToggleGroup group = new ToggleGroup();
	    RadioButton key1RadioBox = new RadioButton("One-Key"); 
        RadioButton key2RadioBox = new RadioButton("Two-keys"); 
        key1RadioBox.setToggleGroup(group);
        key2RadioBox.setToggleGroup(group);
        key1RadioBox.setSelected(true);
	    gridPane.add(key1RadioBox, 0, 4);
	    gridPane.add(key2RadioBox, 1, 4);
	    
	    return gridPane;
	}
	
	private static void setOnActionButtonEncrypt(GridPane grid) {
		
		TextField textFieldInput = (TextField) grid.getChildren().get(1);
		TextField textFieldOutput = (TextField) grid.getChildren().get(3);
		TextField textFieldKey1 = (TextField) grid.getChildren().get(5);
		TextField textFieldKey2 = (TextField) grid.getChildren().get(7);
		RadioButton radioBoxKey1 = (RadioButton) grid.getChildren().get(12);
		RadioButton radioBoxKey2 = (RadioButton) grid.getChildren().get(13);
		Button btnEncrypt = (Button) grid.getChildren().get(8);
		
		btnEncrypt.setOnAction((ActionEvent e) -> {
		    
			boolean oneKey = radioBoxKey1.isSelected();
			boolean twoKeys = radioBoxKey2.isSelected();
			
			boolean onlySecondEmptyKey = ! textFieldKey1.getText().isEmpty() && textFieldKey2.getText().isEmpty();
			boolean onlyFirstEmptyKey = textFieldKey1.getText().isEmpty() && ! textFieldKey2.getText().isEmpty();
			boolean twoNonEmptyFields = ! textFieldKey1.getText().isEmpty() && ! textFieldKey2.getText().isEmpty(); 
			
	    	boolean onlyOneKey = (onlyFirstEmptyKey  || onlySecondEmptyKey ||  twoNonEmptyFields) && oneKey; 
	    	boolean withTwoKey = twoNonEmptyFields && twoKeys;
	    	boolean isEmptyEntry = textFieldInput.getText().isEmpty(); 
	    	
	    	
	    	if (isEmptyEntry) {
	    		printErrorMessage("Hey! input cannot be empty!");
	    		return;
	    	}
	    	
	    	if (onlyOneKey) {
	    		try {
	    			if (!onlyFirstEmptyKey)
	    				encrypt(textFieldInput, textFieldOutput, textFieldKey1);
	    			else
	    				encrypt(textFieldInput, textFieldOutput, textFieldKey2);
	    			printSuccessMessage("Successfully encrypted!");
	    		} catch (NumberFormatException error) {
	    			printErrorMessage("key numbers must be integers. " + error.getMessage());
	    		}
	    	}
	    	else if (withTwoKey) {
	    		try {
		    		encrypt(textFieldInput, textFieldOutput, textFieldKey1, textFieldKey2);
	    			printSuccessMessage("Successfully encrypted with two keys!");
	    		} catch (NumberFormatException error) {
	    			printErrorMessage("key numbers must be integers. " + error.getMessage());
	    		}
	    	}
	    	else {
	    		printErrorMessage("Hey! key filders are empty!");
	    	}
		});
	}
	
	private static void encrypt (TextField textInput, TextField textOutput, TextField textKey1) 
			throws NumberFormatException {
		
		String input = textInput.getText();
			
		int key1 = Integer.parseInt(textKey1.getText());
	
		CaesarCipher cipher = new CaesarCipher(key1);
		String output = cipher.encrypt(input);
		textOutput.setText(output);
	}
	
	private static void encrypt (TextField textInput, TextField textOutput, TextField textKey1, TextField textKey2) 
			throws NumberFormatException {
		
		String input = textInput.getText();
			
		int key1 = Integer.parseInt(textKey1.getText());
		int key2 = Integer.parseInt(textKey2.getText());
	
		CaesarCipher cipher = new CaesarCipher(key1, key2);
		String output = cipher.encryptTwoKeys(input);
		textOutput.setText(output);
	}
	
	private static void printSuccessMessage(String msg) {
		successMsg.setText(msg);
		errorMsg.setText(null);
	}
	
	private static void printErrorMessage(String msg) {
		errorMsg.setText(msg);
		successMsg.setText(null);
	}
	
}
