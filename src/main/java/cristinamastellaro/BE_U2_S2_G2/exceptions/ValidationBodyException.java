package cristinamastellaro.BE_U2_S2_G2.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationBodyException extends RuntimeException {
    private List<String> errorsList;

    public ValidationBodyException(List<String> errorsList) {
        super("Ci sono errori nella validazione del payload");
        this.errorsList = errorsList;
    }
}
