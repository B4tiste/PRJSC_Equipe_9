package emergency.setupData;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.enumDefinition.*;
import emergency.external.GeocodingAPI;
import emergency.interfacesDefinition.IBaseModel;
import emergency.models.*;
import emergency.models.sensorRelated.Capteur;
import emergency.models.sensorRelated.CapteurDonnees;
import emergency.models.sensorRelated.Microcontroller;
import org.modelmapper.internal.bytebuddy.matcher.FilterableList;
import org.springframework.ws.soap.addressing.server.annotation.Address;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public final class TestDataA {

    public static double SEED = 12;
    public static int NB_CENTRE = 10;
    public static int NB_MICROCONTROLLEUR = 66;
    public static int NB_URGENCE = 5;

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
            c_1.setLongitude(pos_1[1] + (pos_2[1]-pos_1[1])*((Math.sin(SEED+m+1.5678)+1)/2.0));

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
                    Vehicule rc_1 = new Vehicule();
                    rc_1.setStatut(ReferentielDefinitions.getStatut(STATUT_RESSOURCE.GARE.name()));
                    rc_1.setAvailable(Boolean.TRUE);
                    rc_1.setNom("Ressource Composante A "+m);
                    rc_1.setRessource(r_1);
                    rc_1.setCapacite(Long.valueOf(10));
                    rc_1.setLatitude(c_1.getLatitude());
                    rc_1.setLongitude(c_1.getLongitude());

                    rc_1.setCentre(c_1);

                    List<Personne> personnes = new ArrayList<>();
                    for(int p = 0;p<(Math.cos(SEED)+1.0)/2.0*(SEED%5+1)+m%2; p++)
                    {
                        Personne p1 = new Personne(
                                "Jack","O'Neill N°"+p,
                                ReferentielDefinitions.getRole(ROLE_PERSONNE.POMPIER.name())
                        );
                        p1.setRessourceComposante(rc_1);
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

    public static List<Microcontroller> getMicrocontroller(String disposition)
    {
        if(disposition.equals(""))
        {
            disposition = "equi";
        }
        var coordinates = CoordinateGenerator.generateCoordinates(pos_1, pos_2, NB_MICROCONTROLLEUR, 0.05);
        List<Microcontroller> microcontrollers = new ArrayList<>();
        for(int m = 0; m < NB_MICROCONTROLLEUR; m++)
        {
            Microcontroller microcontroller = new Microcontroller();
            microcontroller.setNom("MICRO_"+m);
            microcontroller.setEtat(ReferentielDefinitions.getEtat(ETAT_MICROCONTROLLEUR.ALLUME.name()));
            if(disposition=="default")
            {
                microcontroller.setLatitude(pos_1[0] + (pos_2[0]-pos_1[0])*((Math.cos(SEED+m*1.45)+1)/2.0));
                microcontroller.setLongitude(pos_1[1] + (pos_2[1]-pos_1[1])*((Math.sin(SEED+m+1.7678)+1)/2.0));

            }
            else{
                microcontroller.setLatitude(coordinates.get(m)[0]);
                microcontroller.setLongitude(coordinates.get(m)[1]);
            }
            List<Capteur> capteurs = new ArrayList<>();
            Capteur capteur = new Capteur();
            capteur.setCapteurType(ReferentielDefinitions.getCapteurType(TYPE_CAPTEUR.CHALEUR.name()));
            capteur.setMicrocontroller(microcontroller);
            capteur.setIdentifier("CAPTEUR_"+m);
            capteur.setRadius(50.0);



            List<CapteurDonnees> capteurDonnees = new ArrayList<>();
            CapteurDonnees capteurDonnees1 = new CapteurDonnees();
            capteurDonnees1.setCapteur(capteur);
            capteurDonnees1.setIdentifier("CHALEUR_"+m);
            capteurDonnees1.setValeur("0");
            capteurDonnees.add(capteurDonnees1);



            capteur.setCapteurDonnees(capteurDonnees);
            capteurs.add(capteur);
            microcontroller.setCapteurs(capteurs);
            microcontrollers.add(microcontroller);

        }
        return microcontrollers;
    }

    public static List<Urgence> getUrgence()
    {
        List<Urgence> urgences = new ArrayList<>();

        GeocodingAPI apiGeocoding = new GeocodingAPI();


        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

        var centre = ReferentielDefinitions.serviceDefinitions.getCentreService().GetAll();

        for(int u = 0; u < NB_URGENCE; u++)
        {
            Urgence urgence = new Urgence();
            Incident incident = new Incident();
            urgence.setStatut(ReferentielDefinitions.getStatut(STATUT_URGENCE.DEMARRE.name()));
            urgence.setType(ReferentielDefinitions.getTypeUrgence(TYPE_URGENCE.INCENDIE.name()));

            urgence.setDateCreation(date);
            urgence.setDateUpdate(date);

            urgence.setTitre("Urgence TEST n°"+u);

            incident.setLatitude(pos_1[0] + (pos_2[0]-pos_1[0])*((Math.cos(SEED+u)+1*0.4356)/2.0));
            incident.setLongitude(pos_1[1] + (pos_2[1]-pos_1[1])*((Math.sin(SEED+u+2.4678)+1)/2.0));

            Adresse addr = apiGeocoding.reverseGeocode(incident.getLatitude(), incident.getLongitude());

            if(u%4==0)
            {
                incident.setPriorite(ReferentielDefinitions.getPriorite(PRIORITE_INCIDENT.PEU_ELEVEE.name()));
            }
            else if(u%4==2)
            {
                incident.setPriorite(ReferentielDefinitions.getPriorite(PRIORITE_INCIDENT.ELEVEE.name()));
            }
            else{
                incident.setPriorite(ReferentielDefinitions.getPriorite(PRIORITE_INCIDENT.MOYENNE.name()));
            }
            incident.setNom("Incident TEST n°"+u);
            incident.setDescriptionIncident("Voici une description test d'incident n°"+u);
            incident.setRadius(0.05);
            incident.setAdresse(addr);
            incident.setDateCreation(date);
            incident.setDateUpdate(date);
            urgence.setIncident(incident);
            incident.setUrgence(urgence);

            List<Ressource> rcs = new ArrayList<>();
            Double mx_c = 1+u%2+SEED%2;
            for(int d = 0; d<mx_c;d++)
            {
                Ressource r_1 = new Ressource();
                var centre_ = (Centre)centre.get((int)(centre.size()*(d/mx_c) ) );
                r_1.setCentre(centre_);
                r_1.setUrgence(urgence);
                r_1.setNom("Ressource A " + d);
                var ft = ReferentielDefinitions.TYPE_RESSOURCES;
                r_1.setType(ReferentielDefinitions.getTypeRessource(TYPE_RESSOURCE.CAMION_POMPIER.name()));
                r_1.setStatut(ReferentielDefinitions.getStatut(STATUT_RESSOURCE.EN_EXECUTION.name()));
                List<Vehicule> rcComposantes = new ArrayList<>();
                Double mx = 2+SEED%2+u%2+d%2;
                for(int e = 0;e<mx; e++)
                {
                    Vehicule rc_1 = new Vehicule();
                    rc_1.setStatut(ReferentielDefinitions.getStatut(STATUT_RESSOURCE.EN_EXECUTION.name()));
                    rc_1.setAvailable(Boolean.TRUE);
                    rc_1.setNom("Ressource Composante A "+u);
                    rc_1.setRessource(r_1);
                    rc_1.setCapacite(Long.valueOf(10));
                    rc_1.setLatitude(incident.getLatitude());
                    rc_1.setLongitude(incident.getLongitude());

                    rc_1.setCentre(centre_);

                    List<Personne> personnes = new ArrayList<>();
                    for(int p = 0;p<(Math.cos(SEED)+1.0)/2.0*(SEED%7+1)+u%2; p++)
                    {
                        Personne p1 = new Personne(
                                "Jack","O'Neill N°"+p,
                                ReferentielDefinitions.getRole(ROLE_PERSONNE.POMPIER.name())
                        );
                        p1.setRessourceComposante(rc_1);
                        personnes.add(p1);
                    }
                    rc_1.setPersonnes(personnes);
                    rcComposantes.add(rc_1);
                }

                r_1.setVehicules(rcComposantes);

                rcs.add(r_1);
            }
            urgence.setRessources(rcs);
            urgences.add(urgence);
        }
        return urgences;
    }
}