package httparser.thread;

import httparser.frame.FrmMain;
import httparser.table.ItemTableDwnld;
import httparser.table.ItemTableLink;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.m6c.parzing.tool.ToolLoadUrl;

import ressource.FxHtml;
import ressource.FxString;
import ressource.XML.FxXML;
import ressource.XML.XMLDocument;
import ressource.bean.BeanTag;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class OldThrdParse2Url extends OldAncestorThread {

	public OldThrdParse2Url(FrmMain parent) {
		super(parent);
	}

	public void run() {
	  	String szUrl = this.getUrl();
		if (this.isSubLink() || this.isUseIncludeList())
			this.getLink(szUrl);
		else {
			try {
				// Analyse seulement la page actuelle
				this.parseUrl(szUrl);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.getParent().setRunnerLink(null);
	}

	private void getLink(String szUrl) {
		int iNiveauMax = (this.isSubLink()) ? getMaxSubLevel() : 0;// 3 : 0;
		int iNiveauActuel = 0;

		// Liste des Url de la page à traiter
		Vector vGlobalListUrl = new Vector();
		// Liste des Index pour chaque vGlobalListUrl
		Vector vGlobalListLoop = new Vector();
		// Liste des liens de la premiere page à traiter
		Vector vListUrl = new Vector();
		// Liste des mask (RegEx) de liens à traiter
		Vector vListMaskUrlInc = null;
		// Liste des mask (RegEx) de liens à exclure
		Vector vListMaskUrlExc = null;
		if (this.isUseIncludeList()) { // Parcoure les liens contenu dans la
			// liste jTableLinkInclude
			vListUrl = new Vector();
			for (int i = 0; i < this.getParent().getUrlIncludeList().size(); i++) {
				ItemTableLink item = (ItemTableLink) this.getParent().getUrlIncludeList().elementAt(i);
				// Ajoute les liens ajouter dans la liste jTableLinkInclude
				vListUrl.add(item.getUrl());
			}
		} else {
			vListUrl.add(szUrl);
			if (this.isUseIncludeListAsMask()) { // Parcoure les liens
				// contenu dans la liste
				// jTableLinkInclude
				vListMaskUrlInc = new Vector();
				for (int i = 0; i < this.getParent().getUrlIncludeList().size(); i++) {
					ItemTableLink item = (ItemTableLink) this.getParent().getUrlIncludeList().elementAt(i);

					Pattern pat = null;
					if (item.getUrl().endsWith("/i")) {
						String szPat = szUrl.substring(0, szUrl.length() - 2);
						pat = Pattern.compile(szPat, Pattern.CASE_INSENSITIVE);
					} else
						pat = Pattern.compile(item.getUrl());

					// Ajoute les liens ajouter dans la liste
					// jTableLinkInclude
					vListMaskUrlInc.add(pat);
				}

				// Parcoure les liens contenu dans la liste
				// jTableLinkInclude
				vListMaskUrlExc = new Vector();
				for (int i = 0; i < this.getParent().getUrlExcludeList().size(); i++) {
					ItemTableLink item = (ItemTableLink) this.getParent().getUrlExcludeList().elementAt(i);

					Pattern pat = null;
					if (item.getUrl().endsWith("/i")) {
						String szPat = szUrl.substring(0, szUrl.length() - 2);
						pat = Pattern.compile(szPat, Pattern.CASE_INSENSITIVE);
					} else
						pat = Pattern.compile(item.getUrl());

					// Ajoute les liens ajouter dans la liste
					// jTableLinkInclude
					vListMaskUrlExc.add(pat);
				}
			}
		}

		// Ajout des liens de la première page
		vGlobalListUrl.addElement(vListUrl);
		// Ajout un index à Zero par default
		vGlobalListLoop.addElement(new Integer(0));

		if (vListUrl.size() > 0) {

			// Affiche la liste des liens chez le parent
			getParent().showLink(vListUrl);

			// Boucle sur toutes les listes des liens
			while (vGlobalListUrl.size() > 0) {
				// Liste du niveau Actuel
				Vector item = (Vector) vGlobalListUrl.elementAt(iNiveauActuel);
				// Index Actuel de la liste des liens
				int iLoop = ((Integer) vGlobalListLoop.elementAt(iNiveauActuel)).intValue();
				if (iLoop < item.size()) { // Il y a encore des liens à traiter

					// Lien a traiter
					String szUrl2 = (String) item.elementAt(iLoop++);
					// Teste si le lien doit être traiter ou non
					if (!this.getParent().isExclude(szUrl2)) {
						// Met à jour l'index Actuel de la liste des liens
						vGlobalListLoop.setElementAt(new Integer(iLoop), iNiveauActuel);
						if ((szUrl2 != null) && (szUrl2.length() > 0)) {
							try {
								String html = ToolLoadUrl.load(szUrl2, getParent().isCorrectHtml(), getParent().isClean(), getParent().isIndent());
								// Analyse la page
								this.parseUrl(szUrl2, html);
								// Si nous ne somme pas arrivé au niveau Maximum de
								// traitement
								// et si nous voulons aussi les liens contenus dans
								// la page
								if ((iNiveauActuel < iNiveauMax) && this.isSubLink()) {
									// Passe au niveau "Superieur"
									iNiveauActuel++;
									// Met à Zero l'index dans la liste
									iLoop = 0;
									// Recupère la liste des liens contenu dans la
									// page à traiter
									Vector vList = getListLink(szUrl2, html, vListMaskUrlInc, vListMaskUrlExc);
									// Affiche la liste des liens chez le parent
									getParent().showLink(vList);
									// Ajoute la liste des liens a traiter
									vGlobalListUrl.addElement(vList);
									// Ajoute
									vGlobalListLoop.addElement(new Integer(iLoop));
	
									System.out.println("Loop:" + iLoop + " vGlobalListUrl.size:" + vGlobalListUrl.size());
	
									// Ajout les liens trouvés dans la page pour ne
									// pas boucler sur les liens déjà trouvé.
									for (int i = 0; i < vList.size(); i++) {
										vListMaskUrlExc.add(Pattern.compile((String) vList.get(i)));
									}
								}
							} catch (MalformedURLException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} else {
						// Passe à l'Url suivante
						vGlobalListLoop.setElementAt(new Integer(iLoop + 1), iNiveauActuel);
					}
				} else { // Il n'y a plus de liens à traiter dans cette liste
					// Supprime la liste des Url
					vGlobalListUrl.remove(iNiveauActuel);
					// Supprime la liste Index
					vGlobalListLoop.remove(iNiveauActuel);
					// Reviend au niveau "Inferieur"
					iNiveauActuel--;
				}
			}
		}
	}

	/**
	 * Retourne la liste des liens contenu dans szHTML à partir de l'adresse
	 * szURL
	 * 
	 * @param szURL
	 * @param szHTML
	 * @param listMaskUrlInc
	 * @return liste d'objet URL
	 */
	protected Vector getListLink(String szURL, String szHTML, Vector listMaskUrlInc, Vector listMaskUrlExc) {
		Vector ret = new Vector();
		if (szHTML.length() > 0) {
			String szHTMLUp = szHTML.toLowerCase();


//			char[] htmlChar = szHTML.toCharArray();
//			int size = htmlChar.length;
//			int nb=1;
//			for(int i=0 ; i<size ; i++) {
//				if (!szHTML.substring(i, i+nb).toUpperCase().equals(szHTMLUp.substring(i, i+nb))) {
//					int a = 0;
//					a++;
//				}
//			}
			
			
			String baliseLnk = "href=";//"HREF=";
			String szCommentDeb = "<!--";
			String szCommentFin = "-->";
			int iDeb = 0;

			while (iDeb > -1) {
				iDeb = szHTMLUp.indexOf(baliseLnk, iDeb);
				int iDebComment = szHTMLUp.indexOf(szCommentDeb, iDeb);
				int iFinComment = szHTMLUp.indexOf(szCommentFin, iDebComment);
				if ((iDeb > -1) && ((iFinComment == -1) || (iFinComment >= iDebComment))) {
					iDeb += baliseLnk.length();
					StringBuffer str = new StringBuffer();
					boolean bGuillemet = (boolean) (szHTMLUp.charAt(iDeb) == '"');
					boolean bLoop = true;
					if (bGuillemet)
						iDeb++;
					while (bLoop) {
						// char c = szHTMLUp.charAt(iDeb++);
						char c = szHTML.charAt(iDeb++);
						// Pour contourner un problème avec l'Allemand par exemple qui fait une difference entre le minuscule et le majuscule de certaines lettres
						//iDeb += szHTMLUp.substring(iDeb, iDeb+1).length();
						switch (c) {
						case ' ':
							if (!bGuillemet)
								bLoop = false;
							break;
						case '>':
						case '"':
							bLoop = false;
							break;
						}
						if (bLoop)
							str.append(c);
					}
					iDeb += str.length();

					if (str.length() > 0) {
						try {
							/**
							 * Ajout l'url si elle correspond à une entrée de la
							 * liste des liens à Inclure (ou si la liste est
							 * null)
							 */
							boolean bAdd = false;
							URL root = new URL(szURL);
							URL urlTmp = new URL(root, str.toString());
							String szUrl = urlTmp.toString();
							if (listMaskUrlInc == null) {
								// ret.add(szUrl);
								bAdd = true;
							} else {
								for (Iterator iter = listMaskUrlInc.iterator(); iter.hasNext();) {
									Pattern element = (Pattern) iter.next();
									if (element.matcher(szUrl).find()) {
										// ret.add(szUrl);
										bAdd = true;
										break;
									}
								}
							}

							if (bAdd) {
								/**
								 * Ajout l'url si elle ne correspond pas à une
								 * entrée de la liste des liens à Exclure (ou si
								 * la liste est null)
								 */
								if (listMaskUrlExc != null) {
									for (Iterator iter = listMaskUrlExc.iterator(); iter.hasNext();) {
										Pattern element = (Pattern) iter.next();
										if (element.matcher(szUrl).find()) {
											bAdd = false;
											break;
										}
									}
								}
							}

							if (bAdd) {
								ret.add(szUrl);
							}
						} catch (MalformedURLException ex) {
							System.out.println("ERROR: Malformed Url: " + str.toString());
						}
					}
				} else if (iDeb > -1)
					iDeb++;
			}
		}
		return ret;
	}

	/**
	 * Analyse la page actuelle à la recherche des informations à téléchanger
	 * contenu dans la liste jTableDownload
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private void parseUrl(String url) throws MalformedURLException, IOException {
		String html = ToolLoadUrl.load(url, getParent().isCorrectHtml(), getParent().isClean(), getParent().isIndent());
		parseUrl(url, html);
	}

	/**
	 * Analyse la page actuelle à la recherche des informations à téléchanger
	 * contenu dans la liste jTableDownload
	 */
	private void parseUrl(String url, String html) {
		if (getParent().getTxtMethode().equals(METHODE_SEQUENCE)) {
			parseUrl_Sequence(url, html);
		} else {
			parseUrl_Text(url, html);
		}
	}

	/**
	 * Analyse la page actuelle à la recherche des informations à téléchanger
	 * contenu dans la liste jTableDownload
	 */
	private void parseUrl_Sequence(String url, String html) {
		// DEBUG
		// System.out.println( "FUNCTION parseUrl:" );
		Vector vListAction = this.getDownloadList();
		String szTextPath = "";

		if (getDestination().equals(DESTINATION_XML)) {
			StringBuffer sbHTML = new StringBuffer(html);
			String szHtml = sbHTML.toString();

			List listTag = FxHtml.extractHashTag(szHtml);

			if (listTag != null && listTag.size() > 0 && vListAction != null && vListAction.size() > 0) {

				int cnt = 0;
				XMLDocument xmlDoc = new XMLDocument();
				xmlDoc.setEncode(getParent().getTxtDataEncode());

				try {
					Iterator itAction = vListAction.iterator();
					List listAction = new LinkedList();
					while (itAction.hasNext()) {
						ItemTableDwnld data = (ItemTableDwnld) itAction.next();
						listAction.add(data); // Bean de donnée
						listAction.add(FxHtml.extractHashTag(data.getTextStart())); // Text
																					// de
																					// début
						listAction.add(FxHtml.extractHashTag(data.getTextEnd())); // Text
						// de
						// fin
						listAction.add(new Integer(0)); // Indice de recherche
						// dans la liste

						szTextPath = data.getTextPath();
					}

					int tagIdx = 0, sizeTag = listTag.size();
					while (true) {
						Vector vList = new Vector();
						for (int iAct = 0; iAct < listAction.size();) {
							ItemTableDwnld item = (ItemTableDwnld) listAction.get(iAct++);
							List lstActTag = (List) listAction.get(iAct++);
							List lstActTagEnd = (List) listAction.get(iAct++);
							tagIdx = ((Integer) listAction.get(iAct++)).intValue();

							boolean bLoop = true;
							while (bLoop) {
								String szData = "";
								BeanTag tagDat = null, tagAct = null;
								boolean bFind = false;
								Iterator itActTag = lstActTag.iterator();
								while (tagIdx < sizeTag) {
									if (tagAct == null || bFind)
										tagAct = (BeanTag) itActTag.next();
									tagDat = (BeanTag) listTag.get(tagIdx++);
									// if
									// (tagDat.getTag().equalsIgnoreCase(tagAct.getTag()))
									// {
									if (tagDat.equalsAtLeast(tagAct)) {
										// szData += tagDat.getContent();
										bFind = true;
										if (!itActTag.hasNext())
											break;
									} else {
										bFind = false;
									}
								}

								if (bFind) {

									if (item.getTextType().equalsIgnoreCase("image") && tagDat.getName().equalsIgnoreCase("IMG")) {
										szData += tagDat.getTag();
									} else {
										if (tagDat.getContent() != null)
											szData += tagDat.getContent();
									}

									int cntFind = 0;
									for (int i = tagIdx; i < sizeTag; i++) {
										Iterator itActTagEnd = lstActTagEnd.iterator();
										while (itActTagEnd.hasNext() && tagIdx < sizeTag) {
											tagAct = (BeanTag) itActTagEnd.next();
											tagDat = (BeanTag) listTag.get(i);
											if (tagDat.equalsAtLeast(tagAct)) {
												cntFind++;
												i++;
											} else {
												if (item.getTextType().equalsIgnoreCase("image") && tagDat.getName().equalsIgnoreCase("IMG")) {
													szData += tagDat.getTag();
												} else {
													if (tagDat.getTag() != null)
														szData += tagDat.getTag();
													if (tagDat.getContent() != null)
														szData += tagDat.getContent();
													if (tagDat.getEndTag() != null)
														szData += tagDat.getEndTag();
												}
												cntFind = 0;
												break;
											}
										}
										if (cntFind == lstActTagEnd.size()) {
											// szData += szTmp;
											bFind = true;
											break;
										} else {
											bFind = false;
										}
									}

									if (bFind) {
										Vector itemData = new Vector();
										itemData.add(cleanHtml(szData));

										Vector vListText = new Vector();
										vListText.add(item.getTextName());
										vListText.add(itemData);
										vList.add(vListText);
										bLoop = false;
									} else {
										tagIdx++;
									}
								} else {
									tagIdx++;
								}
								// Verifi si on est arrivé à la fin de la liste
								// de tag du document
								if (tagIdx >= sizeTag) {
									if (listAction.size() > 0 && iAct > 0) {
										// Supprime l'action
										listAction.remove(--iAct);
										listAction.remove(--iAct);
										listAction.remove(--iAct);
										listAction.remove(--iAct);
									} else
										bLoop = false;
								} else {
									// Met à jour l'ancien indice
									listAction.set(iAct - 1, new Integer(tagIdx));
								}
							}
						}

						if (vList.size() > 0) {
							cnt++;
							Hashtable attribut = new Hashtable();
							attribut.put("id", Integer.toString(cnt));

							FxXML.beginXMLElement(xmlDoc, "DATA", attribut);
							FxXML.addXMLData(xmlDoc, vList);
							FxXML.closeXMLElement(xmlDoc, "DATA");
						} else if (listTag.size() <= tagIdx) {
							break;
						} else {
							tagIdx++;
						}
					}
				} finally {

					if (cnt > 0) {
						String szFileName = buildFileName(url, szTextPath);
						getParent().setTitle("Writing File:" + szFileName);
						FxXML.writeXML(szFileName, xmlDoc);
						getParent().setTitle("File:" + szFileName + " writed");

						// Clean
						cnt = 0;
						xmlDoc = new XMLDocument();
						xmlDoc.setEncode(getParent().getTxtDataEncode());

					}
				}
			}
		} else if (getDestination().equals(DESTINATION_DATABASE)) {
			// TODO A FAIRE
		}
	}

	/**
	 * Analyse la page actuelle à la recherche des informations à téléchanger
	 * contenu dans la liste jTableDownload
	 */
	private void parseUrl_Text(String url, String html) {
		// DEBUG
		// System.out.println( "FUNCTION parseUrl:" );
		Vector vListAction = this.getDownloadList();
		Vector vListText;
		Vector vListImage;
		String szTextPath = "";

		if (getDestination().equals(DESTINATION_XML)) {
			StringBuffer sbHTML = new StringBuffer(html);
			sbHTML = new StringBuffer(sbHTML.toString().replaceAll("\r", "").replaceAll("\n", "\r\n"));

			XMLDocument xmlDoc = new XMLDocument();
			xmlDoc.setEncode(getParent().getTxtDataEncode());
			int cnt = 0, iEnd = 0;
			boolean bContinue = true;
			do {
				int iTmp = 0;
				vListText = new Vector();
				vListImage = new Vector();
				for (int i = 0; i < vListAction.size(); i++) {
					ItemTableDwnld item = (ItemTableDwnld) vListAction.elementAt(i);
					if (item.getTextType().equalsIgnoreCase("text")) {
						Vector data = null;
						if (getParent().getTxtMethode().equals(METHODE_TEXT_REGEX)) {
							clearUrl_Text_RegEx(sbHTML, item);
						} else {
							clearUrl_Text(sbHTML, item);
						}
						if (data.size() > 0) {
							Vector itemData = new Vector();
							itemData.add(item.getTextName());
							itemData.add(data);
							vListText.add(itemData);
							szTextPath = item.getTextPath();
							// Recupère la position où à été trouvé le text
							iTmp = Integer.parseInt((String) data.elementAt(1));
							// Supprime la position dans la liste
							data.remove(1);
							// DEBUG
							// System.out.println( "itemData TXT Name:" +
							// itemData.elementAt(0) + " data:" +
							// itemData.elementAt(1) );
						}
					} else if (item.getTextType().equalsIgnoreCase("image")) {
						Vector data = clearUrl_Image(sbHTML, item);
						if (data.size() > 0) {
							Vector itemData = new Vector();
							itemData.add(item.getTextName());
							itemData.add(data);
							vListImage.add(itemData);
							vListText.add(itemData);
							// Recupère la position où à été trouvé le text
							iTmp = Integer.parseInt((String) data.elementAt(1));
							// Supprime la position dans la liste
							data.remove(1);
							// DEBUG
							// System.out.println( "itemData IMG Name:" +
							// itemData.elementAt(0) + " data:" +
							// itemData.elementAt(1) );
						}
					}
					if (iTmp > iEnd)
						iEnd = iTmp;
				}
				bContinue = (vListText.size() > 0 || vListImage.size() > 0);
				if (bContinue) {
					cnt++;
					Hashtable attribut = new Hashtable();
					attribut.put("id", Integer.toString(cnt));
					FxXML.beginXMLElement(xmlDoc, "DATA", attribut);
					if (vListText.size() > 0)
						FxXML.addXMLData(xmlDoc, vListText);
					if (vListImage.size() > 0)
						FxXML.addXMLData(xmlDoc, vListImage);
					FxXML.closeXMLElement(xmlDoc, "DATA");
					sbHTML.replace(0, iEnd, "");
				}
			} while (bContinue);

			if (cnt > 1) {
				String szFileName = buildFileName(url, szTextPath);
				getParent().setTitle("Writing File:" + szFileName);
				FxXML.writeXML(szFileName, xmlDoc);
				getParent().setTitle("File:" + szFileName + " writed");
			}
		} else if (getDestination().equals(DESTINATION_DATABASE)) {
			vListText = new Vector();
			vListImage = new Vector();
			for (int i = 0; i < vListAction.size(); i++) {
				ItemTableDwnld item = (ItemTableDwnld) vListAction.elementAt(i);
				if (item.getTextType().equalsIgnoreCase("text")) {
					Vector itemData = new Vector();
					itemData.add(item.getTextName());
					itemData.add(parseUrl_Text_RegEx(html, item));
					vListText.add(itemData);
					szTextPath = item.getTextPath();
					// DEBUG
					// System.out.println( "itemData TXT Name:" +
					// itemData.elementAt(0) + " data:" + itemData.elementAt(1)
					// );
				} else if (item.getTextType().equalsIgnoreCase("image")) {
					Vector itemData = new Vector();
					itemData.add(item.getTextName());
					itemData.add(parseUrl_Image(html, item));
					vListImage.add(itemData);
					vListText.add(itemData);
					// DEBUG
					// System.out.println( "itemData IMG Name:" +
					// itemData.elementAt(0) + " data:" + itemData.elementAt(1)
					// );
				}
			}

			if (vListText.size() > 0) {
				// Récupération du ClassLoader actuel à la place du
				// Class.forName
				// pour une meilleur gestion de la mémoire
				ClassLoader classloader = this.getClass().getClassLoader();
				try {
					// Récupération de la classe
					Class classe = classloader.loadClass("videofuture.toolz.ImportURLFilmToDB");
					// Récupération de la methode avec comme parametre deux
					// String
					Method method = classe.getMethod("execute", new Class[] { Vector.class });
					// Invocation de la methode
					method.invoke(classe.newInstance(), new Object[] { vListText });
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private Vector parseUrl_Text_RegEx(String html, ItemTableDwnld item) {
		Vector ret = new Vector();
		StringBuffer szHTML = new StringBuffer();
		szHTML = new StringBuffer(szHTML.toString().replaceAll("\r", "").replaceAll("\n", "\r\n"));

		if (szHTML.length() > 0) {
			boolean bContinue = true;
			String baliseDebTmp = item.getTextStart().replaceAll("\r", "").replaceAll("\n", "\r\n");
			String baliseFinTmp = item.getTextEnd().replaceAll("\r", "").replaceAll("\n", "\r\n");
			int iDeb = 0;
			int iFin = 0;
			while (bContinue) {
				Pattern pDeb = Pattern.compile(baliseDebTmp);
				Matcher mDeb = pDeb.matcher(szHTML.toString()); // get a matcher
				// object

				Pattern pFin = Pattern.compile(baliseFinTmp);
				Matcher mFin = pFin.matcher(szHTML.toString()); // get a matcher
				// object

				bContinue = (mDeb.find() && mFin.find());
				if (bContinue) {
					iDeb = mDeb.end();
					iFin = mFin.start();
					String str = szHTML.substring(iDeb, iFin);
					str = cleanTag(str);
					ret.add(str);
				}
			}
		}
		return ret;
	}

	private Vector parseUrl_Image(String html, ItemTableDwnld item) {
		Vector ret = new Vector();
		StringBuffer szHTML = new StringBuffer(html);
		szHTML = new StringBuffer(szHTML.toString().replaceAll("\r", "").replaceAll("\n", "\r\n"));

		if (szHTML.length() > 0) {
			boolean bContinue = true;
			String baliseDebTmp = item.getTextStart().replaceAll("\r", "").replaceAll("\n", "\r\n");
			String baliseFinTmp = item.getTextEnd().replaceAll("\r", "").replaceAll("\n", "\r\n");
			int iDeb = 0;
			int iFin = 0;
			while (bContinue) {
				iDeb = szHTML.toString().indexOf(baliseDebTmp, iDeb);
				iFin = szHTML.toString().indexOf(baliseFinTmp, iDeb + baliseDebTmp.length());
				// Cherche la dernière occurance de la balise de fin
				// Même si il y a d'autres balises du même type à l'interieur
				// exemple: <SCRIPT><SCRIPT>blablabla...</SCRIPT></SCRIPT>
				int iTmp = szHTML.toString().indexOf(baliseDebTmp, iDeb + baliseFinTmp.length());
				while ((iTmp > -1) && (iTmp < iFin)) {
					iFin = szHTML.toString().indexOf(baliseFinTmp, iFin + baliseFinTmp.length());
					iTmp = szHTML.toString().indexOf(baliseDebTmp, iTmp + baliseDebTmp.length());
				}
				bContinue = ((iDeb > -1) && (iFin > -1));
				if (bContinue) {
					iDeb += baliseDebTmp.length();
					String str = szHTML.substring(iDeb, iFin).toUpperCase();
					String imgPath = FxHtml.getNextText(str, "SRC=", 0);
					if (imgPath.length() > 0) {
						ret.add(imgPath);
					}
				}
			}
		}
		return ret;
	}

	private Vector clearUrl_Text(StringBuffer szHTML, ItemTableDwnld item) {
		Vector ret = new Vector();

		if (szHTML.length() > 0) {
			String baliseDebTmp = item.getTextStart().replaceAll("\r", "").replaceAll("\n", "\r\n");
			String baliseFinTmp = item.getTextEnd().replaceAll("\r", "").replaceAll("\n", "\r\n");
			int iDeb = 0;
			int iFin = 0;
			iDeb = szHTML.toString().indexOf(baliseDebTmp, iDeb);
			iFin = szHTML.toString().indexOf(baliseFinTmp, iDeb + baliseDebTmp.length());
			// Cherche la dernière occurance de la balise de fin
			// Même si il y a d'autres balises du même type à l'interieur
			// exemple: <SCRIPT><SCRIPT>blablabla...</SCRIPT></SCRIPT>
			int iTmp = szHTML.toString().indexOf(baliseDebTmp, iDeb + baliseFinTmp.length());
			while ((iTmp > -1) && (iTmp < iFin)) {
				iFin = szHTML.toString().indexOf(baliseFinTmp, iFin + baliseFinTmp.length());
				iTmp = szHTML.toString().indexOf(baliseDebTmp, iTmp + baliseDebTmp.length());
			}
			if ((iDeb > -1) && (iFin > -1)) {
				iDeb += baliseDebTmp.length();
				String str = szHTML.substring(iDeb, iFin);
				str = cleanTag(str);
				ret.add(str);
				ret.add(Integer.toString(iFin));
			}
			// szHTML.replace(iDeb, iFin, "");
		}
		return ret;
	}

	private Vector clearUrl_Text_RegEx(StringBuffer szHTML, ItemTableDwnld item) {
		Vector ret = new Vector();
		szHTML = new StringBuffer(szHTML.toString().replaceAll("\r", "").replaceAll("\n", "\r\n"));

		if (szHTML.length() > 0) {

			String baliseDebTmp = item.getTextStart().replaceAll("\r", "").replaceAll("\n", "\r\n");
			String baliseFinTmp = item.getTextEnd().replaceAll("\r", "").replaceAll("\n", "\r\n");
			Pattern pDeb = Pattern.compile(baliseDebTmp);
			Pattern pFin = Pattern.compile(baliseFinTmp);
			int iDeb = 0;
			int iFin = 0;
			Matcher mDeb = pDeb.matcher(szHTML.toString()); // get a matcher
			// object
			while (mDeb.find(iFin)) {
				iDeb = mDeb.end();
				Matcher mFin = pFin.matcher(szHTML.toString()); // get a matcher
				// object
				if (mFin.find(iDeb)) {
					iFin = mFin.start();
					String str = szHTML.substring(iDeb, iFin);
					str = cleanTag(str);
					ret.add(str);
					ret.add(Integer.toString(iFin));
				} else {
					break;
				}
			}

		}
		return ret;
	}

	private Vector clearUrl_Image(StringBuffer szHTML, ItemTableDwnld item) {
		Vector ret = new Vector();

		if (szHTML.length() > 0) {
			boolean bContinue = true;
			String baliseDebTmp = item.getTextStart().replaceAll("\r", "").replaceAll("\n", "\r\n");
			String baliseFinTmp = item.getTextEnd().replaceAll("\r", "").replaceAll("\n", "\r\n");
			int iDeb = 0;
			int iFin = 0;
			while (bContinue) {
				iDeb = szHTML.toString().indexOf(baliseDebTmp, iDeb);
				iFin = szHTML.toString().indexOf(baliseFinTmp, iDeb + baliseDebTmp.length());
				// Cherche la dernière occurance de la balise de fin
				// Même si il y a d'autres balises du même type à l'interieur
				// exemple: <SCRIPT><SCRIPT>blablabla...</SCRIPT></SCRIPT>
				int iTmp = szHTML.toString().indexOf(baliseDebTmp, iDeb + baliseFinTmp.length());
				while ((iTmp > -1) && (iTmp < iFin)) {
					iFin = szHTML.toString().indexOf(baliseFinTmp, iFin + baliseFinTmp.length());
					iTmp = szHTML.toString().indexOf(baliseDebTmp, iTmp + baliseDebTmp.length());
				}
				bContinue = ((iDeb > -1) && (iFin > -1));
				if (bContinue) {
					iDeb += baliseDebTmp.length();
					String str = szHTML.substring(iDeb, iFin).toUpperCase();
					String imgPath = FxHtml.getNextText(str, "SRC=", 0);
					if (imgPath.length() > 0) {
						ret.add(imgPath);
						ret.add(Integer.toString(iFin));
					}
					// szHTML.replace(iDeb, iFin, "");
				}
			}
		}
		return ret;
	}

	private String buildFileName(String szUrl, String path) {
		// return path.concat("\\").concat( (
		// (this.getUrlContext().getQuery()==null ||
		// this.getUrlContext().getQuery().equals("")) ?
		// this.getUrlContext().getFile() : this.getUrlContext().getQuery()));
		URL url = null;
		try {
			url = new URL(szUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String ret = path + "\\";
		ret += Calendar.getInstance().getTimeInMillis();
		if (url!=null) {
			if (url.getQuery() != null && !url.getQuery().equals("")) {
				ret += "_" + this.getUrlContext().getQuery();
			} else {
				if (url.getFile() != null && !url.getFile().equals("")) {
					String filename = url.getFile();
					int idx = filename.lastIndexOf('/');
					if (idx >= 0)
						filename = filename.substring(idx + 1);
					ret += "_" + filename;
				} else {
					ret += "_" + url.getHost();
				}
			}
		}
		return ret;
	}

	/**
	 * Supprime toutes les informations superflu de szHTML
	 * 
	 * @param szHTML
	 *            : Chaine de caractère à traiter
	 * @return
	 */
	private String cleanTag(String szHTML) {
		String str = szHTML;
		str = removeTag(szHTML);
		str = cleanHtml(szHTML);
		return str.trim();
	}

	/**
	 * Supprime toutes les informations superflu de szHTML
	 * 
	 * @param szHTML
	 *            : Chaine de caractère à traiter
	 * @return
	 */
	private String cleanHtml(String szHTML) {
		String str = szHTML;
		str = FxString.replaceString(str, "\r", "");
		str = FxString.replaceString(str, "\n", "");
		str = FxString.replaceString(str, "\t", "");
		str = FxString.replaceString(str, "&gt;", "");
		str = FxString.replaceString(str, "&nbsp;", " ");
		str = FxString.replaceString(str, "&#160;", " ");
		str = FxString.replaceString(str, "         ", " ");
		str = FxString.replaceString(str, "        ", " ");
		str = FxString.replaceString(str, "       ", " ");
		str = FxString.replaceString(str, "     ", " ");
		str = FxString.replaceString(str, "    ", " ");
		str = FxString.replaceString(str, "   ", " ");
		str = FxString.replaceString(str, "  ", " ");
		return str.trim();
	}

	/**
	 * Supprime les tag du texte szHTML
	 * 
	 * @param szHTML
	 * @return
	 */
	private String removeTag(String szHTML) {
		String str = FxString.deleteString(szHTML, "<", ">");
		return str.trim();
	}
}