package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // CREATE
    public void save(Cliente cliente){
        String sql = "INSERT INTO cliente(nome,celular,desconto) VALUES (?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            //Criar uma conexão com o banco
            conn = ConnectionFactory.createConectionToMySQL();

            // Criamos uma PraparedStatement, para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //Adicionar os valores que são esperados pela query
            pstm.setString(1, cliente.getNome());
            pstm.setInt(2, cliente.getCelular());
            pstm.setBoolean(3, cliente.getDesconto());
            // executa query
            pstm.execute();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //Fechar as conexões
            try {
                if(pstm!=null){
                    pstm.close();
                }
                if (conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // READ
    public List<Cliente> getCliente(){
        String sql = "SELECT * FROM cliente";

        List<Cliente> clientes = new ArrayList<Cliente>();

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco """SELECT"""
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();
            while (rset.next()) {
                Cliente cliente = new Cliente();
                // recuperar o id
                cliente.setId(rset.getInt("id"));
                // recuperar nome
                cliente.setNome(rset.getString("nome"));
                // recuperar Celular
                cliente.setCelular(rset.getInt("celular"));
                // recuperar data de cadastro
                cliente.setDesconto(rset.getBoolean("desconto"));

                clientes.add(cliente);
            }
            } catch(Exception e){
                e.printStackTrace();
            }finally{
                try {
                    if (rset != null) {
                        rset.close();
                    }
                    if (pstm != null) {
                        pstm.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return clientes;

    }

    // UPDATE
    public void update(Cliente cliente){
        String sql = "UPDATE cliente SET nome = ?, celular = ?, desconto = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar conexão com o banco
            conn = ConnectionFactory.createConectionToMySQL();

            //Criar a classe para eecutar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Adicionar os valores para atualizar
            pstm.setString(1, cliente.getNome());
            pstm.setInt(2, cliente.getCelular());
            pstm.setBoolean(3, cliente.getDesconto());

            //Qual o ID do registro que deseja atualizar?
            pstm.setInt(4, cliente.getId());

            //Executar a query
            pstm.execute();


        } catch (Exception e){
            e.printStackTrace();

        } finally {
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // DELETE
    public void deleteByID(int id){

        String sql = "DELETE FROM cliente WHERE id=?";

        Connection conn = null;

        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1,id);

            pstm.execute();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    // -------------------------------------------------------
    // SEACH FOR NAME
    public List<Cliente> getClientePorNome(String nome){

        String sql = "SELECT * FROM cliente WHERE nome=?";

        List<Cliente> clientes = new ArrayList<Cliente>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco """SELECT"""
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1,nome);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Cliente cliente = new Cliente();
                // recuperar o id
                cliente.setId(rset.getInt("id"));
                // recuperar nome
                cliente.setNome(rset.getString("nome"));
                // recuperar celular
                cliente.setCelular(rset.getInt("celular"));
                // recuperar data de cadastro
                cliente.setDesconto(rset.getBoolean("desconto"));

                clientes.add(cliente);
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientes;

    }

    // SEACH FOR ID
    public Cliente getClientePorID(int id) {
        String sql = "SELECT * FROM cliente WHERE id=?";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Cliente cliente = null;

        try {
            conn = ConnectionFactory.createConectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {
                cliente = new Cliente();
                cliente.setId(rset.getInt("id"));
                cliente.setNome(rset.getString("nome"));
                cliente.setCelular(rset.getInt("celular"));
                cliente.setDesconto(rset.getBoolean("desconto"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }


}
