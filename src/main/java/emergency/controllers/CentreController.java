package emergency.controllers;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.CentreDto;
import emergency.models.Centre;
import emergency.services.CentreService;
import emergency.services.UrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/Centre")
public class CentreController extends emergency.controllers.BaseController {

    @Autowired
    public CentreService centreService;

    @Autowired
    public ServiceDefinitions services;

    public CentreController(ServiceDefinitions services, CentreService centreService)
    {
        ReferentielDefinitions.serviceDefinitions = services;
        this.services = services;
        this.centreService = centreService;
        this.setService(this.centreService);
    }

    @GetMapping("/GetCenters")
    public ResponseEntity<List<CentreDto>> GetCentres(@RequestParam(value = "OnlyId", defaultValue = "false") boolean OnlyId)
    {
        try {
            var centres = this.centreService.GetAll();

            List<CentreDto> centres_d = new ArrayList<>();
            for(var centre : centres)
            {
                var centre_b = (Centre)centre;
                centres_d.add((CentreDto)centre_b.toDto(OnlyId));
            }

            return (new ResponseEntity<List<CentreDto>>(centres_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<CentreDto>>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/GetCenter")
    public ResponseEntity<CentreDto> GetCentre(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestParam(value = "Id", defaultValue = "0") Long Id
    )
    {
        try {

            var centre = (Centre)this.centreService.getById(Id);
            return (new ResponseEntity<CentreDto>(centre.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<CentreDto>(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/CreateCenter")
    public ResponseEntity<CentreDto> CreateCentre(
            @RequestBody CentreDto centre,
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId
    )
    {
        try {
            var centre_get = (Centre)this.centreService.CreateOrUpdateOrGet(centre.toModel());
            return (new ResponseEntity<CentreDto>(centre_get.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<CentreDto>(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/UpdateCentres")
    public ResponseEntity<List<CentreDto>> UpdateCentres(@RequestBody List<CentreDto> centres)
    {
        try {


            List<CentreDto> centres_d = new ArrayList<>();
            for(var centre : centres)
            {
                var centre_b = (CentreDto)centre;
                centres_d.add(centre_b.toModel().Save(this.services, Boolean.FALSE).toDto(Boolean.FALSE));
            }

            return (new ResponseEntity<List<CentreDto>>(centres_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<CentreDto>>(HttpStatus.NOT_FOUND));
        }
    }


    @PutMapping("/UpdateCenter")
    public ResponseEntity<CentreDto> UpdateCentre(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestBody CentreDto centre
    )
    {
        try {

            var centre_b = (Centre)centre.toModel().Save(this.services, Boolean.FALSE);

            return (new ResponseEntity<CentreDto>(centre_b.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<CentreDto>(HttpStatus.NOT_FOUND));
        }
    }

}