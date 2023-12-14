package com.web.training.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.training.Ticket;
import com.web.training.Service.TicketService;

import jakarta.validation.Valid;

@Controller
public class ControllerClass {

	@Autowired
	TicketService ticketService;

	@GetMapping("/")
	public String homePage(Model model, @RequestParam(required = false, defaultValue = "-1") Integer id) {

		int index = ticketService.getIndexById(id);
		model.addAttribute("ticket", ticketService.getTicketByIndex(index));

		return "form";
	}

	@GetMapping("/table")
	public String tablePage(Model model) {
		model.addAttribute("ticketinfo", ticketService.getTickets());
		return "table";
	}

	@PostMapping("/submitItem")
	public String submitItem(@Valid Ticket ticket, BindingResult result, int id, String category, int ticketNumb) {
		System.out.println(result.hasErrors());

		ticketService.duplicatedFirstName(ticket, result);

		if (result.hasErrors())
			return "form";

		ticketService.updateTicketsAfterSubmit(id, ticket, category, ticketNumb);

		return "redirect:/table";
	}

}
