package emergency.controllers;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.mapper.BaseMapper;
import emergency.interfacesDefinition.IBaseModel;
import emergency.interfacesDefinition.IBaseModelDto;
import emergency.interfacesDefinition.IBaseService;
import emergency.mapper.BaseMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {

    @Autowired
    public ServiceDefinitions serviceDefinitions;

    public BaseController()
    {
        ReferentielDefinitions.serviceDefinitions = serviceDefinitions;
    }


    private IBaseService service;

    private ObjectMapper converter = new ObjectMapper();

    @Autowired
    private BaseMapper baseMapper;

    public ObjectMapper getConverter()
    {
        return this.converter;
    }

    public ObjectMapper setConverter(ObjectMapper mapper)
    {
        this.converter = mapper;
        return this.converter;
    }

    public IBaseService getService()
    {
        return this.service;
    }

    public void setService(IBaseService service)
    {
        this.service = service;
    }

    public BaseMapper getBaseMapper()
    {
        return this.baseMapper;
    }

    public BaseMapper setBaseMapper(BaseMapper mapper)
    {
        this.baseMapper = mapper;
        return this.baseMapper;
    }

    public <T extends IBaseModelDto> IBaseModelDto CastFromJson(String payload, Class<T> valueType)
    {
        try {
            return (IBaseModelDto)this.getConverter().readValue(payload, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T extends IBaseModel> IBaseModel MapFromDto(IBaseModelDto dto)
    {
        return (IBaseModel)this.getBaseMapper().map(dto);
    }

    public ResponseEntity<IBaseModelDto> GetById(@RequestParam Long id)
    {
        if(service == null)
        {
            return (new ResponseEntity<IBaseModelDto>(HttpStatus.NOT_IMPLEMENTED));
        }
        try {
            var model = this.service.getById(id);
            if(model == null)
            {
                return (new ResponseEntity<IBaseModelDto>(HttpStatus.NOT_FOUND));
            }
            else
            {
                IBaseModelDto modelDto;
                try {
                    modelDto = this.getBaseMapper().GetModelMapper().map(model, IBaseModelDto.class);
                    if(modelDto == null)
                    {
                        return (new ResponseEntity<IBaseModelDto>(HttpStatus.NOT_FOUND));
                    }
                    else
                    {
                        return (new ResponseEntity<IBaseModelDto>(modelDto, HttpStatus.OK));
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    return (new ResponseEntity<IBaseModelDto>(HttpStatus.EXPECTATION_FAILED));
                }

            }
        }
        catch (Exception e)
        {
            return (new ResponseEntity<IBaseModelDto>(HttpStatus.NOT_FOUND));
        }
    }


    public ResponseEntity<List<IBaseModelDto>> GetAll()
    {
        List<IBaseModelDto> modelDtos = new ArrayList<>();
        if(service == null)
        {
            return (new ResponseEntity<List<IBaseModelDto>>(HttpStatus.NOT_IMPLEMENTED));
        }
        try {
            var modelList = this.service.GetAll();
            if(modelList == null)
            {
                return (new ResponseEntity<List<IBaseModelDto>>(HttpStatus.NOT_FOUND));
            }
            else
            {

                for(var model : modelList)
                {
                    IBaseModelDto modelDto;
                    try {
                        modelDto = this.getBaseMapper().GetModelMapper().map(model, IBaseModelDto.class);
                        if(modelDto == null)
                        {
                            return (new ResponseEntity<List<IBaseModelDto>>(HttpStatus.NOT_FOUND));
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        return (new ResponseEntity<List<IBaseModelDto>>(HttpStatus.EXPECTATION_FAILED));
                    }
                    if(modelDto != null)
                    {
                        modelDtos.add(modelDto);
                    }
                }


            }
        }
        catch (Exception e)
        {
            return (new ResponseEntity<List<IBaseModelDto>>(HttpStatus.NOT_FOUND));
        }
        return (new ResponseEntity<List<IBaseModelDto>>(modelDtos, HttpStatus.OK));
    }

    public ResponseEntity<IBaseModelDto> Create(@RequestBody IBaseModelDto model)
    {
        IBaseModel targetModel;
        try{
            targetModel = this.getBaseMapper().map(model);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return (new ResponseEntity<IBaseModelDto>(HttpStatus.EXPECTATION_FAILED));
        }
        if(service == null)
        {
            return (new ResponseEntity<IBaseModelDto>(HttpStatus.NOT_IMPLEMENTED));
        }
        try {
            var modelCreated = this.service.Create(targetModel);
            if(modelCreated == null)
            {
                return (new ResponseEntity<IBaseModelDto>(HttpStatus.NOT_FOUND));
            }
            else
            {
                IBaseModelDto modelDto;
                try {
                    modelDto = this.getBaseMapper().GetModelMapper().map(modelCreated, model.getClass());
                    return (new ResponseEntity<IBaseModelDto>(modelDto, HttpStatus.OK));
                }
                catch (Exception e){
                    e.printStackTrace();
                    return (new ResponseEntity<IBaseModelDto>(HttpStatus.EXPECTATION_FAILED));
                }

            }
        }
        catch (Exception e)
        {
            return (new ResponseEntity<IBaseModelDto>(HttpStatus.NOT_FOUND));
        }
    }

    public ResponseEntity<IBaseModelDto> Update(@RequestBody IBaseModelDto model)
    {
        IBaseModel targetModel;
        try{
            targetModel = this.getBaseMapper().map(model);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return (new ResponseEntity<IBaseModelDto>(HttpStatus.EXPECTATION_FAILED));
        }
        if(service == null)
        {
            return (new ResponseEntity<IBaseModelDto>(HttpStatus.NOT_IMPLEMENTED));
        }
        try {
            var modelUpdated = this.service.Update(targetModel);
            if(modelUpdated == null)
            {
                return (new ResponseEntity<IBaseModelDto>(HttpStatus.NOT_FOUND));
            }
            else
            {
                IBaseModelDto modelDto;
                try {
                    modelDto = this.getBaseMapper().GetModelMapper().map(modelUpdated, model.getClass());
                    return (new ResponseEntity<IBaseModelDto>(modelDto, HttpStatus.OK));
                }
                catch (Exception e){
                    e.printStackTrace();
                    return (new ResponseEntity<IBaseModelDto>(HttpStatus.EXPECTATION_FAILED));
                }

            }
        }
        catch (Exception e)
        {
            return (new ResponseEntity<IBaseModelDto>(HttpStatus.NOT_FOUND));
        }
    }

    public ResponseEntity<Boolean> Delete(@RequestBody IBaseModelDto model)
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
        if(service == null)
        {
            return (new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_IMPLEMENTED));
        }
        try {
            var modelDeleted = this.service.Delete(targetModel);
            if(modelDeleted == Boolean.TRUE)
            {
                return (new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_FOUND));
            }
            else
            {
                return (new ResponseEntity<Boolean>(modelDeleted, HttpStatus.OK));
            }
        }
        catch (Exception e)
        {
            return (new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND));
        }
    }
}
