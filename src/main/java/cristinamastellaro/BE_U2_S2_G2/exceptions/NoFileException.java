package cristinamastellaro.BE_U2_S2_G2.exceptions;

public class NoFileException extends RuntimeException {
    public NoFileException() {
        super("Il file è vuoto!");
    }
}
