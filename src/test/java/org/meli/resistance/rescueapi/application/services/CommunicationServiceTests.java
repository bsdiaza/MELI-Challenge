package org.meli.resistance.rescueapi.application.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = { CommunicationService.class })
public class CommunicationServiceTests {

    @Autowired
    private CommunicationService communicationService;

    @Test
    public void shouldCompleteShipMessage() throws Exception {
        String[] kenMessage = new String[]{ "este", "", "", "mensaje", "" };
        String[] skyMessage =  new String[]{ "", "es", "", "", "secreto" };
        String[] satoMessage =  new String[]{ "este", "", "un", "", "" };
        String[][] messages = new String[][]{ kenMessage, skyMessage, satoMessage };

        String decryptedMessage = communicationService.getMessage(messages);
        assertEquals("este es un mensaje secreto", decryptedMessage);
    }
}
