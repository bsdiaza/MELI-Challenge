package org.meli.resistance.rescueapi.infrastructure.rest.requests;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DecryptRescueMessageRequest {
    private List<SatRescueMessageRequest> satellites;

}