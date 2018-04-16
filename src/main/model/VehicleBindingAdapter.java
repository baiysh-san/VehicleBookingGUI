package main.model;

import javafx.beans.property.*;

public class VehicleBindingAdapter {
    private final IntegerProperty distance;
    private final BooleanProperty available;
    private final StringProperty name;
    private final StringProperty environment;
    private VehicleBase vehicleBase;

    public VehicleBindingAdapter(VehicleBase vehicleBase) {
        this.vehicleBase = vehicleBase;
        this.distance = new SimpleIntegerProperty(vehicleBase.getMaxDistance());
        this.available = new SimpleBooleanProperty(vehicleBase.isAvailable());
        this.name = new SimpleStringProperty(vehicleBase.getName());
        this.environment = new SimpleStringProperty(vehicleBase.getOperatingEnvironment().name());
        this.vehicleBase.setBindingAdapter(this);
    }

    public int getDistance() {
        return distance.get();
    }

    public IntegerProperty distanceProperty() {
        return distance;
    }

    public boolean isAvailable() {
        return available.get();
    }

    public BooleanProperty availableProperty() {
        return available;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }


    public void setDistance(int distance) {

        this.distance.set(distance);
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setVehicleBase(VehicleBase vehicleBase) {
        this.vehicleBase = vehicleBase;
        this.vehicleBase.setBindingAdapter(this);
        setAvailable(vehicleBase.isAvailable());
        setDistance(vehicleBase.getMaxDistance());
        setName(vehicleBase.getName());
        setEnvironment(vehicleBase.getOperatingEnvironment().name());
    }

    public String getEnvironment() {
        return environment.get();
    }

    public StringProperty environmentProperty() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment.set(environment);
    }

    public VehicleBase getVehicleBase() {
        return vehicleBase;
    }
}
