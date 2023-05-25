package br.com.mercado.aplicacao;

import br.com.mercado.dao.ClienteDAO;
import br.com.mercado.model.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
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
                printTabela(clienteDao);
            } else if (opcao==2) { // READ
                printTabela(clienteDao);
            } else if (opcao==3) { // UPDATE
                printTabela(clienteDao);
                alterarCadastro(cliente, clienteDao, scn);
            } else if (opcao==4) { // DELETE
                printTabela(clienteDao);
                deletarCadastro(clienteDao, scn);
            } else if (opcao==5) { // BUSCA POR NOME
                buscarPorNome(clienteDao, scn);
            } else if (opcao==6) { // BUSCA POR ID
                exibirItemPeloID(clienteDao, scn);
            } 
        }

    }

    public static void exibirRelatorio() {
        try {
            File arquivo = new File("relatorio.txt");
            Scanner scanner = new Scanner(arquivo);

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                System.out.println(linha);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo do relatório não foi encontrado.");
        }
    }


    public static void printTabela(ClienteDAO clienteDao){
        System.out.println("ID | NOME | TELEFONE | ENDEREÇO | FLAMENGUISTA | FANBOY ONE PIECCE | NASC. EM SOUZA ");
        for(Cliente c : clienteDao.getCliente()){
            System.out.println(" " + c.getId() +
                    " | "+ c.getNome() +
                    " | "+ c.getTelefone() +
                    " | "+ c.getEndereco() +
                    " | "+ c.getTorcedorFlamengo() +
                    " | "+ c.getFanOnePiece() +
                    " | "+ c.getSouza()
            );
        }
    }
    public static void novoCadastro(Cliente cliente, ClienteDAO clienteDao, Scanner scn) {
        System.out.println("\n*** Novo Cadastro ***\n");

        System.out.println("Digite um nome: ");
        String nome = scn.nextLine();
        nome = scn.nextLine();

        System.out.println("Digite um numero de telefone: ");
        int telefone = scn.nextInt();

        System.out.println("Digite um endereco: ");
        String endereco = scn.nextLine();
        endereco = scn.nextLine();

        System.out.println("Voce é torcedor do flamengo? [sim: 1 / não: 0]");
        Boolean flamengo = false;
        int val1 = scn.nextInt();if(val1==1){flamengo = true;}

        System.out.println("Voce é Fã de One Piece? [sim: 1 / não: 0]");
        Boolean onepiece = false;
        int val2 = scn.nextInt();if(val2==1){onepiece = true;}

        System.out.println("Voce é de Souza? [sim: 1 / não: 0]");
        Boolean souza = false;
        int val3 = scn.nextInt();if(val3==1){souza = true;}

        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        cliente.setTorcedorFlamengo(flamengo);
        cliente.setFanOnePiece(onepiece);
        cliente.setSouza(souza);

        clienteDao.save(cliente);
    }
    public static void alterarCadastro(Cliente cliente, ClienteDAO clienteDao, Scanner scn){

        System.out.println("\n*** ALterar Cadastro ***\n");

        System.out.println("Qaul ID você quer fazer a alteração: ");
        int id = scn.nextInt();

        System.out.println("Digite um nome: ");
        String nome = scn.nextLine();
        nome = scn.nextLine();

        System.out.println("Digite um numero de telefone: ");
        int telefone = scn.nextInt();

        System.out.println("Digite um endereco: ");
        String endereco = scn.nextLine();
        endereco = scn.nextLine();

        System.out.println("Voce é torcedor do flamengo? [sim: 1 / não: 0]");
        Boolean flamengo = false;
        int val1 = scn.nextInt();if(val1==1){flamengo = true;}

        System.out.println("Voce é Fã de One Piece? [sim: 1 / não: 0]");
        Boolean onepiece = false;
        int val2 = scn.nextInt();if(val2==1){onepiece = true;}

        System.out.println("Voce é de Souza? [sim: 1 / não: 0]");
        Boolean souza = false;
        int val3 = scn.nextInt();if(val3==1){souza = true;}

        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        cliente.setTorcedorFlamengo(flamengo);
        cliente.setFanOnePiece(onepiece);
        cliente.setSouza(souza);

        clienteDao.update(cliente);
    }
    public static void deletarCadastro(ClienteDAO clienteDao, Scanner scn){
        System.out.println("Qual ID você quer fazer a alteração: ");
        int id = scn.nextInt();
        clienteDao.deleteByID(id);
    }
    public static void buscarPorNome(ClienteDAO clienteDao, Scanner scn){
         System.out.println("\n*** Buscar pelo nome ***\n");
         System.out.println("Qual nome você que procurar:");
         String nome = scn.nextLine();
         nome = scn.nextLine();

         System.out.println("ID | NOME | TELEFONE | ENDEREÇO | FLAMENGUISTA | FANBOY ONE PIECCE | NASC. EM SOUZA ");
         for(Cliente c : clienteDao.getClientePorNome(nome)){
             System.out.println(" " + c.getId() +
                     " | "+ c.getNome() +
                     " | "+ c.getTelefone() +
                     " | "+ c.getEndereco() +
                     " | "+ c.getTorcedorFlamengo() +
                     " | "+ c.getFanOnePiece() +
                     " | "+ c.getSouza()
             );
         }
     }
    public static void exibirItemPeloID(ClienteDAO clienteDao, Scanner scn) {
        System.out.println("\n*** Buscar pelo id ***\n");
        System.out.println("Qual id você quer procurar:");
        int id = scn.nextInt();

        Cliente cliente = clienteDao.getClientePorID(id);

        if (cliente != null) {
            System.out.println("ID | NOME | TELEFONE | ENDEREÇO | FLAMENGUISTA | FANBOY ONE PIECCE | NASC. EM SOUZA ");
            System.out.println(" " + cliente.getId() +
                    " | "+ cliente.getNome() +
                    " | "+ cliente.getTelefone() +
                    " | "+ cliente.getEndereco() +
                    " | "+ cliente.getTorcedorFlamengo() +
                    " | "+ cliente.getFanOnePiece() +
                    " | "+ cliente.getSouza()
            );
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

}
