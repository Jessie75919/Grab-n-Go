package _10_pickRest.controller;

public class StoreBeanSmSize {
	private String name;
	private int rest_id;
	
	public StoreBeanSmSize() {
		// TODO Auto-generated constructor stub
	}

	
	public StoreBeanSmSize(String name, int rest_id) {
		super();
		this.name = name;
		this.rest_id = rest_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRest_id() {
		return rest_id;
	}

	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
	}

	@Override
	public String toString() {
		return "StoreBeanSmSize [name=" + name + ", rest_id=" + rest_id + "]";
	}
	
	

}
