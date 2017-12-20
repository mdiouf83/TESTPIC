package fr.cegape.gestion.licence.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * Description : cette classe permet d'initialiser un datapicker
 * @author mdiouf
 * @see AbstractFormatter
 */
public class DateLabelFormatter extends AbstractFormatter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3344908068145128775L;
	/**
	 * datePattern
	 */
	private String datePattern = "dd/MM/yyyy";
	/**
	 * dateFormatter
	 */
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}

	/**
	 * Description : permet d'initialiser un JDatePickerImpl
	 * @return JDatePanelImpl
	 */
	public JDatePanelImpl getJDatePanelImpl() {
		final UtilDateModel model = new UtilDateModel();
		final int day = Integer.parseInt(dateFormatter.format(new Date()).substring(0, 2));
		final int month = Integer.parseInt(dateFormatter.format(new Date()).substring(3, 5));
		final int year = Integer.parseInt(dateFormatter.format(new Date()).substring(6, 10));
		model.setDate(year, month - 1, day);
		model.setSelected(true);
		final JDatePanelImpl datePanel = new JDatePanelImpl(model);
		return datePanel;
	}

}
