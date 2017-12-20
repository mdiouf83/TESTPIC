package fr.cegape.gestion.licence.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.cegape.gestion.licence.model.GeneratorLicenceModel;
import fr.cegape.gestion.licence.utils.GeneratorLicenceUtils;
import fr.cegape.gestion.licence.utils.MessageUtils;

/**
 * Description : cette classe permet de créer des sous JPanel <br>
 * - Dans le JFrame
 * @author mdiouf
 * @see JPanel
 *
 */
public class GeneratorLicencePanel extends JPanel{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3579451848868166772L;

	/**
	 * LOGGER
	 * 
	 */
	final private static Logger LOGGER = LoggerFactory.getLogger(GeneratorLicencePanel.class);

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
	 * @param gLicenceModel
	 * @param frame
	 */
	public GeneratorLicencePanel(final GeneratorLicenceModel gLicenceModel, final JFrame frame) {
		this.gLicenceModel = gLicenceModel;
		this.frame = frame;
		buildPanel();
	}

	/**
	 * Description  : permet d'ajouter un JPANEL Dans le JFRAME
	 */
	private void buildPanel() {
		final JTabbedPane tabbedPane = new JTabbedPane();

		//PANEL GENERER LICENCE
		final JPanel genererLicencePanel = new JPanel();
		genererLicencePanel.setLayout(null);
		final LicencePanel licenePanel = new LicencePanel(this.getgLicenceModel(), this.getFrame());
		genererLicencePanel.add(licenePanel.buidlPanel1());
		genererLicencePanel.add(licenePanel.buidlPanel2());
		genererLicencePanel.add(licenePanel.buidlPanel3());
		genererLicencePanel.add(licenePanel.buidlPanel4());
		tabbedPane.addTab(MessageUtils.getMessage("gestion.licence.frame.onglet.generer.licence"), genererLicencePanel);

		//PANEL AJOUT MODULE
		final JPanel ajouterModulePanel = new JPanel();
		ajouterModulePanel.setLayout(null);
		final AjouterModulePanel ajModulePanel = new AjouterModulePanel(this.getFrame(), this.getgLicenceModel());
		ajouterModulePanel.add(ajModulePanel.buidlPanel());
		tabbedPane.addTab(MessageUtils.getMessage("gestion.licence.frame.onglet.ajouter.module"), ajouterModulePanel);
		//ON RAFFRAICHIE LA PAGE QUAND ON CREE UN NOUVEAU MODULE
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//SI ON CLIQUE SUR L'ONGLET GENERER LICENCE
				if(tabbedPane.getSelectedIndex() == 0) {
					//ON RECUPERE LE REPERTOIR DES FICHIERS
					final String cheminIndeline = gLicenceModel.getFilePath().concat(File.separator).concat("module_parametre_indeline.csv");
					final String cheminAccis =  gLicenceModel.getFilePath().concat(File.separator).concat("module_parametre_accis.csv");
					//ON LIT LE FICHIER EN FONCTION DE l'APPLICATION
					try {
						BufferedReader bufferedReaderIndeline = null;
						BufferedReader bufferedReaderAccis = null;
						try {
							bufferedReaderIndeline = new BufferedReader(new InputStreamReader(new FileInputStream(new File(cheminIndeline)), "Cp1252"));
							bufferedReaderAccis = new BufferedReader(new InputStreamReader(new FileInputStream(new File(cheminAccis)), "Cp1252"));
						} catch (FileNotFoundException e1) {
							LOGGER.error("ERROR : "  , e1);
						}
						//ON MET DANS UNE LISTE L'ENSEMBLE DES MODULES INDELINE
						gLicenceModel.setListModuleIndelines(GeneratorLicenceUtils.mapRowFile(bufferedReaderIndeline)); 
						//ON MET DANS UNE LISTE L'ENSEMBLE DES MODULES ACCIS
						gLicenceModel.setListModulesAccis(GeneratorLicenceUtils.mapRowFile(bufferedReaderAccis));
						//ON SUPPRIME LE PANEL DE GENERATION DE LICENCE PUIS ON LE RECREE
						genererLicencePanel.removeAll();
						final LicencePanel licenePanel = new LicencePanel(gLicenceModel, frame);
						genererLicencePanel.add(licenePanel.buidlPanel1());
						genererLicencePanel.add(licenePanel.buidlPanel2());
						genererLicencePanel.add(licenePanel.buidlPanel3());
						genererLicencePanel.add(licenePanel.buidlPanel4());
					} catch (UnsupportedEncodingException e1) {
						LOGGER.error("ERROR : "  , e1);
					}
				} 
			}
		});

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(900, 580));
		this.add(tabbedPane, BorderLayout.CENTER);
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
