package project;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is used for creating a GUI to allow the user to search through the
 * records using multiple filters and seeing the records passing those filters
 * in a table.
 * @author vasug Vasu Gupta
 */
public class Project_Viewer {
    private TabPane tabPane;
    private Tab tableViewTab;
    private Tab visualizationTab;
    private VBox tabPaneVBox; // Stores the Tab Pane
            
    private TableView table;
    private ObservableList<Record> data;

    private VBox visualizationSearchVBox; // LHS
    private VBox tableViewSearchVBox; // LHS
    private VBox tableVBox;  // RHS
    
    //== Table Tab Search Box components=====
    private Label statsLabelTab; // Stores the stats of all properties
    private HBox buttonsTab;
    private TextField accNumFieldTab;
    private TextField addressFieldTab;
    private TextField neighFieldTab;
    private ChoiceBox<String> assessmentClassChoiceBoxTab;
    private Button searchButtonTab;
    private Button clearButtonTab;

    private HBox buttonsVis;
    private TextField accNumFieldVis;
    private TextField addressFieldVis;
    private TextField neighFieldVis;
    private ChoiceBox<String> assessmentClassChoiceBoxVis;
    private Button searchButtonVis;
    private Button clearButtonVis;    
    //======================================

    // For Visualization Tab================
    private Button pieChartButton;
    private Button barGraphButton;
    private Button scatterPlotButton;
    
    private VBox graphsVBox; // Stores graph or the Invalid label
        
    private VBox visualizationVBox;
            
    private HBox tableViewTabScreenHBox; // For holding LHS and RHS
    private HBox visualizationTabHBox; // For holding LHS and RHS
    
    private Label nothingToDisplayLabel;
    
    //==Pie Chart
    private PieChart pieChart;
    private BorderPane pieChartBorderPane;
    //== Bar Chart
    private BarChart barChart;
    private BorderPane barGraphBorderPane;
    //== Scatter Chart
    private ScatterChart scatterChart;
    private BorderPane scatterPlotBorderPane;
    //======================================
    
    private Scene scene;
    private Stage primaryStage;
    
