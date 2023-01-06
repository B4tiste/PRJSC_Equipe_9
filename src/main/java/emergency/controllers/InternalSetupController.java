package emergency.controllers;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.enumDefinition.*;
import emergency.modelDto.UrgenceDto;
import emergency.models.*;
import emergency.models.sensorRelated.CapteurType;
import emergency.models.sensorRelated.Etat;
import emergency.setupData.TestDataA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/InternalSetup")
public class InternalSetupController extends emergency.controllers.BaseController {

    @Autowired
    public ServiceDefinitions services;

    @PostMapping("/Setup")
    public ResponseEntity<Boolean> Setup(
            @RequestBody String key
    )
    {
        // On sécurise le setup avec une clé fixe, on aurait pu implémenter du RSA,
        // mais j'ai estimé que c'était trop d'effort pour pas grand chose étant
        // donné que un setup ne doit pas être systématiquement lancé.
        if(key.equals("da39a3ee5e6b4b0d3255bfef95601890afd80709"))
        {

            try{
                //PHASE 1 - ENUM - PRIORITE
                this.services.getPrioriteService().DeleteAll();
                for (PRIORITE_INCIDENT field : PRIORITE_INCIDENT.values()) {
                    var pr = new Priorite(field.name(), field.ordinal());

                    var result = (Priorite)this.services.getPrioriteService().CreateOrUpdateOrGet(
                            pr
                    );

                    ReferentielDefinitions.PRIORITES.add(result);
                }

                //PHASE 2 - ENUM - STATUT
                this.services.getStatutService().DeleteAll();
                for (STATUT_URGENCE field : STATUT_URGENCE.values()) {
                    var st = new Statut(field.name(), field.ordinal());

                    var result = (Statut)this.services.getStatutService().CreateOrUpdateOrGet(
                            st
                    );

                    ReferentielDefinitions.STATUTS.add(result);
                }

                //PHASE 2-2 - ENUM - STATUT

                for (STATUT_RESSOURCE field : STATUT_RESSOURCE.values()) {
                    var st = new Statut(field.name(), field.ordinal());

                    var result = (Statut)this.services.getStatutService().CreateOrUpdateOrGet(
                            st
                    );

                    ReferentielDefinitions.STATUTS.add(result);
                }

                //PHASE 3 - ENUM - TYPE_URGENCE
                this.services.getTypeUrgenceService().DeleteAll();
                for (TYPE_URGENCE field : TYPE_URGENCE.values()) {
                    var ty = new TypeUrgence(field.name(), field.ordinal());

                    var result = (TypeUrgence)this.services.getTypeUrgenceService().CreateOrUpdateOrGet(
                            ty
                    );
                    ReferentielDefinitions.TYPE_URGENCES.add(result);
                }

                //PHASE 4 - ENUM - ROLE_PERSONNE
                this.services.getRoleService().DeleteAll();
                for (ROLE_PERSONNE field : ROLE_PERSONNE.values()) {
                    var ro = new Role(field.name(), Long.valueOf(field.ordinal()));

                    var result = (Role)this.services.getRoleService().CreateOrUpdateOrGet(
                            ro
                    );

                    ReferentielDefinitions.ROLES.add(result);
                }

                //PHASE 5 - ENUM - TYPE_RESSOURCE_COMPOSANTE
                this.services.getTypeRessourceService().DeleteAll();
                for (TYPE_RESSOURCE field : TYPE_RESSOURCE.values()) {
                    var tp = new TypeRessource(field.name(), field.ordinal());

                    var result = (TypeRessource)this.services.getTypeRessourceService().CreateOrUpdateOrGet(
                            tp
                    );

                    ReferentielDefinitions.TYPE_RESSOURCES.add(result);
                }

                //PHASE 6 - ENUM - TYPE_CAPTEUR
                this.services.getTypeCapteurService().DeleteAll();
                for (TYPE_CAPTEUR field : TYPE_CAPTEUR.values()) {
                    var ct = new CapteurType(field.name(), field.ordinal());

                    var result = (CapteurType)this.services.getTypeCapteurService().CreateOrUpdateOrGet(
                            ct
                    );
                    ReferentielDefinitions.CAPTEUR_TYPES.add(result);
                }

                //PHASE 7 - ENUM - ETAT_MICROCONTROLLEUR
                this.services.getEtatService().DeleteAll();
                for (ETAT_MICROCONTROLLEUR field : ETAT_MICROCONTROLLEUR.values()) {
                    var ct = new Etat(field.name(), field.ordinal());

                    var result = (Etat)this.services.getEtatService().CreateOrUpdateOrGet(
                            ct
                    );
                    ReferentielDefinitions.ETATS.add(result);
                }

                //////////////////////////////////////////////////////////////////

                //////////////////////////////////////////////////////////////////

                //PHASE 8 - Generation des centres

                // On nettoie avant tout la base de données
                this.services.getCentreService().DeleteAll();
                this.services.getAdresseService().DeleteAll();
                this.services.getRessourceComposanteService().DeleteAll();
                this.services.getRessourceService().DeleteAll();
                this.services.getPersonneService().DeleteAll();

                var centers = TestDataA.getCentres();

                for(var centre: centers)
                {
                    centre.Save(this.services, Boolean.TRUE);
                }





                return (new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK)) ;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return (new ResponseEntity<Boolean>(Boolean.FALSE,HttpStatus.PRECONDITION_FAILED));
            }
        }
        return (new ResponseEntity<Boolean>(Boolean.FALSE,HttpStatus.UNAUTHORIZED));
    }

}