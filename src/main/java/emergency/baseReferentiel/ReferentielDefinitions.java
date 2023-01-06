package emergency.baseReferentiel;


import emergency.models.*;
import emergency.models.sensorRelated.CapteurType;
import emergency.models.sensorRelated.Etat;

import java.util.ArrayList;
import java.util.List;

public class ReferentielDefinitions {
    public static List<Statut> STATUTS = new ArrayList<Statut>();
    public static List<CapteurType> CAPTEUR_TYPES = new ArrayList<CapteurType>();
    public static List<TypeRessource> TYPE_RESSOURCES = new ArrayList<TypeRessource>();
    public static List<TypeUrgence> TYPE_URGENCES = new ArrayList<TypeUrgence>();
    public static List<Role> ROLES = new ArrayList<Role>();
    public static List<Priorite> PRIORITES = new ArrayList<Priorite>();
    public static List<Etat> ETATS = new ArrayList<Etat>();

    public static Statut getStatut(String name)
    {
        for(var s : STATUTS)
        {
            if(s.getNom()==name)
            {
                return s;
            }
        }
        return null;
    }


    public static  CapteurType getCapteurType(String name)
    {
        for(var ct : CAPTEUR_TYPES)
        {
            if(ct.getType()==name)
            {
                return ct;
            }
        }
        return null;
    }

    public static TypeRessource getTypeRessource(String name)
    {
        for(var tr : TYPE_RESSOURCES)
        {
            if(tr.getType()==name)
            {
                return tr;
            }
        }
        return null;
    }

    public static  TypeUrgence getTypeUrgence(String name)
    {
        for(var tu : TYPE_URGENCES)
        {
            if(tu.getNom()==name)
            {
                return tu;
            }
        }
        return null;
    }

    public static  Role getRole(String name)
    {
        for(var r : ROLES)
        {
            if(r.getNom()==name)
            {
                return r;
            }
        }
        return null;
    }

    public static  Priorite getPriorite(String name)
    {
        for(var p : PRIORITES)
        {
            if(p.getNom()==name)
            {
                return p;
            }
        }
        return null;
    }

    public static Etat getEtat(String name)
    {
        for(var e : ETATS)
        {
            if(e.getNom()==name)
            {
                return e;
            }
        }
        return null;
    }

}