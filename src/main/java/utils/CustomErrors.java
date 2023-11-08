package main.java.utils;

public class CustomErrors {
    public static class InvalidInputArg extends RuntimeException {
        public InvalidInputArg(String message) {
            super(message);
        }
    }
}
