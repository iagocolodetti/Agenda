package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.ManipularCSV;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletAgenda extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ManipularCSV mcsv = new ManipularCSV();
        if (!mcsv.carregarContatos().isEmpty()) request.setAttribute("listaContatos", mcsv.carregarContatos());
        else request.setAttribute("agendavazia", "A agenda ainda não possui contatos.");
        
        request.getRequestDispatcher("agenda.jsp").forward(request, response);
    }
}
