package br.com.mercado.aplicacao;

import br.com.mercado.dao.ClienteDAO;
import br.com.mercado.model.Cliente;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        while(true){
            Cliente cliente = new Cliente();
            ClienteDAO clienteDao = new ClienteDAO();

            Scanner scn = new Scanner(System.in);  // Create a Scanner object

            System.out.println("Escolha uma opção: \n" +
                    "[ 1 ] -> Criar um novo cadastro\n" +
                    "[ 2 ] -> Ler todos os cadastros\n" +
                    "[ 3 ] -> Alterar um cadastro\n" +
                    "[ 4 ] -> Deletar um cadastro\n" +
                    "[ 5 ] -> Pesquisar por nome\n" +
                    "[ 6 ] -> Exibir um cliente\n" +
                    "[ 0 ] -> Sair do programa\n");

            int opcao = scn.nextInt();

            if(opcao==0){
                break;
            } else if (opcao==1) { // CREATE
                novoCadastro(cliente, clienteDao, scn);
            } else if (opcao==2) { // READ
                printTabela(clienteDao);
            } else if (opcao==3) { // UPDATE
                alterarCadastro(clienteDao, scn);
            } else if (opcao==4) { // DELETE
                deletarCadastro(clienteDao, scn);
            } else if (opcao==5) { // BUSCA POR NOME
                buscarPorNome(clienteDao, scn);
            } else if (opcao==6) { // BUSCA POR ID
                exibirItemPeloID(clienteDao, scn);
            }
        }

    }

    public static void printTabela(ClienteDAO clienteDao){
        System.out.println("ID | NOME | CELULAR | DATA ");
        for(Cliente c : clienteDao.getCliente()){
            System.out.println(" " + c.getId() +
                    " | "+ c.getNome() +
                    " | "+ c.getCelular() +
                    " | "+ c.getDesconto());
        }
    }
    public static void novoCadastro(Cliente cliente, ClienteDAO clienteDao, Scanner scn){
        System.out.println("\n*** Novo Cadastro ***\n");

        System.out.println("Digite um nome: ");
        String nome = scn.nextLine();
        nome = scn.nextLine();
        cliente.setNome(nome);

        System.out.println("Digite uma celular: ");
        int celular = scn.nextInt();
        cliente.setCelular(celular);

        System.out.println("Voce é fã de onePiece? [sim: 1 / não: 0]");
        Boolean desconto = false;
        int fa = scn.nextInt();
        if(fa==1){
            desconto = true;
        }
        cliente.setDesconto(desconto);

        clienteDao.save(cliente);
    }
    public static void buscarPorNome(ClienteDAO clienteDao, Scanner scn){
        System.out.println("\n*** Buscar pelo nome ***\n");
        System.out.println("Qual nome você que procurar:");
        String proc = scn.nextLine();

        System.out.println("ID | NOME | CELULAR | DATA ");
        for(Cliente c : clienteDao.getClientePorNome(proc)) {
            System.out.println(" " + c.getId() +
                    " | " + c.getNome() +
                    " | " + c.getCelular() +
                    " | " + c.getDesconto());
        }
    }
    public static void alterarCadastro(ClienteDAO clienteDao, Scanner scn){
        Cliente c1 = new Cliente();
        Scanner scann = new Scanner(System.in); // scn aux

        System.out.println("Qaul ID você quer fazer a alteração: ");
        int id = scann.nextInt();
        c1.setId(id);// É o número que esta no banco de dados

        System.out.println("Digite um nome: ");
        String nome = scn.nextLine();
        nome = scn.nextLine();
        c1.setNome(nome);

        System.out.println("Digite uma celular: ");
        int celular = scn.nextInt();
        c1.setCelular(celular);

        System.out.println("Voce é fã de onePiece? [sim: 1 / não: 0]");
        Boolean desconto = false;
        int fa = scn.nextInt();
        if(fa==1) {
            desconto = true;
        }
        c1.setDesconto(desconto);

        clienteDao.update(c1);
    }
    public static void deletarCadastro(ClienteDAO clienteDao, Scanner scn){
        System.out.println("Qaul ID você quer fazer a alteração: ");
        int id = scn.nextInt();
        clienteDao.deleteByID(id);
    }
    public static void exibirItemPeloID(ClienteDAO clienteDao, Scanner scn) {
        System.out.println("\n*** Buscar pelo id ***\n");
        System.out.println("Qual id você quer procurar:");
        int proc = scn.nextInt();

        Cliente cliente = clienteDao.getClientePorID(proc);
        if (cliente != null) {
            System.out.println("ID | NOME | CELULAR | DATA ");
            System.out.println(" " + cliente.getId() +
                    " | " + cliente.getNome() +
                    " | " + cliente.getCelular() +
                    " | " + cliente.getDesconto());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

}
