package org.meli.resistance.rescueapi.domain.ports;

public interface CommunicationPort {
    String getMessage(String[][] messages) throws Exception;
}
