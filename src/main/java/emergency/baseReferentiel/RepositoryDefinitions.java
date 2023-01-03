package emergency.baseReferentiel;

import emergency.repositories.*;
import emergency.repositories.referentiel.AdresseRepository;
import emergency.repositories.referentiel.PrioriteRepository;
import emergency.repositories.referentiel.RoleRepository;
import emergency.repositories.referentiel.StatutRepository;
import emergency.repositories.type.TypeCapteurRepository;
import emergency.repositories.type.TypeRessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryDefinitions {
    @Autowired
    public AdresseRepository adresseRepository;
    @Autowired
    public PrioriteRepository prioriteRepository;
    @Autowired
    public StatutRepository statutRepository;
    @Autowired
    public TypeRessourceRepository typeRessourceRepository;
    /*@Autowired
    public TypeUrgenceRepository typeUrgenceRepository;*/
    @Autowired
    public IncidentRepository incidentRepository;
    @Autowired
    public UrgenceRepository urgenceRepository;
    @Autowired
    public CentreRepository centreRepository;
    @Autowired
    public MicrocontrollerRepository microcontrollerRepository;
    @Autowired
    public PersonneRepository personneRepository;
    @Autowired
    public RessourceComposanteRepository ressourceComposanteRepository;
    @Autowired
    public VehicleRepository vehicleRepository;
    @Autowired
    public TypeCapteurRepository typeCapteurRepository;
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public RessourceRepository ressourceRepository;




}