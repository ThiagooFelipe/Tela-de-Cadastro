package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteDeConexao {
	public static void main(String[] args) throws SQLException {
		
		Connection connection = ConnectionFactory.faz_conexao();
        System.out.println("Conexão aberta!");
        connection.close();
    }
}
