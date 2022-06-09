package ua.mike.pub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {

    private UUID id;
    private UUID beerId;
    private Integer orderedQty;
    private Integer allocatedQty;
}
