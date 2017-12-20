package fr.cegape.gestion.licence.action;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import fr.cegape.gestion.licence.metier.LicenceMetier;
import fr.cegape.gestion.licence.model.GeneratorLicenceModel;
import fr.cegape.gestion.licence.panel.LicencePanel;
import fr.cegape.gestion.licence.utils.GeneratorLicenceUtils;
import fr.cegape.gestion.licence.utils.MessageUtils;

/**
 * Description : cette classe est la classe d'action <br>
 * - de decryptage d'une licence
 * @author mdiouf
 * @see AbstractAction
 */
public class DecoderLicenceAction extends AbstractAction{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 537859077065815851L;

	/**
	 * licencePanel
	 */
	private LicencePanel licencePanel;

	/**
	 * gLicenceModel
	 */
	private GeneratorLicenceModel gLicenceModel;

	/**
	 * frame
	 */
	private JFrame frame;

	/**
	 * Description : constructeur
	 * @param licencePanel
	 * @param gLicenceModel
	 * @param frame
	 */
	public DecoderLicenceAction(final LicencePanel licencePanel, final GeneratorLicenceModel gLicenceModel, final JFrame frame) {
		super();
		this.licencePanel = licencePanel;
		this.gLicenceModel = gLicenceModel;
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		final StringBuilder builder = new StringBuilder();
		final boolean isError = controlFied(builder);
		//ON DECOCHE TOUTES LES CASES DES MODULES
		GeneratorLicenceUtils.deCocheCheckBox(this.getLicencePanel().getCheckBoxlistModuleAccis());
		GeneratorLicenceUtils.deCocheCheckBox(this.getLicencePanel().getCheckBoxlistModuleIndelines());
		if(isError) {
			JOptionPane.showMessageDialog(this.getFrame(), builder.toString(), MessageUtils.getMessage("gestion.licence.frame.generer.licence.border"),
					JOptionPane.ERROR_MESSAGE);
		} else {
			final LicenceMetier licenceMetier = new LicenceMetier(licencePanel, gLicenceModel);
			licenceMetier.decrypteLicence();
		}
	}

	/**
	 * Description : permet de controler certains champs
	 * @param builder
	 * @return : true / false
	 */
	private boolean controlFied(final StringBuilder builder) {
		//ON REINITIALISE LES CHAMPS
		this.getLicencePanel().getTextFiedNbreDossier().setBackground(Color.white);
		this.getLicencePanel().getTextFiedMoisContrat().setBackground(Color.white);
		this.getLicencePanel().getTextFiedVersion().setBackground(Color.white);
		this.getLicencePanel().getTextFiedTrigClient().setBackground(Color.white);
		this.getLicencePanel().getTextFiedIndice().setBackground(Color.white);
		//ON MET LES BONS TOOLTIP
		this.getLicencePanel().getTextFiedNbreDossier().setToolTipText(
				MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_NBRE_DOSSIER));
		this.getLicencePanel().getTextFiedMoisContrat().setToolTipText(
				MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_MOIS_CONTRAT));
		this.getLicencePanel().getTextFiedVersion().setToolTipText(
				MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_VERSION));
		this.getLicencePanel().getTextFiedTrigClient().setToolTipText(
				MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_TRIGRAMME));
		this.getLicencePanel().getTextFiedIndice().setToolTipText(
				MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_INDICE));
		boolean isError = false;
		if(StringUtils.isEmpty(this.getLicencePanel().getTextFieldNumeroLicence().getText())) {
			this.getLicencePanel().getTextFieldNumeroLicence().setBackground(Color.pink);
			isError = true;
			builder.append(MessageUtils.getMessage(GeneratorLicenceUtils.FIELD_OBLIGATOIRE_NUMERO_LICENCE)).append(GeneratorLicenceUtils.RETOUR_LIGNE);
		} else {
			final String [] numerodLicence = this.getLicencePanel().getTextFieldNumeroLicence().getText().split(GeneratorLicenceUtils.TIRET);
			//ON VERIFIE SI LE NUMERO DE LA LICENCE EST CORRECTE
			if(numerodLicence.length  != 7 || numerodLicence[2].length() > 4 || StringUtils.isEmpty(numerodLicence[2]) ||
					numerodLicence[3].length() > 4 || StringUtils.isEmpty(numerodLicence[3]) ||
					numerodLicence[4].length() > 3 || StringUtils.isEmpty(numerodLicence[4]) ||
					numerodLicence[5].length() > 3 || StringUtils.isEmpty(numerodLicence[5]) ||
					numerodLicence[6].length() > 1 || StringUtils.isEmpty(numerodLicence[6])) {
				builder.append(MessageUtils.getMessage(GeneratorLicenceUtils.FIELD_ERRONER_NUMERO_LICENCE)).append(GeneratorLicenceUtils.RETOUR_LIGNE);
				isError = true;
			}
			//INDELINE
			else if(this.getLicencePanel().getTabbedPane().getSelectedIndex() == 0 && !numerodLicence[0].equals("INL")) {
				builder.append(MessageUtils.getMessage(GeneratorLicenceUtils.FIELD_ERRONER_NUMERO_LICENCE)).append(GeneratorLicenceUtils.RETOUR_LIGNE);
				isError = true;
			}
			//ACCIS
			else if(this.getLicencePanel().getTabbedPane().getSelectedIndex() == 1 && !numerodLicence[0].equals("ACC")) {
				builder.append(MessageUtils.getMessage(GeneratorLicenceUtils.FIELD_ERRONER_NUMERO_LICENCE)).append(GeneratorLicenceUtils.RETOUR_LIGNE);
				isError = true;
			}
			this.getLicencePanel().getTextFieldNumeroLicence().setBackground(Color.white);
		}
		if(StringUtils.isEmpty(this.getLicencePanel().getTextFieldCodeLicence().getText())) {
			this.getLicencePanel().getTextFieldCodeLicence().setBackground(Color.pink);
			isError = true;
			builder.append(MessageUtils.getMessage(GeneratorLicenceUtils.FIELD_OBLIGATOIRE_CODE_LICENCE)).append(GeneratorLicenceUtils.RETOUR_LIGNE);
		} else {
			this.getLicencePanel().getTextFieldCodeLicence().setBackground(Color.white);
		}

		return isError;
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

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

}
