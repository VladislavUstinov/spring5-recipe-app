package guru.springframework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyNumberFormatException extends RuntimeException{
    public MyNumberFormatException() {
        super();
    }

    public MyNumberFormatException(String message) {
        super(message);
    }

    public MyNumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
