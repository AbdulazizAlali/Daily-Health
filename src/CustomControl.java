import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Sample custom control hosting a text field and a button.
 */


public class CustomControl implements Initializable  {
	 @FXML private TextField textField;
	    @FXML private DatePicker date;
	    @FXML private Pane chartsPane;
	    
	    private NumberAxis xAxis = new NumberAxis();
	    private  CategoryAxis yAxis = new CategoryAxis();
	  
	    private NumberAxis xAxis2 = new NumberAxis();
	    private CategoryAxis yAxis2 = new CategoryAxis();
	     
	    private LineChart <String,Number> lineChart = new LineChart <String,Number >(yAxis2, xAxis2);
	    private StackedBarChart<String,Number> stackChart = new StackedBarChart<String,Number>(yAxis, xAxis);
	     
	   
	    private controller controller = new controller();
	    private Month month = controller.retrieve();
	    private ArrayList<Day> monthList= month.getMonthList();
	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			
			
			chartsPane.getChildren().add(lineChart);
			chartsPane.getChildren().add(stackChart);
					
			date.setValue(LocalDate.now());
			changeDate();
//			showInitial();
			
			
		}
	    
	 
        
    
    
    public CustomControl() {
    	
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("custom_control.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//        
//        try {
//            fxmlLoader.load();            
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
    }
    
    public void staticsScene(MouseEvent event) throws IOException {

    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("f.fxml"));
	    cont dailyGoalsController = new cont();
	    loader.setController(dailyGoalsController);
//	    loader.setRoot(this);
	    Parent root = loader.load();
	    System.out.println("gfgf");
	    Stage mainStage =Main.getMainStage();
	    
	    mainStage.setScene(new Scene(root));
	    mainStage.show();
		
		 
	}

	public void addmealScene(MouseEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("helloo.fxml"));
	    controller dailyGoalsController = new controller();
	    loader.setController(dailyGoalsController);
	    Parent root = loader.load();
	    Stage mainStage =Main.getMainStage();
	    
	    mainStage.setScene(new Scene(root));
	    mainStage.show();
	}
	
	public void showChart(MouseEvent event){
		
		
		lineChart.getData().clear();
		stackChart.setVisible(false);
		lineChart.setVisible(true);

		
       
         lineChart.setTitle("Month Summary");
        xAxis.setLabel("Day");
        yAxis.setTickLabelRotation(90);
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Portion");
       
        if(((Label)event.getSource()).getText().equals("   Calories")){
        	lineChart.getData().add(calSeries());
        	lineChart.setTitle("Calories");
        }
        if(((Label)event.getSource()).getText().equals("  Fat")){
        	lineChart.getData().add(fatSeries());
        	lineChart.setTitle("Fat");
        }
        if(((Label)event.getSource()).getText().equals("   Protein")){
        	lineChart.getData().add(proteinSeries());
        	lineChart.setTitle("Protein");
        }
        
        	
        if(((Label)event.getSource()).getText().equals("  Beverages")){
        	lineChart.getData().add(beverSeries());
        	lineChart.setTitle("Biverages");
        	}
        if(((Label)event.getSource()).getText().equals("   Carbos.")){
        	lineChart.getData().addAll(carboSeries());
        	lineChart.setTitle("Carbohydrates");
        }
       
        lineChart.setMaxSize(1000, 800);
        lineChart.setPrefSize(970, 580);
        lineChart.setLegendVisible(true);
       

	}
	
	public Series calSeries(){
		int a = date.getValue().getDayOfMonth()+1;
		 XYChart.Series calSeries = new XYChart.Series();
		 calSeries.setName("Calories");
		for(int i=2 ;i<monthList.size()+2;i++){
        	calSeries.getData().add(new XYChart.Data(((a+i)%31+1)+"",monthList.get((a+i)%31).dailyCalory()));
		}
		return calSeries;
	}
	
	public Series fatSeries(){
		int a = date.getValue().getDayOfMonth()+1;
		 XYChart.Series fatSeries = new XYChart.Series();
		 fatSeries.setName("Fat");
		for(int i=2 ;i<monthList.size()+2;i++){
			fatSeries.getData().add(new XYChart.Data(((a+i)%31+1)+"",monthList.get((a+i)%31).dailyFats()));
		}
		return fatSeries;
	}
	
	public Series beverSeries(){
		int a = date.getValue().getDayOfMonth()+1;
		 XYChart.Series beverSeries = new XYChart.Series();
		 beverSeries.setName("Beverages");
		for(int i=2 ;i<monthList.size()+2;i++){
			beverSeries.getData().add(new XYChart.Data(((a+i)%31+1)+"",monthList.get((a+i)%31).dailyBeverages()));
		}
		return beverSeries;
	}
	
	public Series carboSeries(){
		int a = date.getValue().getDayOfMonth()+1;
		 XYChart.Series carboSeries = new XYChart.Series();
		 carboSeries.setName("Carbohydrates");
		for(int i=2 ;i<monthList.size()+2;i++){
			carboSeries.getData().add(new XYChart.Data(((a+i)%31+1)+"",monthList.get((a+i)%31).dailyCarbo()));
		}
		return carboSeries;
	}
	
	public Series proteinSeries(){
		int a = date.getValue().getDayOfMonth()+1;
		 XYChart.Series proteinSeries = new XYChart.Series();
		 proteinSeries.setName("Protein");
		for(int i=2 ;i<monthList.size()+2;i++){
			proteinSeries.getData().add(new XYChart.Data(((a+i)%31+1)+"",monthList.get((a+i)%31).dailyProtein()));
		}
		return proteinSeries;
	}
	
	public void showInitial(){
		int a = date.getValue().getDayOfMonth()+1;
		stackChart.getData().clear();
		
		lineChart.setVisible(false);
		stackChart.setVisible(true);
		
        stackChart.setTitle("Month Summary");
        xAxis.setLabel("Portion");
        yAxis.setTickLabelRotation(90);
        yAxis.setLabel("Day");
        
        stackChart.getData().addAll(calSeries(),fatSeries(),proteinSeries(),beverSeries(),carboSeries());
        stackChart.setAlternativeRowFillVisible(false);
        stackChart.setCategoryGap(10);
        stackChart.setMaxSize(1000, 800);
        stackChart.setPrefSize(970, 580);


	}

    
    public StringConverter dateFormatter() {
    	
//		showSummary();
		StringConverter converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d - M - Y");

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				}
				return "";
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				}
				return null;
			}
		};
		return converter;
	}
    
    
    
			public void changeDate() {
				
				 
				LocalDate begin = LocalDate.now().minusDays(30);
				LocalDate end = LocalDate.now();

				Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
					@Override
					public DateCell call(DatePicker datePicker) {
						return new DateCell() {

							@Override
							public void updateItem(LocalDate item, boolean empty) {
								super.updateItem(item, empty);
								setStyle("-fx-background-color:  #75D198;");
								if (item.isBefore(begin)) {

									setDisable(true);
									setStyle("-fx-background-color: #EEEEEE;");
								}

								if (item.isAfter(end)) {
									setDisable(true);
									setStyle("-fx-background-color: #EEEEEE;");
								}

							}
						};
					}
				};
				
				date.setDayCellFactory(dayCellFactory);
				
				date.setConverter(dateFormatter());

			}

			
			
	
}
