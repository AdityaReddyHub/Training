package com.bank.reigister.customer.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bank.reigister.customer.pojos.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleApplicationExceptions(Exception ex, WebRequest webReq) {
		List<String> errorMsg = new ArrayList<String>();
		//errorMsg.add(ex.getLocalizedMessage());
		errorMsg.add("Invalid Data");
		ErrorResponse errRes = new ErrorResponse("410", errorMsg);
		return new ResponseEntity<>(errRes,HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(CustomerNotFoundException customerEx){
		List<String> errorMsg = new ArrayList<String>();
		errorMsg.add(customerEx.getLocalizedMessage());
		ErrorResponse errRes = new ErrorResponse("411", errorMsg);
		
		return new ResponseEntity<>(errRes,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateUserException.class)
	public final ResponseEntity<Object> handleDuplicateuserException(DuplicateUserException duplicateUserEx){
		List<String> errorMsg = new ArrayList<String>();
		errorMsg.add(duplicateUserEx.getLocalizedMessage());
		ErrorResponse errRes = new ErrorResponse("412", errorMsg);
		
		return new ResponseEntity<>(errRes,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String,String> errors = new HashMap<String, String>();
		
		ex.getBindingResult().getAllErrors().
		forEach((error->{String fielddName = ((FieldError)error).getField();
		                 String errorMessage =error.getDefaultMessage();
		                 errors.put(fielddName, errorMessage);
		}));
		return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);	
		
		//return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
	
	
/*	public Map<String , String> handleValidationExceptions(MethodArgumentNotValidException ex){
		
		Map<String,String> errors = new HashMap<String, String>();
		
		ex.getBindingResult().getAllErrors().
		forEach((error->{String fielddName = ((FieldError)error).getField();
		                 String errorMessage =error.getDefaultMessage();
		                 errors.put(fielddName, errorMessage);
		}));
		return errors;
	} */
	
	
	
	/*	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions1(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	} 
	*/
	
	/*
	 * @Override public ResponseEntity<Object>
	 * handleValidationExceptions(MethodArgumentNotValidException ex,HttpHeaders
	 * headers, HttpStatus status, WebRequest request){
	 * 
	 * Map<String,String> errors = new HashMap<String,String>();
	 * 
	 * for() }
	 */
}
