package cristinamastellaro.BE_U2_S2_G2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AutorePayload(
        @NotBlank(message = "Il nome non può essere nullo")
        @Size(min = 1, max = 35, message = "Il nome deve essere tra 1 e 35 caratteri")
        String nome,
        @NotBlank(message = "Il cognome non può essere nullo")
        @Size(min = 1, max = 45, message = "Il cognome deve essere tra 1 e 45 caratteri")
        String cognome,
        @NotBlank(message = "L'email non può essere nulla")
        @Email
        String email,
        @NotNull(message = "La data di nascita non può essere lasciata vuota")
//        @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "La data di nascita deve essere scritta nel formato yyyy-mm-dd")
        LocalDate dataDiNascita) {
}
