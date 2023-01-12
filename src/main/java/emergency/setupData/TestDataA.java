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

    public static double SEED = 14;
    public static int NB_CENTRE = 6;
    public static int NB_MICROCONTROLLEUR = 66;
    public static int NB_URGENCE = 5;

    public static double[][] data =  new double[][]{
                new double[]{
                        45.76572663043501,
                        4.862019614664405
                }, new double[] {
                45.76572663043501,
                4.866516216483028
        }, new double[] {
                45.76572663043501,
                4.871012818301651
        }, new double[] {
                45.76572663043501,
                4.875509420120274
        }, new double[] {
                45.76572663043501,
                4.880006021938897
        }, new double[] {
                45.76572663043501,
                4.88450262375752
        }, new double[] {
                45.76572663043501,
                4.888999225576143
        }, new double[] {
                45.76572663043501,
                4.893495827394766
        }, new double[] {
                45.76572663043501,
                4.897992429213389
        }, new double[] {
                45.76572663043501,
                4.902489031032012
        }, new double[] {
                45.76572663043501,
                4.906985632850635
        }, new double[] {
                45.77023946945844,
                4.862019614664405
        }, new double[] {
                45.77023946945844,
                4.866516216483028
        }, new double[] {
                45.77023946945844,
                4.871012818301651
        }, new double[] {
                45.77023946945844,
                4.875509420120274
        }, new double[] {
                45.77023946945844,
                4.880006021938897
        }, new double[] {
                45.77023946945844,
                4.88450262375752
        }, new double[] {
                45.77023946945844,
                4.888999225576143
        }, new double[] {
                45.77023946945844,
                4.893495827394766
        }, new double[] {
                45.77023946945844,
                4.897992429213389
        }, new double[] {
                45.77023946945844,
                4.902489031032012
        }, new double[] {
                45.77023946945844,
                4.906985632850635
        }, new double[] {
                45.774752308481865,
                4.862019614664405
        }, new double[] {
                45.774752308481865,
                4.866516216483028
        }, new double[] {
                45.774752308481865,
                4.871012818301651
        }, new double[] {
                45.774752308481865,
                4.875509420120274
        }, new double[] {
                45.774752308481865,
                4.880006021938897
        }, new double[] {
                45.774752308481865,
                4.88450262375752
        }, new double[] {
                45.774752308481865,
                4.888999225576143
        }, new double[] {
                45.774752308481865,
                4.893495827394766
        }, new double[] {
                45.774752308481865,
                4.897992429213389
        }, new double[] {
                45.774752308481865,
                4.902489031032012
        }, new double[] {
                45.774752308481865,
                4.906985632850635
        }, new double[] {
                45.77926514750529,
                4.862019614664405
        }, new double[] {
                45.77926514750529,
                4.866516216483028
        }, new double[] {
                45.77926514750529,
                4.871012818301651
        }, new double[] {
                45.77926514750529,
                4.875509420120274
        }, new double[] {
                45.77926514750529,
                4.880006021938897
        }, new double[] {
                45.77926514750529,
                4.88450262375752
        }, new double[] {
                45.77926514750529,
                4.888999225576143
        }, new double[] {
                45.77926514750529,
                4.893495827394766
        }, new double[] {
                45.77926514750529,
                4.897992429213389
        }, new double[] {
                45.77926514750529,
                4.902489031032012
        }, new double[] {
                45.77926514750529,
                4.906985632850635
        }, new double[] {
                45.78377798652872,
                4.862019614664405
        }, new double[] {
                45.78377798652872,
                4.866516216483028
        }, new double[] {
                45.78377798652872,
                4.871012818301651
        }, new double[] {
                45.78377798652872,
                4.875509420120274
        }, new double[] {
                45.78377798652872,
                4.880006021938897
        }, new double[] {
                45.78377798652872,
                4.88450262375752
        }, new double[] {
                45.78377798652872,
                4.888999225576143
        }, new double[] {
                45.78377798652872,
                4.893495827394766
        }, new double[] {
                45.78377798652872,
                4.897992429213389
        }, new double[] {
                45.78377798652872,
                4.902489031032012
        }, new double[] {
                45.78377798652872,
                4.906985632850635
        }, new double[] {
                45.788290825552146,
                4.862019614664405
        }, new double[] {
                45.788290825552146,
                4.866516216483028
        }, new double[] {
                45.788290825552146,
                4.871012818301651
        }, new double[] {
                45.788290825552146,
                4.875509420120274
        }, new double[] {
                45.788290825552146,
                4.880006021938897
        }, new double[] {
                45.788290825552146,
                4.88450262375752
        }, new double[] {
                45.788290825552146,
                4.888999225576143
        }, new double[] {
                45.788290825552146,
                4.893495827394766
        }, new double[] {
                45.788290825552146,
                4.897992429213389
        }, new double[] {
                45.788290825552146,
                4.902489031032012
        }, new double[] {
                45.788290825552146,
                4.906985632850635
        }
        };

    public static final double R = 6372.8;
    /*public static double[] pos_1 = {45.74713651948293, 4.9176373375707225};
    public static double[] pos_2 = {45.7899227338535, 4.865472156698866};*/

    public static double[] pos_1 = {45.773823823027685,  4.907187868645735};
    public static double[] pos_2 = {45.79019363295949, 4.8618173788693015 };

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

    public static List<double[]> placeSensors(double lat1, double lon1, double lat2, double lon2, double maxDistance, int n) {
        List<double[]> sensorCoordinates = new ArrayList<>();
        double distance = haversine(lat1, lon1, lat2, lon2);
        int numRows = (int) Math.ceil(distance / maxDistance);
        double dLat = (lat2 - lat1) / numRows;
        double dLon = (lon2 - lon1) / numRows;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                double sensorLat = lat1 + (i * dLat);
                double sensorLon = lon1 + (j * dLon);
                sensorCoordinates.add(new double[]{sensorLat, sensorLon});
                if (sensorCoordinates.size() >= n) {
                    return sensorCoordinates;
                }
            }
        }
        return sensorCoordinates;
    }


    public static double maxDistance(double lat1, double lon1, double lat2, double lon2, int n) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double distance = 2 * Math.asin(Math.sqrt(a)) * R;
        return distance / (2 * Math.sqrt(n));
    }

    public static double maxDistanceF() {
        var max_distance = maxDistance(pos_1[0],pos_1[1],pos_2[0], pos_2[1], NB_MICROCONTROLLEUR);
        return max_distance;
    }





    public static double distance(double lat1, double lat2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double distance = R * dLat;
        return distance;
    }



    public static List<Centre> getCentres()
    {
        List<Centre> centres = new ArrayList<Centre>();
        GeocodingAPI apiGeocoding = new GeocodingAPI();
        for(int m = 0; m < NB_CENTRE; m++)
        {
            Centre c_1 = new Centre();
            c_1.setNom("Centre "+m);
            c_1.setAvailable(Boolean.TRUE);
            var po_lat_lng = PositionGenerator.generatePosition(pos_1, pos_2, SEED+m+1.56);
            c_1.setLatitude(po_lat_lng[0]);
            c_1.setLongitude(po_lat_lng[1]);
            /*c_1.setLatitude(pos_1[0] + (pos_2[0]-pos_1[0])*((Math.cos(SEED+m)+1)/2.0));
            c_1.setLongitude(pos_1[1] + (pos_2[1]-pos_1[1])*((Math.sin(SEED+m+1.5678)+1)/2.0));*/

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
                for(int e = 0;e<5+SEED%2+m%2+d%2; e++)
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
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        var max_distance = maxDistance(pos_1[0],pos_1[1],pos_2[0], pos_2[1], NB_MICROCONTROLLEUR);
       /* var Coordinates = placeSensors(pos_1[0],pos_1[1],pos_2[0], pos_2[1],max_distance, NB_MICROCONTROLLEUR);
        var Coordinates = placeSensors(pos_1[0],pos_1[1],pos_2[0], pos_2[1],max_distance, NB_MICROCONTROLLEUR);*/

        var Coordinates = data;/* CoordinateGeneratorB.createGrid(
                pos_1[0],pos_1[1],pos_2[0], pos_2[1], max_distance, NB_MICROCONTROLLEUR
        );
        /*var Coordina = CoordinateGenerator.generateIntermediatePoints(
                new double[] {pos_1[0],pos_1[1]},
                new double[] {pos_2[0], pos_2[1]},
                NB_MICROCONTROLLEUR,max_distance
        );*/

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
                var po_lat_lngb = PositionGenerator.generatePosition(pos_1, pos_2, SEED+m+0.7680);
                microcontroller.setLatitude(po_lat_lngb[0]);
                microcontroller.setLongitude(po_lat_lngb[1]);
                /*microcontroller.setLatitude(pos_1[0] + (pos_2[0]-pos_1[0])*((Math.cos(SEED+m*1.45)+1)/2.0));
                microcontroller.setLongitude(pos_1[1] + (pos_2[1]-pos_1[1])*((Math.sin(SEED+m+1.7678)+1)/2.0));*/

            }
            else{
                /*var y_diff = distance(pos_1[0], pos_2[0]);
                var x_diff = distance(pos_1[1], pos_2[1]);

                var dis = 500.0;*/

                microcontroller.setLatitude(Coordinates[m][0]);
                microcontroller.setLongitude(Coordinates[m][1]);
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
            capteurDonnees1.setDateUpdate(date);
            capteurDonnees1.setDateCreation(date);
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

            var po_lat_lngb = PositionGenerator.generatePosition(pos_1, pos_2, SEED+u+0.18680);
            incident.setLatitude(po_lat_lngb[0]);
            incident.setLongitude(po_lat_lngb[1]);

            /*incident.setLatitude(pos_1[0] + (pos_2[0]-pos_1[0])*((Math.cos(SEED+u)+1*0.4356)/2.0));
            incident.setLongitude(pos_1[1] + (pos_2[1]-pos_1[1])*((Math.sin(SEED+u+2.4678)+1)/2.0));*/

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
                r_1.setStatut(ReferentielDefinitions.getStatut(STATUT_RESSOURCE.GARE.name()));
                List<Vehicule> rcComposantes = new ArrayList<>();
                Double mx = 2+SEED%2+u%2+d%2;
                for(int e = 0;e<mx; e++)
                {
                    Vehicule rc_1 = new Vehicule();
                    rc_1.setStatut(ReferentielDefinitions.getStatut(STATUT_RESSOURCE.GARE.name()));
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