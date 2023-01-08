package emergency.baseReferentiel;


import emergency.models.*;
import emergency.models.sensorRelated.CapteurType;
import emergency.models.sensorRelated.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReferentielDefinitions {
    @Autowired
    public static ServiceDefinitions serviceDefinitions;

    @Autowired
    public void initServiceSingleton(ServiceDefinitions someThing){
        ReferentielDefinitions.serviceDefinitions = someThing;
    }

    public static List<Statut> STATUTS = new ArrayList<Statut>();
    public static List<CapteurType> CAPTEUR_TYPES = new ArrayList<CapteurType>();
    public static List<TypeRessource> TYPE_RESSOURCES = new ArrayList<TypeRessource>();
    public static List<TypeUrgence> TYPE_URGENCES = new ArrayList<TypeUrgence>();
    public static List<Role> ROLES = new ArrayList<Role>();
    public static List<Priorite> PRIORITES = new ArrayList<Priorite>();
    public static List<Etat> ETATS = new ArrayList<Etat>();

    public static void PrepareReferentiels()
    {

        try {
            if(STATUTS.size()<1)
            {
                STATUTS.clear();
                STATUTS = (List<Statut>)(List<?>) serviceDefinitions.getStatutService().GetAll();
            }
            if(CAPTEUR_TYPES.size()<1)
            {
                CAPTEUR_TYPES.clear();
                CAPTEUR_TYPES = (List<CapteurType>)(List<?>) serviceDefinitions.getTypeCapteurService().GetAll();
            }
            if(TYPE_RESSOURCES.size()<1)
            {
                TYPE_RESSOURCES.clear();
                TYPE_RESSOURCES = (List<TypeRessource>)(List<?>) serviceDefinitions.getTypeRessourceService().GetAll();
            }
            if(TYPE_URGENCES.size()<1)
            {
                TYPE_URGENCES.clear();
                TYPE_URGENCES = (List<TypeUrgence>)(List<?>) serviceDefinitions.getTypeUrgenceService().GetAll();
            }
            if(ROLES.size()<1)
            {
                ROLES.clear();
                ROLES = (List<Role>)(List<?>) serviceDefinitions.getRoleService().GetAll();
            }
            if(PRIORITES.size()<1)
            {
                PRIORITES.clear();
                PRIORITES = (List<Priorite>)(List<?>) serviceDefinitions.getPrioriteService().GetAll();
            }
            if(ETATS.size()<1)
            {
                ETATS.clear();
                ETATS = (List<Etat>)(List<?>) serviceDefinitions.getEtatService().GetAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Statut getStatut(String name)
    {

        PrepareReferentiels();
        for(var s : STATUTS)
        {
            if(s.getNom().equals(name))
            {
                return s;
            }
        }
        return null;
    }


    public static  CapteurType getCapteurType(String name)
    {
        PrepareReferentiels();
        for(var ct : CAPTEUR_TYPES)
        {
            if(ct.getType().equals(name))
            {
                return ct;
            }
        }
        return null;
    }

    public static TypeRessource getTypeRessource(String name)
    {
        PrepareReferentiels();
        for(var tr : TYPE_RESSOURCES)
        {
            if(tr.getType().equals(name))
            {
                return tr;
            }
        }
        return null;
    }

    public static  TypeUrgence getTypeUrgence(String name)
    {
        PrepareReferentiels();
        for(var tu : TYPE_URGENCES)
        {
            if(tu.getNom().equals(name))
            {
                return tu;
            }
        }
        return null;
    }

    public static  Role getRole(String name)
    {
        PrepareReferentiels();
        for(var r : ROLES)
        {
            if(r.getNom().equals(name))
            {
                return r;
            }
        }
        return null;
    }

    public static  Priorite getPriorite(String name)
    {
        PrepareReferentiels();
        for(var p : PRIORITES)
        {
            if(p.getNom().equals(name))
            {
                return p;
            }
        }
        return null;
    }

    public static Etat getEtat(String name)
    {
        PrepareReferentiels();
        for(var e : ETATS)
        {
            if(e.getNom().equals(name))
            {
                return e;
            }
        }
        return null;
    }











    public static Statut getStatut(Long valeur)
    {
        PrepareReferentiels();
        for(var s : STATUTS)
        {
            if(s.getValeur()==valeur)
            {
                return s;
            }
        }
        return null;
    }


    public static  CapteurType getCapteurType(Long valeur)
    {
        PrepareReferentiels();
        for(var ct : CAPTEUR_TYPES)
        {
            if(ct.getValeur()==valeur)
            {
                return ct;
            }
        }
        return null;
    }

    public static TypeRessource getTypeRessource(Long valeur)
    {
        PrepareReferentiels();
        for(var tr : TYPE_RESSOURCES)
        {
            if(tr.getValeur()==valeur)
            {
                return tr;
            }
        }
        return null;
    }

    public static  TypeUrgence getTypeUrgence(Long valeur)
    {
        PrepareReferentiels();
        for(var tu : TYPE_URGENCES)
        {
            if(tu.getValeur()==valeur)
            {
                return tu;
            }
        }
        return null;
    }

    public static  Role getRole(Long valeur)
    {
        PrepareReferentiels();
        for(var r : ROLES)
        {
            if(r.getValeur()==valeur)
            {
                return r;
            }
        }
        return null;
    }

    public static  Priorite getPriorite(Long valeur)
    {
        PrepareReferentiels();
        for(var p : PRIORITES)
        {
            if(p.getValeur()==valeur)
            {
                return p;
            }
        }
        return null;
    }

    public static Etat getEtat(Long valeur)
    {
        PrepareReferentiels();
        for(var e : ETATS)
        {
            if(e.getValeur()==valeur)
            {
                return e;
            }
        }
        return null;
    }

}