package br.com.iagocolodetti.controle;

import java.nio.charset.StandardCharsets;

public class Util {
    static public String decodificar(String codificado) {
        return (new String(codificado.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }
}
