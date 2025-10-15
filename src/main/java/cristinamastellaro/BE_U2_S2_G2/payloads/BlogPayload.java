package cristinamastellaro.BE_U2_S2_G2.payloads;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BlogPayload {
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
    private UUID autoreId;

    public BlogPayload(String categoria, String titolo, String contenuto, int tempoDiLettura, UUID autoreId) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autoreId = autoreId;
    }
}
