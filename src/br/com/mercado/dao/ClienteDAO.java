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
        String sql = "INSERT INTO cliente(nome, telefone, endereco, IsTorcedorFlamengo, IsFanOnePiece, IsSouza) " +
                     "VALUES (?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            //Criar uma conexão com o banco
            conn = ConnectionFactory.createConectionToMySQL();

            // Criamos uma PraparedStatement, para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //Adicionar os valores que são esperados pela query
            pstm.setString(1, cliente.getNome());
            pstm.setInt(2, cliente.getTelefone());
            pstm.setString(3, cliente.getEndereco());
            pstm.setBoolean(4, cliente.getTorcedorFlamengo());
            pstm.setBoolean(5, cliente.getFanOnePiece());
            pstm.setBoolean(6, cliente.getSouza());
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

                cliente.setId(rset.getInt("idCliente"));
                cliente.setNome(rset.getString("nome"));
                cliente.setTelefone(rset.getInt("telefone"));
                cliente.setEndereco(rset.getString("endereco"));
                cliente.setTorcedorFlamengo(rset.getBoolean("IsTorcedorFlamengo"));
                cliente.setFanOnePiece(rset.getBoolean("IsFanOnePiece"));
                cliente.setSouza(rset.getBoolean("IsSouza"));
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
    //UPDATE
    public void update(Cliente cliente){
        String sql = "UPDATE cliente SET nome = ?, telefone = ?, endereco = ?, " +
                     "isTorcedorFlamengo = ?, isFanOnePiece = ?, isSouza = ? " +
                     "WHERE idCliente = ?";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            //Criar conexão com o banco
            conn = ConnectionFactory.createConectionToMySQL();
            //Criar a classe para eecutar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Adicionar os valores para atualizar
            pstm.setString(1, cliente.getNome());
            pstm.setInt(2, cliente.getTelefone());
            pstm.setString(3, cliente.getEndereco());
            pstm.setBoolean(4, cliente.getTorcedorFlamengo());
            pstm.setBoolean(5, cliente.getFanOnePiece());
            pstm.setBoolean(6, cliente.getSouza());

            //Qual o ID do registro que deseja atualizar?
            pstm.setInt(7, cliente.getId());

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
    //DELETE
    public void deleteByID(int id){

        String sql = "DELETE FROM cliente WHERE idCliente = ?";

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
    // Busca por nome
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

                cliente.setId(rset.getInt("idCliente"));
                cliente.setNome(rset.getString("nome"));
                cliente.setTelefone(rset.getInt("telefone"));
                cliente.setEndereco(rset.getString("endereco"));
                cliente.setTorcedorFlamengo(rset.getBoolean("IsTorcedorFlamengo"));
                cliente.setFanOnePiece(rset.getBoolean("IsFanOnePiece"));
                cliente.setSouza(rset.getBoolean("IsSouza"));
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
    // Busca por ID
    public Cliente getClientePorID(int id) {
        String sql = "SELECT * FROM cliente WHERE idCliente=?";
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
                cliente.setId(rset.getInt("idCliente"));
                cliente.setNome(rset.getString("nome"));
                cliente.setTelefone(rset.getInt("telefone"));
                cliente.setEndereco(rset.getString("endereco"));
                cliente.setTorcedorFlamengo(rset.getBoolean("IsTorcedorFlamengo"));
                cliente.setFanOnePiece(rset.getBoolean("IsFanOnePiece"));
                cliente.setSouza(rset.getBoolean("IsSouza"));
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
