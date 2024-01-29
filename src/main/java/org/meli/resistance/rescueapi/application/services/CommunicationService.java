package org.meli.resistance.rescueapi.application.services;

import org.meli.resistance.rescueapi.common.exceptions.InsufficientDataException;
import org.meli.resistance.rescueapi.domain.ports.CommunicationPort;
import org.springframework.stereotype.Service;

@Service
public class CommunicationService implements CommunicationPort {
    @Override
    public String getMessage(String[][] messages) {
        if(
            messages[0].length != messages[1].length ||
            messages[1].length != messages[2].length
        ) {
            throw new InsufficientDataException("La información de los mensajes es inconsistente.");
        }

        int messageLength = messages[0].length;
        String[] completeMessage = new String[messageLength];

        for(int i = 0; i < messageLength; i++) {
            for(int j = 0; j < 3; j++) {
                String messageElement = messages[j][i];
                if(messageElement != null && !messageElement.isEmpty()) {
                    completeMessage[i] = messageElement;
                    break;
                }
            }
            if(completeMessage[i] == null)
                throw new InsufficientDataException("No hay suficiente información para recuperar el mensaje");
        }

        return String.join(" ", completeMessage);
    }
}
