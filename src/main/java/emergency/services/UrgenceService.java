package emergency.services;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.enumDefinition.STATUT_RESSOURCE;
import emergency.modelDto.UrgenceDto;
import emergency.models.*;
import emergency.repositories.UrgenceRepository;
import emergency.repositories.type.TypeRessourceRepository;
import emergency.services.referentiel.AdresseService;
import emergency.services.referentiel.PrioriteService;
import emergency.services.referentiel.StatutService;
import emergency.services.type.TypeRessourceService;
import emergency.services.type.TypeUrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class UrgenceService extends BaseService {

    public final int VEHICULE_RES = 5;
    public UrgenceService(UrgenceRepository urgenceRepository, VehicleService vehicleService, TypeRessourceService typeRessourceService, TypeUrgenceService typeUrgenceService, IncidentService incidentService, AdresseService adresseService, PrioriteService prioriteService, StatutService statutService) {
        this.typeRessourceService = typeRessourceService;
        this.typeUrgenceService = typeUrgenceService;
        this.incidentService = incidentService;
        this.adresseService = adresseService;
        this.prioriteService = prioriteService;
        this.statutService = statutService;
        this.baseRepository = urgenceRepository;
        this.urgenceRepository = urgenceRepository;
        this.vehicleService = vehicleService;
    }

    @Autowired
    public TypeRessourceService typeRessourceService;
    @Autowired
    public VehicleService vehicleService;
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
    @Autowired
    public UrgenceRepository urgenceRepository;



    public int getNumResources(double radius) {
        double radiusInMeters = radius * 1000;
        double area = Math.PI * radiusInMeters * radiusInMeters;
        int nbResources = (int) Math.ceil(area / 100);

        if(nbResources<1)
        {
            nbResources = 1;
        }
        return nbResources;
    }

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
                    createdAdresse = (Adresse) this.adresseService.CreateOrUpdateOrGet(adresseOptIncident.get());
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
                                var inc = (Incident)this.incidentService.CreateOrUpdateOrGet(incident);
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
                    createdIncident = (Incident)this.incidentService.CreateOrUpdateOrGet(incidentOpt.get());
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
                    createdUrgence = (Urgence)this.CreateOrUpdateOrGet(urgence);
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


    public Urgence FillFireUrgency(
            Urgence urgence
    )
    {
        try {
            var urgence_get = (Urgence)this.CreateOrUpdateOrGet(urgence);


            if(urgence_get.getIncident()!=null)
            {
                var rad = urgence_get.getIncident().getRadius();
                int nb_ressources = getNumResources(rad);
                int filled_res = nb_ressources;

                if(urgence_get.getRessources()!=null)
                {
                    for(var res : urgence_get.getRessources())
                    {
                        var veh = res.getVehicules();
                        if(veh != null)
                        {
                            for(var vehicule : veh)
                            {
                                int nb_pers = 0;
                                if(vehicule.getPersonnes()!=null)
                                {
                                    nb_pers = vehicule.getPersonnes().size();
                                }

                                int nv = VEHICULE_RES + nb_pers;

                                filled_res = filled_res - nv;
                                if(filled_res+nv<=0)
                                {
                                    filled_res = filled_res + nv;
                                    var centre = vehicule.getCentre();

                                    var res_c = centre.getRessource();
                                    if(res_c != null)
                                    {
                                        if(res_c.size()>0)
                                        {
                                            vehicule.getRessource().getVehicules().remove(vehicule);

                                            vehicule.setRessource(res_c.get(0));
                                            vehicule.setStatut(
                                                    ReferentielDefinitions.getStatut(STATUT_RESSOURCE.En_DEPLACEMENT_RETOUR.name())
                                            );
                                            res_c.get(0).getVehicules().add(vehicule);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }



                Boolean filled = Boolean.FALSE;
                int n = 0;
                while(filled == Boolean.FALSE)
                {
                    if(filled_res<=0)
                    {
                        filled = Boolean.TRUE;
                        break;
                    }
                    org.springframework.data.domain.Pageable pageable = PageRequest.of(n, 2);
                    var vehicules = this.vehicleService.FindVehicules(
                            urgence_get.getIncident().getLongitude(),
                            urgence_get.getIncident().getLatitude(),
                            pageable
                    );
                    if(vehicules.hasContent())
                    {
                        for(var vehicle : vehicules.getContent())
                        {
                            if(vehicle.isAvailable()==Boolean.TRUE)
                            {
                                int nb_pers = 0;
                                if(vehicle.getPersonnes()!=null)
                                {
                                    nb_pers = vehicle.getPersonnes().size();
                                }

                                int nv = VEHICULE_RES + nb_pers;
                                filled_res = filled_res - nv;

                                var res = urgence_get.getRessources();
                                if(res != null)
                                {
                                    if(res.size()>0)
                                    {
                                        var vehicules_ur = res.get(0).getVehicules();

                                        vehicle.setStatut(
                                                ReferentielDefinitions.getStatut(STATUT_RESSOURCE.EN_DEPLACEMENT_ALLER.name())
                                        );

                                        vehicle.setAvailable(Boolean.FALSE);
                                        res.get(0).getVehicules().remove(vehicle);
                                        vehicle.setRessource(res.get(0));
                                        vehicules_ur.add(vehicle);
                                        res.get(0).setVehicules(vehicules_ur);

                                    }
                                }
                                urgence_get.setRessources(res);
                                if(filled_res<=0)
                                {
                                    filled = Boolean.TRUE;
                                }
                            }
                        }
                    }
                    else {
                        return null;
                    }
                    n++;
                }



            }
            urgence_get = (Urgence)this.CreateOrUpdateOrGet(urgence_get);
            return urgence_get;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
