package org.meli.resistance.rescueapi.infrastructure.rest.requests;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RescueMessageRequest {
    private double distance;
    private String[] message;

}
