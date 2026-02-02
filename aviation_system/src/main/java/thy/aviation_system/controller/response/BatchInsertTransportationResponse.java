package thy.aviation_system.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import thy.aviation_system.dto.TransportationDTO;
import java.util.List;

@Getter
@Setter
@Builder
public class BatchInsertTransportationResponse {
    private int totalRequested;
    private int totalSuccess;
    private int totalFailed;
    private List<TransportationDTO> successList;
    private List<BatchErrorDetail> errorList;
}
