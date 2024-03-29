package website.applicants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GetEnrolleeException extends IllegalArgumentException {
    public GetEnrolleeException(String message) {
        super(message);
    }
}
