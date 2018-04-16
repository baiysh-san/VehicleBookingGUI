package main.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.VehicleBookingMainApp;
import main.model.*;

import java.util.List;

public class VehicleBookingController {
    @FXML
    private TableView<VehicleBindingAdapter> adapterTableView;
    @FXML
    private TableColumn<VehicleBindingAdapter, String> nameColumn;
    @FXML
    private TableColumn<VehicleBindingAdapter, String> enviromentColumn;
    @FXML
    private TableColumn<VehicleBindingAdapter, Integer> distanceColumn;
    @FXML
    private TableColumn<VehicleBindingAdapter, Boolean> availableColumn;

    @FXML
    private TextField searchTfield;
    @FXML
    private RadioButton airButton;
    @FXML
    private RadioButton landButton;
    @FXML
    private RadioButton waterButton;
    @FXML
    private ToggleGroup toogleGroup1;

    private VehicleBookingMainApp mainApp;
    private VehicleManagement vehicleManagement;
    private Customer customer;
    public VehicleBookingController() {
    }
    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        enviromentColumn.setCellValueFactory(cellData -> cellData.getValue().environmentProperty());
        distanceColumn.setCellValueFactory(cellData -> cellData.getValue().distanceProperty().asObject());
        availableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
        airButton.setUserData(OperatingEnvironment.AIR);
        landButton.setUserData(OperatingEnvironment.LAND);
        waterButton.setUserData(OperatingEnvironment.WATER);
    }

    public void setMainApp(VehicleBookingMainApp mainApp) {
        this.mainApp = mainApp;
        adapterTableView.setItems(mainApp.getVehicleBindingAdapters());
    }
    public void handleBookVehicleButton() {
        VehicleBindingAdapter selectedAdapter = adapterTableView.getSelectionModel().selectedItemProperty().getValue();
        if (selectedAdapter != null) {
            if (selectedAdapter.isAvailable()) {
                mainApp.getVehicleManagement().bookVehicle(selectedAdapter.getVehicleBase(), mainApp.getCustomer());
            } else {
                showAlert("This vehicle is not available", "Please select another vehicle!");
            }
        } else {
            showAlert("No vehicle selected", "Please select a vehicle in the table!");
        }

    }
    public void handleSearchVehicleButton() {
        try {
            if (toogleGroup1.getSelectedToggle()!= null) {
                int minReqDis = Integer.parseInt(searchTfield.getText().replaceAll("\\s",""));
                OperatingEnvironment environment = (OperatingEnvironment) toogleGroup1.getSelectedToggle().getUserData();
                List<Vehicle> resultVehicles = mainApp.getVehicleManagement().findMatchingVehicles(minReqDis, environment);
                ObservableList<VehicleBindingAdapter> adapters = FXCollections.observableArrayList();
                for (int i = 0; i < resultVehicles.size(); i++) {
                    VehicleBase base = (VehicleBase) resultVehicles.get(i);
                    adapters.add(base.getBindingAdapter());
                }
                adapterTableView.setItems(adapters);
            } else {
                showAlert("No environment selected", "Please select an environment!");
            }

        } catch (NumberFormatException ex) {
            showAlert(searchTfield.getText()+ " is not an integer!", "Please change the value!");
        }
    }
    public void handleShowAllVehiclesButton() {
        adapterTableView.setItems(mainApp.getVehicleBindingAdapters());
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setVehicleManagement(VehicleManagement vehicleManagement) {
        this.vehicleManagement = vehicleManagement;
    }
    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
