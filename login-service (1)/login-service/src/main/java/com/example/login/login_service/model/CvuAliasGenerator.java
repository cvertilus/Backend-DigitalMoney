package com.example.login.login_service.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CvuAliasGenerator {
    private static final String FILE_PATH = "login-service (1)/login-service/src/main/resources/alias.txt";
    private static final Random random = new Random();
    private Integer cantidadDePalabras = 3;
    private Integer cantidadDeDigitos=22;

//    public static void main(String[] args) {
//        Map<String, String> cvuAlias = generarCvuYAlias();
//        System.out.println("CVU: " + cvuAlias.get("cvu"));
//        System.out.println("Alias: " + cvuAlias.get("alias"));
//    }


    public String getCvu(){
        return  generarCvu();
    }

    public String getAlias (){
        return generarAlias();
    }

    private String generarCvu() {
        StringBuilder cvu = new StringBuilder();
        for (int i = 0; i < this.cantidadDeDigitos; i++) {
            cvu.append(random.nextInt(10)); // Genera un dígito del 0 al 9
        }
        return cvu.toString();
    }

    private  String generarAlias() {
        List<String> palabras = leerPalabrasDesdeArchivo();
        if (palabras.size() < this.cantidadDePalabras) {
            throw new IllegalStateException("El archivo debe contener al menos 3 palabras.");
        }

        Collections.shuffle(palabras); // Mezcla aleatoriamente las palabras
        return String.join(".", palabras.subList(0, 3)); // Toma las 3 primeras palabras mezcladas
    }

    private static List<String> leerPalabrasDesdeArchivo() {
        try {
            return Files.readAllLines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de palabras.", e);
        }
    }
}
