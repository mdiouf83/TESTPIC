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
 * - de génération d'une nouvelle licence
 * @author mdiouf
 * @see AbstractAction
 */
public class GeneratorLicenceAction extends AbstractAction{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -48520828532647325L;

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
	public GeneratorLicenceAction(final LicencePanel licencePanel, final GeneratorLicenceModel gLicenceModel, final JFrame frame) {
		super();
		this.licencePanel = licencePanel;
		this.gLicenceModel = gLicenceModel;
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		this.getLicencePanel().getTextFieldNumeroLicence().setBackground(Color.white);
		this.getLicencePanel().getTextFieldCodeLicence().setBackground(Color.white);
		final StringBuilder builder = new StringBuilder();
		final boolean isError = controlFied(builder);
		//ON DECHOCHE TOUS LES MODULES DE ACCIS
		if(this.getLicencePanel().getTabbedPane().getSelectedIndex() == 0) {
			GeneratorLicenceUtils.deCocheCheckBox(this.getLicencePanel().getCheckBoxlistModuleAccis());
		}
		//ON DECHOCHE TOUS LES MODULES DE INDELINE
		else if (this.getLicencePanel().getTabbedPane().getSelectedIndex() == 1) {
			GeneratorLicenceUtils.deCocheCheckBox(this.getLicencePanel().getCheckBoxlistModuleIndelines());
		}
		if(isError) {
			JOptionPane.showMessageDialog(this.getFrame(), builder.toString(), MessageUtils.getMessage("gestion.licence.frame.generer.licence.border"),
					JOptionPane.ERROR_MESSAGE);
		} else {
			final LicenceMetier licenceMetier = new LicenceMetier(licencePanel, gLicenceModel);
			licenceMetier.generateLicence();
		}
	}

	/**
	 * Description : permet de controler tous les champs <br>
	 * - Tous les champs sont obligatoires
	 * @param builder
	 * @return boolean
	 */
	private boolean controlFied(final StringBuilder builder) {
		boolean isError = false;
		boolean isObligatoire = false;
		if(StringUtils.isEmpty(this.getLicencePanel().getTextFiedNbreDossier().getText())) {
			this.getLicencePanel().getTextFiedNbreDossier().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_FIELD_OBLIGATOIRE));
			this.getLicencePanel().getTextFiedNbreDossier().setBackground(Color.pink);
			isError = true;
			isObligatoire = true;
		} else {
			this.getLicencePanel().getTextFiedNbreDossier().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_NBRE_DOSSIER));
			this.getLicencePanel().getTextFiedNbreDossier().setBackground(Color.white);
		}
		if(StringUtils.isEmpty(this.getLicencePanel().getTextFiedMoisContrat().getText())) {
			this.getLicencePanel().getTextFiedMoisContrat().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_FIELD_OBLIGATOIRE));
			this.getLicencePanel().getTextFiedMoisContrat().setBackground(Color.pink);
			isError = true;
			isObligatoire = true;
		} else {
			this.getLicencePanel().getTextFiedMoisContrat().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_MOIS_CONTRAT));
			this.getLicencePanel().getTextFiedMoisContrat().setBackground(Color.white);
		}
		if(StringUtils.isEmpty(this.getLicencePanel().getTextFiedVersion().getText())) {
			this.getLicencePanel().getTextFiedVersion().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_FIELD_OBLIGATOIRE));
			this.getLicencePanel().getTextFiedVersion().setBackground(Color.pink);
			isError = true;
			isObligatoire = true;
		} else {
			this.getLicencePanel().getTextFiedVersion().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_VERSION));
			this.getLicencePanel().getTextFiedVersion().setBackground(Color.white);
		}
		if(StringUtils.isEmpty(this.getLicencePanel().getTextFiedTrigClient().getText())) {
			this.getLicencePanel().getTextFiedTrigClient().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_FIELD_OBLIGATOIRE));
			this.getLicencePanel().getTextFiedTrigClient().setBackground(Color.pink);
			isError = true;
			isObligatoire = true;
		} else {
			this.getLicencePanel().getTextFiedTrigClient().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_TRIGRAMME));
			this.getLicencePanel().getTextFiedTrigClient().setBackground(Color.white);
		}
		if(StringUtils.isEmpty(this.getLicencePanel().getTextFiedIndice().getText())) {
			this.getLicencePanel().getTextFiedIndice().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_FIELD_OBLIGATOIRE));
			this.getLicencePanel().getTextFiedIndice().setBackground(Color.pink);
			isError = true;
			isObligatoire = true;
		} else {
			this.getLicencePanel().getTextFiedIndice().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_INDICE));
			this.getLicencePanel().getTextFiedIndice().setBackground(Color.white);
		}
		if(this.getLicencePanel().getDatePickerFinValidite().getModel().getValue() == null) {
			isError = true;
			builder.append(MessageUtils.getMessage(GeneratorLicenceUtils.FIELD_OBLIGATOIRE_DATE_FIN)).append(GeneratorLicenceUtils.RETOUR_LIGNE);
		}
		if(isObligatoire) {
			builder.append(MessageUtils.getMessage(GeneratorLicenceUtils.FIELD_OBLIGATOIRE_TEXTFIELD)).append(GeneratorLicenceUtils.RETOUR_LIGNE);
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
