package emergency.services.referentiel;

import emergency.enumDefinition.ADDRESS_FIELD;
import emergency.models.Adresse;
import emergency.repositories.referentiel.AdresseRepository;
import emergency.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdresseService extends BaseService {

    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
        this.baseRepository = adresseRepository;
    }

    @Autowired
    private AdresseRepository adresseRepository;

    public Adresse getBy(String value, ADDRESS_FIELD field)
    {
        try{
            List<Adresse> addr = new ArrayList<>();
            switch(field)
            {
                case CP:
                    addr = this.adresseRepository.findByCodePostal(value);
                case VILLE:
                    addr = this.adresseRepository.findByVille(value);
                case RUE:
                    addr = this.adresseRepository.findByRue(value);
                case PAYS:
                    addr = this.adresseRepository.findByPays(value);
            }
            if(addr.isEmpty())
            {
                return null;
            }
            else
            {
                return addr.get(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }


}