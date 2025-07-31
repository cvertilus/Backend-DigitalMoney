package com.digitalMoney.demo.model;

import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CvuAliasGenerator {
    private static final Random random = new Random();
    private Integer cantidadDePalabras = 3;
    private Integer cantidadDeDigitos=22;

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

    private List<String> leerPalabrasDesdeArchivo() {
        List<String> palabras = new ArrayList<>();
        try (
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("alias.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))
        ) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                palabras.add(linea.trim());
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Error al leer el archivo de palabras.", e);
        }
        return palabras;
    }
}
