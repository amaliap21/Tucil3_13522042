package Dictionary;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DictionaryAPI {
    public String getOnlineData(String word) {
        try {
            StringBuilder data = new StringBuilder();
            URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
                reader.close();

                // Parse the JSON response and get the word value
                JsonArray jsonArray = JsonParser.parseString(data.toString()).getAsJsonArray();
                JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                return jsonObject.get("word").getAsString();
            } else {
                return "Error: " + connection.getResponseMessage();
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
