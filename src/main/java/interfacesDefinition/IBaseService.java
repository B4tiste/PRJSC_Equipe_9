package interfacesDefinition;

public interface IBaseService {

    public IBaseModel getById(Long id);

    public IBaseModel Update(IBaseModel model);

    public Boolean Delete(IBaseModel model);

    public IBaseModel Create(IBaseModel model);
}

