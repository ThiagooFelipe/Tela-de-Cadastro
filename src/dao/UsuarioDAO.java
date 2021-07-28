package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import factory.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO {

	private Connection connection;
	private int id;
	private String nome;
	private String email;
	private String senha;

	public UsuarioDAO() throws SQLException{ 
        new ConnectionFactory();
		this.connection = ConnectionFactory.faz_conexao();
    } 
	
	public void adiciona(Usuario usuario){ 
        String sql = "INSERT INTO dados_cadastro(nome,email,senha) VALUES(?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
	}
}
