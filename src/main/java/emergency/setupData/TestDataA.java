package emergency.setupData;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.enumDefinition.ROLE_PERSONNE;
import emergency.enumDefinition.STATUT_RESSOURCE;
import emergency.enumDefinition.TYPE_RESSOURCE;
import emergency.external.GeocodingAPI;
import emergency.interfacesDefinition.IBaseModel;
import emergency.models.*;
import org.modelmapper.internal.bytebuddy.matcher.FilterableList;
import org.springframework.ws.soap.addressing.server.annotation.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public final class TestDataA {

    public static double SEED = 12;
    public static int NB_CENTRE = 10;

    public static double[] pos_1 = {45.74713651948293, 4.9176373375707225};
    public static double[] pos_2 = {45.7899227338535, 4.865472156698866};



    public static List<Centre> getCentres()
    {
        List<Centre> centres = new ArrayList<Centre>();
        GeocodingAPI apiGeocoding = new GeocodingAPI();
        for(int m = 0; m < NB_CENTRE; m++)
        {
            Centre c_1 = new Centre();
            c_1.setNom("Centre "+m);
            c_1.setAvailable(Boolean.TRUE);
            c_1.setLatitude(pos_1[0] + (pos_2[0]-pos_1[0])*((Math.cos(SEED+m)+1)/2.0));
            c_1.setLongitude(pos_1[0] + (pos_2[0]-pos_1[0])*((Math.sin(SEED+m+1.5678)+1)/2.0));

            Adresse addr = apiGeocoding.reverseGeocode(c_1.getLatitude(), c_1.getLongitude());

            c_1.setAdresse(addr);

            List<Ressource> rcs = new ArrayList<>();
            for(int d = 0; d<1+m%2+SEED%2;d++)
            {
                Ressource r_1 = new Ressource();
                r_1.setCentre(c_1);
                r_1.setNom("Ressource A " + d);
                var ft = ReferentielDefinitions.TYPE_RESSOURCES;
                r_1.setType(ReferentielDefinitions.getTypeRessource(TYPE_RESSOURCE.CAMION_POMPIER.name()));
                r_1.setStatut(ReferentielDefinitions.getStatut(STATUT_RESSOURCE.GARE.name()));
                List<RessourceComposante> rcComposantes = new ArrayList<>();
                for(int e = 0;e<2+SEED%2+m%2+d%2; e++)
                {
                    RessourceComposante rc_1 = new RessourceComposante();
                    rc_1.setAvailable(Boolean.TRUE);
                    rc_1.setNom("Ressource Composante A "+m);
                    rc_1.setRessource(r_1);

                    List<Personne> personnes = new ArrayList<>();
                    for(int p = 0;p<(Math.cos(SEED)+1.0)/2.0*(SEED%5+1)+m%2; p++)
                    {
                        Personne p1 = new Personne(
                                "Jack","O'Neill N°"+p,
                                ReferentielDefinitions.getRole(ROLE_PERSONNE.POMPIER.name())
                        );
                        personnes.add(p1);
                    }
                    rc_1.setPersonnes(personnes);
                    rcComposantes.add(rc_1);
                }

                r_1.setRessourceComposantes(rcComposantes);

                rcs.add(r_1);
            }
            c_1.setRessource(rcs);

            centres.add(c_1);

        }
        return centres;

    }
}