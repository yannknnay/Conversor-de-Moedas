package model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Currency {
    private String baseCode;
    private Map<String, Double> conversionRates;

    public String getBaseCode() {
        return baseCode;
    }

    public Double getConversionRate(String baseCode) {
        return this.conversionRates.get(baseCode);
    }

    public Map<String, Double> getAllConversionRates() {
        return conversionRates;
    }

    public void filterConversionRates(String[] allowedCurrency) {
        this.conversionRates = this.conversionRates.entrySet().stream()
                .filter(entry -> List.of(allowedCurrency).contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public String toString() {
        return STR."Currency{baseCode='\{baseCode}, conversionRates=\{conversionRates}}";
    }

}
