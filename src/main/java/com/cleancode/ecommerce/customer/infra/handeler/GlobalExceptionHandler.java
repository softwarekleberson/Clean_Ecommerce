package com.cleancode.ecommerce.customer.infra.handeler;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalContactException;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalCpfException;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Custom exception
	@ExceptionHandler(IllegalDomainException.class)
	public ResponseEntity<DetailsError> handleExceptionPersonalized(IllegalDomainException ex) {
		DetailsError error = new DetailsError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Custom exception",
				ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(IllegalCpfException.class)
	public ResponseEntity<DetailsError> handleExceptionPersonalized(IllegalCpfException ex) {
		DetailsError error = new DetailsError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Custom exception",
				ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(IllegalContactException.class)
	public ResponseEntity<DetailsError> handleExceptionPersonalized(IllegalContactException ex) {
		DetailsError error = new DetailsError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Custom exception",
				ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Invalid argument error (e.g. @Valid)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DetailsError> handleValidationException(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + ": " + e.getDefaultMessage()).findFirst().orElse("Validation Error");

		DetailsError erro = new DetailsError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation Error",
				message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	// Poorly formatted request body (invalid JSON, for example)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<DetailsError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		DetailsError erro = new DetailsError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Poorly formatted request", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	// Invalid route argument (e.g. invalid ID in URL)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<DetailsError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		DetailsError erro = new DetailsError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Invalid parameter",
				"The Value '" + ex.getValue() + "' is not valid for the parameter '" + ex.getName() + "'.");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	// Any other error (500)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<DetailsError> handleGenericException(Exception ex) {
		DetailsError erro = new DetailsError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal server error", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetailsError> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		String mensagem = "Violação de integridade de dados.";

		Throwable causa = ex.getCause();
		if (causa instanceof ConstraintViolationException) {
			ConstraintViolationException constraintEx = (ConstraintViolationException) causa;
			if (constraintEx.getConstraintViolations() != null && constraintEx.getConstraintViolations().contains("customer.cpf")) {
				mensagem = "There is already a customer registered with this CPF.";
			}
		}

		DetailsError erro = new DetailsError(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Data conflict",
				mensagem);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
}
