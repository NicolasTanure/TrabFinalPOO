package src.model.collections;

import src.model.Cliente;

import java.util.LinkedList;

public class Clientela {
    private LinkedList<Cliente> clientes;
    private String erro;

    public Clientela() {
        clientes = new LinkedList<>();
    }

    public String getErro() {
        return erro;
    }

    public Cliente getCliente(int cod) {
        for (Cliente c : clientes) {
            if (c.getCod() == cod) {
                return c;
            }
        }
        return null;
    }

    public boolean adicionarCliente(Cliente c) {
        if (!consultar(c)) {
            return clientes.add(c);
        }
        return false;
    }

    public boolean consultar(Cliente c) {
        for (Cliente cl : clientes) {
            if (cl.getCod() == c.getCod()) {
                erro = "Código já existente!";
                return true;
            }
            else if (cl.getNome().equals(c.getNome())) {
                erro = "Nome já existente!";
                return true;
            }
            else if (cl.getEmail().equals(c.getEmail())) {
                erro = "Email já existente!";
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Códido;Nome;Email\n");

        for (Cliente c : clientes) {
            s.append(
                   c.getCod() + ";"
                   + c.getNome() + ";"
                    + c.getEmail() + "\n"
            );
        }
        return s.toString();
    }
}
