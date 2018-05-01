package br.com.neja.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.neja.services.exception.AuthorizationException;
import br.com.neja.services.exception.DataIntegrityException;
import br.com.neja.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourcesExceptionHandler {

	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest req) {

		StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> DataIntegrity(DataIntegrityException ex, HttpServletRequest req) {

		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException ex, HttpServletRequest req) {

		ValidationError erro = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação",
				System.currentTimeMillis());
		for(FieldError x : ex.getBindingResult().getFieldErrors()) {
			erro.addErrors(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException ex, HttpServletRequest req) {

		StandardError erro = new StandardError(HttpStatus.FORBIDDEN.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
}
