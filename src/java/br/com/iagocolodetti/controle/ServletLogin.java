package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.Usuario;
import br.com.iagocolodetti.modelo.UsuarioDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        
        Usuario usuario = new UsuarioDAO().buscar(new Usuario(request.getParameter("nome"), request.getParameter("senha")));
        
        if (usuario.getId() != -1) {
            HttpSession objSessao = request.getSession();
            if (objSessao == null) {
                objSessao = request.getSession(true);
            }
            objSessao.setAttribute("usuario", usuario);
            
            rd = request.getRequestDispatcher("agenda");
        }
        else {
            request.setAttribute("nome", Util.decodificar(usuario.getNome()));
            request.setAttribute("erro", "Erro: Usuário e/ou senha inválido(s)");
            rd = request.getRequestDispatcher("index.jsp");
        }
        
        rd.forward(request, response);
    }
}
