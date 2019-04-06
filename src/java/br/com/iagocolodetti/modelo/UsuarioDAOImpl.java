package br.com.iagocolodetti.modelo;

import br.com.iagocolodetti.controle.ConnectionFactory;
import br.com.iagocolodetti.controle.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author iagocolodetti
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    // Create
    public void cadastrar(Usuario usuario)
            throws IllegalArgumentException, IndexOutOfBoundsException, UsuarioExisteException {

        if (usuario.getNome().contains(" ")) {
            throw new IllegalArgumentException("Nome de usuário contém espaços.");
        }
        if (usuario.getNome().length() > 20) {
            throw new IndexOutOfBoundsException("Nome de usuário contém mais que 20 caracteres.");
        }

        if (usuario.getSenha().contains(" ")) {
            throw new IllegalArgumentException("Senha contém espaços.");
        }
        if (usuario.getSenha().length() > 32) {
            throw new IndexOutOfBoundsException("Senha contém mais que 32 caracteres.");
        }

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("INSERT INTO usuario(nome, senha) VALUES(?, ?)");
            ps.setString(1, usuario.getNome());
            ps.setString(2, Util.criptografar(usuario.getSenha()));

            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new UsuarioExisteException();
            } else {
                e.printStackTrace();
            }
        } finally {
            ConnectionFactory.closeConnection(con, ps);
        }
    }

    // Read
    // Se o retorno do ID do usuário for -1, então o Usuário e a Senha não existem ou não coincidem.
    public Usuario buscar(Usuario usuario) {

        usuario.setId(-1);

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT id, senha FROM usuario WHERE nome = ?");
            ps.setString(1, usuario.getNome());

            rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                String criptografada = rs.getString("senha");

                if (Util.verificarSenha(usuario.getSenha(), criptografada)) {
                    usuario.setSenha("");
                } else {
                    usuario.setId(-1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, ps, rs);
        }

        return usuario;
    }
}
