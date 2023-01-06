package emergency.services;

import emergency.interfacesDefinition.IBaseModel;
import emergency.interfacesDefinition.IBaseService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BaseService implements IBaseService {

    public CrudRepository baseRepository;

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
    public Boolean DeleteAll() {

        try {
            baseRepository.deleteAll();
            return Boolean.TRUE;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Boolean.FALSE;
        }

    }

    @Override
    public Boolean DeleteThem(Iterable<Long> ids) {
        if(ids != null)
        {
            try {
                baseRepository.deleteAllById(ids);
                return Boolean.TRUE;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }
        return Boolean.FALSE;
    }


    @Override
    public List<IBaseModel> GetThem(Iterable<Long> ids) {
        if(ids != null)
        {
            try {
                var data = baseRepository.findAllById(ids);
                List<IBaseModel> list = new ArrayList<IBaseModel>();
                for (var s : data) {
                    list.add((IBaseModel)s);
                }
                return list;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return new ArrayList<IBaseModel>();
            }
        }
        return new ArrayList<IBaseModel>();

    }

    @Override
    public List<IBaseModel> CreateOrUpdateThem(List<IBaseModel> models) {
        if(models != null)
        {
            try {
                var data = baseRepository.saveAll(models);
                List<IBaseModel> list = new ArrayList<IBaseModel>();
                for (var s : data) {
                    list.add((IBaseModel)s);
                }
                return list;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return new ArrayList<IBaseModel>();
            }
        }
        return new ArrayList<IBaseModel>();

    }


    @Override
    public List<IBaseModel> GetAll() {
        var model = baseRepository.findAll();
        if(model != null)
        {
            try {
                List<IBaseModel> list = new ArrayList<IBaseModel>();
                for (var s : model) {
                    list.add((IBaseModel)s);
                }
                return list;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return new ArrayList<IBaseModel>();
            }

        }
        else
        {
            return new ArrayList<IBaseModel>();
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

    @Override
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
    @Override
    public IBaseModel CreateOrGet(IBaseModel model) {
        try{
            var isExisting = Boolean.FALSE;
            var id_m = model.getId();
            if(id_m == null)
            {
                isExisting = Boolean.FALSE;
            }
            else{
                isExisting = baseRepository.existsById(model.getId());
            }

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


    @Override
    public IBaseModel CreateOrUpdateOrGet(IBaseModel model) {
        try{
            var element = CreateOrGet(model);
            if(element.equals(model))
            {
                return element;
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
