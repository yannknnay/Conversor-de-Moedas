package model;

import java.util.Map;

public class Currency {
    private String baseCode;
    private Map<String, Double> conversionRates;

    public String getBaseCode() {
        return baseCode;
    }


    public Map<String, Double> getAllConversionRates() {
        return conversionRates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "baseCode='" + baseCode + '\'' +
                ", conversionRates=" + conversionRates +
                '}';
    }
}

