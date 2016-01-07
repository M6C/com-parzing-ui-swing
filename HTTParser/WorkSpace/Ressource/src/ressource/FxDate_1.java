package ressource;

import java.util.*;

public class FxDate {

  public static final char DIVIDE_DATE    = '/';
  public static final char DIVIDESQL_DATE = '-';
  /** D�finition du syst�me horaire */
  static private Locale frmLocale =  new Locale("FRENCH", "FRANCE");

  protected FxDate(){
  }

  //Transformation d'un date FR en Date US
  public static String DateFr2Us(String p_str){
    String	ls_date;

    String	Year_str	=	new String(p_str.substring(6));
    String	Month_str	=	new String(p_str.substring(3, 5));
    String	Day_str		=	new String(p_str.substring(0, 2));

    ls_date	= Month_str + new Character(DIVIDE_DATE).toString();
    ls_date	+=	Day_str + new Character(DIVIDE_DATE).toString();
    ls_date	+=	Year_str;

    return	ls_date;
  }

  // Remplace les caract�res de s�paration de date par le bon caract�re SQL
  // Format Date SQL: Year-Month-Day Hour:Minute:Second
  //						: Year-Month-Day
  //						: Day-Month-Year
  public static String DateSQL(String p_str){
    if(p_str == null) return p_str;
    if(p_str.length() >= 6){
      if((p_str.charAt(2) == DIVIDE_DATE) && (p_str.charAt(4) == DIVIDE_DATE)){
      return p_str.replace(DIVIDE_DATE, DIVIDESQL_DATE);}
    }
    return p_str;

  }

  public int getYear(String p_str){
    return new Integer(p_str.substring(6)).intValue();
  }

        /**
         * Comparer 2 dates et dire si s1 est plus grand que s2
         * @param dt1  Date 1.
         * @param dt2  Date 2.
         * @return Vrai si date1 est >= � date2.
         */
        public static boolean compareDate(Date dt1, Date dt2) {

                if (dt1.compareTo(dt2) >= 0) {
                        return true;
                } else {
                        return false;
                }
        }


        /**
         * Retourne l'annee d'une string au format dd/mm/yyyy.
         * @param date  Une date.
         * @return l'ann�e r�cup�r�e dans la date.
         */
        public static int getAnnee(String date) {
                return (new Integer(date.substring(6, 10))).intValue();
        }


        /**
         * Retourne l'annee d'une date.
         * @param dt  Une date.
         * @return l'ann�e r�cup�r�e dans la date.
         */
        public static int getAnnee(Date dt) {
                Calendar calendar = new GregorianCalendar(getLocale());
                calendar.setTime(dt);

                return (calendar.get(Calendar.YEAR));
        }


        /**
         * Retourne le mois d'une string au format dd/mm/yyyy.
         * @param date  Une date.
         * @return le mois r�cup�r� dans la date.
         */
        public static String getMois(String date) {
                return date.substring(3, 5);
        }


        /**
         * Retourne le mois d'une date.
         * @param date  une date.
         * @return le mois r�cup�r� dans la date.
         */
        public static int getMois(Date dt) {
                Calendar calendar = new GregorianCalendar(getLocale());
                calendar.setTime(dt);

                return (calendar.get(Calendar.MONTH));
        }


        /**
         * Retourne le jour d'une string au format dd/mm/yyyy.
         * @param date  Une date.
         * @return le jour r�cup�r� dans la date.
         */
        public static String getJour(String date) {
                return date.substring(0, 2);
        }


        /**
         * Retourne le jour d'une date.
         * @param date  Une date.
         * @return le jour r�cup�r� dans la date.
         */
        public static String getJour(Date dt) {
                Calendar calendar = new GregorianCalendar(getLocale());
                calendar.setTime(dt);

                return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        }


        /**
         * Incrementer une date : l'incr�ment est le mois.
         * @param dt1  Une date.
         * @return la date incr�ment�e d'un mois.
         */
        public static Date incrementMois(Date dt1) {
                return 	incrementMois(dt1, 1);
        }


