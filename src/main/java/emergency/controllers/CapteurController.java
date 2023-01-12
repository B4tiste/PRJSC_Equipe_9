package emergency.controllers;

import emergency.Algo.CalculGeneral;
import emergency.Algo.CalculMain;
import emergency.Algo.Trouple;
import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.enumDefinition.*;
import emergency.external.GeoapifyAPI;
import emergency.interfacesDefinition.IBaseModel;
import emergency.modelDto.IncidentDto;
import emergency.modelDto.UrgenceDto;
import emergency.modelDto.sensorRelated.CapteurDto;
import emergency.modelView.CapteurDataLocateViewModel;
import emergency.modelView.CapteurDataViewModel;
import emergency.modelView.CapteurDataVincentViewModel;
import emergency.models.Incident;
import emergency.models.Ressource;
import emergency.models.Urgence;
import emergency.models.sensorRelated.Capteur;
import emergency.services.CapteurService;
import emergency.setupData.CoordinateGenerator;
import emergency.setupData.SensorGrouping;
import emergency.setupData.SensorUtils;
import emergency.setupData.TestDataA;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/Capteur")
public class CapteurController extends emergency.controllers.BaseController {

    @Autowired
    public CapteurService capteurService;

    @Autowired
    public ServiceDefinitions services;

    public CapteurController(ServiceDefinitions services, CapteurService capteurService)
    {
        ReferentielDefinitions.serviceDefinitions = services;
        this.services = services;
        this.capteurService = capteurService;
        this.setService(this.capteurService);
    }

