package org.meli.resistance.rescueapi;

import org.meli.resistance.rescueapi.application.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
public class RescueApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RescueApiApplication.class, args);
	}
}
