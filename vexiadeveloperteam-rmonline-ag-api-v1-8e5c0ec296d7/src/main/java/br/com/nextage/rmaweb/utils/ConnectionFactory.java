package br.com.nextage.rmaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.nextage.rmaweb.util.AppProperties;

public class ConnectionFactory {

	
//	<property name="hibernate.dialect">org.hibernate.dialect.SQLServer2008Dialect</property>
//    <property name="hibernate.connection.driver_class">com.microsoft.sqlserver.jdbc.SQLServerDriver</property>
//    <property name="hibernate.connection.url">jdbc:sqlserver://10.1.10.194;databaseName=RMOnline;</property>
//    <property name="hibernate.connection.username">sisRM</property>
//    <property name="hibernate.connection.password">@cessoAGrm0</property>
    
    
	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(AppProperties.getProperty("db.url"), AppProperties.getProperty("db.user"), AppProperties.getProperty("db.password"));
		} catch (SQLException e) {
			System.out.println("Erro ao conectar com o banco de dados: " + e);
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao carregar driver: " + e);
		}
		
		return null;
	}

}
