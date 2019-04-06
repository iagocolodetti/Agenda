package br.com.iagocolodetti.modelo;

/**
 *
 * @author iagocolodetti
 */
public class ContatoNaoExisteException extends Exception {

    public ContatoNaoExisteException() {
        super("Contato não existe.");
    }

    public ContatoNaoExisteException(String mensagem) {
        super(mensagem);
    }
}
