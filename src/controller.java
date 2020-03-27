
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import com.opencsv.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class controller implements Initializable {

	private ToggleGroup group;

	private ArrayList<Day> monthList = new ArrayList<>(31);
	private Month month = new Month(monthList);
	@FXML
	Button addButton;
	@FXML
	private ToggleButton BreakfastButton;
	@FXML
	private ToggleButton LaunchButton;
	@FXML
	private ToggleButton dinnerButton;
	@FXML
	private Pane radioGroupPane;
	@FXML
	private DatePicker date;
	@FXML
	private VBox breakfast;
	@FXML
	private VBox launch;
	@FXML
	private VBox dinner;
	@FXML
	private TextField textField;
	@FXML
	private ListView breakfastList;
	@FXML
	private ListView LunchList;
	@FXML
	private ListView dinnerList;
	TextField proteinText = new TextField("500");
	@FXML
	private Pane barChartPane;
	@FXML
	private Pane pieChartPane;
	@FXML
	private TableView<Edible> searchTable;
	@FXML
	private TableColumn<Edible, String> name;
	@FXML
	private TableColumn<Edible, Double> portion;
	@FXML
	private TableColumn<Edible, Integer> carbo;
	@FXML
	private TableColumn<Edible, Integer> cal;
	@FXML
	private TableColumn<Edible, Integer> protein;
	@FXML
	private TableColumn<Edible, String> unit;
	@FXML
	private TableColumn<Edible, Integer> fat;
	@FXML
	private TextField text;
	@FXML
	private CheckBox beveargesFilter = new CheckBox();

	@FXML
	private CheckBox foodFilter = new CheckBox();

	@FXML
	private ProgressBar proteinBar = new ProgressBar();

	@FXML
	private ProgressBar fatBar = new ProgressBar();

	@FXML
	private ProgressBar beverBar = new ProgressBar();
	@FXML
	private ProgressBar calBar = new ProgressBar();

	@FXML
	private ProgressBar carboBar = new ProgressBar();

	@FXML
	private Pane tempPane;

	private int proteinGoal = 150;
	private int fatGoal = 50;
	private int carboGoal = 2000;
	private int beverGoal = 300;
	private int calGoal = 3000;

	private String searchTerm;

	private ArrayList<Edible> brkfst1;
	private ArrayList<Edible> lunch1;
	private ArrayList<Edible> dinner1;

	public controller() {
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("helloo.fxml"));
//		fxmlLoader.setRoot(this);
//		fxmlLoader.setController(this);
//
//		try {
//			fxmlLoader.load();
//		} catch (IOException exception) {
//
//		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		monthList = retrieve().getMonthList();

		try {

			date.setValue(LocalDate.now());
			setButton();
			show();
			changeDate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setButton() {
		group = new ToggleGroup();

		BreakfastButton.setToggleGroup(group);
		LaunchButton.setToggleGroup(group);
		dinnerButton.setToggleGroup(group);

		BreakfastButton.setSelected(true);

		BreakfastButton.setStyle(
				"-fx-background-color :  #75D198; -fx-border-color : gray ;  -fx-border-width : 2 ; -fx-font-weight : bold ;");

		BreakfastButton.setTextFill(Color.WHITE);
		BreakfastButton.setFont(Font.font(13));
	}

	public void selectedButton(ActionEvent event) {
		//
		BreakfastButton.setStyle(null);
		LaunchButton.setStyle(null);
		dinnerButton.setStyle(null);

		BreakfastButton.setTextFill(Color.BLACK);
		LaunchButton.setTextFill(Color.BLACK);
		dinnerButton.setTextFill(Color.BLACK);

		((ToggleButton) event.getSource()).setStyle(
				"-fx-background-color :  #75D198; -fx-border-color : gray ;  -fx-border-width : 2 ; -fx-font-weight : bold ;");
		((ToggleButton) event.getSource()).setTextFill(Color.WHITE);
		((ToggleButton) event.getSource()).setFont(Font.font(13));

	}

	public ObservableList<Edible> edibleFilter() {

		FilteredList<Edible> filteredData = new FilteredList<>(getResults(), s -> false);

		if (beveargesFilter.isSelected() && foodFilter.isSelected()) {
			filteredData.setPredicate(a -> a.getUnit().equals("ml") || a.getUnit().equals("g"));
		}

		else if (beveargesFilter.isSelected()) {
			filteredData.setPredicate(a -> a.getUnit().equals("ml"));
		}

		else if (foodFilter.isSelected()) {
			filteredData.setPredicate(a -> a.getUnit().equals("g"));
		}

		ObservableList<Edible> results = FXCollections.observableArrayList(filteredData);
		int a = date.getValue().getDayOfMonth() - 1;

		return results;
	}

	public void show() {

		name.setCellValueFactory(new PropertyValueFactory<Edible, String>("food"));
		portion.setCellValueFactory(new PropertyValueFactory<Edible, Double>("portion"));
		unit.setCellValueFactory(new PropertyValueFactory<Edible, String>("unit"));
		cal.setCellValueFactory(new PropertyValueFactory<Edible, Integer>("cal"));
		fat.setCellValueFactory(new PropertyValueFactory<Edible, Integer>("fat"));
		carbo.setCellValueFactory(new PropertyValueFactory<Edible, Integer>("carbo"));
		protein.setCellValueFactory(new PropertyValueFactory<Edible, Integer>("protein"));
		searchTable.setItems(edibleFilter());

	}

	public Edible search(String[] nextLine) {

		Edible temp = (new Edible(nextLine[0], Double.parseDouble(nextLine[1]), nextLine[2],
				Integer.parseInt(nextLine[3]), Integer.parseInt(nextLine[4]), Integer.parseInt(nextLine[5]),
				Integer.parseInt(nextLine[6])));

		return temp;
	}

	public ObservableList<Edible> getResults() {

		ObservableList<Edible> results = FXCollections.observableArrayList();

		try {

			@SuppressWarnings("deprecation")
			FileReader temp = new FileReader("nutritionvalues-data.csv");
			CSVReader reader = new CSVReader(temp, ',', '"');

			String[] nextLine = reader.readNext();
			nextLine = reader.readNext();

			searchTerm = text.getText();
			if (searchTerm.equals(""))
				while ((nextLine = reader.readNext()) != null)
					results.add(search(nextLine));

			else
				while ((nextLine = reader.readNext()) != null)
					if (nextLine[0].contains(searchTerm))
						results.add(search(nextLine));
		} catch (Exception EOF) {

		}

		return results;

	}

	@FXML
	public void add(ActionEvent event) throws IOException {

		int a = date.getValue().getDayOfMonth() - 1;
		try {
			Edible temp = searchTable.getSelectionModel().getSelectedItem();

			if (((ToggleButton) (group.getSelectedToggle())).getText().equals("Breakfast")) {
				monthList.get(a).getBrkfst().add(temp);
			} else if (((ToggleButton) (group.getSelectedToggle())).getText().equals("Lunch")) {
				monthList.get(a).getLunch().add(temp);

			} else if (((ToggleButton) (group.getSelectedToggle())).getText().equals("Dinner")) {
				monthList.get(a).getDinner().add(temp);
			}

			showSummary();

		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);

			alert.setTitle("WARNING !");
			alert.setHeaderText("No Meal Has Been Chosen !");
			alert.show();

			addmealScene();
		}

	}

	public void completedGoal(ProgressBar progressBar) {
		if (progressBar.getProgress() >= 1)
			progressBar.setStyle("-fx-accent: #FFDF00 ;");
	}

	public void showSummary() {

		int a = date.getValue().getDayOfMonth() - 1;

		ObservableList<String> breakfastAraayList = FXCollections.observableArrayList();
		ObservableList<String> lunchAraayList = FXCollections.observableArrayList();
		ObservableList<String> dinnerAraayList = FXCollections.observableArrayList();

		try {

			for (Edible y : monthList.get(a).getBrkfst()) {
				breakfastAraayList.add(y.getFood());
			}
			for (Edible y : monthList.get(a).getLunch()) {
				lunchAraayList.add(y.getFood());
			}
			for (Edible y : monthList.get(a).getDinner()) {
				dinnerAraayList.add(y.getFood());
			}
		} catch (Exception e) {

		}

		SortedList<String> sortedBreakfast = new SortedList(breakfastAraayList);
		SortedList<String> sortedLunch = new SortedList(lunchAraayList);
		SortedList<String> sortedDinner = new SortedList(dinnerAraayList);

		sortedBreakfast.setComparator(new Comparator<String>() {

			@Override
			public int compare(String arg0, String arg1) {
				return arg0.compareToIgnoreCase(arg1);
			}
		});

		sortedLunch.setComparator(new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.compareToIgnoreCase(arg1);
			}
		});

		sortedDinner.setComparator(new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.compareToIgnoreCase(arg1);
			}
		});

		breakfastList.setItems(sortedBreakfast);
		LunchList.setItems(sortedLunch);
		dinnerList.setItems(sortedDinner);

		showProgress();

	}

	public void showProgress() {

		int a = date.getValue().getDayOfMonth() - 1;

		fatBar.setProgress(monthList.get(a).dailyFats() / (double) fatGoal);
		calBar.setProgress(monthList.get(a).dailyCalory() / (double) calGoal);
		carboBar.setProgress(monthList.get(a).dailyCarbo() / (double) carboGoal);
		beverBar.setProgress(monthList.get(a).dailyBeverages() / (double) beverGoal);
		proteinBar.setProgress(monthList.get(a).dailyProtein() / (double) proteinGoal);

		fatBar.setStyle("-fx-accent: blue ;");
		proteinBar.setStyle("-fx-accent: blue;");
		carboBar.setStyle("-fx-accent: blue;");
		beverBar.setStyle("-fx-accent: blue;");
		calBar.setStyle("-fx-accent: blue;");

		completedGoal(fatBar);
		completedGoal(proteinBar);
		completedGoal(carboBar);
		completedGoal(beverBar);
		completedGoal(calBar);

	}

	public void write() {

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("log.dat"));
			out.writeObject(month);
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Month retrieve() {

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("log.dat"));
			month = (Month) in.readObject();

		} catch (FileNotFoundException e) {
			for (int i = 0; i < 31; i++) {
				brkfst1 = new ArrayList<Edible>();
				lunch1 = new ArrayList<Edible>();
				dinner1 = new ArrayList<Edible>();
				Day b = new Day(brkfst1, lunch1, dinner1);
				monthList.add(i, b);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return month;

	}

	public void staticsScene(MouseEvent event)throws IOException {
		write();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("custom_control.fxml"));
	    CustomControl dailyGoalsController = new CustomControl();
	    loader.setController(dailyGoalsController);
//	    loader.setRoot(this);
	    Parent root = loader.load();
	    System.out.println("gfgf");
	    Stage mainStage =Main.getMainStage();
	    
	    mainStage.setScene(new Scene(root));
	    mainStage.show();
	}

	public void addmealScene()throws IOException {

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

	public void mouseEntered() {
		addButton.setStyle(" -fx-background-radius: 50; -fx-background-color: #75D198; -fx-cursor: hand;");
		addButton.setTextFill(Color.WHITE);
	}

	public void mouseExited() {
		addButton.setStyle(
				"-fx-border-color: #75D198; -fx-border-radius: 50; -fx-border-width: 3; -fx-background-color: transparent; -fx-cursor: hand;");
		addButton.setTextFill(Color.rgb(117, 209, 152));
	}

	public StringConverter dateFormatter() {

		showSummary();

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

	public void close() {
		System.exit(0);
	}

	public void newGoals() {

		VBox window = new VBox();
		Label protein = new Label("Protein goal");
		TextField proteinText = new TextField(proteinGoal + "");
		Label fat = new Label("Fat goal");
		TextField fatText = new TextField(fatGoal + "");
		Label carbo = new Label("Carbohydrates goal");
		TextField carboText = new TextField(carboGoal + "");
		Label bever = new Label("Beverages goal");
		TextField beverText = new TextField(beverGoal + "");
		Label cal = new Label("Calories goal");
		TextField calText = new TextField(calGoal + "");

		Button enter = new Button("Enter");

		window.getChildren().add(protein);
		window.getChildren().add(proteinText);
		window.getChildren().add(fat);
		window.getChildren().add(fatText);
		window.getChildren().add(carbo);
		window.getChildren().add(carboText);
		window.getChildren().add(bever);
		window.getChildren().add(beverText);
		window.getChildren().add(cal);
		window.getChildren().add(calText);
		window.getChildren().add(enter);

		enter.setOnAction(e -> {
			int proteing = Integer.parseInt(proteinText.getText());
			proteinGoal = proteing;
			int fatg = Integer.parseInt(fatText.getText());
			fatGoal = fatg;
			int carbog = Integer.parseInt(carboText.getText());
			carboGoal = carbog;
			int beverg = Integer.parseInt(beverText.getText());
			beverGoal = beverg;
			int calg = Integer.parseInt(calText.getText());
			calGoal = calg;
			showProgress();
		});
		Stage changeGoals = new Stage();

		changeGoals.setScene(new Scene(window));
		changeGoals.setTitle("Change your daily goals");
		changeGoals.show();

	}

}