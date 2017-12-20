package fr.cegape.gestion.licence.metier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.cegape.gestion.licence.model.GeneratorLicenceModel;
import fr.cegape.gestion.licence.panel.AjouterModulePanel;
import fr.cegape.gestion.licence.utils.GeneratorLicenceUtils;

/**
 * Description : cette classe est la partie metier de creation d'un module
 * @author mdiouf
 *
 */
public class AjouterModuleMetier {

	/**
	 * LOGGER
	 * 
	 */
	final private static Logger LOGGER = LoggerFactory.getLogger(AjouterModuleMetier.class);

	/**
	 * ajouterModulePanel
	 */
	private AjouterModulePanel ajouterModulePanel;

	/**
	 * gLicenceModel
	 */
	private GeneratorLicenceModel gLicenceModel;

	/**
	 * Description : constructeur
	 * @param ajouterModulePanel
	 * @param gLicenceModel
	 */
	public AjouterModuleMetier(final AjouterModulePanel ajouterModulePanel, final GeneratorLicenceModel gLicenceModel) {
		this.ajouterModulePanel = ajouterModulePanel;
		this.gLicenceModel = gLicenceModel;
	}

	/**
	 * Description : permet d'ajouter un module
	 */
	public void ajouterModuleMetier() {
		//ON RECUPERE LE REPERTOIR DES FICHIERS
		final String cheminIndeline = this.getgLicenceModel().getFilePath().concat(File.separator).concat("module_parametre_indeline.csv");
		final String cheminAccis = this.getgLicenceModel().getFilePath().concat(File.separator).concat("module_parametre_accis.csv");
		//ON CREE LE NOUVEAU MODULE DANS LE FICHIER
		try {
			//ON LIT LE FICHIER EN FONCTION DE l'APPLICATION
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = this.getAjouterModulePanel().getRadioIndeline().isSelected() ? 
						new BufferedReader(new InputStreamReader(new FileInputStream(new File(cheminIndeline)), "Cp1252")) :
							new BufferedReader(new InputStreamReader(new FileInputStream(new File(cheminAccis)), "Cp1252"));
			} catch (FileNotFoundException e1) {
				LOGGER.error("ERROR : " , e1);
			}
			//ON CALCUL LE NOUVEAU MODULE
			final String newModuleId = GeneratorLicenceUtils.calculNumeroModule(bufferedReader);
			//ON CONSTRUIT LE NOUVEAU MODULE
			final String s_donnees = this.getAjouterModulePanel().getTextFielNomModule().getText().concat(" (").concat(newModuleId)
					.concat(")").concat(GeneratorLicenceUtils.SEPERATOR).concat(GeneratorLicenceUtils.TIRET)
					.concat(String.valueOf(Integer.parseInt(newModuleId) + 55));
			//ON ECRIT DANS LES FICHIERS CORRESPONDANTS
			try {
				final BufferedWriter bWriter = this.getAjouterModulePanel().getRadioIndeline().isSelected() ? 
						new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cheminIndeline, true), "Cp1252")) :
							new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cheminAccis, true), "Cp1252"));
						GeneratorLicenceUtils.writeFile(bWriter, s_donnees);
			} catch (IOException e) {
				LOGGER.error("ERROR : " , e);
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("ERROR : " , e);
		}
	}

	/**
	 * @return the ajouterModulePanel
	 */
	public AjouterModulePanel getAjouterModulePanel() {
		return ajouterModulePanel;
	}

	/**
	 * @return the gLicenceModel
	 */
	public GeneratorLicenceModel getgLicenceModel() {
		return gLicenceModel;
	}

}
