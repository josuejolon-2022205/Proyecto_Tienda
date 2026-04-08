package com.JosueJolon.ProyectoTienda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoTiendaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoTiendaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("test api");
    }
}
