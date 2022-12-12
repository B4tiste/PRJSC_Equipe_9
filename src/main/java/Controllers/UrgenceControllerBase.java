package Controllers;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseService;
import ModelDto.UrgenceDto;
import Models.Urgence;
import Services.UrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/Urgence")
public class UrgenceControllerBase extends BaseController {

    public UrgenceControllerBase(UrgenceService service)
    {
        this.setService(service);
    }

    @GetMapping("/CreateUrgence")
    public ResponseEntity<UrgenceDto> CreateUrgence(@RequestBody Urgence urgence)
    {
        IBaseModel targetModel;
        try{
            targetModel = this.getBaseMapper().map(model);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return (new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.EXPECTATION_FAILED));
        }
    }





}
