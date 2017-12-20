package fr.cegape.gestion.licence.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description : permet de recuperer les valeurs des cles du fichier properties
 * @author mdiouf
 *
 */
public class MessageUtils {

	/**
	 * LOGGER
	 * 
	 */
	final private static Logger LOGGER = LoggerFactory.getLogger(MessageUtils.class);

	/**
	 * Propriétés chargées
	 */
	private static final Properties PROPERTIES = initProperties();

	/**
	 * Charge le properties
	 * 
	 * @return Le properties chargé
	 */
	private static Properties initProperties() {
		Properties properties = null;
		try {
			properties = new Properties();
			final String pathToFile = System.getProperty("file_property");
			InputStreamReader fileInputStream = new InputStreamReader(new FileInputStream(new File(pathToFile)), "Cp1252");
			properties.load(fileInputStream);
		} catch (IOException e) {
			LOGGER.error("ERROR : " , e);
		}
		return properties;
	}

	/**
	 * Description : permet de recuperer la valeur d'une cle
	 * @param keyMessage
	 * @return : ma valeur de la cle
	 */
	public static String getMessage(final String keyMessage) {

		return PROPERTIES.getProperty(keyMessage);
	}

}
