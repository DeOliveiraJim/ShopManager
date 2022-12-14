package com.manager.shopmanager.exceptions;

import java.net.ConnectException;
import java.sql.SQLException;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JacksonException;

@RestControllerAdvice
public class ControllersExceptionsHandler extends ResponseEntityExceptionHandler {
    public ControllersExceptionsHandler() {
        super();
    }

    @ExceptionHandler(ConnectException.class)
    protected ResponseEntity<Object> handleConnectException(ConnectException ex,
            WebRequest request) {
        return sendResponseEntity(
                createErrorResponse(ex, "Le serveur est actuellement " +
                        "indisponible veuillez r??essayez plus tard ", HttpStatus.SERVICE_UNAVAILABLE, request));
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSQLException(SQLException ex,
            WebRequest request) {
        return sendResponseEntity(
                createErrorResponse(ex, "La base de donn??e est actuellement " +
                        "indisponible veuillez r??essayez plus tard ", HttpStatus.SERVICE_UNAVAILABLE, request));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
            WebRequest request) {
        StringBuilder message = new StringBuilder();
        String[] m = ex.getConstraintViolations().toString().split(",");
        message.append(m[1].split("=")[1] + " ");
        message.append(m[0].split("=")[1]);

        return sendResponseEntity(
                createErrorResponse(ex, message.toString(), HttpStatus.BAD_REQUEST, request));
    }

    @ExceptionHandler(ElementNotFoundException.class)
    protected ResponseEntity<Object> handleNoSuchElementFoundException(ElementNotFoundException ex,
            WebRequest request) {
        return sendResponseEntity(
                createErrorResponse(ex, "Requested element not found.", HttpStatus.NOT_FOUND, request));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = ex.getMethod() +
                " http method is not supported. " +
                "Supported methods are " +
                ex.getSupportedHttpMethods();
        return sendResponseEntity(createErrorResponse(ex, message, status, request));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getContentType() +
                " media type is not supported. " +
                "Supported media types are " +
                (ex.getSupportedMediaTypes());
        return sendResponseEntity(createErrorResponse(ex, message, status, request));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "This media type is not acceptable. " +
                "Acceptable media types are " +
                (ex.getSupportedMediaTypes());
        return sendResponseEntity(createErrorResponse(ex, message, status, request));
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return sendResponseEntity(createErrorResponse(ex, ex.getMessage(), status, request));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return sendResponseEntity(createErrorResponse(ex, ex.getMessage(), status, request));
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return sendResponseEntity(createErrorResponse(ex, ex.getMessage(), status, request));
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder message = new StringBuilder("JSON request not readable, cause -> ");
        Throwable cause = ex.getCause();
        if (cause instanceof JacksonException) {
            message.append(((JacksonException) cause).getOriginalMessage());
        } else {
            message.append(cause);
        }
        return sendResponseEntity(createErrorResponse(ex, message.toString(), status, request));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return sendResponseEntity(createErrorResponse(ex, status, request));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage err = createErrorResponse(ex, "Problem in JSON request, check 'errors' for more information",
                status, request);
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            err.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return sendResponseEntity(err);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return sendResponseEntity(createErrorResponse(ex, status, request));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        return sendResponseEntity(createErrorResponse(ex, status, request));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return sendResponseEntity(createErrorResponse(ex, status, request));
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
            HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return sendResponseEntity(createErrorResponse(ex, status, webRequest));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return sendResponseEntity(createErrorResponse(ex, status, request));
    }

    private ErrorMessage createErrorResponse(Exception exception,
            HttpStatus httpStatus,
            WebRequest request) {
        return createErrorResponse(exception, exception.getMessage(), httpStatus, request);
    }

    private ErrorMessage createErrorResponse(Exception exception,
            String message,
            HttpStatus httpStatus,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        ErrorMessage err = new ErrorMessage(httpStatus.value(), message,
                servletWebRequest.getRequest().getServletPath(), servletWebRequest.getRequest().getMethod());

        return err;
    }

    private ResponseEntity<Object> sendResponseEntity(ErrorMessage err) {
        return ResponseEntity.status(err.getStatus()).body(err);
    }

}
