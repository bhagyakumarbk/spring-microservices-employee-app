package com.employee.app.model;

public class AddressDTO {
	private int id;
	private String lane1;
	private String lane2;
	private long zip;
	private String state;

	private int employeeId;

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the lane1
	 */
	public String getLane1() {
		return lane1;
	}

	/**
	 * @param lane1 the lane1 to set
	 */
	public void setLane1(String lane1) {
		this.lane1 = lane1;
	}

	/**
	 * @return the lane2
	 */
	public String getLane2() {
		return lane2;
	}

	/**
	 * @param lane2 the lane2 to set
	 */
	public void setLane2(String lane2) {
		this.lane2 = lane2;
	}

	/**
	 * @return the zip
	 */
	public long getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(long zip) {
		this.zip = zip;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "AddressEntity [id=" + id + ", lane1=" + lane1 + ", lane2=" + lane2 + ", zip=" + zip + ", state=" + state
				+ "]";
	}

}
