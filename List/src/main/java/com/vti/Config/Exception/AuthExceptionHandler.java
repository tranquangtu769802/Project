package com.vti.Config.Exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// AuthenticationEntryPoint: 401, AccessDeniedHandler: 403
@Component
public class AuthExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

	@Autowired
	private MessageSource messageSource;
	
	private String getMessage(String key) {
		return messageSource.getMessage(
				key, 
				null, 
				"Default message", 
				LocaleContextHolder.getLocale());
	}
	
	// Spring Security
	// 401 unauthorized
	// Spring Security
	// 401 unauthorized
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException exception) throws IOException, ServletException {

		String message = getMessage("AuthenticationException.message");
		String detailMessage = exception.getLocalizedMessage();
		int code = 8;
		String moreInformation = "http://localhost:8075/api/v1/exception/8";

		ErrorResponse errorResponse = new ErrorResponse(message, detailMessage, null, code, moreInformation);

		// convert object to json
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(errorResponse);

		// return json
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
	}

	// Spring Security
	// 403 Forbidden
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
					   AccessDeniedException exception) throws IOException, ServletException {

		String message = getMessage("AccessDeniedException.message");
		String detailMessage = exception.getLocalizedMessage();
		int code = 9;
		String moreInformation = "http://localhost:8075/api/v1/exception/9";

		ErrorResponse errorResponse = new ErrorResponse(message, detailMessage, null, code, moreInformation);

		// convert object to json
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(errorResponse);

		// return json
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
	}

}

