package fr.cegape.gestion.licence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.cegape.gestion.licence.model.GeneratorLicenceModel;
import fr.cegape.gestion.licence.panel.GeneratorLicenceFrame;
import fr.cegape.gestion.licence.utils.GeneratorLicenceUtils;
import fr.cegape.gestion.licence.utils.MessageUtils;

/**
 * Description : cette est la classe principale
 * @author mdiouf
 * 
 */
public class GeneratorLicenceMain {

	/**
	 * LOGGER
	 * 
	 */
	final private static Logger LOGGER = LoggerFactory.getLogger(GeneratorLicenceMain.class);

	public static void main(String [] args) {
		System.out.println("Runnning.............................");
		for (int i = 0; i < args.length; i++) {
			System.out.println("arg" + i + " = " + args[i]);
		}
		//ON VERIFIE LE NOMBRE D'ARGUMENTS 
		if (args.length != 1) {
			System.out.println("Nombre d'argument possible est de 1 : args[0] = fichier message.proprerties");
			return;
		}
		// Initialize log4J
		try {
			final DOMConfigurator log4jDomConfig = new DOMConfigurator();
			final InputStream log4JIs =GeneratorLicenceMain.class.getResourceAsStream("/log4j.xml");
			final BufferedReader bufferedlog4J = new BufferedReader(new InputStreamReader(log4JIs, "Cp1252"));
			log4jDomConfig.doConfigure(bufferedlog4J,LogManager.getLoggerRepository());
		} catch (final Exception e) {
			System.out.println("Impossible to initialize LOG4J" + e);
			return;
		}
		//ON VERIFIE SI LE FICHIER FOURNIT EN PARAMETRE EST BON
		final String file_message_properties = args[0].replace("\\", File.separator).replace("\"", "");
		if(!GeneratorLicenceUtils.verifFileTypeFileInLog4j(file_message_properties)) {
			LOGGER.error("Le chemin ou l'extension du fichier "+ file_message_properties + " n'est pas bon");
			return;
		}
		//ON MET LE CHEMIN DU FICHIER PROPERTIES DANS LE SYSTEM
		System.setProperty("file_property", file_message_properties);
		//ON instancie l'objet Model
		final GeneratorLicenceModel gLicenceModel = new GeneratorLicenceModel();
		//ON RECUPERE LE CHEMIN DE TOUS LES FICHIERS
		final String pathAllFile = MessageUtils.getMessage("gestion.licence.path.file.path");
		gLicenceModel.setFilePath(pathAllFile);
		// ON LIT LE FICHIER OU EST STOCKE LES MODULES INDELINES
		try {
			try {
				final BufferedReader bufferedReaderAllModuleIndeline = new BufferedReader(new InputStreamReader(
						new FileInputStream(new File(pathAllFile.concat(File.separator).concat("module_parametre_indeline.csv"))), "Cp1252"));
				// ON LIT LE FICHIER OU EST STOCKE LES MODULES ACCIS
				final BufferedReader bufferedReaderAllAccis = new BufferedReader(new InputStreamReader(
						new FileInputStream(new File(pathAllFile.concat(File.separator).concat("module_parametre_accis.csv"))), "Cp1252"));
				//ON MET DANS UNE LISTE L'ENSEMBLE DES MODULES INDELINE
				gLicenceModel.setListModuleIndelines(GeneratorLicenceUtils.mapRowFile(bufferedReaderAllModuleIndeline)); 
				//ON MET DANS UNE LISTE L'ENSEMBLE DES MODULES ACCIS
				gLicenceModel.setListModulesAccis(GeneratorLicenceUtils.mapRowFile(bufferedReaderAllAccis));
				//ON LANCE LE JFRAME
				new GeneratorLicenceFrame(gLicenceModel);
			} catch (FileNotFoundException e) {
				LOGGER.error("ERROR : " , e);
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("ERROR : " , e);
		}
	}
}
