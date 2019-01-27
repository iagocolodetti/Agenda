package br.com.iagocolodetti.modelo;

import br.com.iagocolodetti.controle.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    
    // Create
    public void cadastrar(Usuario usuario) throws UsuarioExisteException {
        
        if (usuarioExiste(usuario.getNome())) throw new UsuarioExisteException();
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("INSERT INTO usuario(nome, senha) VALUES(?, ?)");
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
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
            ps = con.prepareStatement("SELECT id FROM usuario WHERE nome = ? and senha = ?");
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());

            rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con, ps, rs);
        }

        return usuario;
    }
    
    private boolean usuarioExiste(String nome) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean existe = false;

        try {
            ps = con.prepareStatement("SELECT id FROM usuario WHERE nome = ?");
            ps.setString(1, nome);

            rs = ps.executeQuery();

            if (rs.next()) {
                existe = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con, ps, rs);
        }

        return existe;
    }
}
