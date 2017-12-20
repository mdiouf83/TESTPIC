package fr.cegape.gestion.licence.utils;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.cegape.gestion.licence.panel.LicencePanel;

/**
 * Description : cette classe est une classe static ou est <br>
 *  - implemente plusieurs fonctionnalites de l'application
 * @author mdiouf
 * 
 */
public class GeneratorLicenceUtils {

	/**
	 * LOGGER
	 * 
	 */
	final private static Logger LOGGER = LoggerFactory.getLogger(GeneratorLicenceUtils.class);

	/**
	 * CSV
	 */
	final static String CSV = "csv";

	/**
	 * SEPERATOR
	 */
	public final static String SEPERATOR = ";";
	/**
	 * JTEXTFIELD_TOOLTIP_NBRE_DOSSIER
	 */
	public final static String JTEXTFIELD_TOOLTIP_NBRE_DOSSIER = "gestion.licence.frame.generer.licence.tooltip.nombre.dossier";
	/**
	 * JTEXTFIELD_TOOLTIP_MOIS_CONTRAT
	 */
	public final static String JTEXTFIELD_TOOLTIP_MOIS_CONTRAT = "gestion.licence.frame.generer.licence.tooltip.mois.contrat";
	/**
	 * JTEXTFIELD_TOOLTIP_VERSION
	 */
	public final static String JTEXTFIELD_TOOLTIP_VERSION = "gestion.licence.frame.generer.licence.tooltip.version";
	/**
	 * JTEXTFIELD_TOOLTIP_TRIGRAMME
	 */
	public final static String JTEXTFIELD_TOOLTIP_TRIGRAMME = "gestion.licence.frame.generer.licence.tooltip.trigramme.client";
	/**
	 * JTEXTFIELD_TOOLTIP_INDICE
	 */
	public final static String JTEXTFIELD_TOOLTIP_INDICE = "gestion.licence.frame.generer.licence.tooltip.indice";
	/**
	 * JTEXTFIELD_TOOLTIP_FIELD_OBLIGATOIRE
	 */
	public final static String JTEXTFIELD_TOOLTIP_FIELD_OBLIGATOIRE = "gestion.licence.frame.generer.licence.field.obligatoire";
	/**
	 * FIELD_OBLIGATOIRE_DATE_FIN
	 */
	public final static String FIELD_OBLIGATOIRE_DATE_FIN = "gestion.licence.frame.generer.licence.date.fin.validite.obligatoire";
	/**
	 * FIELD_OBLIGATOIRE_TEXTFIELD
	 */
	public final static String FIELD_OBLIGATOIRE_TEXTFIELD = "gestion.licence.frame.generer.licence.field.pink.obligatoire";
	/**
	 * FIELD_OBLIGATOIRE_NUMERO_LICENCE
	 */
	public final static String FIELD_OBLIGATOIRE_NUMERO_LICENCE = "gestion.licence.frame.generer.licence.numero.licence.obligatoire";
	/**
	 * FIELD_OBLIGATOIRE_CODE_LICENCE
	 */
	public final static String FIELD_OBLIGATOIRE_CODE_LICENCE = "gestion.licence.frame.generer.licence.code.licence.obligatoire";
	/**
	 * FIELD_ERRONER_NUMERO_LICENCE
	 */
	public final static String FIELD_ERRONER_NUMERO_LICENCE = "gestion.licence.frame.generer.licence.numero.licence.erroner";
	/**
	 * RETOUR_LIGNE
	 */
	public final static String RETOUR_LIGNE = "\n";
	/**
	 * NUMERO_LICENCE_INDELINE
	 * INL-WS-(nombre de dossiers)-(mois contrat)-(version)-(trigramme)-(indice)
	 */
	public final static String NUMERO_LICENCE_INDELINE = "INL-WS-";
	/**
	 * NUMERO_LICENCE_ACCIS
	 * ACC-WS-(nombre de dossiers)-(mois contrat)-(version)-(trigramme)-(indice)
	 */
	public final static String NUMERO_LICENCE_ACCIS = "ACC-WS-";
	/**
	 * TIRET
	 */
	public final static String TIRET = "-";
	/**
	 * VIDE
	 */
	public final static String VIDE = "";
	/**
	 * JTEXTFIELD_TOOLTIP_NAME_MODULE
	 */
	public final static String JTEXTFIELD_TOOLTIP_NAME_MODULE = "gestion.licence.frame.ajout.module.tooltip.libelle.module";
	/**
	 * JTEXTFIELD_OBLIGATOIRE_NAME_MODULE
	 */
	public final static String JTEXTFIELD_OBLIGATOIRE_NAME_MODULE = "gestion.licence.frame.ajout.module.obligatoire";
	/**
	 * XML
	 */
	public final static String PROPERTIES_FILE = "properties";

