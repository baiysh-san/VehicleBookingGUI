package main.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleManagement {
	private Map<Integer, Vehicle> bookedVehicles;
	private List<Vehicle> vehicles;

	public VehicleManagement() {
		bookedVehicles = new HashMap<>();
		vehicles = new ArrayList<>();
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public List<Vehicle> findMatchingVehicles(int minRequiredDistance, OperatingEnvironment environment) {
		List<Vehicle> matched = new ArrayList<Vehicle>();
		for (Vehicle v : vehicles) {
			if (v.canOperateOn(environment) && v.getMaxDistance() >= minRequiredDistance && v.isAvailable()) {
				matched.add(v);
			}
		}
		return matched;
	}

	public boolean bookVehicle(Vehicle vehicle, Customer customer) {
		if (bookedVehicles.containsValue(vehicle) || !vehicles.contains(vehicle)) {
			return false;
		} else {
			vehicle.book();
			bookedVehicles.put(customer.getId(), vehicle);
			return true;
		}
	}

	public void showBookedVehicles() {
		System.out.println("Diese Vehicles sind gebucht: ");
		for (Vehicle v : bookedVehicles.values()) {
			System.out.println("- " + v.getName());
		}
	}
}
