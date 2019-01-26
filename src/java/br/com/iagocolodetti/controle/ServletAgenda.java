package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.Contato;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletAgenda extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Contato> contatos = CSV.carregarContatos();
        if (!contatos.isEmpty()) request.setAttribute("listaContatos", contatos);
        else request.setAttribute("agendavazia", "A agenda ainda não possui contatos.");
        
        request.getRequestDispatcher("agenda.jsp").forward(request, response);
    }
}
