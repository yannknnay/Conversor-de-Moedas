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