        /**
         * Incrementer une date : l'incr�ment est le mois.
         * @param dt1  Une date.
         * @return la date incr�ment�e de nb mois mois.
         */
        public static Date incrementMois(Date dt1, int nombre) {

                GregorianCalendar calendar = new GregorianCalendar(frmLocale);
                calendar.setTime(dt1);
                calendar.add(Calendar.MONTH, nombre);

                return calendar.getTime();
        }


        /**
         * Retourne le nombre de mois entre deux dates.
         * @param dateDeb  date de d�but � prendre en compte.
         * @param dateFin  date de fin � prendre en compte.
         * @return le nombre de mois entre dateDeb et dateFin.
         */
        public static int dureeMois(Date dateDeb, Date dateFin) {

                if (dateFin.compareTo(dateDeb) >= 0) {
                        int nbMois = 0;

                        GregorianCalendar calendar = new GregorianCalendar(frmLocale);
                        calendar.setTime(dateDeb);
                        Date dt = calendar.getTime();

                  while (dateFin.compareTo(dt) >= 0) {
                          calendar.add(Calendar.MONTH,1);
                                nbMois++;
                          dt = calendar.getTime();
                        }

                        if (Integer.parseInt(getJour(dateFin)) < calendar.get(Calendar.DAY_OF_MONTH))
                                nbMois++;

                        return nbMois;

                } else {
                  return -1;
                }
        }


        /**
         * Retourne une date �gale � "date" incr�ment�e de "incr" jours.
         * @param dt  la date � incr�menter.
         * @param incr  le nombre de jours � incr�menter.
         * @return le r�sultat de l'incr�mentation.
         */
        public static Date incrementJour(Date dt, int incr) {

                GregorianCalendar calendar = new GregorianCalendar(frmLocale);
                calendar.setTime(dt);
                calendar.add(Calendar.HOUR, incr * 24);

                return calendar.getTime();
        }


        /**
         * Retourne le nombre de jours entre la date1 et la date2
         * @param date1  premi�re date.
         * @param date2  seconde date.
         * @return le nombre de jours entre la date1 et la date2. 0 si le nombre est n�gatif.
         */
        public static int nbJours(Date dt1, Date dt2) {
                if (compareDate(dt1,dt2)) {
                  return 0;
                } else {

                        int nbJours = 0;

                        GregorianCalendar calendar = new GregorianCalendar(frmLocale);
                        calendar.setTime(dt1);
                        Date dt = calendar.getTime();

                  while (!compareDate(dt, dt2)) {
                          calendar.add(Calendar.DATE,1);
                                nbJours++;
                          dt = calendar.getTime();
                        }
                        return nbJours;
                }
        }



        /**
         * Retourne le nombre de jours dans le mois nbMois de
         * l'ann�e nbAnnee.
         * @param nbMois  le mois.
         * @param nbAnnee  l'ann�e.
         * @return le nombre de jours dans le mois
         */
        public static int maxJour(float nbAnnee, float nbMois) {

                // 1er cas ... les mois de 30 jours
                if ( (nbMois == 4) || (nbMois == 6) || (nbMois == 9) || (nbMois == 11) ) {
                        return 30;
                } else {
                        // Mois de f�vrier...
                        if (nbMois == 2) {
                                int inc = 0;
                                if (Math.round(nbAnnee/4) == (nbAnnee/4)) {
                                                 inc++;
                                }
                                if (Math.round(nbAnnee/100)==(nbAnnee/100)) {
                                                 inc--;
                                }
                                if (Math.round(nbAnnee/400)==(nbAnnee/400)) {
                                                 inc++;
                                }
                                return ( 28 + inc );
                        // Les mois � 31 jours...
                        } else {
                                return 31;
                        }
                }
        }

        /**
         * Retourne la valeur de la propri�t� frmLocale.
         * @return valeur de la propri�t� Locale
         */
        public static Locale getLocale() {
                return frmLocale;
        }
}
