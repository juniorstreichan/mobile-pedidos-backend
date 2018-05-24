package br.com.neja.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

import br.com.neja.services.exception.AuthorizationException;
import br.com.neja.services.exception.DataIntegrityException;
import br.com.neja.services.exception.FileException;
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

		ValidationError erro = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação",
				System.currentTimeMillis());
		for (FieldError x : ex.getBindingResult().getFieldErrors()) {
			erro.addErrors(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException ex, HttpServletRequest req) {

		StandardError erro = new StandardError(HttpStatus.FORBIDDEN.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException ex, HttpServletRequest req) {

		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception ex, HttpServletRequest req) {

		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException ex, HttpServletRequest req) {

		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException ex, HttpServletRequest req) {

		HttpStatus code = HttpStatus.valueOf(ex.getErrorCode());
		StandardError erro = new StandardError( code.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(code).body(erro);
	}
}






















