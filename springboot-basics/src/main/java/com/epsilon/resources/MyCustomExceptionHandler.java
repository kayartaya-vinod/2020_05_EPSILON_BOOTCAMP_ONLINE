package com.epsilon.resources;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// This is a centralized exception handler controller.
// Can have one ore more exception handlers.
@ControllerAdvice
public class MyCustomExceptionHandler {

	@ExceptionHandler({ NoSuchElementException.class })
	public ResponseEntity<?> handleException(Exception ex) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("timestamp", new Date());
		map.put("message", ex.getMessage());
		map.put("status", 404);

		return ResponseEntity.status(404).body(map);
	}
}
