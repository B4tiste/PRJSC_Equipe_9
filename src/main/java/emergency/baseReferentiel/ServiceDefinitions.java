package emergency.baseReferentiel;

import emergency.repositories.*;
import emergency.services.*;
import emergency.services.referentiel.*;
import emergency.services.type.TypeCapteurService;
import emergency.services.type.TypeRessourceService;
import emergency.services.type.TypeUrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ServiceDefinitions {

    public ServiceDefinitions(AdresseService adresseService, PrioriteService prioriteService, StatutService statutService, TypeRessourceService typeRessourceService, TypeUrgenceService typeUrgenceService, IncidentService incidentService, UrgenceService urgenceService, CentreService centreService, MicrocontrollerService microcontrollerService, PersonneService personneService, RessourceComposanteService ressourceComposanteService, VehicleService vehicleService, TypeCapteurService typeCapteurService, RoleService roleService, RessourceService ressourceService, EtatService etatService, CapteurService capteurService) {
        this.adresseService = adresseService;
        this.prioriteService = prioriteService;
        this.statutService = statutService;
        this.typeRessourceService = typeRessourceService;
        this.typeUrgenceService = typeUrgenceService;
        this.incidentService = incidentService;
        this.urgenceService = urgenceService;
        this.centreService = centreService;
        this.microcontrollerService = microcontrollerService;
        this.personneService = personneService;
        this.ressourceComposanteService = ressourceComposanteService;
        this.vehicleService = vehicleService;
        this.typeCapteurService = typeCapteurService;
        this.roleService = roleService;
        this.ressourceService = ressourceService;
        this.etatService = etatService;
        this.capteurService = capteurService;
    }

    public AdresseService getAdresseService() {
        return adresseService;
    }

    public void setAdresseService(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    public PrioriteService getPrioriteService() {
        return prioriteService;
    }

    public void setPrioriteService(PrioriteService prioriteService) {
        this.prioriteService = prioriteService;
    }

    public StatutService getStatutService() {
        return statutService;
    }

    public void setStatutService(StatutService statutService) {
        this.statutService = statutService;
    }

    public TypeRessourceService getTypeRessourceService() {
        return typeRessourceService;
    }

    public void setTypeRessourceService(TypeRessourceService typeRessourceService) {
        this.typeRessourceService = typeRessourceService;
    }

    public TypeUrgenceService getTypeUrgenceService() {
        return typeUrgenceService;
    }

    public void setTypeUrgenceService(TypeUrgenceService typeUrgenceService) {
        this.typeUrgenceService = typeUrgenceService;
    }

    public IncidentService getIncidentService() {
        return incidentService;
    }

    public void setIncidentService(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    public UrgenceService getUrgenceService() {
        return urgenceService;
    }

    public void setUrgenceService(UrgenceService urgenceService) {
        this.urgenceService = urgenceService;
    }

    public CentreService getCentreService() {
        return centreService;
    }

    public void setCentreService(CentreService centreService) {
        this.centreService = centreService;
    }

    public MicrocontrollerService getMicrocontrollerService() {
        return microcontrollerService;
    }

    public void setMicrocontrollerService(MicrocontrollerService microcontrollerService) {
        this.microcontrollerService = microcontrollerService;
    }

    public PersonneService getPersonneService() {
        return personneService;
    }

    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    public RessourceComposanteService getRessourceComposanteService() {
        return ressourceComposanteService;
    }

    public void setRessourceComposanteService(RessourceComposanteService ressourceComposanteService) {
        this.ressourceComposanteService = ressourceComposanteService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public TypeCapteurService getTypeCapteurService() {
        return typeCapteurService;
    }

    public void setTypeCapteurService(TypeCapteurService typeCapteurService) {
        this.typeCapteurService = typeCapteurService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public RessourceService getRessourceService() {
        return ressourceService;
    }

    public void setRessourceService(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    public EtatService getEtatService() {
        return etatService;
    }

    public void setEtatService(EtatService etatService) {
        this.etatService = etatService;
    }

    public CapteurService getCapteurService() {
        return capteurService;
    }

    public void setCapteurService(CapteurService capteurService) {
        this.capteurService = capteurService;
    }

    public CapteurDonneesService getCapteurDonneesService() {
        return capteurDonneesService;
    }

    public void setCapteurDonneesService(CapteurDonneesService capteurDonneesService) {
        this.capteurDonneesService = capteurDonneesService;
    }

    @Autowired
    private AdresseService adresseService;
    @Autowired
    private PrioriteService prioriteService;
    @Autowired
    private StatutService statutService;
    @Autowired
    private TypeRessourceService typeRessourceService;
    @Autowired
    private TypeUrgenceService typeUrgenceService;
    @Autowired
    private IncidentService incidentService;
    @Autowired
    private UrgenceService urgenceService;
    @Autowired
    private CentreService centreService;
    @Autowired
    private MicrocontrollerService microcontrollerService;
    @Autowired
    private PersonneService personneService;
    @Autowired
    private RessourceComposanteService ressourceComposanteService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private TypeCapteurService typeCapteurService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RessourceService ressourceService;
    @Autowired
    private EtatService etatService;
    @Autowired
    private CapteurService capteurService;
    @Autowired
    private CapteurDonneesService capteurDonneesService;
}