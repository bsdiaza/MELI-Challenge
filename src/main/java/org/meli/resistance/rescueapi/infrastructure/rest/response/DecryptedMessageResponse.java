package org.meli.resistance.rescueapi.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.Point;

@Getter
@Setter
@AllArgsConstructor
public class DecryptedMessageResponse {
    private Point position;
    private String message;

}
