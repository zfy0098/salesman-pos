package com.rhjf.salesman.service;

/**
 * Created by hadoop on 2017/10/19.
 *
 * @author hadoop
 *
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
