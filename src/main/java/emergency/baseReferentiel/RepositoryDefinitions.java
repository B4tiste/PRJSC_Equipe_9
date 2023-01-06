package emergency.baseReferentiel;


import emergency.repositories.*;
import emergency.repositories.referentiel.*;
import emergency.repositories.type.TypeCapteurRepository;
import emergency.repositories.type.TypeRessourceRepository;
import emergency.repositories.type.TypeUrgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class RepositoryDefinitions {

    public RepositoryDefinitions(AdresseRepository adresseRepository, PrioriteRepository prioriteRepository, StatutRepository statutRepository, TypeRessourceRepository typeRessourceRepository, TypeUrgenceRepository typeUrgenceRepository, IncidentRepository incidentRepository, UrgenceRepository urgenceRepository, CentreRepository centreRepository, MicrocontrollerRepository microcontrollerRepository, PersonneRepository personneRepository, RessourceComposanteRepository ressourceComposanteRepository, VehicleRepository vehicleRepository, TypeCapteurRepository typeCapteurRepository, RoleRepository roleRepository, RessourceRepository ressourceRepository, EtatRepository etatRepository, CapteurRepository capteurRepository, CapteurDonneesRepository capteurDonneesRepository) {
        this.adresseRepository = adresseRepository;
        this.prioriteRepository = prioriteRepository;
        this.statutRepository = statutRepository;
        this.typeRessourceRepository = typeRessourceRepository;
        this.typeUrgenceRepository = typeUrgenceRepository;
        this.incidentRepository = incidentRepository;
        this.urgenceRepository = urgenceRepository;
        this.centreRepository = centreRepository;
        this.microcontrollerRepository = microcontrollerRepository;
        this.personneRepository = personneRepository;
        this.ressourceComposanteRepository = ressourceComposanteRepository;
        this.vehicleRepository = vehicleRepository;
        this.typeCapteurRepository = typeCapteurRepository;
        this.roleRepository = roleRepository;
        this.ressourceRepository = ressourceRepository;
        this.etatRepository = etatRepository;
        this.capteurRepository = capteurRepository;
        this.capteurDonneesRepository = capteurDonneesRepository;
    }

    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private PrioriteRepository prioriteRepository;
    @Autowired
    private StatutRepository statutRepository;
    @Autowired
    private TypeRessourceRepository typeRessourceRepository;
    @Autowired
    private TypeUrgenceRepository typeUrgenceRepository;
    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private UrgenceRepository urgenceRepository;
    @Autowired
    private CentreRepository centreRepository;
    @Autowired
    private MicrocontrollerRepository microcontrollerRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private RessourceComposanteRepository ressourceComposanteRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private TypeCapteurRepository typeCapteurRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RessourceRepository ressourceRepository;
    @Autowired
    private EtatRepository etatRepository;
    @Autowired
    private CapteurRepository capteurRepository;
    @Autowired
    private CapteurDonneesRepository capteurDonneesRepository;



    public AdresseRepository getAdresseRepository() {
        return adresseRepository;
    }

    public void setAdresseRepository(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    public PrioriteRepository getPrioriteRepository() {
        return prioriteRepository;
    }

    public void setPrioriteRepository(PrioriteRepository prioriteRepository) {
        this.prioriteRepository = prioriteRepository;
    }

    public StatutRepository getStatutRepository() {
        return statutRepository;
    }

    public void setStatutRepository(StatutRepository statutRepository) {
        this.statutRepository = statutRepository;
    }

    public TypeRessourceRepository getTypeRessourceRepository() {
        return typeRessourceRepository;
    }

    public void setTypeRessourceRepository(TypeRessourceRepository typeRessourceRepository) {
        this.typeRessourceRepository = typeRessourceRepository;
    }

    public TypeUrgenceRepository getTypeUrgenceRepository() {
        return typeUrgenceRepository;
    }

    public void setTypeUrgenceRepository(TypeUrgenceRepository typeUrgenceRepository) {
        this.typeUrgenceRepository = typeUrgenceRepository;
    }

    public IncidentRepository getIncidentRepository() {
        return incidentRepository;
    }

    public void setIncidentRepository(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public UrgenceRepository getUrgenceRepository() {
        return urgenceRepository;
    }

    public void setUrgenceRepository(UrgenceRepository urgenceRepository) {
        this.urgenceRepository = urgenceRepository;
    }

    public CentreRepository getCentreRepository() {
        return centreRepository;
    }

    public void setCentreRepository(CentreRepository centreRepository) {
        this.centreRepository = centreRepository;
    }

    public MicrocontrollerRepository getMicrocontrollerRepository() {
        return microcontrollerRepository;
    }

    public void setMicrocontrollerRepository(MicrocontrollerRepository microcontrollerRepository) {
        this.microcontrollerRepository = microcontrollerRepository;
    }

    public PersonneRepository getPersonneRepository() {
        return personneRepository;
    }

    public void setPersonneRepository(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public RessourceComposanteRepository getRessourceComposanteRepository() {
        return ressourceComposanteRepository;
    }

    public void setRessourceComposanteRepository(RessourceComposanteRepository ressourceComposanteRepository) {
        this.ressourceComposanteRepository = ressourceComposanteRepository;
    }

    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public TypeCapteurRepository getTypeCapteurRepository() {
        return typeCapteurRepository;
    }

    public void setTypeCapteurRepository(TypeCapteurRepository typeCapteurRepository) {
        this.typeCapteurRepository = typeCapteurRepository;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RessourceRepository getRessourceRepository() {
        return ressourceRepository;
    }

    public void setRessourceRepository(RessourceRepository ressourceRepository) {
        this.ressourceRepository = ressourceRepository;
    }

    public EtatRepository getEtatRepository() {
        return etatRepository;
    }

    public void setEtatRepository(EtatRepository etatRepository) {
        this.etatRepository = etatRepository;
    }

    public CapteurRepository getCapteurRepository() {
        return capteurRepository;
    }

    public void setCapteurRepository(CapteurRepository capteurRepository) {
        this.capteurRepository = capteurRepository;
    }

    public CapteurDonneesRepository getCapteurDonneesRepository() {
        return capteurDonneesRepository;
    }

    public void setCapteurDonneesRepository(CapteurDonneesRepository capteurDonneesRepository) {
        this.capteurDonneesRepository = capteurDonneesRepository;
    }
}