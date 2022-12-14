package com.ebf.EBF_portal.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public class EmployeeServiceException extends RuntimeException {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EmployeeServiceException(String message) {
	        super(message);
	    }
	}
