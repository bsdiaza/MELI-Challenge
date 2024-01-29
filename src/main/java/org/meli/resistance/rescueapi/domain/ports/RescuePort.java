package org.meli.resistance.rescueapi.domain.ports;

import org.meli.resistance.rescueapi.domain.models.DecryptedRescueMessage;
import org.meli.resistance.rescueapi.infrastructure.rest.requests.RescueMessageRequest;
import org.meli.resistance.rescueapi.infrastructure.rest.requests.SatRescueMessageRequest;
;import java.util.List;

public interface RescuePort {
    public DecryptedRescueMessage decryptRescueMessage(List<SatRescueMessageRequest> rescueMessages);
    public void updateSateliteMessage(String sateliteName, RescueMessageRequest rescueMessageRequest);
    public DecryptedRescueMessage decryptRescueMessageSplit();

}
