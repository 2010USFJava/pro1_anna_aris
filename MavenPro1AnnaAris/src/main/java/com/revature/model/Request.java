package com.revature.model;
import java.sql.Timestamp;
import java.util.List;

public class Request {
	
	private int requestId;
	private int employeeId;
	private Timestamp dateMade;
	private String eventDate;
	private String eventType;
	private String eventTime;
	private double cost;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String eventDescription;
	private String supStatus;
	private String headStatus;
	private String benStatus;
	private List<Document> additionalDocs;
	
	public Request(int employeeId, String eventDate, String eventType, String eventTime, double cost, String street,
			String city, String state, String zip, String eventDescription) {
		
		this.employeeId = employeeId;
		this.eventDate = eventDate;
		this.eventType = eventType;
		this.eventTime = eventTime;
		this.cost = cost;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.eventDescription = eventDescription;
	}
	public Request(int employeeId, String eventDate, String eventType, String eventTime, double cost, String street,
			String city, String state, String zip, String eventDescription, List<Document> additionalDocs) {
		
		this.employeeId = employeeId;
		this.eventDate = eventDate;
		this.eventType = eventType;
		this.eventTime = eventTime;
		this.cost = cost;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.eventDescription = eventDescription;
		this.additionalDocs = additionalDocs;
	}
	public Request(int requestId, int employeeId, Timestamp dateMade, String eventDate, String eventType,
			String eventTime, double cost, String street, String city, String state, String zip,
			String eventDescription, String supStatus, String headStatus, String benStatus,
			List<Document> additionalDocs) {
		
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.dateMade = dateMade;
		this.eventDate = eventDate;
		this.eventType = eventType;
		this.eventTime = eventTime;
		this.cost = cost;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.eventDescription = eventDescription;
		this.supStatus = supStatus;
		this.headStatus = headStatus;
		this.benStatus = benStatus;
		this.additionalDocs = additionalDocs;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Timestamp getDateMade() {
		return dateMade;
	}
	public void setDateMade(Timestamp dateMade) {
		this.dateMade = dateMade;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getSupStatus() {
		return supStatus;
	}
	public void setSupStatus(String supStatus) {
		this.supStatus = supStatus;
	}
	public String getHeadStatus() {
		return headStatus;
	}
	public void setHeadStatus(String headStatus) {
		this.headStatus = headStatus;
	}
	public String getBenStatus() {
		return benStatus;
	}
	public void setBenStatus(String benStatus) {
		this.benStatus = benStatus;
	}
	public List<Document> getAdditionalDocs() {
		return additionalDocs;
	}
	public void setAdditionalDocs(List<Document> additionalDocs) {
		this.additionalDocs = additionalDocs;
	}

}
