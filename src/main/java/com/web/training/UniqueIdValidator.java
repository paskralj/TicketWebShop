package com.web.training;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueIdValidator implements ConstraintValidator<UniqueId, Integer> {
	
	private final Set<Integer> uniqueIds;
	
	public UniqueIdValidator(List<Ticket> tickets) {
		this.uniqueIds = new HashSet<>();
		for (Ticket ticket : tickets) {
			uniqueIds.add(ticket.getId());
		}
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value != null && uniqueIds.add(value);
	}

}
