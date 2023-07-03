/*
 * Copyright (C) 2023 Fauzan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package my.com.vic3.rest.app.controller;

import my.com.vic3.common.lib.handler.exception.BadRequestException;
import my.com.vic3.common.lib.handler.exception.NotFoundException;
import my.com.vic3.common.lib.handler.exception.SystemException;
import my.com.vic3.common.lib.handler.exception.UnauthorizedException;
import my.com.vic3.common.lib.handler.exception.UnsupportedTypeException;
import my.com.vic3.common.lib.ExceptionResponse;
import my.com.vic3.common.lib.handler.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Fauzan
 * 
 * This customized ResponseEntityExceptionHandler must be placed alongside Controller classes.
 * 
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    public CustomizedResponseEntityExceptionHandler() {
    }
    
    /**
     * Exception Handler for Error 400 Bad Request
     * 
     * @param ex
     * @param request
     * @return 
     */
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
        String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
        String message = HttpStatus.BAD_REQUEST.name();
        return new ResponseEntity<>(new ResponseHandler().generateExceptionResponse(code, message, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Exception Handler for Error 401 Unauthorized
     * 
     * @param ex
     * @param request
     * @return 
     */
    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<ExceptionResponse> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {    
        String code = String.valueOf(HttpStatus.UNAUTHORIZED.value());
        String message = HttpStatus.UNAUTHORIZED.name();
        return new ResponseEntity<>(new ResponseHandler().generateExceptionResponse(code, message, ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
  
    /**
     * Exception Handler for Error 404 Not Found
     * 
     * @param ex
     * @param request
     * @return 
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {   
        String code = String.valueOf(HttpStatus.NOT_FOUND.value());
        String message = HttpStatus.NOT_FOUND.name();
        return new ResponseEntity<>(new ResponseHandler().generateExceptionResponse(code, message, ex.getMessage()), HttpStatus.NOT_FOUND);
    }
  
    /**
     * Exception Handler for Error 415 Unsupported Type
     * 
     * @param ex
     * @param request
     * @return 
     */
    @ExceptionHandler(UnsupportedTypeException.class)
    public final ResponseEntity<ExceptionResponse> handleUnsupportedTypeException(UnsupportedTypeException ex, WebRequest request) {    
        String code = String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        String message = HttpStatus.UNSUPPORTED_MEDIA_TYPE.name();
        return new ResponseEntity<>(new ResponseHandler().generateExceptionResponse(code, message, ex.getMessage()), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    
    /**
     * Exception Handler for Error 500 Internal Server Error
     * 
     * @param ex
     * @param request
     * @return 
     */
    @ExceptionHandler(SystemException.class)
    public final ResponseEntity<ExceptionResponse> handleInternalServerException(SystemException ex, WebRequest request) {
        String code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String message = HttpStatus.INTERNAL_SERVER_ERROR.name();
      return new ResponseEntity<>(new ResponseHandler().generateExceptionResponse(code, message, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
}
}
