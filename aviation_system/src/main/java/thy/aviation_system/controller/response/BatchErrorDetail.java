package thy.aviation_system.controller.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BatchErrorDetail {
    private int index;
    private String errorMessage;
}
