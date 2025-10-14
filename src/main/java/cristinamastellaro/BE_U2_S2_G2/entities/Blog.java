package cristinamastellaro.BE_U2_S2_G2.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Getter
@Setter
@ToString
public class Blog {
    @Setter(AccessLevel.NONE)
    private long id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;

    public Blog(String categoria, String titolo, String contenuto, int tempoDiLettura) {
        Random rd = new Random();
        id = rd.nextInt(100000, 999999);
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        cover = "https://picsum.photos/200/300";
    }
}
