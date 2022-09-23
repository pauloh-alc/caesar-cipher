package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
		
		Scene scene = new Scene(border, 550, 320);
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
	    Text output = new Text("Encrypted text"); 
	    
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
	  
	    gridPane.setVgap(5); 
	    gridPane.setHgap(5);       
	  
	    gridPane.setAlignment(Pos.CENTER); 
	   
	    gridPane.add(input, 0, 0); 
	    gridPane.add(textFieldInput, 1, 0); 
	    gridPane.add(output, 0, 1);       
	    gridPane.add(textFieldOutput, 1, 1); 
	    gridPane.add(key1, 0, 2);
	    gridPane.add(textFieldKey1, 1, 2); 
	    gridPane.add(key2, 0, 3);
	    gridPane.add(textFieldKey2, 1, 3); 
	    
	    gridPane.add(buttonEncrypt, 0, 4); 
	    gridPane.add(buttonDecrypt, 1, 4);  
	    
	    Text msgError = new Text();
	    msgError.setFill(Color.web("#ec2241"));
	    gridPane.add(msgError, 1, 5);  
	    
	    return gridPane;
	}
	
	private static void setOnActionButtonEncrypt(GridPane grid) {
		
		TextField textFieldInput = (TextField) grid.getChildren().get(1);
		TextField textFieldOutput = (TextField) grid.getChildren().get(3);
		TextField textFieldKey1 = (TextField) grid.getChildren().get(5);
		TextField textFieldKey2 = (TextField) grid.getChildren().get(7);
		Text msgError = (Text) grid.getChildren().get(10);
		Button btnEncrypt = (Button) grid.getChildren().get(8);
		
		btnEncrypt.setOnAction((ActionEvent e) -> {
		    
	    	boolean onlyOneKey = ! textFieldKey1.getText().isEmpty() && textFieldKey2.getText().isEmpty();
	    	boolean withTwoKey = ! textFieldKey1.getText().isEmpty() && ! textFieldKey2.getText().isEmpty();
	    
	    	if (onlyOneKey) {
	    		System.out.println("chama método encrypt para 1 key");
	    	}
	    	else if (withTwoKey) {
	    		System.out.println("chama método ecrypt para 2 keys");
	    	}
	    	else
	    		System.out.println("mostra mensage, de error");
		});
	}
}
