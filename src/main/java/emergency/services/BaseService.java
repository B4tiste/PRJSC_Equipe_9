package emergency.services;

import emergency.interfacesDefinition.IBaseModel;
import emergency.interfacesDefinition.IBaseService;
import org.springframework.stereotype.Service;
import emergency.repositories.*;


@Service
public class BaseService implements IBaseService {

    public BaseRepository baseRepository;

    @Override
    public IBaseModel getById(Long id) {
        var model = baseRepository.findById(id);
        if(model.isPresent())
        {
            try {
                return (IBaseModel) model.get();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }

        }
        else
        {
            return null;
        }
    }


    public Boolean isExist(IBaseModel model) {
        try{
            var id = model.getId();
            return this.getById(id) != null ? Boolean.TRUE : Boolean.FALSE;
        }catch(Exception e){
            return Boolean.FALSE;
        }

    }

    @Override
    public IBaseModel Update(IBaseModel model) {
        try{
            var result = baseRepository.save(model);
            return (IBaseModel)result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean Delete(IBaseModel model) {
        try{
            baseRepository.delete(model);
            return Boolean.TRUE;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    @Override
    public IBaseModel Create(IBaseModel model) {
        try{
            var result = baseRepository.save(model);
            return (IBaseModel)result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public IBaseModel CreateOrUpdate(IBaseModel model) {
        try{
            var result = baseRepository.save(model);
            return (IBaseModel)result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public IBaseModel CreateOrGet(IBaseModel model) {
        try{
            var isExisting = baseRepository.existsById(model.getId());
            if(isExisting==Boolean.TRUE)
            {
                var elem =  baseRepository.findById(model.getId());
                if(elem.isPresent())
                {
                    return (IBaseModel) elem.get();
                }
                else
                {
                    return null;
                }
            }
            else
            {
                var result = baseRepository.save(model);
                return (IBaseModel)result;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
