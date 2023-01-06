package emergency.interfacesDefinition;

import java.util.List;

public interface IBaseService {

    public IBaseModel getById(Long id);

    public IBaseModel Update(IBaseModel model);

    public Boolean Delete(IBaseModel model);

    public IBaseModel Create(IBaseModel model);

    Boolean DeleteThem(Iterable<Long> ids);

    List<IBaseModel> GetThem(Iterable<Long> ids);

    List<IBaseModel> CreateOrUpdateThem(List<IBaseModel> models);

    public List<IBaseModel> GetAll();

    public Boolean DeleteAll();

    IBaseModel CreateOrUpdate(IBaseModel model);

    IBaseModel CreateOrGet(IBaseModel model);

    IBaseModel CreateOrUpdateOrGet(IBaseModel model);
}

