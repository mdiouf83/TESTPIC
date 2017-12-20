package fr.cegape.gestion.licence.utils;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Description : cette classe permet de formatter un JTextField
 * @author mdiouf
 *@see DocumentFilter
 */
public class UppercaseDocumentFilter extends DocumentFilter{

	@Override
	public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
		fb.insertString(offset, text.toUpperCase(), attr);
	}

	@Override
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		fb.replace(offset, length, text.toUpperCase(), attrs);
	}

	/**
	 * Description  permet de convertir en masjuscule la valeur d'un JTextFiel au mot de le renseigner
	 * @param textField
	 */
	public void setToUpperCase(final JTextField textField) {
		final DocumentFilter filter = new UppercaseDocumentFilter();
		final AbstractDocument textFieldDoc = (AbstractDocument) textField.getDocument();
		textFieldDoc.setDocumentFilter(filter);
	}

}
