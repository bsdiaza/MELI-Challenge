package org.meli.resistance.rescueapi.domain.models;

import lombok.*;

import java.awt.Point;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DecryptedRescueMessage {
    private Point position;
    private String message;

}
