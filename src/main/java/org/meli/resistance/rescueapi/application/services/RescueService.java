package org.meli.resistance.rescueapi.application.services;

import org.meli.resistance.rescueapi.common.exceptions.InsufficientDataException;
import org.meli.resistance.rescueapi.domain.models.DecryptedRescueMessage;
import org.meli.resistance.rescueapi.domain.models.MessageInfo;
import org.meli.resistance.rescueapi.domain.models.Satelite;
import org.meli.resistance.rescueapi.domain.ports.CommunicationPort;
import org.meli.resistance.rescueapi.domain.ports.LocationPort;
import org.meli.resistance.rescueapi.domain.ports.RescuePort;
import org.meli.resistance.rescueapi.infrastructure.rest.requests.RescueMessageRequest;
import org.meli.resistance.rescueapi.infrastructure.rest.requests.SatRescueMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RescueService implements RescuePort {

    @Autowired
    private CommunicationPort communicationPort;
    @Autowired
    private LocationPort locationPort;

    @Override
    public DecryptedRescueMessage decryptRescueMessage(List<SatRescueMessageRequest> rescueMessages) {
        ArrayList<MessageInfo> messageInfo = getMessagesInfo(rescueMessages);
        Double[] satDistances = getSatDistances(messageInfo);
        String[][] satMessages = getSatMessages(messageInfo);

        Point shipPosition = locationPort.GetLocation(satDistances);
        String decryptedMessage = communicationPort.getMessage(satMessages);

        return new DecryptedRescueMessage(shipPosition, decryptedMessage);
    }

    public void updateSateliteMessage(
            String sateliteName,
            RescueMessageRequest rescueMessageRequest
    ) {
        getSateliteByName(sateliteName);
        Satelite satelite = Satelite.getSateliteByName(sateliteName);
        satelite.setDistance(rescueMessageRequest.getDistance());
        satelite.setMessage(rescueMessageRequest.getMessage());
        Satelite.updateSatelite(satelite);
    }

    public DecryptedRescueMessage decryptRescueMessageSplit() {
        List<SatRescueMessageRequest> satRescueMessagesRequest = new ArrayList<>();
        List<String> satelitesNames = Satelite.getSatelitesNames();

        for(String sateliteName: satelitesNames) {
            Satelite satelite = Satelite.getSateliteByName(sateliteName);
            SatRescueMessageRequest satRescueMessageRequest = new SatRescueMessageRequest(
                    satelite.getDistance(),
                    satelite.getMessage(),
                    sateliteName
            );
            satRescueMessagesRequest.add(satRescueMessageRequest);
        }

        return decryptRescueMessage(satRescueMessagesRequest);
    }

     public ArrayList<MessageInfo> getMessagesInfo(List<SatRescueMessageRequest> rescueMessages) {
         Map<String, MessageInfo> messagesInfoMap = new HashMap<>();

         for (SatRescueMessageRequest rescueMessage : rescueMessages) {
             String satName = rescueMessage.getName();
             Satelite satelite = Satelite.getSateliteByName(satName);
             if(satelite == null)
                 continue;
             messagesInfoMap.put(satName, new MessageInfo(rescueMessage.getMessage(), rescueMessage.getDistance()));
         }

         MessageInfo satKenMSGInfo = messagesInfoMap.get(Satelite.KENOBI.getName());
         MessageInfo satSkyMSGInfo = messagesInfoMap.get(Satelite.SKYWALKER.getName());
         MessageInfo satSatoMSGInfo = messagesInfoMap.get(Satelite.SATO.getName());

         validateMessagesInfo(satKenMSGInfo, satSkyMSGInfo, satSatoMSGInfo);

         ArrayList<MessageInfo> messagesInfo = new ArrayList<>();
         messagesInfo.add(satKenMSGInfo);
         messagesInfo.add(satSkyMSGInfo);
         messagesInfo.add(satSatoMSGInfo);

         return messagesInfo;
     }

    public String[][] getSatMessages(List<MessageInfo> messagesInfo) {
        return messagesInfo.stream().map(MessageInfo::getMessage).toArray(String[][]::new);
    }

    public Double[] getSatDistances(List<MessageInfo> messagesInfo) {
        return messagesInfo.stream().map(MessageInfo::getDistance).toArray(Double[]::new);
    }

    public void validateMessagesInfo(
            MessageInfo satKenMSGInfo,
            MessageInfo satSkyMSGInfo,
            MessageInfo satSatoMSGInfo
    ) {
        if(
                satKenMSGInfo == null ||
                satSkyMSGInfo == null ||
                satSatoMSGInfo == null
        ) {
            throw new InsufficientDataException("Es necesario ingresar la información de los tres satelites para calcular su ubicación y mensaje");
        }
    }

    public Satelite getSateliteByName(
            String sateliteName
    ) {
        Satelite satelite = Satelite.getSateliteByName(sateliteName);

        if(satelite == null)
            throw  new InsufficientDataException("La inforamcion del satelite no coincide con los satelites registrados: [ 'kenobi', 'Skywalker', 'Sato' ]");

        return satelite;
    }
}
