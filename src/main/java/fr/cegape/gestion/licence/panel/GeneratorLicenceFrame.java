package fr.cegape.gestion.licence.panel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.cegape.gestion.licence.model.GeneratorLicenceModel;
import fr.cegape.gestion.licence.utils.MessageUtils;

/**
 * Description : cette classe est la classe qui contient le JFrame
 * @author mdiouf
 *
 */
public class GeneratorLicenceFrame{

	/**
	 * gLicenceModel
	 */
	private GeneratorLicenceModel gLicenceModel;

	/**
	 * Description : constructeur
	 * @param gLicenceModel
	 */
	public GeneratorLicenceFrame(final GeneratorLicenceModel gLicenceModel) {
		super();
		this.gLicenceModel = gLicenceModel;
		showFrame();
	}

	/**
	 * Description  : permet d'initialiser le JFrame
	 */
	private void showFrame() {
		final JFrame frame = new JFrame();
		final GeneratorLicencePanel generatorLicencePanel = new GeneratorLicencePanel(gLicenceModel, frame);
		generatorLicencePanel.setOpaque(true);

		// Configurer le JFrame
		frame.setTitle(MessageUtils.getMessage("gestion.licence.frame.title"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(generatorLicencePanel);
		frame.pack();
		final ImageIcon img = new ImageIcon(getClass().getResource("/cegape.png"));
		frame.setIconImage(img.getImage());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * @return the gLicenceModel
	 */
	public GeneratorLicenceModel getgLicenceModel() {
		return gLicenceModel;
	}

}
