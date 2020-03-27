import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class cont {
	
public cont() {
    	
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("f.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//        
//        try {
//            fxmlLoader.load();            
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
    }

public void fill(ActionEvent event) throws IOException {
	 System.out.println("gfgf");
	FXMLLoader loader = new FXMLLoader(getClass().getResource("helloo.fxml"));
    controller dailyGoalsController = new controller();
    loader.setController(dailyGoalsController);
    Parent root = loader.load();
    System.out.println("gfgf");
    Stage mainStage =Main.getMainStage();
    
    mainStage.setScene(new Scene(root));
    mainStage.show();
}
public void fil(ActionEvent event) throws IOException {
	 System.out.println("gfgf");
	FXMLLoader loader = new FXMLLoader(getClass().getResource("custom_control.fxml"));
	CustomControl dailyGoalsController = new CustomControl();
   loader.setController(dailyGoalsController);
   Parent root = loader.load();
   System.out.println("gfgf");
   Stage mainStage =Main.getMainStage();
   
   mainStage.setScene(new Scene(root));
   mainStage.show();
}

}
