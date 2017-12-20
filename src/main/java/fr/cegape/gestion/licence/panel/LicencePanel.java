package fr.cegape.gestion.licence.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.DimensionUIResource;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import fr.cegape.gestion.licence.action.DecoderLicenceAction;
import fr.cegape.gestion.licence.action.GeneratorLicenceAction;
import fr.cegape.gestion.licence.model.GeneratorLicenceModel;
import fr.cegape.gestion.licence.utils.DateLabelFormatter;
import fr.cegape.gestion.licence.utils.GeneratorLicenceUtils;
import fr.cegape.gestion.licence.utils.MessageUtils;
import fr.cegape.gestion.licence.utils.UppercaseDocumentFilter;

/**
 * Description : cette classe est la classe Panel <br>
 * - de generation et decryptage d'une licence
 * @author mdiouf
 *
 */
public class LicencePanel{

	/**
	 * butonGerenerLicence
	 */
	private JButton butonGerenerLicence;
	/**
	 * radioAccis
	 */
	private JRadioButton radioAccis;
	/**
	 * radioIndeline
	 */
	private JRadioButton radioIndeline;
	/**
	 * textFiedNbreDossier
	 */
	private JTextField textFiedNbreDossier;
	/**
	 * textFiedMoisContrat
	 */
	private JTextField textFiedMoisContrat;
	/**
	 * textFiedVersion
	 */
	private JTextField textFiedVersion;
	/**
	 * textFiedTrigClient
	 */
	private JTextField textFiedTrigClient;
	/**
	 * textFiedIndice
	 */
	private JTextField textFiedIndice;
	/**
	 * datePickerFinValidite
	 */
	private JDatePickerImpl datePickerFinValidite;
	/**
	 * textFieldCodeLicence
	 */
	private JTextField textFieldNumeroLicence;
	/**
	 * textFieldCodeLicence
	 */
	private JTextField textFieldCodeLicence;
	/**
	 * gLicenceModel
	 */
	private GeneratorLicenceModel gLicenceModel;
	/**
	 * checkBoxlistModuleIndelines
	 */
	List<JCheckBox> checkBoxlistModuleIndelines;
	/**
	 * checkBoxlistModuleAccis
	 */
	List<JCheckBox> checkBoxlistModuleAccis;
	/**
	 * tabbedPane
	 */
	private JTabbedPane tabbedPane;
	/**
	 * frame
	 */
	private JFrame frame;
	/**
	 * butonDecoderLicence
	 */
	private JButton butonDecoderLicence;

	/**
	 * Description : constructeur
	 * @param gLicenceModel
	 * @param frame
	 */
	public LicencePanel(final GeneratorLicenceModel gLicenceModel, final JFrame frame) {
		this.gLicenceModel = gLicenceModel;
		this.frame = frame;
	}

