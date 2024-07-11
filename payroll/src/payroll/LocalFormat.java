package payroll;

import java.text.*;

public class LocalFormat
{
	//Instance variables
	private NumberFormat currencyNumberFormat;
	private NumberFormat decimalNumberFormat;

	//Class constructor
	LocalFormat()
	{
		//Get formatting for default locale
		currencyNumberFormat = NumberFormat.getCurrencyInstance();
		decimalNumberFormat = NumberFormat.getInstance();
		currencyNumberFormat.setMinimumFractionDigits(2);
		currencyNumberFormat.setMaximumFractionDigits(2);
	}

	//Methods
	public String FormatCurrency (float numberFloat)
	{
		//Format as currency, 2 decimal positions
		//currencyNumberFormat.setMinimumFractionDigits(2);
		//currencyNumberFormat.setMaximumFractionDigits(2);
		String formattedDataString = currencyNumberFormat.format(numberFloat);
		return formattedDataString;
	}
	public String FormatCurrency (float numberFloat, int decimalPositionsInteger)
	{
		//Format as currency, variable number of decimal positions

		currencyNumberFormat.setMinimumFractionDigits(decimalPositionsInteger);
		currencyNumberFormat.setMaximumFractionDigits(decimalPositionsInteger);
		String formattedDataString = currencyNumberFormat.format(numberFloat);
		return formattedDataString;
	}
	public String FormatCurrency (double numberDouble)
	{
		//Format as currency, 2 decimal positions

		String formattedDataString = currencyNumberFormat.format(numberDouble);
		return formattedDataString;
	}
	public String FormatCurrency (double numberDouble, int decimalPositionsInteger)
	{
		//Format as currency, variable number of decimal positions

		currencyNumberFormat.setMinimumFractionDigits(decimalPositionsInteger);
		currencyNumberFormat.setMaximumFractionDigits(decimalPositionsInteger);
		String formattedDataString = currencyNumberFormat.format(numberDouble);
		return formattedDataString;
	}
	public String FormatDecimal (float numberFloat)
	{
		//Format as decimal, 2 decimal positions

		String formattedDataString = decimalNumberFormat.format(numberFloat);
		return formattedDataString;
	}
	public String FormatDecimal (float numberFloat, int decimalPositionsInteger)
	{
		//Format as decimal, variable number of decimal positions
		//Set decimal positions

		decimalNumberFormat.setMinimumFractionDigits(decimalPositionsInteger);
		decimalNumberFormat.setMaximumFractionDigits(decimalPositionsInteger);
		String formattedDataString = decimalNumberFormat.format(numberFloat);
		return formattedDataString;
}
}