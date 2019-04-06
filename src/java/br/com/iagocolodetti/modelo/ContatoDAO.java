package br.com.iagocolodetti.modelo;

/**
 *
 * @author iagocolodetti
 */
public interface ContatoDAO {

    public void cadastrar(int usuario_id, Contato contato)
            throws IndexOutOfBoundsException, IllegalArgumentException, ContatoExisteException;

    public java.util.ArrayList<Contato> carregar(Usuario usuario);

    public boolean contatoExiste(int usuario_id, Contato contato);

    public void alterar(int usuario_id, Contato contato)
            throws IndexOutOfBoundsException, IllegalArgumentException, ContatoExisteException, ContatoNaoExisteException;

    public void remover(int usuario_id, Contato contato)
            throws ContatoNaoExisteException;
}
