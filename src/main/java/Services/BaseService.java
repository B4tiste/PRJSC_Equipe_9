package Services;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseService;
import org.springframework.stereotype.Service;
import Repositories.*;


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
}
