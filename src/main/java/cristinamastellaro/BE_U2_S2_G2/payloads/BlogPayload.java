package cristinamastellaro.BE_U2_S2_G2.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogPayload {
    private String categoria;
    private String titolo;
    private String contenuto;
    private String cover;
    private int tempoDiLettura;
}
