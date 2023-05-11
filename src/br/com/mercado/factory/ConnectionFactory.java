package br.com.mercado.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    //Nome do usuário do mysql
    private static final String USERNAME = "root";
    // senha do banco
    private static final String PASSWORD = "root";

    //Caminho do banco de dados, porta, nome do banco de dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mercado";
    /*
    Conexão com o banco de dados
     */
    public static Connection createConectionToMySQL() throws Exception{
        //Faz com que a classe seja carregada pela JVM
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Cria a conexão com o banco de dados
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;
    }

    public static void main(String[] args) throws Exception{
        // Recuperar uma conexão com o banco de dados
        Connection con = createConectionToMySQL();

        //Testar conexão
        if(con!=null){
            System.out.println("Conexão obetida com sucesso!");
            con.close();
        }
    }
}
