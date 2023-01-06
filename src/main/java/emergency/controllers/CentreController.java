package emergency.controllers;

import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.CentreDto;
import emergency.models.Centre;
import emergency.services.CentreService;
import emergency.services.UrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/Centre")
public class CentreController extends emergency.controllers.BaseController {

    @Autowired
    public CentreService centreService;

    public CentreController(
    ) {
        this.setService(this.centreService);
    }

    @GetMapping("/GetCentres")
    public ResponseEntity<List<CentreDto>> GetCentres()
    {
        try {
            var centres = this.centreService.GetAll();

            List<CentreDto> centres_d = new ArrayList<>();
            for(var centre : centres)
            {
                var modelDto = this.getBaseMapper().GetModelMapper().map(centre, (new CentreDto()).getClass());
                centres_d.add((CentreDto)modelDto);
            }

            return (new ResponseEntity<List<CentreDto>>(centres_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<CentreDto>>(HttpStatus.EXPECTATION_FAILED));
        }
    }
}