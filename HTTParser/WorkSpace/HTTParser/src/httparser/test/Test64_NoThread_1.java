package httparser.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class Test64_NoThread extends Thread {

	private static String format_date = "yyyy/MM/dd-HH:mm:ss";
	private static SimpleDateFormat sdf = new SimpleDateFormat(format_date);

	private static boolean mode_progression = true;
	private static int increment = 1;
	private static int decalage = 0;
	private static int output_cnt = 200000;
	private static int nb_thread = 5;
	private static String chaine_debut= "a";
	private static String code = "j/NbH+z7zEoDCSQNeAv5k9bR9eQ";
	private static boolean continueTraitement = true;
	private static String chaine_trouve = "";
	private static String chaine_code = "";
	private static int cnt=0;
	private static Test64_NoThread instance;

	private static List listThread = new LinkedList();
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		init(args);
		
		instance = new Test64_NoThread();
		
		instance.start();
	}
	
	public void run () {

    char[] motFinalEnCode = new String(chaine_debut).toCharArray();
		String motACoder;

    while (continueTraitement) {

    	motFinalEnCode = getMot(motFinalEnCode);
			motACoder = new String((char[])motFinalEnCode);
			setChaine_code(codeChaine(motACoder));

			if (getChaine_code().startsWith(code)) {
				setChaine_trouve(getChaine_code());
				continueTraitement = false;
			}

    	if (((getCnt())%output_cnt)==0) {
				System.out.println(sdf.format(Calendar.getInstance().getTime())+" "+getCnt()+":"+motACoder + " -> " + getChaine_code());
			}
    }

    if (getChaine_trouve()!=null && !getChaine_trouve().equals("")) {
			System.out.println("******************************************************");
			System.out.println("*"+sdf.format(Calendar.getInstance().getTime())+" "+getCnt()+":"+getChaine_trouve() + " -> " + getChaine_code());
			System.out.println("******************************************************");
    }
	}

	private static void init(String[] args) {

    for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if ("-mode_progression".equalsIgnoreCase(arg)) {
				mode_progression = new Boolean(args[++i]).booleanValue();
				break;
			}
		}

    init_default();

    for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if ("-word".equalsIgnoreCase(arg)) {
				chaine_debut = args[++i];
			}
			else if ("-increment".equalsIgnoreCase(arg)) {
				increment = new Integer(args[++i]).intValue();
			}
			else if ("-nb_thread".equalsIgnoreCase(arg)) {
				nb_thread = new Integer(args[++i]).intValue();
			}
			else if ("-decalage".equalsIgnoreCase(arg)) {
				decalage = new Integer(args[++i]).intValue();
			}
			else if ("-output_cnt".equalsIgnoreCase(arg)) {
				output_cnt = new Integer(args[++i]).intValue();
			}
			else if ("-code".equalsIgnoreCase(arg)) {
				code = args[++i];
			}
			else if ("-format_date".equalsIgnoreCase(arg)) {
				format_date = args[++i];
				sdf = new SimpleDateFormat(format_date);
			}
		}

    init_show();
	}

	private static void init_default() {
		increment = (mode_progression) ? 1 : -1;
		chaine_debut= (mode_progression) ? "a" : "999999999999";
	}

	private static void init_show() {
		System.out.println("---------------------------------------------------");
		System.out.println("| Mode_progression -> " + mode_progression);
		System.out.println("| Increment -> " + increment);
		System.out.println("| Decalage -> " + decalage);
		System.out.println("| Output_cnt -> " + output_cnt);
		System.out.println("| Nb_thread -> " + nb_thread);
		System.out.println("| Word -> " + chaine_debut);
		System.out.println("| Code -> " + code);
		System.out.println("| Format_date -> " + format_date);
		System.out.println("|");
		System.out.println("| Date -> " + sdf.format(Calendar.getInstance().getTime()));
		System.out.println("---------------------------------------------------");
	}

	private static char[] getMot(char[] motFinalEnCode) {

		int nbCar = motFinalEnCode.length;
		int indexEnCours = 0;
		int nbCarFin = 0; 

		for (int i = nbCar-1; i >= 0; i--) {
			indexEnCours = (int)motFinalEnCode[i];
			if (mode_progression) {
				// a-z
				if (indexEnCours>=97) {
					motFinalEnCode[i] = (char)((indexEnCours<122)? indexEnCours+increment : (char)(65+decalage));
					break;
				}
				// A-Z
				else if (indexEnCours>=65) {
					motFinalEnCode[i] = (char)((indexEnCours<90)? indexEnCours+increment : (char)(48+decalage));
					break;
				}
				// 0-9
				else if (indexEnCours>=48) {
					if (indexEnCours<57) {
						motFinalEnCode[i] = (char)(indexEnCours+increment);
						break;
					}
					else {
						nbCarFin++;
						motFinalEnCode[i] = (char)(97+decalage);
					}
				}
				else {
					motFinalEnCode[i] = (char)(97+decalage);
				}
			}
			else {
				// 0-9
				if (indexEnCours<=57) {
					motFinalEnCode[i] = (char)((indexEnCours>48)? indexEnCours+increment : (char)(90+decalage));
					break;
				}
				// A-Z
				else if (indexEnCours<=90) {
					motFinalEnCode[i] = (char)((indexEnCours>65)? indexEnCours+increment : (char)(122+decalage));
					break;
				}
				// a-z
				else if (indexEnCours<=122) {
					if (indexEnCours>97) {
						motFinalEnCode[i] = (char)(indexEnCours+increment);
						break;
					}
					else {
						nbCarFin++;
						motFinalEnCode[i] = (char)(57+decalage);
					}
				}
				else {
					motFinalEnCode[i] = (char)(57+decalage);
				}
			}
		}

		if (nbCar==nbCarFin) {
			if (mode_progression) {
				char[] tmp = motFinalEnCode;
				motFinalEnCode = new char[nbCar + 1];
				for (int i = 0; i < nbCar; i++) {
					motFinalEnCode[i] = tmp[i];
				}
				// a
				motFinalEnCode[nbCar] = (char)(97+decalage);
			}
			else {
				if (nbCar<0) {
					motFinalEnCode = null;
				}
				else {
					char[] tmp = motFinalEnCode;
					motFinalEnCode = new char[--nbCar];
					for (int i = 0; i < nbCar; i++) {
						motFinalEnCode[i] = tmp[i];
					}
				}
			}
		}
		// return false;
		return motFinalEnCode;
	}

	private static String codeChaine(String chaine) {

		byte[] j = DigestUtils.sha(chaine);

		String bb = new String(Base64.encodeBase64(j));

		return bb;

	}

	public boolean isContinueTraitement() {
		return continueTraitement;
	}

	public void setContinueTraitement(boolean continueTraitement) {
		Test64_NoThread.continueTraitement = continueTraitement;
	}

	public String getChaine_trouve() {
		return chaine_trouve;
	}

	public void setChaine_trouve(String chaine_trouve) {
		Test64_NoThread.chaine_trouve = chaine_trouve;
	}

	public String getChaine_code() {
		return chaine_code;
	}

	public void setChaine_code(String chaine_code) {
		Test64_NoThread.chaine_code = chaine_code;
	}

	public int getCnt() {
		return cnt;
	}

	public synchronized void setCnt(int cnt) {
		Test64_NoThread.cnt = cnt;
	}

	public String getChaine_debut() {
		return chaine_debut;
	}

	public void setChaine_debut(String chaine_debut) {
		Test64_NoThread.chaine_debut = chaine_debut;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		Test64_NoThread.code = code;
	}

	public int getDecalage() {
		return decalage;
	}

	public void setDecalage(int decalage) {
		Test64_NoThread.decalage = decalage;
	}

	public String getFormat_date() {
		return format_date;
	}

	public void setFormat_date(String format_date) {
		Test64_NoThread.format_date = format_date;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		Test64_NoThread.increment = increment;
	}

	public boolean isMode_progression() {
		return mode_progression;
	}

	public void setMode_progression(boolean mode_progression) {
		Test64_NoThread.mode_progression = mode_progression;
	}

	public int getOutput_cnt() {
		return output_cnt;
	}

	public void setOutput_cnt(int output_cnt) {
		Test64_NoThread.output_cnt = output_cnt;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		Test64_NoThread.sdf = sdf;
	}
}