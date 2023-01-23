package website.applicants.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SaveException extends DataIntegrityViolationException {
    public SaveException(String message) {
        super(message);
    }
}
