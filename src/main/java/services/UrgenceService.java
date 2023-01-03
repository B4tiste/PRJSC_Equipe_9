package services;

import models.*;
import services.referentiel.AdresseService;
import services.referentiel.PrioriteService;
import services.referentiel.StatutService;
import services.type.TypeRessourceService;
import services.type.TypeUrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrgenceService extends BaseService {

    @Autowired
    public TypeRessourceService typeRessourceService;
    @Autowired
    public TypeUrgenceService typeUrgenceService;
    @Autowired
    public IncidentService incidentService;
    @Autowired
    public AdresseService adresseService;
    @Autowired
    public PrioriteService prioriteService;
    @Autowired
    public StatutService statutService;




    /* Creer une urgence avec un incident par défaut*/
    public Urgence CreateUrgence(
            Urgence urgence, /* Urgence à créer */
            Optional<Incident> incidentOpt, /* Incident à créer */
            Optional<Adresse> adresseOptIncident /* Adresse de l'incident */
    )
    {
        try{

            long millis = System.currentTimeMillis();
            java.sql.Date dateNow = new java.sql.Date(millis);


            /*
            Est ce que la manoeuvre de creation des instances associées
            a bien fonctionnée pour celles qui sont nécessaires à la
            création de notre instance principale
             */
            Boolean manoeuvre = Boolean.TRUE;

            Incident createdIncident = null;
            Adresse createdAdresse = null;


            Urgence createdUrgence = null;


            //CREATING ADDRESS

            try{
                if(adresseOptIncident.isPresent())
                {
                    createdAdresse = (Adresse) this.adresseService.CreateOrGet(adresseOptIncident.get());
                    if(createdAdresse==null) {
                        manoeuvre = Boolean.FALSE;
                        System.out.println("[Urgence Service] > CreateUrgence > Adresse creation for incident return null");
                    }
                    else
                    {
                        if(incidentOpt.isPresent())
                        {
                            incidentOpt.get().setAdresse(createdAdresse);
                        }
                        else
                        {
                            var incident = urgence.getIncident();
                            incident.setAdresse(createdAdresse);
                            try{
                                var inc = (Incident)this.incidentService.Update(incident);
                                if(inc != null)
                                {
                                    urgence.setIncident(inc);
                                }
                                else
                                {
                                    System.out.println("[Urgence Service] > CreateUrgence > Impossible to update incident with adress change");
                                    manoeuvre = Boolean.FALSE;
                                }

                            }catch(Exception e)
                            {
                                e.printStackTrace();
                                manoeuvre = Boolean.FALSE;
                            }
                        }

                    }
                }

            }
            catch(Exception e)
            {
                e.printStackTrace();
                manoeuvre = Boolean.FALSE;
            }

            if(manoeuvre== Boolean.FALSE){return null;}


            //CREATING INCIDENT


            try{
                if(incidentOpt.isPresent())
                {
                    createdIncident = (Incident)this.incidentService.CreateOrGet(incidentOpt.get());
                    if(createdIncident==null) {
                        manoeuvre = Boolean.FALSE;
                        System.out.println("[Urgence Service] > CreateUrgence > incident creation return null");
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                manoeuvre = Boolean.FALSE;
            }

            if(manoeuvre== Boolean.FALSE){return null;}




            if(manoeuvre == Boolean.TRUE)
            {
                try{
                    createdUrgence = (Urgence)this.Create(urgence);
                }
                catch(Exception e)
                {
                    System.out.println("[Urgence Service] > CreateUrgence > urgence creation failed");
                    e.printStackTrace();
                    manoeuvre = Boolean.FALSE;
                }


            }
            if(manoeuvre== Boolean.TRUE)
            {
                return createdUrgence;
            }
            else
            {
                return null;
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
