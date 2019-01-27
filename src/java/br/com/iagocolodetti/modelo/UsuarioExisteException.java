package br.com.iagocolodetti.modelo;

public class UsuarioExisteException extends Exception {
    public UsuarioExisteException() {
        super("Usuário já existe.");
    }
    
    public UsuarioExisteException(String mensagem) {
        super(mensagem);
    }
}
