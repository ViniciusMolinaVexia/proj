package br.com.nextage.rmaweb.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
	private static Properties properties;

	private static Properties getPropertyUtils() {
		if (properties == null) {
			try (InputStream input = PropertyUtils.class.getClassLoader().getResourceAsStream("app.properties")) {

				properties = new Properties();

				if (input == null) {
					throw new RuntimeException("Arquivo app.properties não encontrado!");
				}

				// load a properties file from class path, inside static method
				properties.load(input);

				// get the property value and print it out
				System.out.println(properties.getProperty("db.url"));
				System.out.println(properties.getProperty("db.user"));
				System.out.println(properties.getProperty("db.password"));

			} catch (IOException ex) {
				ex.printStackTrace();
				throw new RuntimeException("Arquivo app.properties não encontrado!");
			}
		}
		return properties;
	}

	public static String getProperty(String key) {
		return getPropertyUtils().getProperty(key);
	}
}
