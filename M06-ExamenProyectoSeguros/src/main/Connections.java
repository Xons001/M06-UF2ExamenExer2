package main;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Connections {

	public void Connections(Connection conn) {
		String usuari = "root";
		String contr = "";
		String url = "jdbc:mysql://localhost:3306/seguros?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver cargado correctamente");
			conn = DriverManager.getConnection(url, usuari, contr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
