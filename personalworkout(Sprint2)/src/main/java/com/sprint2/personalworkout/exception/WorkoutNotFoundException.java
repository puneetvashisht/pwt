package com.sprint2.personalworkout.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class WorkoutNotFoundException extends Exception {
	

		private static final long serialVersionUID = 1L;
		

		public WorkoutNotFoundException(String message) {
			super(message);
			
		}

		

	}


