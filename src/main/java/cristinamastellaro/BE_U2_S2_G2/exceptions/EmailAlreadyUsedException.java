package cristinamastellaro.BE_U2_S2_G2.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("La mail " + email + " è già in uso");
    }
}
