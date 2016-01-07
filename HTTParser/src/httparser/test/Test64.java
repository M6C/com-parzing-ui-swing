package httparser.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class Test64 extends Thread {

	private static String format_date = "yyyy/MM/dd-HH:mm:ss";
	private static String format_directory = "yyyy-MM-dd/HH/";
	private static String format_filename = "yyyyMMddHHmm'.txt'";
	private static SimpleDateFormat sdf = new SimpleDateFormat(format_date);
	private static SimpleDateFormat sdfDir = new SimpleDateFormat(format_directory);
	private static SimpleDateFormat sdfFile = new SimpleDateFormat(format_filename);
	private static StringBuffer stbData = new StringBuffer();
	
	private static boolean mode_progression = true;
	private static int increment = 1;
	private static int decalage = 0;
	private static int output_cnt = 10000;
	private static boolean output_file = false;
	private static boolean output_zip = false;
	private static int nb_thread = 0;
	private static String chaine_debut= "a";
	private static String code = "j/NbH+z7zEoDCSQNeAv5k9bR9eQ";
	private static boolean continueTraitement = true;
	private static String chaine_trouve = "";
	private static String chaine_code = "";
	private static int cnt=0;
	private static Test64 instance;
	private static boolean modeThread = false;

	private static List listThread = new LinkedList();

	private String motACoder;
	private Iterator itThread = null;
	private Test64Thread thead = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		init(args);
		
		instance = new Test64();
		
		instance.start();
	}
	
	public void run () {

        char[] motFinalEnCode = new String(chaine_debut).toCharArray();
    
        ini_thread();
    
        while (continueTraitement) {
    
        	motFinalEnCode = getMot(motFinalEnCode);
			motACoder = new String((char[])motFinalEnCode);

			if (modeThread) {
				run_thread();
			}
			else {

    	    	motFinalEnCode = getMot(motFinalEnCode);
				motACoder = new String((char[])motFinalEnCode);
				setChaine_code(codeChaine(motACoder));

				if (getChaine_code().startsWith(code)) {
					setChaine_trouve(getChaine_code());
					continueTraitement = false;
				}

    	    	if (((cnt++)%output_cnt)==0) {
    					System.out.println(sdf.format(Calendar.getInstance().getTime())+" "+getCnt()+":"+motACoder + " -> " + getChaine_code());
    
    					writeData();
    	    	}

			}
        }
    
        if (getChaine_trouve()!=null && !getChaine_trouve().equals("")) {
    			System.out.println("******************************************************");
    			System.out.println("*"+sdf.format(Calendar.getInstance().getTime())+" "+getCnt()+":"+getChaine_trouve() + " -> " + getChaine_code());
    			System.out.println("******************************************************");
        }
	}

	public synchronized void addData(String word, String code) {
		if (output_file) {
			stbData.append(word).append("\t").append(code).append("\r\n");
		}
	}

	public synchronized void writeData() {
		if (output_file) {
			File dir = new File(sdfDir.format(Calendar.getInstance().getTime()));
			if (!dir.exists())
				dir.mkdirs();
			File file = new File(dir, sdfFile.format(Calendar.getInstance().getTime()));
			try {
				FileOutputStream fos = new FileOutputStream(file, true);
				if (output_zip) {
					GZIPOutputStream gz = new GZIPOutputStream(fos);
					gz.write(stbData.toString().getBytes());
					ObjectOutputStream oos = new ObjectOutputStream(gz);
					oos.writeObject(stbData.toString());
				} else {
					fos.write(stbData.toString().getBytes());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			stbData = new StringBuffer();
		}
	}
/*
	private void logDebug_isExecute() {
		Test64Thread thead = null;
      	Iterator it = listThread.iterator();
      	System.err.print("cnt:"+getCnt()+" ");
      	for (int i=0 ; it.hasNext() ; i++) {
      		thead = (Test64Thread)it.next();
      		System.err.print(i+":"+(thead.isExecute() ? "1 " : "0 "));
      	}
      	System.err.println("-");
	}
*/
	private void ini_thread() {
		for (int i=0 ; i<nb_thread ; i++) {
			listThread.add(new Test64Thread());
		}
	}

	private void run_thread() {

		if (thead==null || !itThread.hasNext()) {
  		itThread = listThread.iterator();

  		// Passe la main aux autres thread pour execution
    	Iterator it = listThread.iterator();
    	while(it.hasNext()) {
       	thead = (Test64Thread)it.next();
    		while(thead.isExecute())
    			Thread.yield();
    	}
		}
  	thead = (Test64Thread)itThread.next();
  	thead.restart(this, motACoder);

  	// DEBUG
  	//logDebug_isExecute()
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
				else if ("-output_file".equalsIgnoreCase(arg)) {
					output_file = new Boolean(args[++i]).booleanValue();
				}
				else if ("-output_zip".equalsIgnoreCase(arg)) {
					output_zip = new Boolean(args[++i]).booleanValue();
				}
				else if ("-code".equalsIgnoreCase(arg)) {
					code = args[++i];
				}
				else if ("-format_date".equalsIgnoreCase(arg)) {
					format_date = args[++i];
					sdf = new SimpleDateFormat(format_date);
				}
			}
        
      modeThread = (nb_thread>0);
  
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
		System.out.println("| output_file -> " + output_file);
		System.out.println("| output_zip -> " + output_zip);
		System.out.println("| Nb_thread -> " + nb_thread + " (modeThread:"+modeThread+")");
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
		Test64.continueTraitement = continueTraitement;
	}

	public String getChaine_trouve() {
		return chaine_trouve;
	}

	public void setChaine_trouve(String chaine_trouve) {
		Test64.chaine_trouve = chaine_trouve;
	}

	public String getChaine_code() {
		return chaine_code;
	}

	public void setChaine_code(String chaine_code) {
		Test64.chaine_code = chaine_code;
	}

	public int getCnt() {
		return cnt;
	}

	public synchronized void setCnt(int cnt) {
		Test64.cnt = cnt;
	}

	public String getChaine_debut() {
		return chaine_debut;
	}

	public void setChaine_debut(String chaine_debut) {
		Test64.chaine_debut = chaine_debut;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		Test64.code = code;
	}

	public int getDecalage() {
		return decalage;
	}

	public void setDecalage(int decalage) {
		Test64.decalage = decalage;
	}

	public String getFormat_date() {
		return format_date;
	}

	public void setFormat_date(String format_date) {
		Test64.format_date = format_date;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		Test64.increment = increment;
	}

	public boolean isMode_progression() {
		return mode_progression;
	}

	public void setMode_progression(boolean mode_progression) {
		Test64.mode_progression = mode_progression;
	}

	public int getOutput_cnt() {
		return output_cnt;
	}

	public void setOutput_cnt(int output_cnt) {
		Test64.output_cnt = output_cnt;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		Test64.sdf = sdf;
	}
}