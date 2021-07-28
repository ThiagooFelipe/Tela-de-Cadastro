package factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection faz_conexao() throws SQLException {
	
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://localhost/cadastro", "root", "");
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}
	}
}
