package com.web.training.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.web.training.Constants;
import com.web.training.Ticket;
import com.web.training.Repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	TicketRepository ticketRepository;

	public Ticket getTicketFromIndex(int index) {
		return ticketRepository.getTicketFromIndex(index);
	}

	public List<Ticket> getTickets() {
		return ticketRepository.getTickets();
	}

	public void addTickets(Ticket ticket) {
		ticketRepository.addTickets(ticket);
	}

	public void updateTickets(int index, Ticket ticket) {
		ticketRepository.updateTickets(index, ticket);
	}

	public int getIndexById(int id) {
		for (int i = 0; i < getTickets().size(); i++) {
			if (getTickets().get(i).getId() == id) {
				return i;
			}
		}
		return Constants.NOTFOUND;
	}

	public Ticket getTicketByIndex(int index) {
		if (index == Constants.NOTFOUND) {
			return new Ticket();
		} else {
			return getTicketFromIndex(index);
		}
	}

	public void duplicatedFirstName(Ticket ticket, BindingResult result) {
		if (ticket.getFirstname().equals(ticket.getLastname()))
			result.rejectValue("firstname", "", "Pls enter name different then last name ");
	}

	/**
	 * @param id
	 * @param ticket
	 * @param category
	 * @param ticketNumb
	 * 
	 *                   If index = NOTFOUND, then add tickets and generate new
	 *                   index for it ,else calculate total price and update ticket
	 */
	public void updateTicketsAfterSubmit(int id, Ticket ticket, String category, int ticketNumb) {
		int index = getIndexById(id);
		if (index == Constants.NOTFOUND) {
			addTickets(ticket);
			index = getIndexById(id);
		}

		ticket.calculateTotalPrice(category, ticketNumb);
		updateTickets(index, ticket);
	}

}