    /**
     * Constructor for the class. Sets the primary stage for the JavaFx Application
     * Sets up the GUI with the left hand side with search fields and
     * the right hand side with the an empty table with columns defined
     * @param primaryStage 
     */
    public Project_Viewer(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setUpGUI();
    }
    /**
     * Sets the stats label on the left hand side with the String passed as param
     * @param result 
     */
    public void setStatsLabel(String result){
        statsLabelTab.setText(result);
    }
    /**
     * Returns the assessment class selected in the drop down menu
     * @return Selected class in the drop down. Null if nothing is selected.
     */
    public String getAssessmentClass(){
        return assessmentClassChoiceBoxTab.getValue();
    }
    public String getAssessmentClassVis(){
        return assessmentClassChoiceBoxVis.getValue();
    }
    /**
     * Sets the assessment classes in the drop down as the values from the
     * dataset.
     * @param assClasses List of all the assessment classes in the dataset
     */
    public void setAssessmentClasses(List<String> assClasses){
        assessmentClassChoiceBoxTab.getItems().addAll(assClasses);
        assessmentClassChoiceBoxVis.getItems().addAll(assClasses);
    }
    /**
     * Gets the text in the neighbourhood text field on LHS
     * @return String storing the text in neighbourhood text field
     */
    public String getNeighbourhood(){
        return neighFieldTab.getText().trim();        
    }    
    public String getNeighbourhoodVis(){
        return neighFieldVis.getText().trim();        
    }    
    /**
     * Gets the text in the address text field on LHS
     * @return String storing the text in address text field
     */
    public String getAddress(){
        return addressFieldTab.getText().trim();        
    }
    public String getAddressVis(){
        return addressFieldVis.getText().trim();        
    }    
    /**
     * Gets the text in the account number text field on LHS
     * @return String storing the text in account number text field
     */
    public String getAccNum(){
        return accNumFieldTab.getText().trim();
    }
    public String getAccNumVis(){
        return accNumFieldVis.getText().trim();
    }
    /**
     * Getting the search button
     * @return Search Button
     */
    public Button getTableSearchButton(){
        return searchButtonTab;
    }
    /**
     * Getting the button for Pie Chart in visualization tab
     * @return Pie chart button
     */
    public Button getPieChartButton() {
        return pieChartButton;
    }
    /**
     * Get the button for bar graph for in visualization tab
     * @return bar graph button
     */
    public Button getBarGraphButton() {
        return barGraphButton;
    }
    /**
     * Get the button for scatter plot for in visualization tab
     * @return 
     */
    public Button getScatterPlotButton() {
        return scatterPlotButton;
    }
    /**
     * Get search button on the left hand side for the visualization tab
     * @return Search button
     */
    public Button getVisualSearchButton(){
        return searchButtonVis;
    }
    /**
     * Get clear button on the left hand side for the visualization tab
     * @return Clear button
     */
    public Button getVisualClearButton(){
        return clearButtonVis;
    }
    /**
     * Getting the clear button
     * @return Clear Button
     */
    public Button getTableClearButton(){
        return clearButtonTab;
    }
    /**
     * Sets all the fields to empty values
     */
    public void resetAllVisFields(){
        accNumFieldVis.setText("");
        addressFieldVis.setText("");
        neighFieldVis.setText("");
        assessmentClassChoiceBoxVis.setValue(null);
    }
    /**
     * Sets all the fields to empty values
     */
    public void resetAllTabFields(){
        accNumFieldTab.setText("");
        addressFieldTab.setText("");
        neighFieldTab.setText("");
        assessmentClassChoiceBoxTab.setValue(null);
    }
    /**
     * Update the table on the RHS in the GUI with the value in records
     * @param records Value(s) to be filled in the table
     */
    public void updateTable(List<Record> records){
        data = FXCollections.observableArrayList(records);
        table.setItems(data);
    }
    /**
     * Creating the pie chart without any values
     */
    public void createPieChart() {
        pieChartBorderPane = new BorderPane();
        pieChart = new PieChart();
        pieChart.setTitle("Representation of the Range of Assessed Values");
        pieChart.setPrefHeight(750);
        pieChart.setPrefWidth(750);
        pieChartBorderPane.setCenter(pieChart);
    }
    /**
     * Update the pie chart with the values passed
     */
    public void updatePieChart(ObservableList<PieChart.Data> data){
        if (data == null) return;        
        graphsVBox.getChildren().clear(); // Clearing the graph or label
        pieChart.getData().clear();
        pieChart.getData().addAll(data);
        graphsVBox.getChildren().add(pieChartBorderPane);
    }
    /**
     * Creates the bar graph without adding any values to it
     */
    public void createBarGraph(){
        barGraphBorderPane = new BorderPane();
        // X- Axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Wards");
        // Y-Axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Assessment Values");
        // Create Bar chart
        barChart = new BarChart(xAxis, yAxis);
        barChart.setPrefHeight(750);
        barChart.setPrefWidth(750);
        barChart.setTitle("Average assessment value of properties per ward");
        // Adding the data to the Bar Chart
        barGraphBorderPane.setCenter(barChart);
    }
    /**
     * Updates the bar graph based on the data given
     * @param data 
     */
    public void updateBarGraph(XYChart.Series<String, Number> data){
        if (data == null) return;
        graphsVBox.getChildren().clear(); // Clearing the graph or label
        barChart.getData().clear(); //Clear values in the bar chart
        barChart.getData().add(data); // Add values to the bar chart
        graphsVBox.getChildren().add(barGraphBorderPane);
    }
    /**
     * Creates the scatter plot without adding any values to it
     */
    public void createScatterPlot(){
        scatterPlotBorderPane = new BorderPane();        
        // X- Axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setTickLabelsVisible(false);
        xAxis.setLabel("Neighbourhoods");
        // Y-Axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Assessment Values");
        // Create Scatter Plot chart
        scatterChart = new ScatterChart(xAxis, yAxis);
        scatterChart.setPrefHeight(750);
        scatterChart.setPrefWidth(750); 
        scatterChart.setTitle("Average assessment value of properties per neighbourhood");
        scatterPlotBorderPane.setCenter(scatterChart);
    }
    /**
     * Updates the scatter plot with the values passed to it
     */
    public void updateScatterPlot(XYChart.Series<String, Number> data){
        if (data == null) return;
        graphsVBox.getChildren().clear();
        scatterChart.getData().clear();
        scatterChart.getData().add(data);
        graphsVBox.getChildren().add(scatterPlotBorderPane);
    }
    /**
     * Creating the label for the Visualization tab when there is no graph available
     */
    private void createNothingToDisplayLabel(){
        // Label for Address
        nothingToDisplayLabel = new Label("Nothing to Display!");
        nothingToDisplayLabel.setFont(new Font("Ariel",35));   
    }
    /**
     * Displaying the label by getting rid of the graph in the VBox
     */
    public void displayNothingToDisplayLabel(){
        graphsVBox.getChildren().clear();
        graphsVBox.getChildren().add(nothingToDisplayLabel);        
    }

