package org.meli.resistance.rescueapi.application.services;


import org.junit.jupiter.api.Test;
import org.meli.resistance.rescueapi.common.exceptions.InsufficientDataException;
import org.meli.resistance.rescueapi.domain.models.DecryptedRescueMessage;
import org.meli.resistance.rescueapi.domain.models.MessageInfo;
import org.meli.resistance.rescueapi.infrastructure.rest.requests.DecryptRescueMessageRequest;
import org.meli.resistance.rescueapi.infrastructure.rest.requests.RescueMessageRequest;
import org.meli.resistance.rescueapi.infrastructure.rest.requests.SatRescueMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
public class RescueServiceTests {

    @Autowired
    private RescueService rescueService;

    @Test
    public void shouldValidateMessagesInfoSuccess() {
        MessageInfo satKenMessageInfo = new MessageInfo(new String[]{"un", "", ""}, 10);
        MessageInfo satSkyMessageInfo = new MessageInfo(new String[]{"", "buen", ""}, 10);
        MessageInfo satSatoMessageInfo = new MessageInfo(new String[]{"", "", "mensaje"}, 10);

        rescueService.validateMessagesInfo(satKenMessageInfo, satSkyMessageInfo, satSatoMessageInfo);
    }

    @Test
    public void shouldValidateMessagesInfoFail() {
        MessageInfo satKenMessageInfo = new MessageInfo(new String[]{"un", "", ""}, 10);
        MessageInfo satSkyMessageInfo = new MessageInfo(new String[]{"", "buen", ""}, 10);
        MessageInfo satSatoMessageInfo = null;

        assertThrows(InsufficientDataException.class, () -> {
            rescueService.validateMessagesInfo(satKenMessageInfo, satSkyMessageInfo, satSatoMessageInfo);
        });
    }

    @Test
    public void shouldGetSatDistances() {
        MessageInfo satSkyMessageInfo = new MessageInfo(new String[]{"", "buen", ""}, 20);
        MessageInfo satKenMessageInfo = new MessageInfo(new String[]{"un", "", ""}, 10);
        MessageInfo satSatoMessageInfo = new MessageInfo(new String[]{"", "", "mensaje"}, 30);

        ArrayList<MessageInfo> messagesInfo = new ArrayList<>();
        messagesInfo.add(satKenMessageInfo);
        messagesInfo.add(satSkyMessageInfo);
        messagesInfo.add(satSatoMessageInfo);

        Double[] distances = rescueService.getSatDistances(messagesInfo);

        Double[] expectedDistances = new Double[]{10.0, 20.0, 30.0};
        assertArrayEquals(distances, expectedDistances);
    }

    @Test
    public void shouldGetSatMessages() {
        MessageInfo satKenMessageInfo = new MessageInfo(new String[]{"un", "", ""}, 10);
        MessageInfo satSkyMessageInfo = new MessageInfo(new String[]{"", "buen", ""}, 20);
        MessageInfo satSatoMessageInfo = new MessageInfo(new String[]{"", "", "mensaje"}, 30);

        ArrayList<MessageInfo> messagesInfo = new ArrayList<>();
        messagesInfo.add(satKenMessageInfo);
        messagesInfo.add(satSkyMessageInfo);
        messagesInfo.add(satSatoMessageInfo);

        String[][] messages = rescueService.getSatMessages(messagesInfo);

        String[][] expectedMessages = new String[][]{
                {"un", "", ""},
                {"", "buen", ""},
                {"", "", "mensaje"}
        };
        assertArrayEquals(messages, expectedMessages);
    }

    @Test
    public void shouldGetMessagesInfo() {

        SatRescueMessageRequest satSkyMessageInfoRequest = new SatRescueMessageRequest(20.0, new String[]{"", "buen", ""}, "Skywalker");
        SatRescueMessageRequest satKenMessageInfoRequest = new SatRescueMessageRequest(10, new String[]{"un", "", ""}, "Kenobi");
        SatRescueMessageRequest satSatoMessageInfoRequest = new SatRescueMessageRequest(30, new String[]{"", "", "mensaje"}, "Sato");

        ArrayList<SatRescueMessageRequest> messagesInfoRequest = new ArrayList<>();
        messagesInfoRequest.add(satKenMessageInfoRequest);
        messagesInfoRequest.add(satSkyMessageInfoRequest);
        messagesInfoRequest.add(satSatoMessageInfoRequest);

        List<MessageInfo> messagesInfo = rescueService.getMessagesInfo(messagesInfoRequest);

        MessageInfo satKenMessageInfo = new MessageInfo(new String[]{"un", "", ""}, 10);
        MessageInfo satSkyMessageInfo = new MessageInfo(new String[]{"", "buen", ""}, 20);
        MessageInfo satSatoMessageInfo = new MessageInfo(new String[]{"", "", "mensaje"}, 30);

        ArrayList<MessageInfo> expectedMessagesInfo = new ArrayList<>();
        expectedMessagesInfo.add(satKenMessageInfo);
        expectedMessagesInfo.add(satSkyMessageInfo);
        expectedMessagesInfo.add(satSatoMessageInfo);

        for(int i = 0; i < messagesInfoRequest.size(); i++) {
            assertEquals(messagesInfo.get(i).getDistance(), expectedMessagesInfo.get(i).getDistance());
            assertArrayEquals(messagesInfo.get(i).getMessage(),expectedMessagesInfo.get(i).getMessage());
        }
    }

    @Test
    public void shouldDecryptRescueMessage() {

        SatRescueMessageRequest satSkyMessageInfoRequest = new SatRescueMessageRequest(200, new String[]{"", "buen", ""}, "Skywalker");
        SatRescueMessageRequest satKenMessageInfoRequest = new SatRescueMessageRequest(670.820393249937, new String[]{"un", "", ""}, "Kenobi");
        SatRescueMessageRequest satSatoMessageInfoRequest = new SatRescueMessageRequest(400, new String[]{"", "", "mensaje"}, "Sato");

        ArrayList<SatRescueMessageRequest> messagesInfoRequest = new ArrayList<>();
        messagesInfoRequest.add(satKenMessageInfoRequest);
        messagesInfoRequest.add(satSkyMessageInfoRequest);
        messagesInfoRequest.add(satSatoMessageInfoRequest);


        DecryptedRescueMessage decryptedRescueMessage = rescueService.decryptRescueMessage(messagesInfoRequest);

        DecryptedRescueMessage expectedDecryptedRescueMessage = new DecryptedRescueMessage(new Point(100,100), "un buen mensaje");

        assertEquals(decryptedRescueMessage.getMessage(), expectedDecryptedRescueMessage.getMessage());
        assertEquals(decryptedRescueMessage.getPosition(), expectedDecryptedRescueMessage.getPosition());
    }
}
