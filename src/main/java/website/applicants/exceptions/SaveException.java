package website.applicants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SaveException extends Exception {
    public SaveException(String message) {
        super(message);
    }
}
