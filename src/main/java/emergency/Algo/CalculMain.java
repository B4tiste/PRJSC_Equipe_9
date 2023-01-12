package emergency.Algo;

import java.util.ArrayList;
import java.util.List;
/*CalculMain f = new CalculMain();
var incendies = f.CalculMainRun();*/
public class CalculMain {


    private int nbPoint = 0;
    private AccesDataEmergency db_e;


    public List<Trouple> CalculMainRun()
    {
        List<Trouple > tab;
        ApproximerIncendie calc = new ApproximerIncendie();

        List<Trouple> fires = new ArrayList<Trouple>();
        List<List<Trouple> > incendiesZone= new ArrayList<List<Trouple> >();
        List<Trouple> incendieZone;
        List<Trouple > tabUnIncendie;

        double couple[]= new double[2];

        int intensite = 0;

        db_e = new AccesDataEmergency();

        tab = db_e.getSensorValues();
        tab = retirerValeurNull(tab);
        tab = trierTabIntensiter(tab);

        nbPoint = tab.size();

        while (tab.size() > 0)
        {
            incendieZone = detectIncendieZone(tab);
            if (incendieZone.size() > 1 && isInIncendieZone(incendieZone.get(0),incendiesZone) == -1)
            {
                incendiesZone.add(detectIncendieZone(tab));
            }

            tabUnIncendie = selectUnIncendie (tab);

            if (tabUnIncendie.size() > 1 && (incendieZone.size()>1 && incendieZone.indexOf(tabUnIncendie.get(0)) == -1 || incendieZone.size()<1))
            {
                if(tabUnIncendie.get(0).getIntensite() == tabUnIncendie.get(1).getIntensite())
                {
                    couple[0] = tabUnIncendie.get(0).getx();
                    couple[1] = tabUnIncendie.get(0).gety();
                    intensite = tabUnIncendie.get(0).getIntensite() ;
                }
                else
                {
                    couple = calc.launchCalclul(tabUnIncendie,tabUnIncendie.size());
                    intensite = tabUnIncendie.get(0).getIntensite() ;
                }
            }
            else if (incendieZone.size() == 1 && tabUnIncendie.size() > 1)
            {
                int valIsInLangue = isInIncendieZone(incendieZone.get(0),incendiesZone) ;
                if(valIsInLangue == -1)
                {
                    if(tabUnIncendie.get(0).getIntensite() == tabUnIncendie.get(1).getIntensite())
                    {
                        couple[0] = tabUnIncendie.get(0).getx();
                        couple[1] = tabUnIncendie.get(0).gety();
                        intensite = tabUnIncendie.get(0).getIntensite() ;
                    }
                    else
                    {
                        couple = calc.launchCalclul(tabUnIncendie,tabUnIncendie.size());
                        intensite = tabUnIncendie.get(0).getIntensite() ;
                    }
                }
                else
                {
                    System.out.print("Zone ");
                    couple = calc.launchCalculIncendieZone(incendiesZone.get(valIsInLangue));
                    intensite = incendiesZone.get(valIsInLangue).get(0).getIntensite() * incendiesZone.get(valIsInLangue).size();
                }
            }
            else if (tabUnIncendie.size() == 1)
            {
                couple[0] = tabUnIncendie.get(0).getx();
                couple[1] = tabUnIncendie.get(0).gety();
                intensite = tabUnIncendie.get(0).getIntensite() ;
            }
            else
            {
                couple[0] = 0;
                couple[1] = 0;
                intensite = 0;
            }

            if (!(incendieZone.size()>1 && incendieZone.indexOf(tabUnIncendie.get(0)) != -1 ))
            {

                System.out.println("Incendie en : " + "("+couple[0] + " " + couple[1]+") d'une intensitÃ© de : " +intensite );

                try {
                    if(couple[0]>0.0)
                    {
                        fires.add(new Trouple(-1, couple[0],couple[1],intensite));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
        db_e.saveValuesFires(fires);
        return fires;
    }

    /**
     * @param point un capteur qu'on veux vÃ©rifier s'il est dans une zone d'incendie
     * @param incendiesZone les diffÃ©rentcapteur dans une zone d'incendue
     * @return l'index (-1 s'il n'existe pÃ¢s)
     */
    private int isInIncendieZone(Trouple point,List<List<Trouple> > incendiesZone)
    {
        int i = 0;
        int ret = -1;
        while (i < incendiesZone.size() && incendiesZone.get(i).indexOf(point) == -1)
        {
            i++;
        }
        if (i < incendiesZone.size() && incendiesZone.get(i).indexOf(point) != -1)
        {
            ret = i;
        }
        else
        {
            ret = -1;
        }

        return ret;
    }

    /**
     * @param tab
     * @return
     */
    private List<Trouple > detectIncendieZone(List<Trouple > tab)
    {
        List<Trouple > incendieZone = new ArrayList<Trouple >();
        int intensiter = tab.get(0).getIntensite();
        int i = 0;
        int j = 0;
        incendieZone.add(tab.get(0));
        for (j = 0; j < incendieZone.size(); j++)
        {
            i = 0;
            while (i < tab.size() && tab.get(i).getIntensite() == intensiter)
            {
                double distance =  Math.sqrt(Math.pow(incendieZone.get(j).getx() - tab.get(i).getx(), 2) + Math.pow(incendieZone.get(j).gety() - tab.get(i).gety(), 2) );
                System.out.println(distance);
                if (distance <= 2 && incendieZone.indexOf(tab.get(i)) == -1)
                {
                    incendieZone.add(tab.get(i));
                }
                i++;
            }

        }
        //System.out.println("langue: "+  langue);
        return incendieZone;
    }

    /**
     * @param tab la liste des capteur recu
     * @return retourne la liste des capteurs recu par tirÃ©e par intensitÃ©
     */
    private List<Trouple > trierTabIntensiter(List<Trouple > tab)
    {
        List<Trouple > tabTrier = new ArrayList<Trouple >();
        while (tab.size() > 0)
        {
            int index  = maxTab(tab);
            tabTrier.add(tab.get(index));
            tab.remove(index);
        }
        return tabTrier;
    }

    /**
     * @param tab la liste des capteurs reÃ§u
     * @return la valeur maximum de l'intensiter
     */
    private int maxTab(List<Trouple > tab)
    {
        int index = 0;
        double maxVal = tab.get(0).getIntensite();
        for (int i = 0; i < tab.size(); i++)
        {
            if (maxVal < tab.get(i).getIntensite())
            {
                maxVal = tab.get(i).getIntensite();
                index = i;
            }
        }
        return index;
    }


    /**
     * @param tab la liste des capteurs recu
     * @param point le point pour lequel on recherche le point le plus proche
     * @return l'index du point le plus proche du point en paramÃ¨tre
     */
    private int indexPointPlusProche (List<Trouple > tab, Trouple point)
    {
        int index = -1;
        boolean drap = false;
        double distanceMin = Math.sqrt(Math.pow(point.getx() - tab.get(0).getx(), 2) + Math.pow(point.gety() - tab.get(0).gety(), 2) );
        for (int i = 0; i < tab.size(); i++)
        {

            if (distanceMin >= Math.sqrt(Math.pow(point.getx() - tab.get(i).getx(), 2) + Math.pow(point.gety() - tab.get(i).gety(), 2) ) && Math.sqrt(Math.pow(point.getx() - tab.get(i).getx(), 2) + Math.pow(point.gety() - tab.get(i).gety(), 2) ) < 10)
            {
                if (!drap && distanceMin == Math.sqrt(Math.pow(point.getx() - tab.get(i).getx(), 2) + Math.pow(point.gety() - tab.get(i).gety(), 2) ))
                {
                    distanceMin = Math.sqrt(Math.pow(point.getx() - tab.get(i).getx(), 2) + Math.pow(point.gety() - tab.get(i).gety(), 2) );
                    index = i;

                }
                else if (!drap)
                {
                    distanceMin = Math.sqrt(Math.pow(point.getx() - tab.get(i).getx(), 2) + Math.pow(point.gety() - tab.get(i).gety(), 2) );
                    index = i;
                }
            }
        }

        return index;
    }

    /**
     * @param tab
     * @return
     */
    private List<Trouple > selectUnIncendie (List<Trouple > tab)
    {
        double distance = -1;
        double distancePrec = 0;
        List<Trouple > tabUnIncendie = new ArrayList<Trouple >();
        tabUnIncendie.add(tab.get(0));
        tab.remove(0);
        int i = 0;
        int iprec = -1;

        if (tab.size() > 0)
        {
            //distance = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));
            distancePrec = 0;
            i = indexPointPlusProche (tab,tabUnIncendie.get(0));

            if (i >= 0 && i < tab.size())
            {
                distance = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));
            }
        }

        //System.out.println("i " + i);

        while ( i < tab.size() && i >= 0 && tab.size() > 0 && distancePrec <= distance  && tab.get(i).getIntensite() <= tabUnIncendie.get(tabUnIncendie.size()-1).getIntensite())
        {
            iprec = i;
            distance = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));

            if (distancePrec <= distance && distance < tabUnIncendie.get(0).getIntensite() && distance < tab.get(i).getIntensite())
            {
                distancePrec = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));
                tabUnIncendie.add(tab.get(i));
                //System.out.println("ajout");
                tab.remove(i);
            }
            if (tab.size()>0)
            {
                i = indexPointPlusProche (tab,tabUnIncendie.get(0));
                if (i < tab.size() && i >= 0)
                {
                    distance = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));
                }
            }
            if (iprec == i)
            {
                i ++;
            }

        }

        return tabUnIncendie;
    }

    /**
     * @param tab le tableau de tout les capteurs reÃ§u
     * @return retourne la liste des trouple dont l'intensitÃ© est null
     */
    private List<Trouple> retirerValeurNull(List<Trouple > tab)
    {
        int i = tab.size()-1;
        while (i >= 0)
        {
            if (tab.get(i).getIntensite() <= 0)
            {
                tab.remove(i);
            }
            i--;

        }
        nbPoint = tab.size();

        return tab;

    }
}