package fr.cegape.gestion.licence.panel;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.DimensionUIResource;

import fr.cegape.gestion.licence.action.AjouterModuleAction;
import fr.cegape.gestion.licence.model.GeneratorLicenceModel;
import fr.cegape.gestion.licence.utils.GeneratorLicenceUtils;
import fr.cegape.gestion.licence.utils.MessageUtils;

/**
 * Description : cette classe est la classe Panel <br>
 * - d'ajout d'un module
 * @author mdiouf
 *
 */
public class AjouterModulePanel {

	/**
	 * butonAjouterModule
	 */
	private JButton butonAjouterModule;
	/**
	 * radioAccis
	 */
	private JRadioButton radioAccis;
	/**
	 * radioIndeline
	 */
	private JRadioButton radioIndeline;
	/**
	 * textFielNomModule
	 */
	private JTextField textFielNomModule;
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
	 */
	public AjouterModulePanel(final JFrame frame, final GeneratorLicenceModel gLicenceModel) {
		this.frame = frame;
		this.gLicenceModel = gLicenceModel;
	}

	/**
	 * Description : permet d'ajouter un JPanel du JPanel Principal
	 * @return : JPanel
	 */
	public JPanel buidlPanel() {
		final JPanel panel = new JPanel();
		final TitledBorder border = new TitledBorder(MessageUtils.getMessage("gestion.licence.frame.ajout.module.border"));
		border.setTitleJustification(TitledBorder.LEFT);
		panel.setBorder(border);
		panel.setLayout(new BorderLayout());
		//PANEL NORD
		final JPanel panelNord = new JPanel();
		final GroupLayout layout = new GroupLayout(panelNord);
		panelNord.setLayout(layout);
		layout.setAutoCreateGaps(true);
		final GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		final GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		//Ajout des Boutons radios Dans le Panel
		radioAccis = new JRadioButton(MessageUtils.getMessage("gestion.licence.frame.accis"));
		radioIndeline = new JRadioButton(MessageUtils.getMessage("gestion.licence.frame.indeline"), true);
		final ButtonGroup group = new ButtonGroup();
		group.add(radioAccis);
		group.add(radioIndeline);
		//AJOUT LIBELLE MODULE
		final JLabel labelNameModule = new JLabel(MessageUtils.getMessage("gestion.licence.frame.ajout.module.label.libelle.module"));
		textFielNomModule = GeneratorLicenceUtils.getTextField(30, false, MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_NAME_MODULE));
		textFielNomModule.setPreferredSize(new DimensionUIResource(100, 40));
		//HORIZONTAL
		hGroup.addGroup(layout.createParallelGroup().addComponent(radioIndeline).addComponent(labelNameModule));
		hGroup.addGroup(layout.createParallelGroup().addComponent(radioAccis).addComponent(textFielNomModule));
		layout.setHorizontalGroup(hGroup);
		//VERTICAL
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(radioIndeline).addComponent(radioAccis));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(labelNameModule).addComponent(textFielNomModule));
		layout.setVerticalGroup(vGroup);
		panel.add(panelNord, BorderLayout.NORTH);
		//PANEL SUD
		final JPanel panelSud = new JPanel();
		//AJOUT BOUTON ACTION
		final AjouterModuleAction ajModuleAction = new AjouterModuleAction(this.getFrame(), this.getgLicenceModel(), this);
		butonAjouterModule = new JButton(MessageUtils.getMessage("gestion.licence.frame.ajout.module.buton.ajouter.module"));
		butonAjouterModule.addActionListener(ajModuleAction);
		panelSud.add(butonAjouterModule);
		//ON MET UN SEPARATEUR
		panelSud.add(Box.createVerticalStrut(50));
		panel.add(panelSud, BorderLayout.CENTER);
		panel.setBounds(10, 5, 885, 547);
		return panel;
	}

	/**
	 * @return the butonAjouterModule
	 */
	public JButton getButonAjouterModule() {
		return butonAjouterModule;
	}

	/**
	 * @return the radioAccis
	 */
	public JRadioButton getRadioAccis() {
		return radioAccis;
	}

	/**
	 * @return the radioIndeline
	 */
	public JRadioButton getRadioIndeline() {
		return radioIndeline;
	}

	/**
	 * @return the textFielNomModule
	 */
	public JTextField getTextFielNomModule() {
		return textFielNomModule;
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
