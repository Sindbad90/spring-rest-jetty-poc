package spring.rest.poc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import spring.rest.poc.entity.RestErrorResponse;
import spring.rest.poc.exception.BadRequestException;
import spring.rest.poc.exception.DuplicateRecordException;
import spring.rest.poc.exception.ResourceNotFoundException;


@ControllerAdvice
public class ExceptionController implements ApplicationEventPublisherAware {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateRecordException.class)
    public @ResponseBody
    RestErrorResponse handleDuplicateRecordException(DuplicateRecordException ex) {

        logger.info("Converting exception to RestResponse : {}", ex.getMessage());
        return new RestErrorResponse(ex, "This record Already Exists.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public @ResponseBody
    RestErrorResponse handleNotFoundException(ResourceNotFoundException ex) {

        logger.info("Converting exception to RestResponse : {}", ex.getMessage());
        return new RestErrorResponse(ex, "Sorry, requested resource was not found.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody
    RestErrorResponse handleBadRequestException(BadRequestException ex) {

        logger.info("Converting exception to RestResponse : {}", ex.getMessage());
        return new RestErrorResponse(ex, "Invalid Request.");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    RestErrorResponse handleInternalServerException(RuntimeException ex) {

        logger.info("Converting exception to RestResponse : {}", ex.getMessage());
        return new RestErrorResponse(ex, "Something went wrong.");
    }
}
