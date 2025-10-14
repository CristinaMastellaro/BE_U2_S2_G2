package cristinamastellaro.BE_U2_S2_G2.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
@ToString
public class Autore {
    @Setter(AccessLevel.NONE)
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    public Autore(String nome, String cognome, String email, LocalDate dataDiNascita) {
        Random rd = new Random();
        id = rd.nextInt(100000, 999999);
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        avatar = "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;

    }

}
