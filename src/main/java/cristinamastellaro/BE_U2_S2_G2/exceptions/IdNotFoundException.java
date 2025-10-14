package cristinamastellaro.BE_U2_S2_G2.exceptions;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(long id) {
        super("Non è stato trovato nessun elemento con id " + id);
    }
}
