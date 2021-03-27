package com.example.dahlia_android.data;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class SignUpResult<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    public SignUpResult() {
    }

    @Override
    public String toString() {
        if (this instanceof SignUpResult.Success) {
            SignUpResult.Success success = (SignUpResult.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof SignUpResult.Error) {
            SignUpResult.Error error = (SignUpResult.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    // Success sub-class
    public final static class Success<T> extends SignUpResult {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error extends SignUpResult {
        private Exception error;

        public Error(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }
    }
}