	/**
	 * Description  : cette methode permet de ne pas lire les lignes vides du
	 * fichier CSV <br>
	 * 
	 * @param line : line
	 * @return true si la ligne contient des données false si que des ';'
	 * 
	 */
	static boolean contientData(final String line) {
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) != ';') {
				return true;
			}
		}
		return false;
	}

	/**
	 * Description  : readFile method <br>
	 * cette methode permet de lire la totalite du fichier et de le mettre dans <br>
	 * une liste les donnees de toutes les tables <br>
	 * @param br : le fichier à lire
	 * @return une liste contenant toutes les lignes non vides
	 */
	static List<String> readFile(final BufferedReader br) {
		List<String> result = null;
		try {
			result = new ArrayList<String>();
			for (String line = br.readLine(); (line != null); line = br.readLine()) {
				if (contientData(line)) {
					result.add(line);
				}
			}
			br.close();
		} catch (IOException e) {
			LOGGER.error("ERROR : " , e);
		}
		return result;
	}

	/**
	 * Description  : permet d'ajouter la liste des modules dans un map
	 * @param br
	 * @return : une liste de map
	 */
	public static Map<String, String> mapRowFile(final BufferedReader br) {
		final List<String> listDataFile = readFile(br);
		final Map<String, String> mapDataFile = new HashMap<String, String>();
		if(listDataFile != null && !listDataFile.isEmpty()) {
			for(final String line : listDataFile) {
				if(line != null) {
					int cpt = 0;
					final String[] datas = line.split(SEPERATOR);
					final String keyMap = datas.length > cpt ? datas[cpt++].trim() : null;
					final String valueMap = datas.length > cpt ? datas[cpt++].trim() : null;
					mapDataFile.put(keyMap, valueMap);
				}
			}
		}

		return mapDataFile;
	}

	/**
	 * Description  : permet de trier un map de facon descendant en fonction du cle
	 * @param map
	 * @return une map Entry trie de facon ascendante
	 */
	static <K,V extends Comparable<? super V>> 
	List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {

		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());

		Collections.sort(sortedEntries, 
				new Comparator<Entry<K,V>>() {
			public int compare(Entry<K,V> e1, Entry<K,V> e2) {
				return ((String) e1.getKey()).compareTo((String) e2.getKey());
			}
		}
				);

		return sortedEntries;
	}

	/**
	 * Description  : permet de recuper les noms des modules et le mettre dans une liste
	 * @param mapModule
	 * @return : une liste de mmodules
	 */
	static List<String> listModule(final Map<String, String> mapModule) {
		final List<String> listModules = new ArrayList<String>();
		for(Map.Entry<String, String> mapEntries : GeneratorLicenceUtils.entriesSortedByValues(mapModule)) {
			listModules.add(mapEntries.getKey());
		}
		return listModules;
	}

	/**
	 * Description : permet de construire une liste JCheckBox en fonction des modules
	 * @param mapModule
	 * @return : List<JCheckBox>
	 */
	public static List<JCheckBox> listCheckBox(final Map<String, String> mapModule) {
		final List<JCheckBox> checkBoxlistModules = new ArrayList<JCheckBox>();
		final List<String> listModules = listModule(mapModule);
		for(final String module : listModules) {
			final JCheckBox checkBox = new JCheckBox(module);
			checkBoxlistModules.add(checkBox);
		}
		return checkBoxlistModules;
	}

	/**
	 * Description : permet d'ajouter une liste de JCheckBox dans un JPanel
	 * @param checkBoxlistModules
	 * @return : un JPanel
	 */
	public static JPanel addPanel(final List<JCheckBox> checkBoxlistModules) {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(10, 1));
		panel.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		for (final JCheckBox checkBox : checkBoxlistModules) {
			if(checkBox != null) {
				panel.add(checkBox);
			}
		}
		return panel;
	}

	/**
	 * Description : permet d'initialiser un JTextField
	 * @param size : authorized length
	 * @param isVerifNumber : si doit contenir que des valeurs numeriques
	 * @param textToolTip : tooltip
	 * @return JTextField
	 */
	public static JTextField getTextField(final int size, final boolean isVerifNumber, final String textToolTip) {
		final JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(25, 25));
		textField.setToolTipText(textToolTip);
		UppercaseDocumentFilter upDocumentFilter = new UppercaseDocumentFilter();
		upDocumentFilter.setToUpperCase(textField);
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( (isVerifNumber && ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) || (textField.getText().length() == size)) {
					e.consume();
				}
			}
		});
		return textField;
	}

	/**
	 * Description : permet de mettre dans une liste la liste des JCheckBox Sélectionnés
	 * @param checkBoxlistModules
	 * @return : list de Modules Sélectionnés
	 */
	public static List<String> listCheckBoxSelect(final List<JCheckBox> checkBoxlistModules) {
		final List<String> listChekSelect = new ArrayList<String>();
		for(final JCheckBox checkBox : checkBoxlistModules) {
			if(checkBox.isSelected()) {
				listChekSelect.add(checkBox.getText());
			}
		}
		return listChekSelect;
	}

	/**
	 * Description : permet de récupéer les valeurs des modules en fonction des cle
	 * @param listModules
	 * @param mapModule
	 * @return StringBuilder
	 */
	public static StringBuilder modulekey(final List<String> listModules, final Map<String, String> mapModule) {
		final StringBuilder builder = new StringBuilder();
		for(final String module : listModules) {
			builder.append(mapModule.get(module));
		}
		return builder;
	}

	/**
	 * Description : permet de convertir une chaine en ASCI <br>
	 *- Si n° ASCII est compris entre 53 et 57, on ajoute 12 au n° ASCII <br>
	 *-	Si n° ASCII est compris entre 86 et 90, on ajoute 11 au n° ASCII <br>
	 *-	Si le n° ASCII est supérieur à 118 alors on ajoute 70 <br>
	 *-	Sinon on ajoute 5 au n° ASCII <br>
	 * @param chaine
	 * @return : StringBuilder
	 */
	public static StringBuilder crypteAsciChaine(final String chaine) {
		final StringBuilder builder = new StringBuilder();
		for(int i = 0; i < chaine.length() ; i++) {
			//On convertir le caractere en asci
			int numAsci = (int) chaine.charAt(i);
			if(numAsci >= 53 && numAsci <= 57) {
				numAsci += 12;
			} else if(numAsci >= 86 && numAsci <= 90) {
				numAsci += 11;
			}else if(numAsci > 118) {
				numAsci += 70;
			} else {
				numAsci += 5;
			}
			builder.append((char) numAsci);
		}
		return builder;
	}

	/**
	 * Description : permet de reuperer le nombre ASCII d'un caractere et de faire la somme
	 * @param chaine
	 * @return int : somme des caracteres ASCII
	 */
	public static int sommeNumeroLicence(final String chaine) {
		int somme_num_licence = 0;
		for(int i = 0; i < chaine.length() ; i++) {
			somme_num_licence += (int) chaine.charAt(i);
		}
		return somme_num_licence;
	}

	/**
	 * Description : permet de crypter le nombre de dossier
	 * @param nbrDossier
	 * @return : chaine crypter du nbre de dossier
	 */
	public static String nbreDossierCrypter(final String nbrDossier) {
		String nbreDossierCrypter = StringUtils.EMPTY;
		final int convertInt = Integer.parseInt(nbrDossier);
		if(convertInt < 10) {
			nbreDossierCrypter = "555".concat(crypteAsciChaine(nbrDossier).toString());
		} else if(convertInt < 100) {
			nbreDossierCrypter = "55".concat(crypteAsciChaine(nbrDossier).toString());
		}  else if(convertInt < 1000) {
			nbreDossierCrypter = "5".concat(crypteAsciChaine(nbrDossier).toString());
		}  else if(convertInt > 1000) {
			nbreDossierCrypter = crypteAsciChaine(nbrDossier).toString();
		} 
		return nbreDossierCrypter;
	}

	/**
	 * Description : permet de crypter la date de fin de valide
	 * @param dateFin
	 * @return : date de fin de validite crypter
	 */
	public static String dateFinValiditeCrypte(final Date dateFin) {
		final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		final String dateFormat = crypteAsciChaine(format.format(dateFin)).toString();
		return dateFormat;
	}

	/**
	 * Description permet de decrypter le code licence <br>
	 * - On enleve les tirets et diviser pour 3 chiffres du numero de licence <br>
	 * - On recupere le code licence et faire la somme de leur caractere ASCII <br>
	 * - On decrypte le nombre de dossier et la date de fin de validite <br>
	 * - On recupere l'ensemble des modules de lalicence et cocher leur chechbox <br>
	 * @param licencePanel
	 */
	public static void decrypterCode(final LicencePanel licencePanel) {
		int i_somme_licence = 0;
		if (licencePanel.getTextFieldNumeroLicence().getText() != null) {
			final String s_num_licence = licencePanel.getTextFieldNumeroLicence().getText().replaceAll(TIRET, VIDE);
			//enlever les tirets et diviser pour 3 chiffres
			for (int i = 0; i < s_num_licence.length(); i++) {
				char carac = s_num_licence.charAt(i);
				i_somme_licence += (int) carac;
			}
		}
		int i_somme_code = 0;
		final String s_code_licence = licencePanel.getTextFieldCodeLicence().getText().replaceAll(TIRET, VIDE);
		if (s_code_licence != null) {
			for (int i = 0; i < s_code_licence.length() - 3; i++) {
				char carac = s_code_licence.charAt(i);
				i_somme_code += (int) carac;
			}
		}
		String s_somme_code = StringUtils.EMPTY;
		if (s_code_licence.length() >= 3) {
			s_somme_code = s_code_licence.substring(s_code_licence.length() - 3, s_code_licence.length());
		}
		String s_chaine_decryptee = StringUtils.EMPTY;
		if (s_code_licence != null) {
			for (int i = 0; i < s_code_licence.length(); i++) {
				char carac = s_code_licence.charAt(i);
				int nombre = (int) carac;
				if (nombre > 47 && nombre < 53) {
					nombre += 70;
				} else if (nombre > 64 && nombre < 70) {
					nombre -= 12;
				} else if (nombre > 96 && nombre < 102) {
					nombre -= 11;
				} else {
					nombre -= 5;
				}
				s_chaine_decryptee += (char) nombre;
			}
		}
		if (!StringUtils.isEmpty(s_chaine_decryptee) && s_code_licence.length() >= 14 && !StringUtils.isEmpty(s_somme_code)) {
			String s_somme_numero = s_chaine_decryptee.substring(0, 3);
			if (s_somme_numero.equals(String.valueOf(i_somme_licence).substring(String.valueOf(i_somme_licence).length() - 3,
					String.valueOf(i_somme_licence).length())) && s_somme_code.equals(String.valueOf(i_somme_code)
							.substring(String.valueOf(i_somme_code).length() - 3))) {
				final String s_nb_dossier = s_chaine_decryptee.substring(3, 7);
				licencePanel.getTextFiedNbreDossier().setText(String.valueOf(Integer.parseInt(s_nb_dossier)));
				final String s_date_fin = s_chaine_decryptee.substring(7, 15);
				final int year = Integer.parseInt(s_date_fin.substring(0, 4));
				final int month = Integer.parseInt(s_date_fin.substring(4, 6)) - 1;
				final int day = Integer.parseInt(s_date_fin.substring(6, 8));
				licencePanel.getDatePickerFinValidite().getModel().setDate(year, month, day);
				if (s_code_licence.length() > 18) {
					//ON RECUPERE LA LISTE DES CLES DES MODULES (LES CLES COMMENCENT DU CARECTERE 18 avec les "-" au CARACTERE DE LA LONGUEUR DU CODE LICENCE - 4
					final String[] listKeyModules = licencePanel.getTextFieldCodeLicence().getText().substring(18, 
							licencePanel.getTextFieldCodeLicence().getText().length() - 4).split(TIRET);
					final List<JCheckBox> checkBoxlistModules = licencePanel.getTabbedPane().getSelectedIndex() == 0 ?
							licencePanel.getCheckBoxlistModuleIndelines() : licencePanel.getCheckBoxlistModuleAccis();
							cocheCheckBox(checkBoxlistModules, listKeyModules);
				}
			}
		}
	}

	/**
	 * Description : permet de cocher les modules qui sont dans la licence <br>
	 * - Lorsqu'on décode la licence
	 * @param checkBoxlistModules
	 * @param listKeyModulesLicence
	 */
	static void cocheCheckBox(final List<JCheckBox> checkBoxlistModules, final String[] listKeyModulesLicence) {
		for(final JCheckBox chBox : checkBoxlistModules) {
			for(int i = 0 ; i < listKeyModulesLicence.length ; i++) {
				//LE VRAI CODE LICENCE ON ENLEVE -55
				final int module = Integer.parseInt(listKeyModulesLicence[i]) - 55;
				final String moduleLicence = "(".concat(String.valueOf(module)).concat(")");
				if(chBox != null && chBox.getText().contains(moduleLicence)) {
					chBox.setSelected(true);
					break;
				}
			}
		}
	}

	/**
	 * Description : permet de décocher un cheBox
	 * @param checkBoxlistModules
	 */
	public static void deCocheCheckBox(final List<JCheckBox> checkBoxlistModules) {
		for(final JCheckBox chBox : checkBoxlistModules) {
			if(chBox != null) {
				chBox.setSelected(false);
			}
		}
	}

	/**
	 * Description : permet d'écrire dans un fichier
	 * @param bufferedWriter
	 * @param s_donnees
	 */
	public static void writeFile(final BufferedWriter bufferedWriter, final String s_donnees) {
		try {
			//ON ECRIT DANS LE FICHIER
			bufferedWriter.write(RETOUR_LIGNE);
			bufferedWriter.write(s_donnees);
		} catch (IOException e) {
			LOGGER.error("ERROR : " , e);
		}
		finally {
			if(bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					LOGGER.error("ERROR : " , e);
				}
			}
		}
	}

	/**
	 * Description : Pour calculer le numéro du module <br>
	 *  - On recupere l'iD du dernier module qui dans le fichier <br>
	 *  - ON LE CONVERTIT EN INT PUIS ON ENLEVE 55 pour avoir le bon nombre
	 *  - ON PARCOURS DE 1 à 9 <br>
	 *  - ON AJOUTE + 1 AU NOMBRE <br>
	 *  - ON RECUPERE LE DERNIER CARACTERE DU NOMBRE AJOUTER + 1 <br>
	 *  - ON RECUPERE SON NOMBRE  ASCII ET ON AJOUTE + 5 <br>
	 *  - PUIS ON RECUPERE LE CARACTE ASCII DU NOMBRE AJOUTER + 5 <br>
	 *  - ON VERIFIE SI LE CARACTERE ASCII DU NOMBRE AJOUTER + 5 est compris entre 0 et 9 et A et Z <br>
	 *  - Si oui on prend le numero correspondant et on sort du boucle sinon on continuer a rechercher le bon numero
	 *  
	 * @param bufferedReader
	 * @return le numero du nouveau module module
	 */
	public static String calculNumeroModule(final BufferedReader bufferedReader) {
		final String lastLine = readLastLineFile(bufferedReader);
		// On recupere l'iD du dernier module qui dans le fichier
		final String lastModuleFile = !StringUtils.isEmpty(lastLine) ? lastLine.split(SEPERATOR)[1] : StringUtils.EMPTY;
		String newModuleId = StringUtils.EMPTY;
		if(!StringUtils.isEmpty(lastModuleFile)) {
			//ON LE CONVERTIT EN INT PUIS ON ENLEVE 55 pour avoir le bon nombre
			int lastModuleId = (-1) * Integer.parseInt(lastModuleFile) - 55;
			//ON PARCOURS DE 1 à 9
			for(int i = 1 ; i < 10 ; i++) {
				//ON AJOUTE + 1 AU NOMBRE
				lastModuleId += i;
				//ON RECUPERE LE DERNIER CARACTERE DU NOMBRE AJOUTER + 1
				final String lastCaractere = String.valueOf(lastModuleId).substring(String.valueOf(lastModuleId).length()
						- 1, String.valueOf(lastModuleId).length());
				//ON RECUPERE SON NOMBRE  ASCII ET ON AJOUTE + 5
				char character = lastCaractere.charAt(0); 
				final int caractereAsciiModule = (int)character + 5;
				//PUIS ON RECUPERE LE CARACTE ASCII DU NOMBRE AJOUTER + 5
				final char caractereAsciiModuleAddCinq = (char) caractereAsciiModule;
				//ON VERIFIE SI LE CARACTERE ASCII DU NOMBRE AJOUTER + 5 est compris entre 0 et 9 et A et Z
				if(Character.isLetter(caractereAsciiModuleAddCinq) || Character.isDigit(caractereAsciiModuleAddCinq)) {
					newModuleId = String.valueOf(lastModuleId);
					break;
				}
			}
		}
		return newModuleId;
	}

	/**
	 * Description : permet de lire la derniere ligne du fichier
	 * @param br
	 * @return : dernier ligne du fichier
	 */
	static String readLastLineFile(final BufferedReader br) {
		String lastLine = StringUtils.EMPTY;
		try {
			for (String line = br.readLine(); (line != null); line = br.readLine()) {
				if (contientData(line)) {
					lastLine = line;
				}
			}
			br.close();
		} catch (IOException e) {
			LOGGER.error("ERROR : " , e);
		}
		return lastLine;
	}
	/**
	 * Description cette methode permet de verifier si l'extention du fichier
	 * est au format properties <br>
	 * et que le repertoir fournit en parametre existe
	 * @param file
	 * @return : true / false
	 */
	public static boolean verifFileTypeFileInLog4j(final String file) {
		final String extension = file.substring(file.lastIndexOf(".") + 1);
		if (new File(file).exists()) {
			if (extension.trim().equals(PROPERTIES_FILE)) {
				return true;
			}
			return false;
		}
		return false;
	}
}
