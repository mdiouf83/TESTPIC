package fr.cegape.gestion.licence.model;

import java.util.Map;

/**
 * Description : cette classe est la classe Model de toute l'application
 * @author mdiouf
 *
 */
public class GeneratorLicenceModel {

	/**
	 * listModuleIndelines
	 */
	private Map<String, String> listModuleIndelines;

	/**
	 * listModulesAccis
	 */
	private Map<String, String> listModulesAccis;

	/**
	 * filePath
	 */
	private String filePath;

	/**
	 * @return the listModuleIndelines
	 */
	public Map<String, String> getListModuleIndelines() {
		return listModuleIndelines;
	}

	/**
	 * @param listModuleIndelines the listModuleIndelines to set
	 */
	public void setListModuleIndelines(Map<String, String> listModuleIndelines) {
		this.listModuleIndelines = listModuleIndelines;
	}

	/**
	 * @return the listModulesAccis
	 */
	public Map<String, String> getListModulesAccis() {
		return listModulesAccis;
	}

	/**
	 * @param listModulesAccis the listModulesAccis to set
	 */
	public void setListModulesAccis(Map<String, String> listModulesAccis) {
		this.listModulesAccis = listModulesAccis;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GeneratorLicenceModel [listModuleIndelines="
				+ listModuleIndelines + ", listModulesAccis="
				+ listModulesAccis + ", filePath=" + filePath + "]";
	}

}
