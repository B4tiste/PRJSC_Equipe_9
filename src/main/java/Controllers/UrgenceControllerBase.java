package Controllers;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseService;
import ModelDto.AdresseDto;
import ModelDto.IncidentDto;
import ModelDto.TypeUrgenceDto;
import ModelDto.UrgenceDto;
import ModelView.UrgenceCreateViewModel;
import Models.Adresse;
import Models.Incident;
import Models.Urgence;
import Services.Type.TypeRessourceService;
import Services.Type.TypeUrgenceService;
import Services.UrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping("/Urgence")
public class UrgenceControllerBase extends BaseController {

    //@Autowired
    public UrgenceService urgenceService;

    public UrgenceControllerBase(
    )
    {
        this.urgenceService = new UrgenceService();
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
                    return (new ResponseEntity<UrgenceDto>((UrgenceDto)modelDto,HttpStatus.OK)) ;
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





}
