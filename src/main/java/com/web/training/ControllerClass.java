package com.web.training;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class ControllerClass {

	private List<Ticket> tickets = new ArrayList<>();

	@GetMapping("/")
	public String homePage(Model model, @RequestParam(required = false) String id) {

		int index;
		if (id != null) {
			index = getIndexById(Integer.parseInt(id));
		} else {
			index = Constants.NOTFOUND;
		}

		if (index == Constants.NOTFOUND) {
			model.addAttribute("ticket", new Ticket());
		} else {
			model.addAttribute(tickets.get(index));
		}

		return "form";
	}

	@GetMapping("/table")
	public String tablePage(Model model) {
		model.addAttribute("ticketinfo", tickets);
		return "table";
	}

	@PostMapping("/submitItem")
	public String submitItem(@Valid Ticket ticket, BindingResult result, int id, String category, int ticketNumb) {
		System.out.println(result.hasErrors());
		
		if (result.hasErrors())
			return "form";

		int index = getIndexById(id);
		if (index == Constants.NOTFOUND) {
			tickets.add(ticket);
			index = getIndexById(id);
		}
			
		ticket.calculateTotalPrice(category, ticketNumb);
		tickets.set(index, ticket);

		return "redirect:/table";
	}

	public int getIndexById(int id) {
		for (int i = 0; i < tickets.size(); i++) {
			if (tickets.get(i).getId() == id) {
				return i;
			}
		}
		return Constants.NOTFOUND;
	}

}
