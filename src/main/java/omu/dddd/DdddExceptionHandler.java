package omu.dddd;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import omu.dddd.domain.ApiHandlableException;

@RestControllerAdvice
class DdddExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * contoller dto validation error handler
     */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, ?> handleException(MethodArgumentNotValidException ex) {

        Map<String, String> map = new HashMap<>();

        BindingResult result = ex.getBindingResult();
        for (FieldError err : result.getFieldErrors()) {
            map.put(err.getField(), err.getDefaultMessage());
        }

        logger.info("** Validation Error **");
        logger.info(map.toString());

        return map;
	}

    /**
     * contoller dto validation error handler
     */
	@ExceptionHandler(ApiHandlableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, ?> handleException(ApiHandlableException ex) {

        Map<String, String> map = new HashMap<>();

        map.put("error", ex.getMessage());

        logger.info("** Validation Error **");
        logger.info(map.toString());

        return map;
	}
}