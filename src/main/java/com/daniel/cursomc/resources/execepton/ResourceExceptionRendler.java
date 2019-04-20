package com.daniel.cursomc.resources.execepton;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.daniel.cursomc.services.execeptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionRendler {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StanddardError> objectNotFound(ObjectNotFoundException e,HttpServletRequest request){
		StanddardError err = new StanddardError(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
