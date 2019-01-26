package br.com.iagocolodetti.controle;

import br.com.iagocolodetti.modelo.Contato;
import br.com.iagocolodetti.modelo.ContatoExisteException;
import br.com.iagocolodetti.modelo.ContatoNaoExisteException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CSV {
    
    private static final String ARQUIVO_CSV = Thread.currentThread().getContextClassLoader().getResource("..//resource//contatos.csv").getFile().replace("%20", " ");
    private static final File ARQUIVO = new File(ARQUIVO_CSV);
    
    public static ArrayList<Contato> carregarContatos() {
        ArrayList<Contato> contatos = new ArrayList<Contato>();
        
        try {
            Scanner is = new Scanner(ARQUIVO);
            while (is.hasNextLine()) {
                String[] dados = is.nextLine().split(";");
                contatos.add(new Contato(dados[0], dados[1], dados[2]));
            }
            is.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return contatos;
    }
    
    public static void adicionarContato(Contato contato) throws IllegalArgumentException, ContatoExisteException {
        if (contato.getNome().contains(";") || contato.getEmail().contains(";") || contato.getTelefone().contains(";"))
            throw new IllegalArgumentException("Caractere \";\" proibido.");
        
        try {
            // ------- Não adicionar o contato se já existir. -------
            ArrayList<Contato> contatos = carregarContatos();
            Iterator<Contato> it = contatos.iterator();
            while (it.hasNext()) if (contato.equals(it.next())) throw new ContatoExisteException();
            // -------------------------------------------------------
            
            FileWriter fw = new FileWriter(ARQUIVO.getAbsoluteFile(), true);
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
    
    public static void alterarContato(Contato atual, Contato novo) throws IllegalArgumentException, ContatoExisteException, ContatoNaoExisteException {
        if (novo.getNome().contains(";") || novo.getEmail().contains(";") || novo.getTelefone().contains(";"))
            throw new IllegalArgumentException("Caractere \";\" proibido.");
        
        try {
            int i = 0, index = -1;
            
            ArrayList<Contato> contatos = carregarContatos();
            Iterator<Contato> it = contatos.iterator();
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
            it = contatos.iterator();
            while (it.hasNext()) if (novo.equals(it.next())) throw new ContatoExisteException();
            // -------------------------------------------------------
            
            contatos.set(index, novo);
            
            /*
            ARQUIVO.delete();
            ARQUIVO.createNewFile();
            */
            FileWriter fw = new FileWriter(ARQUIVO.getAbsoluteFile());
            for (Contato c : contatos) {
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
    
    public static void removerContato(Contato contato) throws ContatoNaoExisteException {
        
        try {
            boolean contatoExiste = false;
            ArrayList<Contato> contatos = carregarContatos();
            Iterator<Contato> it = contatos.iterator();
            while (it.hasNext()) {
                if (it.next().equals(contato)) {
                    it.remove();
                    contatoExiste = true;
                    break;
                }
            }
            if (!contatoExiste) throw new ContatoNaoExisteException();
            
            FileWriter fw = new FileWriter(ARQUIVO.getAbsoluteFile());
            for (Contato c : contatos) {
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
