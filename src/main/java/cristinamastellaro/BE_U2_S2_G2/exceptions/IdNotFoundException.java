package cristinamastellaro.BE_U2_S2_G2.exceptions;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(UUID id) {
        super("Non è stato trovato nessun elemento con id " + id);
    }

    public IdNotFoundException(long id) {
        super("Non è stato trovato nessun autore con id " + id);
    }
}
