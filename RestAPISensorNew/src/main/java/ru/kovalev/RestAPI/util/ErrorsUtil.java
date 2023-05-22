package ru.kovalev.RestAPI.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorsUtil{

	public static void returnErrorsToClient(BindingResult bindingResult) {
		StringBuilder errorMessage = new StringBuilder();
		
		List<FieldError> errors = bindingResult.getFieldErrors();
		for (FieldError error : errors) {
			errorMessage.append(error.getField())
						.append(" - ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
						.append(";");
		}
		
		throw new ValueSensorException(errorMessage.toString());
		
	}
	
}
