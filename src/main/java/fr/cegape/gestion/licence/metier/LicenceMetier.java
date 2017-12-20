package fr.cegape.gestion.licence.metier;

import java.util.Date;

import fr.cegape.gestion.licence.model.GeneratorLicenceModel;
import fr.cegape.gestion.licence.panel.LicencePanel;
import fr.cegape.gestion.licence.utils.GeneratorLicenceUtils;

/**
 * Description : cette classe est la classe metier <br>
 * - de generation et decryptage d'une licence
 * @author mdiouf
 *
 */
public class LicenceMetier {

	/**
	 * licencePanel
	 */
	private LicencePanel licencePanel;

	/**
	 * gLicenceModel
	 */
	private GeneratorLicenceModel gLicenceModel;

	/**
	 * Description : constructeur
	 * @param licencePanel
	 * @param gLicenceModel
	 */
	public LicenceMetier(final LicencePanel licencePanel, final GeneratorLicenceModel gLicenceModel) {
		this.licencePanel = licencePanel;
		this.gLicenceModel = gLicenceModel;
	}

	/**
	 * Description : permet de generer la licence INDELINE <br>
	 * - this.getLicencePanel().getTabbedPane().getSelectedIndex() == 0 <br>
	 * - correspond a l'onglet du projet indeline <br>
	 * - ON ECRIT LE NUMERO DU LICENCE DANS LE JTEXTFIELD <br>
	 * - On supprime les Tiret du numero de licence <br>
	 * - On converti en ASCII Le numero de Licence et les additiones <br>
	 * - ON PREND LES 3 DERNIERS CARACTERE DU CHAINE ASCII ET RETRANSFORME EN ASCII <br>
	 * - On recupere le nombre de dossier crypter <br>
	 * - ON recupere la date de fin de validite crypter <br>
	 * - On ajoute les modules <br>
	 * - On supprime les tirets du code licence <br>
	 * - On converti en ASCII Le code de Licence et les additiones <br>
	 * - ON PREND LES 3 DERNIERS CARACTERE DU CHAINE ASCII <br>
	 * - ON AJOUTE LE CODE LICENCE DANS LE JTEXFIELD <br>
	 */
	public void generateLicence() {
		final StringBuilder builderCodeLicence = new StringBuilder();
		String numeroLicence = this.getLicencePanel().getTabbedPane().getSelectedIndex() == 0 ? 
				GeneratorLicenceUtils.NUMERO_LICENCE_INDELINE : GeneratorLicenceUtils.NUMERO_LICENCE_ACCIS;
		numeroLicence = numeroLicence.concat(this.getLicencePanel().getTextFiedNbreDossier().getText()).
				concat(GeneratorLicenceUtils.TIRET).concat(this.getLicencePanel().getTextFiedMoisContrat().getText()).
				concat(GeneratorLicenceUtils.TIRET).concat(this.getLicencePanel().getTextFiedVersion().getText()).
				concat(GeneratorLicenceUtils.TIRET).concat(this.getLicencePanel().getTextFiedTrigClient().getText()).
				concat(GeneratorLicenceUtils.TIRET).concat(this.getLicencePanel().getTextFiedIndice().getText());
		//ON ECRIT LE NUMERO DU LICENCE DANS LE JTEXTFIELD
		this.getLicencePanel().getTextFieldNumeroLicence().setText(numeroLicence);
		//On supprime les Tiret du numero de licence
		numeroLicence = numeroLicence.replaceAll(GeneratorLicenceUtils.TIRET, GeneratorLicenceUtils.VIDE);
		//On converti en ASCII Le numero de Licence et les additiones
		numeroLicence = String.valueOf(GeneratorLicenceUtils.sommeNumeroLicence(numeroLicence));
		//ON PREND LES 3 DERNIERS CARACTERE DU CHAINE ASCII ET RETRANSFORME EN ASCII
		final String debut_code_lic =  GeneratorLicenceUtils.crypteAsciChaine(
				numeroLicence.substring(numeroLicence.length() - 3, numeroLicence.length())).toString();
		builderCodeLicence.append(debut_code_lic).append(GeneratorLicenceUtils.TIRET);
		//On recupere le nombre de dossier crypter
		String nbreDossier_crypter = GeneratorLicenceUtils.nbreDossierCrypter(this.getLicencePanel().getTextFiedNbreDossier().getText());
		builderCodeLicence.append(nbreDossier_crypter).append(GeneratorLicenceUtils.TIRET);
		//ON recupere la date de fin de validite crypter
		final String dateFinVal_crypte = GeneratorLicenceUtils.dateFinValiditeCrypte(
				(Date) this.getLicencePanel().getDatePickerFinValidite().getModel().getValue());
		builderCodeLicence.append(dateFinVal_crypte);
		//On ajoute les modules
		final StringBuilder modules = GeneratorLicenceUtils.modulekey(
				GeneratorLicenceUtils.listCheckBoxSelect(this.getLicencePanel().getTabbedPane().getSelectedIndex() == 0 ?
						this.getLicencePanel().getCheckBoxlistModuleIndelines() : this.getLicencePanel().getCheckBoxlistModuleAccis()),
						this.getLicencePanel().getTabbedPane().getSelectedIndex() == 0 ?
								this.getgLicenceModel().getListModuleIndelines() : this.getgLicenceModel().getListModulesAccis())
								.append(GeneratorLicenceUtils.TIRET);
		builderCodeLicence.append(modules.toString());
		//On supprime les tirets du code licence
		String code_licence = builderCodeLicence.toString().replaceAll(GeneratorLicenceUtils.TIRET, GeneratorLicenceUtils.VIDE);
		//On converti en ASCII Le code de Licence et les additiones
		code_licence = String.valueOf(GeneratorLicenceUtils.sommeNumeroLicence(code_licence));
		//ON PREND LES 3 DERNIERS CARACTERE DU CHAINE ASCII
		final String fin_code_lic = code_licence.substring(code_licence.length() - 3, code_licence.length());
		builderCodeLicence.append(fin_code_lic);
		//ON AJOUTE LE CODE LICENCE DANS LE JTEXFIELD
		this.getLicencePanel().getTextFieldCodeLicence().setText(builderCodeLicence.toString());
	}

	/**
	 * Description : permet de décripter une licence <br>
	 * - On recupere le numero de licence pour renseigner certains champs <br>
	 * - On decrypte le code licence pour recuperer le numero de dossier et date de fin de validite <br>
	 */
	public void decrypteLicence() {
		//ON RECUPER LE NUMERO DE LICENCE POUR RENSEIGNER LES CHAMPS DU FORMULAIRE
		final String [] numerodLicence = this.getLicencePanel().getTextFieldNumeroLicence().getText().split(GeneratorLicenceUtils.TIRET);
		this.getLicencePanel().getTextFiedMoisContrat().setText(numerodLicence[3]);
		this.getLicencePanel().getTextFiedVersion().setText(numerodLicence[4]);
		this.getLicencePanel().getTextFiedTrigClient().setText(numerodLicence[5]);
		this.getLicencePanel().getTextFiedIndice().setText(numerodLicence[6]);
		GeneratorLicenceUtils.decrypterCode(licencePanel);
	}

	/**
	 * @return the licencePanel
	 */
	public LicencePanel getLicencePanel() {
		return licencePanel;
	}

	/**
	 * @return the gLicenceModel
	 */
	public GeneratorLicenceModel getgLicenceModel() {
		return gLicenceModel;
	}

}
