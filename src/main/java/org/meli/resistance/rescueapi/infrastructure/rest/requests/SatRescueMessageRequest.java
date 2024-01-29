package org.meli.resistance.rescueapi.infrastructure.rest.requests;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SatRescueMessageRequest extends RescueMessageRequest {
    private String name;

    public SatRescueMessageRequest(double distance, String[] message, String name) {
        super(distance, message);
        this.name = name;
    }
}
