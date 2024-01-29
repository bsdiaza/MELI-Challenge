package org.meli.resistance.rescueapi.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageInfo {
    private String[] message;
    private double distance;

}
