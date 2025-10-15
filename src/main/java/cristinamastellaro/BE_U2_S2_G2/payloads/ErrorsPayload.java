package cristinamastellaro.BE_U2_S2_G2.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class ErrorsPayload {
    private String message;
    private LocalDateTime time;
}
