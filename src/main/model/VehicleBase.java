package main.model;

public abstract class VehicleBase implements Vehicle {
	private int distance;
	private boolean available = true;
	private String name;
	private OperatingEnvironment environment;
	private VehicleBindingAdapter bindingAdapter;
	public VehicleBase(String name, int distance, OperatingEnvironment environment) {
		super();
		this.name = name;
		this.distance = distance;
		this.environment = environment;
	}

	public OperatingEnvironment getOperatingEnvironment() {
		return environment;
	}

	public void setOperatingEnvironment(OperatingEnvironment environment) {
		if (!this.environment.equals(environment)) {
			this.environment = environment;
			notifyBindingAdapter();
		}
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		if (this.available != available) {
			this.available = available;
			notifyBindingAdapter();
		}
	}

	public int getMaxDistance() {
		return distance;
	}

	public void setMaxDistance(int distance) {
		if (this.distance != distance) {
			this.distance = distance;
			notifyBindingAdapter();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!this.name.equals(name)) {
			this.name = name;
			notifyBindingAdapter();
		}
	}

	public boolean canOperateOn(OperatingEnvironment environment) {
		if (environment == this.environment) {
			return true;
		}
		return false;
	}

	public void book() {
		setAvailable(false);
	}

	public void setBindingAdapter(VehicleBindingAdapter bindingAdapter) {
		this.bindingAdapter = bindingAdapter;
	}
	public void notifyBindingAdapter() {
		bindingAdapter.setVehicleBase(this);
	}

    public VehicleBindingAdapter getBindingAdapter() {
        return bindingAdapter;
    }
}
