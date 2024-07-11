package testscore;
import java.text.NumberFormat;

public class LocalFormat {
    private NumberFormat currencyNumberFormat;
    private NumberFormat decimalNumberFormat;

    public LocalFormat() {
        // Get formatting for default locale
        currencyNumberFormat = NumberFormat.getCurrencyInstance();
        decimalNumberFormat = NumberFormat.getInstance();
        currencyNumberFormat.setMinimumFractionDigits(2);
        currencyNumberFormat.setMaximumFractionDigits(2);
    }

    public String FormatCurrency(float numberFloat) {
        String formattedDataString = currencyNumberFormat.format(numberFloat);
        return formattedDataString;
    }

    public String FormatCurrency(float numberFloat, int decimalPositionsInteger) {
        currencyNumberFormat.setMinimumFractionDigits(decimalPositionsInteger);
        currencyNumberFormat.setMaximumFractionDigits(decimalPositionsInteger);
        String formattedDataString = currencyNumberFormat.format(numberFloat);
        return formattedDataString;
    }

    public String FormatCurrency(double numberDouble) {
        String formattedDataString = currencyNumberFormat.format(numberDouble);
        return formattedDataString;
    }

    public String FormatCurrency(double numberDouble, int decimalPositionsInteger) {
        currencyNumberFormat.setMinimumFractionDigits(decimalPositionsInteger);
        currencyNumberFormat.setMaximumFractionDigits(decimalPositionsInteger);
        String formattedDataString = currencyNumberFormat.format(numberDouble);
        return formattedDataString;
    }

    public String FormatDecimal(double numberDouble) {
        String formattedDataString = decimalNumberFormat.format(numberDouble);
        return formattedDataString;
    }

    public String FormatDecimal(double numberDouble, int decimalPositionsInteger) {
        decimalNumberFormat.setMinimumFractionDigits(decimalPositionsInteger);
        decimalNumberFormat.setMaximumFractionDigits(decimalPositionsInteger);
        String formattedDataString = decimalNumberFormat.format(numberDouble);
        return formattedDataString;
    }
}
