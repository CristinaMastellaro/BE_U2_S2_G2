package cristinamastellaro.BE_U2_S2_G2.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record BlogPayload(
        @NotBlank(message = "La categoria non può essere lasciata vuota")
        String categoria,
        @NotBlank(message = "Il titolo non può essere vuoto")
        @Size(min = 1, max = 50, message = "Il titolo deve avere tra l'1 e i 50 caratteri")
        String titolo,
        @NotBlank(message = "Il contenuto non può essere vuoto")
        String contenuto,
        @NotNull(message = "Il tempo di lettura non può essere nullo")
        int tempoDiLettura,
        @NotNull(message = "È necessario indicare l'uuid dell'autore")
        UUID autoreId) {
}
