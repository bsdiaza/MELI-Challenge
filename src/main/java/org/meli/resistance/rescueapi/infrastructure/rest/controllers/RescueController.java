package org.meli.resistance.rescueapi.infrastructure.rest.controllers;

import jakarta.validation.Valid;
import org.meli.resistance.rescueapi.domain.models.DecryptedRescueMessage;
import org.meli.resistance.rescueapi.domain.ports.RescuePort;
import org.meli.resistance.rescueapi.infrastructure.rest.requests.DecryptRescueMessageRequest;
import org.meli.resistance.rescueapi.infrastructure.rest.requests.RescueMessageRequest;
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @PostMapping("/top-secret-split/{satelite}")
    public ResponseEntity<String> updateSateliteMessage(
            @Valid @RequestBody RescueMessageRequest rescueMessageRequest,
            @PathVariable String satelite
    ) {
        rescuePort.updateSateliteMessage(satelite, rescueMessageRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Mensaje actualizado correctamente");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/top-secret-split")
    public ResponseEntity<DecryptedMessageResponse> decryptRescueMessageSplit(
    ) {
        DecryptedRescueMessage decryptedMessage = rescuePort.decryptRescueMessageSplit();
        DecryptedMessageResponse response = new DecryptedMessageResponse(decryptedMessage.getPosition(),decryptedMessage.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
