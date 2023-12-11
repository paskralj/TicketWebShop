package com.web.training.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.training.Ticket;

@Repository
public class TicketRepository {

	private List<Ticket> tickets = new ArrayList<>();
	
	public Ticket getTicketFromIndex(int index) {
		return tickets.get(index);
	}
	
	public List<Ticket> getTickets(){
		return tickets;
	}
	
	public void addTickets(Ticket ticket) {
		tickets.add(ticket);
	}
	
	public void updateTickets(int index, Ticket ticket) {
		tickets.set(index, ticket);
	}
	
}
