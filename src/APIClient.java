import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class APIClient {
    public static void main(String[] args){
        try{
            String apiUrl = "https://official-joke-api.appspot.com/random_joke";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine = null; // Initialize the variable
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();



                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(response.toString());

                String setup = (String) jsonObject.get("setup");
                String type = (String) jsonObject.get("type");
                String punchline = (String) jsonObject.get("punchline");

                System.out.println("Type : " + type);
                System.out.println("Setup : " + setup);
                System.out.println("Punchline : " + punchline);
            } else{
                System.out.println("Request failed with response code " + responseCode);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
