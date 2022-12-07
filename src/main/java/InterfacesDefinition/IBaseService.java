package InterfacesDefinition;

import jakarta.persistence.*;

public interface IBaseService {

    public IBaseModel getById(Long id);

    public void Update(IBaseModel model);

    public void Delete(IBaseModel model);

    public IBaseModel Create(IBaseModel model);
}

