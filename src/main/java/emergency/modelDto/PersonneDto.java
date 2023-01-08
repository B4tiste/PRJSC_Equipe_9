package emergency.modelDto;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.Personne;
import com.fasterxml.jackson.annotation.JsonProperty;
import emergency.models.Role;
import org.jetbrains.annotations.NotNull;


public class PersonneDto extends GBaseDto implements IBaseModelDto {
    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    @NotNull
    @JsonProperty("prenom")
    private String prenom;


    @JsonProperty("role")
    private RoleDto role;


    @JsonProperty("roleId")
    private Long roleId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public RoleDto getRole() {
        return role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public Personne toModel()
    {
        Personne model = new Personne();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setNom(this.getNom());
        model.setPrenom(this.getPrenom());
        if(this.getRole()!=null)
        {
            try {
                model.setRole((Role) this.getRole().toModel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(this.getRoleId()!=null)
        {
            try {
                model.setRole(ReferentielDefinitions.getRole(this.getRoleId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return model;
    }
}