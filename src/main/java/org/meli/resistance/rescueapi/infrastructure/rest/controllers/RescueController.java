package org.meli.resistance.rescueapi.infrastructure.rest.controllers;

import jakarta.validation.Valid;
import org.meli.resistance.rescueapi.domain.models.DecryptedRescueMessage;
import org.meli.resistance.rescueapi.domain.ports.RescuePort;
import org.meli.resistance.rescueapi.infrastructure.rest.requests.DecryptRescueMessageRequest;
import org.meli.resistance.rescueapi.infrastructure.rest.response.DecryptedMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1.0/rescue")
public class RescueController {

    @Autowired
    private RescuePort rescuePort;


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @PostMapping("/top-secret")
    public ResponseEntity<DecryptedMessageResponse> decryptRescueMessage(
            @Valid @RequestBody DecryptRescueMessageRequest rescueMessageRequest
    ) {
        DecryptedRescueMessage decryptedMessage = rescuePort.decryptRescueMessage(rescueMessageRequest.getSatellites());
        DecryptedMessageResponse response = new DecryptedMessageResponse(decryptedMessage.getPosition(),decryptedMessage.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
