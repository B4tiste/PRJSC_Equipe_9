package baseReferentiel;

import repositories.*;
import repositories.referentiel.AdresseRepository;
import repositories.referentiel.PrioriteRepository;
import repositories.referentiel.RoleRepository;
import repositories.referentiel.StatutRepository;
import repositories.type.TypeCapteurRepository;
import repositories.type.TypeRessourceRepository;
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