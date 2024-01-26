package org.meli.resistance.rescueapi.application.services;

import org.meli.resistance.rescueapi.domain.ports.CommunicationPort;

public class CommunicationService implements CommunicationPort {
    @Override
    public String getMessage(String[][] messages) {
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

        }

        return String.join(" ", completeMessage);
    }
}
