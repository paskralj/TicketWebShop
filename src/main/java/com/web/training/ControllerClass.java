package com.web.training;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerClass {
	
	private List<Ticket> tickets = new ArrayList<>();

	@GetMapping("/")
	public String homePage(Model model, @RequestParam(required = false) String id) {
		model.addAttribute("cities", Constants.CITIES);
		model.addAttribute("categories", Constants.CATEGORIES);
		model.addAttribute("ticketnumber", Constants.TICKETNUMBER);
		
		int index;
		if (id != null) {
			index = getIndexById(Integer.parseInt(id));
		} else {
			index = Constants.NOTFOUND;
		}
		
		
		if (index == Constants.NOTFOUND) {
			model.addAttribute("ticket",new Ticket());
		} else {
			model.addAttribute(tickets.get(index));
		}
		
//		model.addAttribute("ticket",new Ticket());
		return "form";
	}
	
	@GetMapping("/table")
	public String tablePage(Model model) {
		model.addAttribute("ticketinfo",tickets);
		return "table";
	}
	
	@PostMapping("/submitItem")
	public String submitItem(Ticket ticket,int id, String category, int ticketNumb) {
		
		
		
//		tickets.add(ticket);
		int index = getIndexById(id);
//		ticket.calculateTotalPrice(category, ticketNumb);
		if (index == Constants.NOTFOUND) {
			tickets.add(ticket);
			index = getIndexById(id);
			ticket.calculateTotalPrice(category, ticketNumb);
			tickets.set(index, ticket);
		} else {
			ticket.calculateTotalPrice(category, ticketNumb);
			tickets.set(index, ticket);
		}
//		tickets.set(index, ticket);
		return "redirect:/table";
	}
	
	public int getIndexById(int id) {
		for (int i=0; i<tickets.size();i++) {
			if (tickets.get(i).getId() == id) {
				return i;
			}
		}
		return Constants.NOTFOUND;
	}
	
}
