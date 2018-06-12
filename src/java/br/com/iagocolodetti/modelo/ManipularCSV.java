package br.com.iagocolodetti.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ManipularCSV {
    
    private final String ARQUIVO_CSV = Thread.currentThread().getContextClassLoader().getResource("..//resource//contatos.csv").getFile().replace("%20", " ");;
    
    public ArrayList<Contato> carregarContatos() {
        ArrayList<Contato> lista = new ArrayList<Contato>();
        File arquivo = new File(ARQUIVO_CSV);
        try {
            Scanner is = new Scanner(arquivo);
            while (is.hasNextLine()) {
                String[] dados = is.nextLine().split(";");
                lista.add(new Contato(dados[0], dados[1], dados[2]));
            }
            is.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void adicionarContato(Contato contato) throws IllegalArgumentException, ContatoExisteException {
        if (contato.getNome().contains(";") || contato.getEmail().contains(";") || contato.getTelefone().contains(";"))
            throw new IllegalArgumentException("Caractere \";\" proibido.");
        File arquivo = new File(ARQUIVO_CSV);
        try {
            // ------- Não adicionar o contato se já existir. -------
            ArrayList<Contato> lista = carregarContatos();
            Iterator<Contato> it = lista.iterator();
            while (it.hasNext()) if (contato.equals(it.next())) throw new ContatoExisteException();
            // -------------------------------------------------------
            
            FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(), true);
            fw.write(contato.getNome() + ";" + contato.getEmail() + ";" + contato.getTelefone() + System.getProperty("line.separator"));
            fw.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void alterarContato(Contato atual, Contato novo) throws IllegalArgumentException, ContatoExisteException, ContatoNaoExisteException {
        if (novo.getNome().contains(";") || novo.getEmail().contains(";") || novo.getTelefone().contains(";"))
            throw new IllegalArgumentException("Caractere \";\" proibido.");
        File arquivo = new File(ARQUIVO_CSV);
        try {
            int i = 0, index = -1;
            
            ArrayList<Contato> lista = carregarContatos();
            Iterator<Contato> it = lista.iterator();
            // -- Conferir se existe e pegar a posição em que está. --
            while (it.hasNext()) {
                if (it.next().equals(atual)) {
                    index = i;
                    break;
                }
                i++;
            }
            // ------ Se o contato a ser alterado não existir. ------
            if (index == -1) throw new ContatoNaoExisteException();
            // -------------------------------------------------------
            
            // -- Não alterar o contato se já existir outro igual. --
            it = lista.iterator();
            while (it.hasNext()) if (novo.equals(it.next())) throw new ContatoExisteException();
            // -------------------------------------------------------
            
            lista.set(index, novo);
            
            /*
            arquivo.delete();
            arquivo.createNewFile();
            */
            FileWriter fw = new FileWriter(arquivo.getAbsoluteFile());
            for (Contato c : lista) {
                fw.write(c.getNome() + ";" + c.getEmail() + ";" + c.getTelefone() + System.getProperty("line.separator"));
            }
            fw.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void removerContato(Contato contato) throws ContatoNaoExisteException {
        File arquivo = new File(ARQUIVO_CSV);
        try {
            boolean contatoExiste = false;
            ArrayList<Contato> lista = carregarContatos();
            Iterator<Contato> it = lista.iterator();
            while (it.hasNext()) {
                if (it.next().equals(contato)) {
                    it.remove();
                    contatoExiste = true;
                    break;
                }
            }
            if (!contatoExiste) throw new ContatoNaoExisteException();
            
            /*
            arquivo.delete();
            arquivo.createNewFile();
*/
            FileWriter fw = new FileWriter(arquivo.getAbsoluteFile());
            for (Contato c : lista) {
                fw.write(c.getNome() + ";" + c.getEmail() + ";" + c.getTelefone() + System.getProperty("line.separator"));
            }
            fw.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
