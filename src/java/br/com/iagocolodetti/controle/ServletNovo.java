package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.ContatoExisteException;
import br.com.iagocolodetti.modelo.Contato;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletNovo extends HttpServlet {

    private void erro(HttpServletRequest request, String erro) {
        request.setAttribute("erro", "Erro: " + erro);
        request.setAttribute("nome", util.decodificar(request.getParameter("nome")));
        request.setAttribute("email", util.decodificar(request.getParameter("email")));
        request.setAttribute("telefone", util.decodificar(request.getParameter("telefone")));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.sendRedirect("novo.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!request.getSession().isNew() && request.getSession().getAttribute("usuarioSessao") != null) {
            try {
                CSV.adicionarContato(new Contato(util.decodificar(request.getParameter("nome")), util.decodificar(request.getParameter("email")), util.decodificar(request.getParameter("telefone"))));
                request.setAttribute("sucesso", "Novo contato cadastrado com sucesso.");
            }
            catch (IllegalArgumentException e) {
                erro(request, e.getMessage());
            }
            catch (ContatoExisteException e) {
                erro(request, e.getMessage());
            }
        }
        
        request.getRequestDispatcher("novo.jsp").forward(request, response);
    }
}
