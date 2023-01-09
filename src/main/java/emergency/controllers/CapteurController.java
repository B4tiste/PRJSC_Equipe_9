package emergency.controllers;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.sensorRelated.CapteurDto;
import emergency.models.sensorRelated.Capteur;
import emergency.services.CapteurService;
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




}