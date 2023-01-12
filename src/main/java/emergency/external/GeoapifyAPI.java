package emergency.external;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import emergency.models.Adresse;

import java.io.IOException;
import java.net.URL;

public class GeoapifyAPI {

    private static final String API_KEY = "32f2c0a18a7c45dea90ca5da4bb2b32e";
    private static final String API_URL = "https://api.geoapify.com/v1/geocode/reverse?lat=%s&lon=%s&limit=1&apiKey=%s";

    public static Adresse getAddress(double latitude, double longitude) throws IOException {
        String url = String.format(API_URL, latitude, longitude, API_KEY);
        JsonNode root = new ObjectMapper().readTree(new URL(url));

        JsonNode addressNode = root.get("features").get(0).get("properties");

        String rue = addressNode.get("address_line1").textValue();
        String ville = addressNode.get("city").textValue();
        String etat = addressNode.get("state").textValue();
        String codePostal = addressNode.get("postcode").textValue();
        String pays = addressNode.get("country").textValue();

        Adresse adresse = new Adresse();
        adresse.setRue(rue);
        adresse.setVille(ville);
        adresse.setEtat(etat);
        adresse.setCodePostal(codePostal);
        adresse.setPays(pays);

        return adresse;
    }
}