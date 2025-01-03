package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Currency;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class CurrencyHandler {
    private Map<String, String> currenciesName;
    private Map<String, Currency> currencies = new HashMap<>();

    public CurrencyHandler() {
        loadCurrencies();
        populateCurrencies();
    }

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    public Map<String, String> getCurrenciesName() {
        return currenciesName;
    }

    public double convert(JTextField textField, String fromCurrency, String toCurrency) {
        double amount = parseToDouble(textField.getText());

        if (amount <= 0.0) return 0.0;

        Currency fromCurrencyObject = currencies.get(fromCurrency);
        String isoCode = currenciesName.get(toCurrency);
        Double rate = fromCurrencyObject.getConversionRate(isoCode);

        return amount * rate;
    }

    private double parseToDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric value", "Error", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }
    }

    private void loadCurrencies() {
        try (FileReader reader = new FileReader("src/model/currencies.json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            currenciesName = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateJComboBox(JComboBox<String> comboBox) {
        for (String currencyName : currenciesName.keySet()) {
            comboBox.addItem(currencyName);
        }
    }

    public void populateCurrencies() {
        APIController controller = new APIController();
        for (String name : currenciesName.keySet()) {
            String currencyCode = currenciesName.get(name);
            Currency newCurrency = controller.createCurrency(currencyCode);
            currencies.put(name, newCurrency);
        }
    }
}