    /**
     * Setting up Search button and Clear button side by side on the Left Hand Side of the GUI
     * @param buttons 
     */
    public void setUpButtonsTable(HBox buttons){
        VBox searchButtonVBox = new VBox(10); // set spacing between these nodes to 10

        searchButtonTab = new Button();
        searchButtonTab.setText("Search");
        searchButtonVBox.setAlignment(Pos.CENTER_LEFT);
        searchButtonVBox.getChildren().addAll(searchButtonTab);
        
        VBox clearButtonVBox = new VBox(10); // set spacing between these nodes to 10

        clearButtonTab = new Button();
        clearButtonTab.setText("Clear");
        clearButtonVBox.setAlignment(Pos.CENTER_LEFT);
        clearButtonVBox.getChildren().addAll(clearButtonTab);

        buttons.setAlignment(Pos.BASELINE_LEFT);
        buttons.getChildren().addAll(searchButtonVBox, clearButtonVBox);        
    }
    /**
     * Setting up Search button and Clear button side by side on the Left Hand Side of the GUI
     * @param buttons 
     */
    public void setUpButtonsVisual(HBox buttons){
        VBox searchButtonVBox = new VBox(10); // set spacing between these nodes to 10

        searchButtonVis = new Button();
        searchButtonVis.setText("Search");
        searchButtonVBox.setAlignment(Pos.CENTER_LEFT);
        searchButtonVBox.getChildren().addAll(searchButtonVis);
        
        VBox clearButtonVBox = new VBox(10); // set spacing between these nodes to 10

        clearButtonVis = new Button();
        clearButtonVis.setText("Clear");
        clearButtonVBox.setAlignment(Pos.CENTER_LEFT);
        clearButtonVBox.getChildren().addAll(clearButtonVis);

        buttons.setAlignment(Pos.BASELINE_LEFT);
        buttons.getChildren().addAll(searchButtonVBox, clearButtonVBox);        
    }

