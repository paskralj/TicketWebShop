package com.web.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.web.training.Repository.TicketRepository;
import com.web.training.Service.TicketService;

@ExtendWith(SpringExtension.class)
public class TicketServiceTest {

	@Mock
	private TicketRepository ticketRepository;

	@InjectMocks
	private TicketService ticketService;

	@Test
	public void getIndexByIdTest() {
		Ticket ticket = new Ticket("Berlin", "Category One (40e)", 2, "Tomislav", "Tomislav", 1950);

		when(ticketRepository.getTickets()).thenReturn(Arrays.asList(ticket));

		int valid = ticketService.getIndexById(ticket.getId());
		int notFound = ticketService.getIndexById(123123123);

		assertEquals(0, valid);
		assertEquals(Constants.NOTFOUND, notFound);
	}

	@Test
	public void getTicketByIndexTest() {

		Ticket ticket = new Ticket("Berlin", "Category One (40e)", 2, "Tomislav", "Tomislav", 1950);
		when(ticketRepository.getTickets()).thenReturn(Arrays.asList(ticket));

		int index = ticketService.getIndexById(ticket.getId());

		when(ticketRepository.getTicketFromIndex(index)).thenReturn(ticket);

		Ticket valid = ticketService.getTicketByIndex(index);
		Ticket notFound = ticketService.getTicketByIndex(3213123);

		assertEquals(ticket, valid);
		assertEquals(null, notFound);
	}

	@Test
	public void updateTicketsAfterSubmitTest() {
		
		Ticket ticket = new Ticket("Berlin", "Category One (40e)", 2, "Tomislav", "Tomislav", 1950);
		when(ticketRepository.getTickets()).thenReturn(Arrays.asList(ticket));
		int index = ticketService.getIndexById(ticket.getId());
		Ticket updatedTicket = new Ticket("Hamburg", "Category Four (10e)", 4, "Ante", "Antic", 1911);
		when(ticketRepository.getTicketFromIndex(index)).thenReturn(updatedTicket);
		
//		Mockito.doNothing().when(ticketRepository).addTickets(ticket);
		Mockito.doNothing().when(ticketRepository).updateTickets(index, updatedTicket);
				
		ticketService.updateTicketsAfterSubmit(ticket.getId(), ticket, ticket.getCategory(), ticket.getTicketNumb());
		Ticket resultTicket = ticketService.getTicketByIndex(index);
		assertEquals("Hamburg", ticket.getCity());
		
	}

}
