package httparser.test;

import java.util.Calendar;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class Test64Thread extends Thread {

	private String chaine_debut= "a";
	private Test64 parent = null;
	private boolean execute = false;

	public Test64Thread() {
		super();
		start();
	}

	public Test64Thread(Test64 parent, String chaine) {
		setParent(parent);
		this.chaine_debut = chaine;
	}

	public synchronized void restart(Test64 parent, String chaine) {
		setParent(parent);
		this.chaine_debut = chaine;
		execute = true;
	}

	public void run() {

		while (true) {
			if (execute) {
				String motCode = codeChaine(chaine_debut);

				parent.addData(chaine_debut, motCode);

				if (motCode.startsWith(parent.getCode())) {
					System.out.println("******************************************************");
					System.out.println("*"+this+"-"+parent.getSdf().format(Calendar.getInstance().getTime())+" "+parent.getCnt()+":"+chaine_debut + " -> " + motCode);
					System.out.println("******************************************************");
					parent.setChaine_trouve(motCode);
					parent.setContinueTraitement(false);
				}
		
				parent.setCnt(parent.getCnt()+1);

	    	if (((parent.getCnt())%parent.getOutput_cnt())==0) {
					System.out.println(parent.getSdf().format(Calendar.getInstance().getTime())+" "+parent.getCnt()+":"+chaine_debut + " -> " + motCode);

					parent.writeData();
				}

	    	execute = false;
			}
			else {
				Thread.yield();
			}
		}
	}

	private static String codeChaine(String chaine) {

		byte[] j = DigestUtils.sha(chaine);

		String bb = new String(Base64.encodeBase64(j));

		return bb;

	}

	public Test64 getParent() {
		return parent;
	}

	public void setParent(Test64 parent) {
		this.parent = parent;
	}

	public boolean isExecute() {
		return execute;
	}
}