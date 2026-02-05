package se.iths.connie.serviceandcomponent.exception;

public class MaxWithdrawalExceededException extends RuntimeException {

    public MaxWithdrawalExceededException(String message) {
        super(message);
    }
}