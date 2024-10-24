package models;

import java.util.Map;
import java.util.TreeMap;

public record ResponseAPI(String base_code, TreeMap<String, Double> conversion_rates) {
}