    /**
     * Sets up the LHS of the GUI by creating search fields, buttons and labels.
     */
    public void setUpSearchBoxTable(){
        tableViewSearchVBox = new VBox(10);    // set spacing between these nodes to 10
        tableViewSearchVBox.setPadding(new Insets(0, 0, 0, 10)); // Add padding to the left
        
        // Label for search Vbox
        Label assessmentLabelTab = new Label("Find Property Assessment");
        assessmentLabelTab.setFont(new Font("Ariel",20));
    
        // Label for Account Number
        Label accNumLabelTab = new Label("Account Number");
        accNumLabelTab.setFont(new Font("Ariel",15));

        // Text field for account number user input
        accNumFieldTab = new TextField();
        
        // Label for Address
        Label addressLabelTab = new Label("Address");
        addressLabelTab.setFont(new Font("Ariel",15));
        // Text field for address user input
        addressFieldTab = new TextField();

        // Label for Neighbourhood name
        Label neighLabelTab = new Label("Neighbourhood");
        neighLabelTab.setFont(new Font("Ariel",15));
        
        // Text field for neighbourhood user input
        neighFieldTab = new TextField();

        // Label for Assessment class
        Label assessmentClassLabelTab = new Label("Assessment class");
        assessmentClassLabelTab.setFont(new Font("Ariel", 15));
        
        // Choice box drop down menu
        assessmentClassChoiceBoxTab = new ChoiceBox<>();
        assessmentClassChoiceBoxTab.setPrefWidth(200);

        // Creating Search and clear buttons
        buttonsTab = new HBox(10);    // set spacing between these nodes to 10
        setUpButtonsTable(buttonsTab); // Sets up Search and Clear button
        
        // Label for Stats
        Label statsHeadingLabelTab = new Label("Edmonton Property Assessments");
        statsHeadingLabelTab.setFont(new Font("Ariel", 13));

        // Label for Stats
        statsLabelTab = new Label("");
        statsLabelTab.setFont(new Font("Ariel", 13));
        statsLabelTab.setStyle("-fx-background-color: #ffffff");
        statsLabelTab.setMinSize(200, 200);
        statsLabelTab.setAlignment(Pos.TOP_LEFT);
        
        
        tableViewSearchVBox.setAlignment(Pos.CENTER_LEFT);
        tableViewSearchVBox.getChildren().addAll(assessmentLabelTab, accNumLabelTab, 
                accNumFieldTab, addressLabelTab, addressFieldTab, neighLabelTab, neighFieldTab,
                assessmentClassLabelTab, assessmentClassChoiceBoxTab, buttonsTab, statsHeadingLabelTab, statsLabelTab
                );   
    }
    /**
     * Sets up the RHS with the table structure (with columns), but no data in 
     * the table
     * @param propertyLabel 
     */
    public void setUpTableBoxGUI(Label propertyLabel){
        tableVBox = new VBox(10);
        tableVBox.setPadding(new Insets(0, 0, 0, 10));
        
        List<Record> records = new ArrayList<>(); // Empty records
        createSimpleTable(records); // 'table' is populated with data
        
        tableVBox.setAlignment(Pos.CENTER_LEFT);
        tableVBox.getChildren().addAll(propertyLabel,table);
    }
    /**
     * Design of Table View Tab
     */
    private void setUpTableViewTab(){
        tableViewTabScreenHBox = new HBox(50);     // creating a horizontal container
                
        Label propertyLabel = new Label("Edmonton Properties");
        propertyLabel.setFont(new Font("Ariel",20));
        
        // Setting up the search box on the LHS
        setUpSearchBoxTable();
        
        // Setting up the RHS for the table
        setUpTableBoxGUI(propertyLabel);
            
        tableViewTabScreenHBox.setAlignment(Pos.BASELINE_LEFT);
        // Adding the SearchBox and the TableBox to the Screen
        tableViewTabScreenHBox.getChildren().addAll(tableViewSearchVBox, tableVBox); 
        
        tableViewTab.setContent(tableViewTabScreenHBox);
    }
    /**
     * LHS tab for Search Box for Visualization Tab is created here
     */
    private void setUpSearchBoxVisual(){
        visualizationSearchVBox = new VBox(10);    // set spacing between these nodes to 10
        visualizationSearchVBox.setPadding(new Insets(0, 0, 0, 10)); // Add padding to the left
        
        // Label for search Vbox
        Label assessmentLabelVis = new Label("Find Property Assessment");
        assessmentLabelVis.setFont(new Font("Ariel",20));
    
        // Label for Account Number
        Label accNumLabelVis = new Label("Account Number");
        accNumLabelVis.setFont(new Font("Ariel",15));

        // Text field for account number user input
        accNumFieldVis = new TextField();
        
        // Label for Address
        Label addressLabelVis = new Label("Address");
        addressLabelVis.setFont(new Font("Ariel",15));
        // Text field for address user input
        addressFieldVis = new TextField();

        // Label for Neighbourhood name
        Label neighLabelVis = new Label("Neighbourhood");
        neighLabelVis.setFont(new Font("Ariel",15));
        
        // Text field for neighbourhood user input
        neighFieldVis = new TextField();

        // Label for Assessment class
        Label assessmentClassLabelVis = new Label("Assessment class");
        assessmentClassLabelVis.setFont(new Font("Ariel", 15));
        
        // Choice box drop down menu
        assessmentClassChoiceBoxVis = new ChoiceBox<>();
        assessmentClassChoiceBoxVis.setPrefWidth(200);

        // Creating Search and clear buttons
        buttonsVis = new HBox(10);    // set spacing between these nodes to 10
        setUpButtonsVisual(buttonsVis); // Sets up Search and Clear button        
        
        visualizationSearchVBox.setAlignment(Pos.CENTER_LEFT);
        visualizationSearchVBox.getChildren().addAll(assessmentLabelVis, accNumLabelVis, 
                accNumFieldVis, addressLabelVis, addressFieldVis, neighLabelVis, neighFieldVis,
                assessmentClassLabelVis, assessmentClassChoiceBoxVis, buttonsVis
                );
    }
    /**
     * Setting up Buttons on the RHS for Calculating Pie chart, Bar graph and 
     * scatter plot
     */
    private HBox setUpVisualizationMethodButtons(){
        HBox visualizationMethodsHbox = new HBox(20);
        
        pieChartButton = new Button();
        pieChartButton.setText("View Pie Chart");
        
        barGraphButton = new Button();
        barGraphButton.setText("View Bar Graph");
        
        scatterPlotButton = new Button();
        scatterPlotButton.setText("View Scatter Plot");
        visualizationMethodsHbox.setAlignment(Pos.BASELINE_LEFT);
        // Adding the SearchBox and the visualization box to the Screen
        visualizationMethodsHbox.getChildren().addAll(pieChartButton, barGraphButton, scatterPlotButton);
        return visualizationMethodsHbox;        
    }
    /**
     * RHS of visualization tab for Displaying the buttons and the graphs
     * @param statsHeadingLabel 
     */
    private void setUpVisualizationGUI(Label statsHeadingLabel){
        visualizationVBox = new VBox(30);
        
        HBox visualizationMethodsHbox = setUpVisualizationMethodButtons();

        graphsVBox = new VBox(10); // Stores graphs in it
        
        visualizationVBox.getChildren().addAll(statsHeadingLabel, visualizationMethodsHbox, graphsVBox);
        
        visualizationTabHBox.setAlignment(Pos.BASELINE_LEFT);
        // Adding the SearchBox and the visualization box to the Screen
        visualizationTabHBox.getChildren().addAll(visualizationSearchVBox, visualizationVBox); 
        
        // Initializing the graphs and labels
        createNothingToDisplayLabel();
        createPieChart();
        createBarGraph();        
        createScatterPlot();
    }
    /**
     * Design of Visualization Tab (Both LHS and RHS)
     */
    private void setUpVisualizationTab(){
        visualizationTabHBox = new HBox(50);     // creating a horizontal container

        // Label for Stats- For RHS
        Label statsHeadingLabel = new Label("Displaying statistics for Property Assessment Value ");
        statsHeadingLabel.setFont(new Font("Ariel", 20));
        
        // Left hand side- Search Box
        setUpSearchBoxVisual();
        
        // Right hand side- Vbox for storing the Visuals
        setUpVisualizationGUI(statsHeadingLabel);

        // Adding the Hbox to the Visualization Tab
        visualizationTab.setContent(visualizationTabHBox);
    }
    /**
     * Creating Table View tab and Visualization tab
     */
    private void createTabs(){
        // Creating pane for all the tabs
        tabPane = new TabPane();
        // Making the pane as wide as the window
        tabPane.prefWidthProperty().bind(primaryStage.widthProperty());
        
        // Creating Table View Tab
        tableViewTab = new Tab("Table View");
        tableViewTab.setClosable(false);
        
        // Creating Visualization tab
        visualizationTab = new Tab("Visualization");
        visualizationTab.setClosable(false);
        
        // Adding tabs to the Tab Panes
        tabPane.getTabs().addAll(tableViewTab, visualizationTab);
        
        // Creating VBox which contains the TabPane
        tabPaneVBox = new VBox();
        tabPaneVBox.getChildren().addAll(tabPane);
    }
    /**
     * Sets up the GUI with the left hand side with search fields and
     * the right hand side with the an empty table with columns defined
     */
    public void setUpGUI(){
        primaryStage.setTitle("Edmonton Property Data"); // Title of the window

        createTabs();
        
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(tabPaneVBox);
        

        setUpTableViewTab();
        setUpVisualizationTab();        
        
        // Setting the scene
        scene = new Scene(tableViewTabScreenHBox, 1350, 900);
        scene = new Scene(root);
        primaryStage.setScene(scene);
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    /** 
     * Creating the table, set up columns, and specify the attributes to use
     * from the records.
     * .csv file
     */
    private void createSimpleTable(List<Record> records){   
        data = FXCollections.observableArrayList(records);

        TableColumn<Record, Long> account = new TableColumn<>("Account");
        account.setMinWidth(150);
        account.setCellValueFactory(new PropertyValueFactory("accNum"));
        
        TableColumn address = new TableColumn("Address");
        address.setMinWidth(200);
        address.setCellValueFactory(new PropertyValueFactory("address"));
        
        TableColumn assessed_val = new TableColumn("Assessed Value");
        assessed_val.setMinWidth(150);
        assessed_val.setCellValueFactory(new PropertyValueFactory("assessedVal"));
        
        TableColumn assessment_class = new TableColumn("Assessment Class");
        assessment_class.setMinWidth(150);
        assessment_class.setCellValueFactory(new PropertyValueFactory("assessmentClass"));
        

        TableColumn neighbourhood = new TableColumn("Neighbourhood");
        neighbourhood.setMinWidth(200);
        neighbourhood.setCellValueFactory(new PropertyValueFactory("neighbourhoodInfo"));

        TableColumn latitude = new TableColumn("Latitude");
        latitude.setMinWidth(150);
        latitude.setCellValueFactory(new PropertyValueFactory("latitude"));

        TableColumn longitude = new TableColumn("Longitude");
        longitude.setMinWidth(150);
        longitude.setCellValueFactory(new PropertyValueFactory("longitude"));

        table = new TableView<>();
        table.setEditable(false);
        table.setPrefSize(1500, 900);
        table.getColumns().addAll(account, address, assessed_val, assessment_class, neighbourhood, latitude, longitude);
        table.setItems(data);
    }
}
