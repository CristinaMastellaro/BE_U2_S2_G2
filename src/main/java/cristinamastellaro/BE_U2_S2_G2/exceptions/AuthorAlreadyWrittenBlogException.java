package cristinamastellaro.BE_U2_S2_G2.exceptions;

import java.util.UUID;

public class AuthorAlreadyWrittenBlogException extends RuntimeException {
    public AuthorAlreadyWrittenBlogException(UUID id) {
        super("L'autore con id " + id + " ha già scritto un blog!");
    }
}
