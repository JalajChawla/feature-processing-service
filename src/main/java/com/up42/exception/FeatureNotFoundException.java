package com.up42.exception;

/**
 * @author jalajchawla
 */
public class FeatureNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public FeatureNotFoundException(String msg) {
        super(msg);
    }
}
