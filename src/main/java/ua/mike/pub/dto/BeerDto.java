package ua.mike.pub.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto implements Serializable  {

    private UUID id;
    private String beerName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantityOnHand;

}
