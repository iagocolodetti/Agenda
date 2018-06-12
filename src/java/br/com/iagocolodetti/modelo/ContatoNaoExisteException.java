package br.com.iagocolodetti.modelo;

public class ContatoNaoExisteException extends Exception {
    public ContatoNaoExisteException() {
        super("Contato não existe.");
    }
    
    public ContatoNaoExisteException(String mensagem) {
        super(mensagem);
    }
}
