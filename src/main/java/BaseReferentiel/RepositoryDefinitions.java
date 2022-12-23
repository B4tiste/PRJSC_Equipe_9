package BaseReferentiel;

import Models.Adresse;
import Repositories.*;
import Repositories.Referentiel.AdresseRepository;
import Repositories.Referentiel.PrioriteRepository;
import Repositories.Referentiel.RoleRepository;
import Repositories.Referentiel.StatutRepository;
import Repositories.Type.TypeCapteurRepository;
import Repositories.Type.TypeRessourceRepository;
import Repositories.Type.TypeUrgenceRepository;
import Services.Referentiel.AdresseService;
import Services.Referentiel.PrioriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    @Autowired
    public TypeUrgenceRepository typeUrgenceRepository;
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