package fr.cegape.gestion.licence.action;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import fr.cegape.gestion.licence.metier.AjouterModuleMetier;
import fr.cegape.gestion.licence.model.GeneratorLicenceModel;
import fr.cegape.gestion.licence.panel.AjouterModulePanel;
import fr.cegape.gestion.licence.utils.GeneratorLicenceUtils;
import fr.cegape.gestion.licence.utils.MessageUtils;

/**
 * Description : cette classe est la classe d'action <br>
 * - de la creation d'un module
 * @author mdiouf
 * @see AbstractAction
 */
public class AjouterModuleAction extends AbstractAction{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8709640348170632615L;
	/**
	 * ajouterModulePanel
	 */
	private AjouterModulePanel ajouterModulePanel;
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
	 * @param frame
	 * @param gLicenceModel
	 * @param ajouterModulePanel
	 */
	public AjouterModuleAction(final JFrame frame, final GeneratorLicenceModel gLicenceModel, final AjouterModulePanel ajouterModulePanel) {
		super();
		this.frame = frame;
		this.gLicenceModel = gLicenceModel;
		this.ajouterModulePanel = ajouterModulePanel;
	}

	public void actionPerformed(ActionEvent e) {
		final StringBuilder builder = new StringBuilder();
		if(controlFied(builder)) {
			JOptionPane.showMessageDialog(this.getFrame(), builder.toString(), MessageUtils.getMessage("gestion.licence.frame.ajout.module.border")
					, JOptionPane.ERROR_MESSAGE);
		} else {
			final AjouterModuleMetier ajouterModuleMetier = new AjouterModuleMetier(ajouterModulePanel, gLicenceModel);
			ajouterModuleMetier.ajouterModuleMetier();
		}
	}

	/**
	 * Description : permet de controler certains champs
	 * @param builder
	 * @return boolean
	 */
	private boolean controlFied(final StringBuilder builder) {
		if(StringUtils.isEmpty(this.getAjouterModulePanel().getTextFielNomModule().getText())) {
			builder.append(MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_OBLIGATOIRE_NAME_MODULE)).append(GeneratorLicenceUtils.RETOUR_LIGNE);
			this.getAjouterModulePanel().getTextFielNomModule().setBackground(Color.pink);
			this.getAjouterModulePanel().getTextFielNomModule().setToolTipText(
					MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_OBLIGATOIRE_NAME_MODULE));
			return true;
		}
		this.getAjouterModulePanel().getTextFielNomModule().setBackground(Color.white);
		this.getAjouterModulePanel().getTextFielNomModule().setToolTipText(MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_NAME_MODULE));
		return false;
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

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}
}
