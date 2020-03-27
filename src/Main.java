import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Main extends Application {
	static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		this.primaryStage=primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("helloo.fxml"));
		controller controller = new controller();
        loader.setController(controller);
        Parent root = loader.load();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        	
	           @Override
	           public void handle(WindowEvent e) {
	        	  
	        	   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        	  
	        	    alert.setTitle("Save your work");
	        	    alert.setHeaderText("Do you want to save your changes ?");
	        	    alert.setContentText("You may lose your last work session ! ");
	        	    alert.getButtonTypes().clear();
	        	    
	        	    
	        	    Optional<ButtonType> result;
	        	   
	        	    ButtonType saveButton = new ButtonType("Save changes");
	        	    ButtonType noSaveButton = new ButtonType("Do not Save");
	        	    ButtonType cancelButton = new ButtonType("Cancel");
	        	   
	        	    alert.getButtonTypes().add(saveButton);
	        	    alert.getButtonTypes().add(noSaveButton);
	        	    alert.getButtonTypes().add(cancelButton);
	        	    
	        	    result = alert.showAndWait();
	        	    if (result.get() == saveButton) {
	        	    	
	        	    }
	        	    else if (result.get() == noSaveButton) {
//	        	    	controller.write();
	        	    }
	        	    else{
	        	    	e.consume();
	        	    	
	        	    }
			    }
			}
		);
	       
			primaryStage.setScene( new Scene(root));
			primaryStage.show();

		
			
		
	}
	public static Stage getMainStage(){
		return primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
