package emergency.Algo;


import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.models.sensorRelated.Capteur;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Component
public class AccesDataEmergency {


    public void APICommunicationBdd()
    {

    }

    public List<Capteur> getCapteurs() {
        var capteur = ReferentielDefinitions.serviceDefinitions.getCapteurService().GetAll();
        List<Capteur> c = new ArrayList<>();
        for(var cap : capteur)
        {
            c.add((Capteur)cap);
        }
        return c;
    }

    public List<Trouple> getSensorValues ()
    {

        var capteurs = this.getCapteurs();
        List<Trouple> tr = new ArrayList<>();
        for(var cap : capteurs)
        {
            tr.add(new Trouple(
                    Long.valueOf(cap.getId()).intValue(),
                    cap.getMicrocontroller().getLongitude(),
                    cap.getMicrocontroller().getLatitude(),
                    Integer.parseInt(cap.getCapteurDonnees().get(0).getValeur())
            ));
        }

        return tr;
    }


    public void saveValuesFires(List<Trouple> fires)
    {


    }

}