	/**
	 * Description : permet d'ajouter un JPanel a l'EST du JPanel Principal
	 * @return : JPanel
	 */
	public JPanel buidlPanel1() {
		final JPanel panel = new JPanel();
		final GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		final GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		final GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		final TitledBorder border = new TitledBorder(MessageUtils.getMessage("gestion.licence.frame.generer.licence.panel1.border"));
		border.setTitleJustification(TitledBorder.LEFT);
		panel.setBorder(border);
		//Ajout des Boutons radios Dans le Panel
		radioAccis = new JRadioButton(MessageUtils.getMessage("gestion.licence.frame.accis"));
		radioIndeline = new JRadioButton(MessageUtils.getMessage("gestion.licence.frame.indeline"), true);
		final ButtonGroup group = new ButtonGroup();
		group.add(radioAccis);
		group.add(radioIndeline);
		//AJOUT NBRE DE DOSSIER
		final JLabel labelNbreDossier = new JLabel(MessageUtils.getMessage("gestion.licence.frame.generer.licence.label.nombre.dossier"));
		textFiedNbreDossier = GeneratorLicenceUtils.getTextField(4, true, MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_NBRE_DOSSIER));
		//AJOUT MOIS CONTRAT
		final JLabel labelMoiscontrat = new JLabel(MessageUtils.getMessage("gestion.licence.frame.generer.licence.label.mois.contrat"));
		textFiedMoisContrat = GeneratorLicenceUtils.getTextField(4, true, MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_MOIS_CONTRAT));
		//AJOUT VERSION
		final JLabel labelVersion = new JLabel(MessageUtils.getMessage("gestion.licence.frame.generer.licence.label.version"));
		textFiedVersion = GeneratorLicenceUtils.getTextField(3, true, MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_VERSION));
		//AJOUT Trigramme Client
		final JLabel labeltrigramClient = new JLabel(MessageUtils.getMessage("gestion.licence.frame.generer.licence.label.trigramme.client"));
		textFiedTrigClient = GeneratorLicenceUtils.getTextField(3, false, MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_TRIGRAMME));
		//AJOUT Indice
		final JLabel labelIndice = new JLabel(MessageUtils.getMessage("gestion.licence.frame.generer.licence.label.indice"));
		textFiedIndice = GeneratorLicenceUtils.getTextField(1, true, MessageUtils.getMessage(GeneratorLicenceUtils.JTEXTFIELD_TOOLTIP_INDICE));
		//AJOUT Date fin de valide
		final JLabel labeDateFinValidite = new JLabel(MessageUtils.getMessage("gestion.licence.frame.generer.licence.label.date.fin.validite"));
		final DateLabelFormatter datFormatter = new DateLabelFormatter();
		datePickerFinValidite = new JDatePickerImpl(datFormatter.getJDatePanelImpl(), datFormatter);
		//HORIZONTAL
		hGroup.addGroup(layout.createParallelGroup().addComponent(radioIndeline).addComponent(labelNbreDossier).addComponent(labelMoiscontrat)
				.addComponent(labelVersion).addComponent(labeltrigramClient).addComponent(labelIndice).addComponent(labeDateFinValidite));
		hGroup.addGroup(layout.createParallelGroup().addComponent(radioAccis).addComponent(textFiedNbreDossier).addComponent(textFiedMoisContrat)
				.addComponent(textFiedVersion).addComponent(textFiedTrigClient).addComponent(textFiedIndice).addComponent(datePickerFinValidite));
		layout.setHorizontalGroup(hGroup);
		//VERTICAL
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(radioIndeline).addComponent(radioAccis));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(labelNbreDossier).addComponent(textFiedNbreDossier));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(labelMoiscontrat).addComponent(textFiedMoisContrat));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(labelVersion).addComponent(textFiedVersion));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(labeltrigramClient).addComponent(textFiedTrigClient));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(labelIndice).addComponent(textFiedIndice));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(labeDateFinValidite).addComponent(datePickerFinValidite));
		layout.setVerticalGroup(vGroup);
		panel.setBounds(0, 0, 305, 450);
		return panel;
	}

	/**
	 * Description  : permet d'ajouter un JPanel a l'OUEST du JPanel Principal
	 * @return JPanel
	 */
	public JPanel buidlPanel2() {
		final JPanel panel = new JPanel();
		final TitledBorder border = new TitledBorder(MessageUtils.getMessage("gestion.licence.frame.generer.licence.panel2.border"));
		border.setTitleJustification(TitledBorder.LEFT);
		panel.setBorder(border);
		UppercaseDocumentFilter upDocumentFilter = new UppercaseDocumentFilter();
		//AJOUT NUMERO LICENCE
		final JLabel labelNumeroLicence = new JLabel(MessageUtils.getMessage("gestion.licence.frame.generer.licence.label.numero.licence"));
		labelNumeroLicence.setFont(new Font("Arial", Font.BOLD, 30));
		textFieldNumeroLicence = new JTextField();
		textFieldNumeroLicence.setPreferredSize(new DimensionUIResource(580, 40));
		upDocumentFilter.setToUpperCase(textFieldNumeroLicence);
		textFieldNumeroLicence.setFont(new Font(textFieldNumeroLicence.getText(), Font.BOLD, 20));
		panel.add(labelNumeroLicence); panel.add(textFieldNumeroLicence);
		//AJOUT CODE LICENCE
		final JLabel labelCodeLicence = new JLabel(MessageUtils.getMessage("gestion.licence.frame.generer.licence.label.code.licence"));
		labelCodeLicence.setFont(new Font("Arial", Font.BOLD, 30));
		textFieldCodeLicence = new JTextField();
		textFieldCodeLicence.setPreferredSize(new DimensionUIResource(580, 40));
		upDocumentFilter.setToUpperCase(textFieldCodeLicence);
		textFieldCodeLicence.setFont(new Font(textFieldCodeLicence.getText(), Font.BOLD, 20));
		panel.add(labelCodeLicence); panel.add(textFieldCodeLicence);
		panel.setBounds(310, 0, 595, 210);
		return panel;
	}

	/**
	 * Description  : permet d'ajouter un JPanel au SUD OUEST du JPanel Principal
	 * @return : JPanel
	 */
	public JPanel buidlPanel3() {
		final JPanel panel = new JPanel();
		tabbedPane = new JTabbedPane();
		final TitledBorder border = new TitledBorder(MessageUtils.getMessage("gestion.licence.frame.generer.licence.panel3.border"));
		border.setTitleJustification(TitledBorder.LEFT);
		panel.setBorder(border);

		// Add panelIndeline Tab
		checkBoxlistModuleIndelines = GeneratorLicenceUtils.listCheckBox(this.getgLicenceModel().getListModuleIndelines());
		final JPanel panelIndeline = GeneratorLicenceUtils.addPanel(checkBoxlistModuleIndelines);
		tabbedPane.addTab(MessageUtils.getMessage("gestion.licence.frame.indeline"), panelIndeline);

		// Add panelAccis Tab
		checkBoxlistModuleAccis = GeneratorLicenceUtils.listCheckBox(this.getgLicenceModel().getListModulesAccis());
		final JPanel panelAccis = GeneratorLicenceUtils.addPanel(checkBoxlistModuleAccis);
		tabbedPane.addTab(MessageUtils.getMessage("gestion.licence.frame.accis"), panelAccis);

		radioIndeline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});

		radioAccis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabbedPane.getSelectedIndex() == 0) {
					radioIndeline.setSelected(true);
				} else {
					radioAccis.setSelected(true);
				}
			}
		});

		panel.setLayout(new BorderLayout());
		panel.add(tabbedPane, BorderLayout.CENTER);
		panel.setBounds(310, 220, 595, 342);
		return panel;
	}

	/**
	 * Description  : permet d'ajouter un JPanel au SUD EST du JPanel Principal
	 * @return JPanel
	 */
	public JPanel buidlPanel4() {
		final JPanel panel = new JPanel();
		final GeneratorLicenceAction gLicenceAction = new GeneratorLicenceAction(this, this.getgLicenceModel(), this.getFrame());
		butonGerenerLicence = new JButton(MessageUtils.getMessage("gestion.licence.frame.generer.licence.bouton.generer.licence"));
		butonGerenerLicence.addActionListener(gLicenceAction);
		//DECODER LICENCE
		final DecoderLicenceAction decoderLicenceAction = new DecoderLicenceAction(this, this.getgLicenceModel(), this.getFrame());
		butonDecoderLicence = new JButton(MessageUtils.getMessage("gestion.licence.frame.generer.licence.bouton.decoder.licence"));
		butonDecoderLicence.addActionListener(decoderLicenceAction);
		final Box box = Box.createHorizontalBox();
		box.add(butonGerenerLicence);
		//ON MET UN ESPACE DE 10
		box.add(Box.createHorizontalStrut(10));
		box.add(butonDecoderLicence);
		panel.add(box);
		panel.setBounds(30, 488, 260, 52);
		return panel;
	}

	/**
	 * @return the butonGerenerLicence
	 */
	public JButton getButonGerenerLicence() {
		return butonGerenerLicence;
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
	 * @return the textFiedNbreDossier
	 */
	public JTextField getTextFiedNbreDossier() {
		return textFiedNbreDossier;
	}

	/**
	 * @return the textFiedMoisContrat
	 */
	public JTextField getTextFiedMoisContrat() {
		return textFiedMoisContrat;
	}

	/**
	 * @return the textFiedVersion
	 */
	public JTextField getTextFiedVersion() {
		return textFiedVersion;
	}

	/**
	 * @return the textFiedTrigClient
	 */
	public JTextField getTextFiedTrigClient() {
		return textFiedTrigClient;
	}

	/**
	 * @return the textFiedIndice
	 */
	public JTextField getTextFiedIndice() {
		return textFiedIndice;
	}

	/**
	 * @return the textFieldNumeroLicence
	 */
	public JTextField getTextFieldNumeroLicence() {
		return textFieldNumeroLicence;
	}

	/**
	 * @return the textFieldCodeLicence
	 */
	public JTextField getTextFieldCodeLicence() {
		return textFieldCodeLicence;
	}

	/**
	 * @return the datePickerFinValidite
	 */
	public JDatePickerImpl getDatePickerFinValidite() {
		return datePickerFinValidite;
	}

	/**
	 * @return the gLicenceModel
	 */
	public GeneratorLicenceModel getgLicenceModel() {
		return gLicenceModel;
	}

	/**
	 * @return the checkBoxlistModuleIndelines
	 */
	public List<JCheckBox> getCheckBoxlistModuleIndelines() {
		return checkBoxlistModuleIndelines;
	}

	/**
	 * @return the checkBoxlistModuleAccis
	 */
	public List<JCheckBox> getCheckBoxlistModuleAccis() {
		return checkBoxlistModuleAccis;
	}

	/**
	 * @return the tabbedPane
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @return the butonDecoderLicence
	 */
	public JButton getButonDecoderLicence() {
		return butonDecoderLicence;
	}

}