    @GetMapping("/GetCapteurs")
    public ResponseEntity<List<CapteurDto>> GetCapteurs(@RequestParam(value = "OnlyId", defaultValue = "false") boolean OnlyId)
    {
        try {
            var capteurs = this.capteurService.GetAll();

            List<CapteurDto> capteurs_d = new ArrayList<>();
            for(var capteur : capteurs)
            {
                var capteur_b = (Capteur)capteur;
                capteurs_d.add((CapteurDto)capteur_b.toDto(OnlyId));
            }

            return (new ResponseEntity<List<CapteurDto>>(capteurs_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<CapteurDto>>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/GetCapteursData")
    public ResponseEntity<List<CapteurDataViewModel>> GetCapteursData(@RequestParam(value = "OnlyId", defaultValue = "false") boolean OnlyId)
    {
        try {
            var capteurs = this.capteurService.GetAll();

            List<CapteurDataViewModel> capteurs_d = new ArrayList<>();
            for(var capteur : capteurs)
            {
                var capteur_b = (Capteur)capteur;
                var model = new CapteurDataViewModel();
                model.idCapteur = capteur_b.getId();
                model.idenCapteur = capteur_b.getIdentifier();
                try {
                    model.numCapteur = Integer.valueOf(capteur_b.getIdentifier().split("_")[1]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
                model.etat = capteur_b.getMicrocontroller().getEtat().getNom();
                model.intCapteur = Integer.valueOf(capteur_b.getCapteurDonnees().get(0).getValeur());
                capteurs_d.add(model);
            }

            return (new ResponseEntity<List<CapteurDataViewModel>>(capteurs_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<CapteurDataViewModel>>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/GetCapteursDataLocate")
    public ResponseEntity<List<CapteurDataLocateViewModel>> GetCapteursDataLocate(@RequestParam(value = "OnlyId", defaultValue = "false") boolean OnlyId)
    {
        try {
            var capteurs = this.capteurService.GetAll();

            List<CapteurDataLocateViewModel> capteurs_d = new ArrayList<>();
            for(var capteur : capteurs)
            {
                var capteur_b = (Capteur)capteur;
                var model = new CapteurDataLocateViewModel();
                model.idCapteur = capteur_b.getId();
                model.lat = capteur_b.getMicrocontroller().getLatitude();
                model.lng = capteur_b.getMicrocontroller().getLongitude();
                model.idenCapteur = capteur_b.getIdentifier();
                try {
                    model.numCapteur = Integer.valueOf(capteur_b.getIdentifier().split("_")[1]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
                model.etat = capteur_b.getMicrocontroller().getEtat().getNom();
                model.intCapteur = Integer.valueOf(capteur_b.getCapteurDonnees().get(0).getValeur());
                capteurs_d.add(model);
            }

            return (new ResponseEntity<List<CapteurDataLocateViewModel>>(capteurs_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<CapteurDataLocateViewModel>>(HttpStatus.NOT_FOUND));
        }
    }


    @GetMapping("/GetCapteursDataVincent")
    public ResponseEntity<List<CapteurDataVincentViewModel>> GetCapteursDataVincent(@RequestParam(value = "OnlyId", defaultValue = "false") boolean OnlyId)
    {
        try {
            var capteurs = this.capteurService.GetAll();

            List<CapteurDataVincentViewModel> capteurs_d = new ArrayList<>();
            for(var capteur : capteurs)
            {
                var capteur_b = (Capteur)capteur;
                var model = new CapteurDataVincentViewModel();
                model.idSensor = capteur_b.getId().intValue();
                model.x = capteur_b.getMicrocontroller().getLongitude();
                model.y = capteur_b.getMicrocontroller().getLatitude();
                model.intensite = Integer.valueOf(capteur_b.getCapteurDonnees().get(0).getValeur());
                capteurs_d.add(model);
            }

            return (new ResponseEntity<List<CapteurDataVincentViewModel>>(capteurs_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<CapteurDataVincentViewModel>>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/GetCapteur")
    public ResponseEntity<CapteurDto> GetCapteur(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestParam(value = "Id", defaultValue = "0") Long Id
    )
    {
        try {

            var capteur = (Capteur)this.capteurService.getById(Id);
            return (new ResponseEntity<CapteurDto>(capteur.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<CapteurDto>(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/CreateCapteur")
    public ResponseEntity<CapteurDto> CreateCapteur(
            @RequestBody CapteurDto capteur,
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId
    )
    {
        try {
            var capteur_get = (Capteur)this.capteurService.CreateOrUpdateOrGet(capteur.toModel());
            return (new ResponseEntity<CapteurDto>(capteur_get.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<CapteurDto>(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/UpdateCapteurs")
    public ResponseEntity<List<CapteurDto>> UpdateCapteurs(@RequestBody List<CapteurDto> capteurs)
    {
        try {


            List<CapteurDto> capteurs_d = new ArrayList<>();
            for(var capteur : capteurs)
            {
                var capteur_b = (CapteurDto)capteur;
                capteurs_d.add(capteur_b.toModel().Save(this.services, Boolean.FALSE).toDto(Boolean.FALSE));
            }

            return (new ResponseEntity<List<CapteurDto>>(capteurs_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<CapteurDto>>(HttpStatus.NOT_FOUND));
        }
    }


    @PutMapping("/UpdateCapteur")
    public ResponseEntity<CapteurDto> UpdateCapteur(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestBody CapteurDto capteur
    )
    {
        try {

            var capteur_b = (Capteur)capteur.toModel().Save(this.services, Boolean.FALSE);

            return (new ResponseEntity<CapteurDto>(capteur_b.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<CapteurDto>(HttpStatus.NOT_FOUND));
        }
    }


    @PutMapping("/UpdateCapteurDataTestDangerous")
    public ResponseEntity<List<CapteurDto>> UpdateCapteurDataTestDangerous(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestParam(value = "Offset", defaultValue = "0") Integer Offset
    )
    {
        try {

            var capt = this.capteurService.GetAll();
            int m = 0;
            List<Capteur> capteurs = new ArrayList<>();
            List<CapteurDataViewModel> capteursV = new ArrayList<>();
            for(var cap : capt)
            {
                var capteur = (Capteur)cap;
                var valeur = Math.cos((Offset+m/10)*Math.sin(Offset + m/10))*4.0;

                if(valeur>0)
                {
                    //UpdateCapteurData(OnlyId,capteur.getId(), (int)(valeur*9));
                    CapteurDataViewModel capf = new CapteurDataViewModel();
                    capf.etat = capteur.getMicrocontroller().getEtat().getNom();
                    capf.idenCapteur = capteur.getIdentifier();
                    capf.idCapteur = capteur.getId();

                    capteur.getCapteurDonnees().get(0).setValeur((int)valeur+"");
                    capf.intCapteur = Integer.valueOf(capteur.getCapteurDonnees().get(0).getValeur());
                    capteursV.add(capf);
                    capteurs.add(capteur);
                }
                else {
                    //UpdateCapteurData(OnlyId,capteur.getId(), 0);
                    capteur.getCapteurDonnees().get(0).setValeur((int)0+"");
                    CapteurDataViewModel capf = new CapteurDataViewModel();
                    capf.etat = capteur.getMicrocontroller().getEtat().getNom();
                    capf.idenCapteur = capteur.getIdentifier();
                    capf.idCapteur = capteur.getId();

                    capteurs.add(capteur);

                    capf.intCapteur = Integer.valueOf(capteur.getCapteurDonnees().get(0).getValeur());
                    capteursV.add(capf);
                }


                m = m + 1;
            }
            var models = this.capteurService.GetAll();
            List<CapteurDto> all_capteurs = new ArrayList<>();
            for(var model : models)
            {
                all_capteurs.add(((Capteur)model).toDto(OnlyId));


            }
            var upd = UpdateCapteurDataList(OnlyId, capteursV);
            return (new ResponseEntity<List<CapteurDto>>(all_capteurs, HttpStatus.OK));


        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<CapteurDto>>(HttpStatus.NOT_FOUND));
        }
    }

    @PutMapping("/UpdateCapteurData")
    public ResponseEntity<List<UrgenceDto>> UpdateCapteurData(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestParam(value = "Id") Long Id,
            @RequestParam(value = "Intensite") Integer Intensite
    )
    {
        try {
            /* ATTENTION : on retourne ici des urgences, créées à partir des données des capteurs */
            var capt = (Capteur)this.capteurService.getById(Id);

            capt.getCapteurDonnees().get(0).setValeur(Intensite+"");
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            capt.getCapteurDonnees().get(0).setDateUpdate(date);
            var capteur_b = (Capteur)this.capteurService.CreateOrUpdateOrGet(capt);
            Trouple.calcDistanceUnitaire(700/*TestDataA.maxDistanceF()*1000*/);
            CalculMain f = new CalculMain();
            var incendies = f.CalculMainRun();

            var urgencies = this.services.getUrgenceService().GetAll();
            //We clean urgencies that have a position that moved to much or are down
            List<Long> toRemove = new ArrayList<>();

            for(var urg : urgencies) {
                Boolean is_in_ray = Boolean.FALSE;
                for (var incendie : incendies) {
                    var urgence = (Urgence) urg;
                    var inc = urgence.getIncident();
                    var dis = CoordinateGenerator.calculateDistance(
                            new double[]{inc.getLatitude(), inc.getLongitude()},
                            new double[]{incendie.gety(), incendie.getx()}
                    );
                    if(dis<0.05)
                    {
                        is_in_ray = Boolean.TRUE;
                    }

                }
                if(is_in_ray==Boolean.FALSE)
                {
                    for(var r : ((Urgence)urg).getRessources())
                    {
                        var vehs = r.getVehicules();
                        for(int n = 0;n<vehs.size(); n++)
                        {
                            var veh = vehs.get(n);
                            veh.setAvailable(Boolean.TRUE);
                            r.getVehicules().remove(veh);
                            veh.getCentre().getRessource().get(0).getVehicules().add(veh);
                            this.services.getCentreService().CreateOrUpdateOrGet(veh.getCentre());
                        }
                    }
                    toRemove.add(urg.getId());
                }
            }
            if(toRemove.size()>0)
            {
                this.services.getUrgenceService().DeleteThem(toRemove);
            }





            List<UrgenceDto> urgences = new ArrayList<>();

            for(var incendie : incendies)
            {
                var incident = this.services.getIncidentService().FindIncidentInRadius(
                        incendie.getx(), incendie.gety(), 0.05
                );
                Boolean found = Boolean.FALSE;
                if(incident != null)
                {
                    if(incident.size()>0)
                    {
                        found = Boolean.TRUE;
                        incident.get(0).setLatitude(incendie.gety());
                        incident.get(0).setLongitude(incendie.getx());
                        incident.get(0).setDateUpdate(date);

                        incident.get(0).setRadius((double)incendie.getRayon());
                        incident.get(0).Save(services, Boolean.FALSE);


                        urgences.add(incident.get(0).getUrgence().toDto(OnlyId));
                    }
                }

                if(found == Boolean.FALSE)
                {
                    var adresse = GeoapifyAPI.getAddress(incendie.gety(), incendie.getx());
                    Urgence urgence = new Urgence();
                    urgence.setTitre("Feu - "+adresse.getVille());
                    urgence.setType(ReferentielDefinitions.getTypeUrgence(TYPE_URGENCE.INCENDIE.name()));
                    urgence.setDateCreation(date);
                    urgence.setDateUpdate(date);

                    urgence.setStatut(ReferentielDefinitions.getStatut(STATUT_URGENCE.DEMARRE.name()));

                    Incident incident1 = new Incident();
                    incident1.setUrgence(urgence);
                    incident1.setDateUpdate(date);
                    incident1.setDateCreation(date);
                    incident1.setDescriptionIncident("Incident à "+adresse.getRue());
                    incident1.setLongitude(incendie.getx());
                    incident1.setLatitude(incendie.gety());
                    incident1.setRadius((double)incendie.getRayon()/100.0);
                    incident1.setAdresse(adresse);
                    incident1.setNom("Incident : "+adresse.getVille());
                    if(incendie.getIntensite()<3)
                    {
                        incident1.setPriorite(
                                ReferentielDefinitions.getPriorite(PRIORITE_INCIDENT.PEU_ELEVEE.name())
                        );
                    }
                    else if(incendie.getIntensite()<6)
                    {
                        incident1.setPriorite(
                                ReferentielDefinitions.getPriorite(PRIORITE_INCIDENT.MOYENNE.name())
                        );
                    }
                    else if(incendie.getIntensite()<=9)
                    {
                        incident1.setPriorite(
                                ReferentielDefinitions.getPriorite(PRIORITE_INCIDENT.ELEVEE.name())
                        );
                    }
                    urgence.setIncident(incident1);
                    urgence.setTitre("Incident");

                    Ressource res = new Ressource();
                    res.setUrgence(urgence);
                    res.setNom("Urgence à "+adresse.getVille());
                    res.setType(
                            ReferentielDefinitions.getTypeRessource(TYPE_RESSOURCE.CAMION_POMPIER.name())
                    );

                    res.setStatut(
                            ReferentielDefinitions.getStatut(STATUT_RESSOURCE.EN_DEPLACEMENT_ALLER.name())
                    );
                    List<Ressource> ress = new ArrayList<>();
                    ress.add(res);
                    urgence.setRessources(ress);

                    var urgence_n = (Urgence)this.services.getUrgenceService().FillFireUrgency(urgence);
                    urgences.add(urgence_n.toDto(OnlyId));

                }
            }

            return (new ResponseEntity<List<UrgenceDto>>(urgences, HttpStatus.OK));


        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<UrgenceDto>>(HttpStatus.NOT_FOUND));
        }
    }







    @PutMapping("/UpdateCapteurDataList")
    public ResponseEntity<List<UrgenceDto>> UpdateCapteurDataList(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestBody List<CapteurDataViewModel> capteurDataViewModels
    )
    {
        try {
            /* ATTENTION : on retourne ici des urgences, créées à partir des données des capteurs */
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            for(var cap : capteurDataViewModels)
            {
                if(cap.idCapteur != null)
                {
                    var capt = (Capteur)this.capteurService.getById(cap.idCapteur);

                    capt.getCapteurDonnees().get(0).setValeur(cap.intCapteur+"");
                    //capt.getCapteurDonnees().get(0).s(cap.intCapteur+"");
                    capt.getCapteurDonnees().get(0).setDateUpdate(date);
                    if(capt.getMicrocontroller().getEtat().getNom()!=cap.etat)
                    {
                        capt.getMicrocontroller().setEtat(ReferentielDefinitions.getEtat(
                                cap.etat
                        ));
                        var microcontroller = this.services.getMicrocontrollerService().CreateOrUpdateOrGet(capt.getMicrocontroller());
                    }
                    else {
                        var capteur_b = (Capteur)this.capteurService.Update(capt);
                        var c = capteur_b;
                    }


                }

            }

            Trouple.calcDistanceUnitaire(700/*TestDataA.maxDistanceF()*1000*/);
            CalculMain f = new CalculMain();
            var incendies = f.CalculMainRun();
            List<CapteurDataLocateViewModel> data = new ArrayList<>();
            var cappps = this.capteurService.GetAll();
            var mlm = 0;
            for(var c : cappps)
            {
                var go = (Capteur)c;
                var mod = new CapteurDataLocateViewModel();
                mod.lng = go.getMicrocontroller().getLongitude();
                mod.lat = go.getMicrocontroller().getLatitude();
                mod.intCapteur = Integer.parseInt(go.getCapteurDonnees().get(0).getValeur());
                if(mod.intCapteur>0)
                {
                    mlm++;
                }
                mod.etat = go.getMicrocontroller().getEtat().getNom();
                mod.idenCapteur = go.getIdentifier();
                data.add(mod);
            }
            var doco = SensorGrouping.clusterSensors(data, (int)(mlm/66.0 * 10), 1.0);
            var coord = SensorGrouping.groupSensors(data);

            incendies = new ArrayList<>();
            for(var io : doco)
            {
                var mo = new Trouple();
                int count = SensorUtils.countSensorsInRadius(data, io.lat, io.lng, 1.4);
                mo.x = io.lng;
                mo.y = io.lat;
                mo.intensite = count;
                mo.idSensor = 0;


                incendies.add(mo);
            }

            var urgencies = this.services.getUrgenceService().GetAll();
            //We clean urgencies that have a position that moved to much or are down
            List<Long> toRemove = new ArrayList<>();

            for(var urg : urgencies) {
                Boolean is_in_ray = Boolean.FALSE;
                for (var incendie : incendies) {
                    var urgence = (Urgence) urg;
                    var inc = urgence.getIncident();
                    var dis = CoordinateGenerator.calculateDistance(
                            new double[]{inc.getLatitude(), inc.getLongitude()},
                            new double[]{incendie.gety(), incendie.getx()}
                    );
                    if(dis<0.05)
                    {
                        is_in_ray = Boolean.TRUE;
                    }

                }
                if(is_in_ray==Boolean.FALSE)
                {
                    for(var r : ((Urgence)urg).getRessources())
                    {
                        var vehs = r.getVehicules();
                        for(int n = 0;n<vehs.size(); n++)
                        {
                            var veh = vehs.get(n);
                            veh.setAvailable(Boolean.TRUE);
                            r.getVehicules().remove(veh);
                            veh.getCentre().getRessource().get(0).getVehicules().add(veh);
                            this.services.getCentreService().CreateOrUpdateOrGet(veh.getCentre());
                        }
                    }
                    toRemove.add(urg.getId());
                }
            }
            if(toRemove.size()>0)
            {
                this.services.getUrgenceService().DeleteThem(toRemove);
            }





            List<UrgenceDto> urgences = new ArrayList<>();

            for(var incendie : incendies)
            {
                var incident = this.services.getIncidentService().FindIncidentInRadius(
                        incendie.getx(), incendie.gety(), 0.05
                );
                Boolean found = Boolean.FALSE;
                if(incident != null)
                {
                    if(incident.size()>0)
                    {
                        found = Boolean.TRUE;
                        incident.get(0).setLatitude(incendie.gety());
                        incident.get(0).setLongitude(incendie.getx());
                        incident.get(0).setDateUpdate(date);

                        incident.get(0).setRadius((double)incendie.intensite);
                        incident.get(0).Save(services, Boolean.FALSE);


                        urgences.add(incident.get(0).getUrgence().toDto(OnlyId));
                    }
                }

                if(found == Boolean.FALSE)
                {
                    var adresse = GeoapifyAPI.getAddress(incendie.gety(), incendie.getx());
                    Urgence urgence = new Urgence();
                    urgence.setTitre("Feu - "+adresse.getVille());
                    urgence.setType(ReferentielDefinitions.getTypeUrgence(TYPE_URGENCE.INCENDIE.name()));
                    urgence.setDateCreation(date);
                    urgence.setDateUpdate(date);

                    urgence.setStatut(ReferentielDefinitions.getStatut(STATUT_URGENCE.DEMARRE.name()));

                    Incident incident1 = new Incident();
                    incident1.setUrgence(urgence);
                    incident1.setDateUpdate(date);
                    incident1.setDateCreation(date);
                    incident1.setDescriptionIncident("Incident à "+adresse.getRue());
                    incident1.setLongitude(incendie.getx());
                    incident1.setLatitude(incendie.gety());
                    incident1.setRadius((double)incendie.intensite);
                    incident1.setAdresse(adresse);
                    incident1.setNom("Incident : "+adresse.getVille());
                    if(incendie.getIntensite()<3)
                    {
                        incident1.setPriorite(
                                ReferentielDefinitions.getPriorite(PRIORITE_INCIDENT.PEU_ELEVEE.name())
                        );
                    }
                    else if(incendie.getIntensite()<6)
                    {
                        incident1.setPriorite(
                                ReferentielDefinitions.getPriorite(PRIORITE_INCIDENT.MOYENNE.name())
                        );
                    }
                    else if(incendie.getIntensite()<=9)
                    {
                        incident1.setPriorite(
                                ReferentielDefinitions.getPriorite(PRIORITE_INCIDENT.ELEVEE.name())
                        );
                    }
                    else
                    {
                        incident1.setPriorite(
                                ReferentielDefinitions.getPriorite(PRIORITE_INCIDENT.ELEVEE.name())
                        );
                    }
                    urgence.setIncident(incident1);
                    urgence.setTitre("Incident");

                    Ressource res = new Ressource();
                    res.setUrgence(urgence);
                    res.setNom("Urgence à "+adresse.getVille());
                    res.setType(
                            ReferentielDefinitions.getTypeRessource(TYPE_RESSOURCE.CAMION_POMPIER.name())
                    );

                    res.setStatut(
                            ReferentielDefinitions.getStatut(STATUT_RESSOURCE.EN_DEPLACEMENT_ALLER.name())
                    );
                    List<Ressource> ress = new ArrayList<>();
                    ress.add(res);
                    urgence.setRessources(ress);

                    var urgence_n = (Urgence)this.services.getUrgenceService().FillFireUrgency(urgence);
                    if(urgence_n!=null)
                    {
                        urgences.add(urgence_n.toDto(OnlyId));
                    }


                }
            }

            return (new ResponseEntity<List<UrgenceDto>>(urgences, HttpStatus.OK));


        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<UrgenceDto>>(HttpStatus.NOT_FOUND));
        }
    }




}