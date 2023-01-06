package emergency.external;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import emergency.models.Adresse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@EnableCaching
public class GeocodingAPI {
    public static String ACCESS_KEY = "131c8fae481d876ccfe958af0814e257";
    public static String API_URL = "http://api.positionstack.com/v1/";

    @Cacheable("addresses")
    public Adresse reverseGeocode(double latitude, double longitude) {
        Adresse addr = new Adresse();
        addr.setCodePostal("69000");
        addr.setEtat("Rhones-Alpes");
        addr.setPays("France");
        addr.setVille("Lyon");
        addr.setRue("12 rue des beaumes");
        return addr;
       /* RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "reverse?access_key=" + ACCESS_KEY + "&query=" + latitude + "," + longitude;
        String result = restTemplate.getForObject(url, String.class);

        // on lit l'adresse dans le json
        Adresse adresse = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(result);
            JsonNode data = root.path("data");
            JsonNode firstResult = data.get(0);
            JsonNode addressNode = firstResult.path("address");

            adresse = new Adresse();
            adresse.setRue(addressNode.path("road").asText());
            adresse.setVille(addressNode.path("city").asText());
            adresse.setEtat(addressNode.path("region").asText());
            adresse.setCodePostal(addressNode.path("postal_code").asText());
            adresse.setPays(addressNode.path("country").asText());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adresse;*/
    }
}