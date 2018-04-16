package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.model.*;
import main.view.VehicleBookingController;

import java.io.IOException;

public class VehicleBookingMainApp extends Application {

    private Stage primaryStage;
    private VBox rootLayout;
    private ObservableList<VehicleBindingAdapter> vehicleBindingAdapters = FXCollections.observableArrayList();

    private VehicleManagement vehicleManagement;
    private Customer customer;

    public VehicleBookingMainApp() {

        vehicleBindingAdapters.add(new VehicleBindingAdapter(new Boat("Boat A", 1000)));
        vehicleBindingAdapters.add(new VehicleBindingAdapter(new eCar("eCar B", 2000)));
        vehicleBindingAdapters.add(new VehicleBindingAdapter(new Helicopter("Helicopter C", 3000)));
        vehicleManagement = new VehicleManagement();
        customer = new Customer(1, "John Custerman");
        addAllVehiclesToVManagement();
    }
    public void addAllVehiclesToVManagement() {
        for (VehicleBindingAdapter adapter: vehicleBindingAdapters) {
            vehicleManagement.addVehicle(adapter.getVehicleBase());
        }
    }
    public VehicleManagement getVehicleManagement() {
        return vehicleManagement;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ObservableList<VehicleBindingAdapter> getVehicleBindingAdapters() {
        return vehicleBindingAdapters;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("VehicleBookingApp");
        initRootLayout();
    }
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VehicleBookingMainApp.class.getResource("view/BookingOverview.fxml"));

            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            VehicleBookingController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
