package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.Usuario;
import br.com.iagocolodetti.modelo.UsuarioDAO;
import br.com.iagocolodetti.modelo.UsuarioExisteException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletCadastrar extends HttpServlet {

    private void erro(HttpServletRequest request, String erro) {
        request.setAttribute("erro", "Erro: " + erro);
        request.setAttribute("nome", Util.decodificar(request.getParameter("nome")));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!request.getSession().isNew() && request.getSession().getAttribute("usuario") != null) {
            response.sendRedirect("agenda");
        }
        else {
            response.sendRedirect("cadastrar.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!request.getSession().isNew() && request.getSession().getAttribute("usuario") != null) {
            response.sendRedirect("agenda");
        }
        else {
            try {
                Usuario usuario = new Usuario(Util.decodificar(request.getParameter("nome")), Util.decodificar(request.getParameter("senha")));
                new UsuarioDAO().cadastrar(usuario);
                request.setAttribute("sucesso", "Usuário \"" + usuario.getNome() + "\" cadastrado com sucesso.");
            }
            catch (IllegalArgumentException | IndexOutOfBoundsException | UsuarioExisteException e) {
                erro(request, e.getMessage());
            }
            request.getRequestDispatcher("cadastrar.jsp").forward(request, response);
        }
    }
}
