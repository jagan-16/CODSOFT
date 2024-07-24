import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyConverterGUI {
    private static final String API_KEY = "2b73b7b80157a5a19f8bb1fb"; // Replace with your actual API key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Currency Converter");
            frame.setSize(400, 250);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
            JTextField baseCurrencyField = new JTextField("");
            JTextField targetCurrencyField = new JTextField("");
            JTextField amountField = new JTextField("");
            JButton convertButton = new JButton("Convert");
            JLabel resultLabel = new JLabel("Result: ");

        
            frame.add(new JLabel("Base Currency:"));
            frame.add(baseCurrencyField);
            frame.add(new JLabel("Target Currency:"));
            frame.add(targetCurrencyField);
            frame.add(new JLabel("Amount:"));
            frame.add(amountField);
            frame.add(convertButton);
            frame.add(resultLabel);

            
            convertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String baseCurrency = baseCurrencyField.getText().toUpperCase();
                        String targetCurrency = targetCurrencyField.getText().toUpperCase();
                        double amount = Double.parseDouble(amountField.getText());

                        if (!isSupportedCurrency(baseCurrency) || !isSupportedCurrency(targetCurrency)) {
                            resultLabel.setText("Unsupported currency.");
                            return;
                        }

                        Map<String, Double> conversionRates = getConversionRates(baseCurrency);

                        if (conversionRates.containsKey(targetCurrency)) {
                            double convertedAmount = amount * conversionRates.get(targetCurrency);
                            DecimalFormat df = new DecimalFormat("#.##");
                            resultLabel.setText(amount + " " + baseCurrency + " = " + df.format(convertedAmount) + " " + targetCurrency);
                        } else {
                            resultLabel.setText("Conversion rate not available.");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        resultLabel.setText("Error during conversion.");
                    }
                }
            });

            frame.setVisible(true);
        });
    }

    private static boolean isSupportedCurrency(String currency) {
        String[] supportedCurrencies = {"INR", "USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF"};
        for (String supported : supportedCurrencies) {
            if (supported.equals(currency)) {
                return true;
            }
        }
        return false;
    }

    private static Map<String, Double> getConversionRates(String baseCurrency) throws Exception {
        String urlString = API_URL + baseCurrency;
        URL url = new URL(urlString);
        HttpURLConnection connection = null;
        BufferedReader in = null;
        Map<String, Double> conversionRates = new HashMap<>();

        try {
            // Open connection
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read response
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(in).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            // Extract conversion rates
            for (Map.Entry<String, JsonElement> entry : rates.entrySet()) {
                conversionRates.put(entry.getKey(), entry.getValue().getAsDouble());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error retrieving conversion rates: " + e.getMessage(), e);
        } finally {
            // Close resources
            if (in != null) {
                in.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return conversionRates;
    }
}
