package cristinamastellaro.BE_U2_S2_G2.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsValidationDTO(String message, LocalDateTime timeStamp, List<String> errorMessages) {
}
