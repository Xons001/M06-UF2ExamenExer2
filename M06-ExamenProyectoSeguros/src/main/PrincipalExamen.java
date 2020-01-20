package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PrincipalExamen {

	//Connections conex = new Connections();

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);

		boolean salir = false;
		while (salir  == false) {
			System.out.println("------------------------------------------------");
			System.out.println("Menu");
			System.out.println("======================");
			System.out.println("1.-Update importe dermatitis");
			System.out.println("2.-Consulta sin acabar");
			System.out.println("3.-Fin");
			System.out.println("=====================");

			int pos = lector.nextInt();
			switch (pos) {
			case 1:
				updateImporteDermatitis();
				break;

			case 2:
				break;

			case 3:
				System.out.println("Fin");
				salir=true;
				break;

			default:
				System.out.println("Escribe bien el numero");
				break;
			}
		}
	}

	public static void updateImporteDermatitis() {

		String usuari = "root";
		String contr = "";
		String url = "jdbc:mysql://localhost:3306/seguros?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver cargado correctamente");
			Connection conn = DriverManager.getConnection(url, usuari, contr);
			System.out.println("Conexion creada correctamente");
			
			PreparedStatement modificar;
			modificar = conn.prepareStatement("update asistenciamedica set importe = ? where breveDescripcion = 'Dermatitis infecciosa'");
			modificar.setDouble(1, 6523.34);
			modificar.executeUpdate();
		
			Statement statement = conn.createStatement();
			String consulta = "select idAsistenciaMedica, breveDescripcion, importe from asistenciamedica where breveDescripcion = 'Dermatitis infecciosa';";
			ResultSet rs = statement.executeQuery(consulta);
			
			while(rs.next()) {
				int idAsistencia = rs.getInt(1);
				String descripcion = rs.getString(2);
				Double importe = rs.getDouble(3);

				System.out.println("ID: " + idAsistencia + ", descripcion: " + descripcion + ", importe: " + importe);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("No ha cargado el driver");
		} catch (SQLException e2) {
			System.out.println("Excepcio del tipus SQL");
			e2.printStackTrace();
		}
		
	}

	public static void MostrarImportePorSeguro() {

		String usuari = "root";
		String contr = "";
		String url = "jdbc:mysql://localhost:3306/seguros?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver cargado correctamente");
			Connection conn = DriverManager.getConnection(url, usuari, contr);
			System.out.println("Conexion creada correctamente");
		
			PreparedStatement cantidad;
			cantidad = conn.prepareStatement("select COUNT(*) from seguro");
			ResultSet rs = cantidad.executeQuery();
			int cant = 0;
			while(rs.next()) {
				cant = rs.getInt(1);
				System.out.println("Cantidad " +cant);
			}
			
			for (int i = 1; i < cant+1; i++) {
				PreparedStatement importe = conn.prepareStatement("select import from asistenciamedica where idSeguro = " + i);
				ResultSet rsSuma = importe.executeQuery();
				double suma = 0;
				while(rsSuma.next()) {
					double importes = rs.getDouble(1);
					suma = suma + importes;
				}
				
				PreparedStatement mostrarSeguro = conn.prepareStatement("select idSeguro from seguro where idSeguro = " + i);
				ResultSet rsConsulta = mostrarSeguro.executeQuery();
				while(rsConsulta.next()) {
					int id = rsConsulta.getInt(1);
					//me falta seguir por aqui
					
				}
				System.out.println("Cantidad " +suma);
			}
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("No ha cargado el driver");
		} catch (SQLException e2) {
			System.out.println("Excepcio del tipus SQL");
			e2.printStackTrace();
		}
		
	}
}
