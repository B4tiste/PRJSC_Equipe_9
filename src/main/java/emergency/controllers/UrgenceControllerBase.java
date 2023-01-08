package emergency.controllers;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.interfacesDefinition.IBaseModel;
import emergency.modelDto.UrgenceDto;
import emergency.modelView.UrgenceCreateViewModel;
import emergency.models.Adresse;
import emergency.models.Incident;
import emergency.models.Urgence;
import emergency.services.UrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping("/Urgence")
public class UrgenceControllerBase extends emergency.controllers.BaseController {

    @Autowired
    public UrgenceService urgenceService;

    @Autowired
    public ServiceDefinitions services;

    public UrgenceControllerBase(ServiceDefinitions services, UrgenceService service)
    {
        ReferentielDefinitions.serviceDefinitions = services;
        this.urgenceService = service;
        this.services = services;
        this.setService(this.urgenceService);
    }


    @GetMapping("/argent")
    public ResponseEntity<String> getTest()
    {
        return (new ResponseEntity<String>("ARGENT", HttpStatus.OK)) ;
    }

    @PostMapping("/CreateUrgence")
    public ResponseEntity<UrgenceDto> CreateUrgence(
            @RequestBody UrgenceCreateViewModel urgenceCreate
    )
    {
        var d = this.getBaseMapper().GetModelMapper().getTypeMaps();
        var urgence = urgenceCreate.urgence;
        var incident = urgenceCreate.incident;
        var adresse = urgenceCreate.adresse;

        //return (new ResponseEntity<UrgenceDto>(HttpStatus.GATEWAY_TIMEOUT)) ;
        IBaseModel targetModel;
        IBaseModel targetModelIncident;
        IBaseModel targetModelAddress;
        try{
            targetModel = this.getBaseMapper().map(urgence);
            targetModelIncident = this.getBaseMapper().map(incident);
            targetModelAddress = this.getBaseMapper().map(adresse);
            if(targetModel != null && targetModelAddress != null && targetModelIncident != null)
            {
                //Due to the database, we are constrained to create new instances of the related Models
                try{
                    var result = this.urgenceService.CreateUrgence(
                            (Urgence)targetModel,
                            Optional.of((Incident)targetModelIncident),
                            Optional.of((Adresse)targetModelAddress)
                    );

                    var modelDto = this.getBaseMapper().GetModelMapper().map(result, urgence.getClass());
                    return (new ResponseEntity<UrgenceDto>(modelDto,HttpStatus.OK)) ;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    return (new ResponseEntity<UrgenceDto>(HttpStatus.PRECONDITION_FAILED));
                }

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return (new ResponseEntity<UrgenceDto>(HttpStatus.EXPECTATION_FAILED));
        }
        return (new ResponseEntity<UrgenceDto>(HttpStatus.EXPECTATION_FAILED));
    }


    @GetMapping("/GetUrgencies")
    public ResponseEntity<List<UrgenceDto>> GetUrgencies(@RequestParam(value = "OnlyId", defaultValue = "false") boolean OnlyId)
    {
        try {
            var urgencies = this.urgenceService.GetAll();

            List<UrgenceDto> urgencies_d = new ArrayList<>();
            for(var urgence : urgencies)
            {
                var urgence_b = (Urgence)urgence;
                urgencies_d.add((UrgenceDto)urgence_b.toDto(OnlyId));
            }

            return (new ResponseEntity<List<UrgenceDto>>(urgencies_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<UrgenceDto>>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/GetUrgency")
    public ResponseEntity<UrgenceDto> GetUrgence(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestParam(value = "Id", defaultValue = "0") Long Id
    )
    {
        try {

            var urgence = (Urgence)this.urgenceService.getById(Id);
            return (new ResponseEntity<UrgenceDto>(urgence.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<UrgenceDto>(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/CreateUrgency")
    public ResponseEntity<UrgenceDto> CreateUrgence(
            @RequestBody UrgenceDto urgence,
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId
    )
    {
        try {
            var urgence_get = (Urgence)this.urgenceService.CreateOrUpdateOrGet(urgence.toModel());
            return (new ResponseEntity<UrgenceDto>(urgence_get.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<UrgenceDto>(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/FillFireUrgency")
    public ResponseEntity<UrgenceDto> FillUrgency(
            @RequestBody UrgenceDto urgence,
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId
    )
    {
        try {
            var urgence_get = this.urgenceService.FillFireUrgency(urgence.toModel());
            if(urgence_get!=null)
            {
                return (new ResponseEntity<UrgenceDto>(urgence_get.toDto(OnlyId), HttpStatus.OK));
            }
            else {
                return (new ResponseEntity<UrgenceDto>(HttpStatus.EXPECTATION_FAILED));
            }


        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<UrgenceDto>(HttpStatus.NOT_FOUND));
        }
    }




}
