package com.web.training;

public class Ticket {

	private String city;
	private String category;
	private String ticketNumb;
	private String firstname;
	private String lastname;
	private int id;
	private int totalPrice;

	public Ticket(String city, String category, String ticketNumb, String firstname, String lastname, int id) {
		super();
		this.city = city;
		this.category = category;
		this.ticketNumb = ticketNumb;
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;

		int ticketIntNumber = Integer.parseInt(ticketNumb);

		this.totalPrice = calculateTotalPrice(category, ticketIntNumber);

	}

	public Ticket() {
		
		
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getTicketNumb() {
		return ticketNumb;
	}

	public void setTicketNumb(String ticketNumb) {
		this.ticketNumb = ticketNumb;
	}

	public int calculateTotalPrice(String category, int ticketNumb) {

		int result = Constants.DEFAULTRESULT;
		switch (category) {
		case "Category One (40e)":
			result = 40 * ticketNumb;
			break;
		case "Category Two (30e)":
			result = 30 * ticketNumb;
			break;
		case "Category Three (20e)":
			result = 20 * ticketNumb;
			break;
		case "Category Four (10e)":
			result = 10 * ticketNumb;
			break;
		}
		this.totalPrice=result;
		return result;
	}

}
