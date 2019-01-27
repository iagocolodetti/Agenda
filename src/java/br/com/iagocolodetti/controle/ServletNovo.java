package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.ContatoExisteException;
import br.com.iagocolodetti.modelo.Contato;
import br.com.iagocolodetti.modelo.ContatoDAO;
import br.com.iagocolodetti.modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletNovo extends HttpServlet {

    private void erro(HttpServletRequest request, String erro) {
        request.setAttribute("erro", "Erro: " + erro);
        request.setAttribute("nome", Util.decodificar(request.getParameter("nome")));
        request.setAttribute("email", Util.decodificar(request.getParameter("email")));
        request.setAttribute("telefone", Util.decodificar(request.getParameter("telefone")));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.sendRedirect("novo.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        if (!request.getSession().isNew() && usuario != null) {
            try {
                Contato contato = new Contato(Util.decodificar(request.getParameter("nome")), Util.decodificar(request.getParameter("email")), Util.decodificar(request.getParameter("telefone")));
                new ContatoDAO().cadastrar(usuario.getId(), contato);
                request.setAttribute("sucesso", "Novo contato cadastrado com sucesso.");
            }
            catch (ContatoExisteException e) {
                erro(request, e.getMessage());
            }
        }
        
        request.getRequestDispatcher("novo.jsp").forward(request, response);
    }
}
