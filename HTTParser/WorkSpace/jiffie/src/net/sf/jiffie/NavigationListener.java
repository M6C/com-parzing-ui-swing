/*
 * file:       NavigationListener.java
 * author:     Mark Richter
 * copyright:  (c) Packwood Software Limited 2004
 * date:       25/08/2004
 */

/*
 * This file is part of JIFFIE.
 *
 * JIFFIE is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * JIFFIE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JIFFIE; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package net.sf.jiffie;


/**
 * Interface which must be implemented by classes wishing to receive
 * navigation events from Internet Explorer. The events themselves are described 
 * in detail at:
 * 
 * http://msdn.microsoft.com/library/default.asp?url=/workshop/browser/webbrowser/reflist_vb.asp
 */
public interface NavigationListener
{
   /**
    * BeforeNavigate event handler.
    *
    * @param url String that specifies the URL to which the browser is navigating.
    * @param targetFrameName String that specifies the name of the frame in which
    *        the resource will be displayed, or null if no named frame is targeted
    *        for the resource.
    * @return true to cancel the navigation or false to allow it to continue.
    */
   public boolean beforeNavigate (String url, String targetFrameName);
   
   /**
    * DocumentComplete event handler.
    * 
    * @param url String that specifies the URL, Universal Naming Convention (UNC)
    *        file name, or pointer to an item identifier list (PIDL) of the
    *        loaded document.
    */
   public void documentComplete (String url);

   /**
    * DownloadBegin event handler.
    */
   public void downloadBegin ();

   /**
    * DownloadComplete event handler.
    */
   public void downloadComplete ();
   
   /**
    * FileDownload event handler.
    * 
    * @param active true if the file is active
    * @return true to cancel the download
    */
   public boolean fileDownload(boolean active);
   
   /**
    * NavigateComplete event handler.
    * 
    * @param url String that specifies the URL, Universal Naming Convention (UNC)
    *        file name, or pointer to an item identifier list (PIDL) that was
    *        navigated to.
    */
   public void navigateComplete (String url);

   /**
    * NavigateError event handler.
    *
    * @param url String expression that evaluates to the URL for which navigation failed.
    * @param targetFrameName String that specifies the name of the frame in which
    *        the resource was to be displayed, or null if no named frame was targeted
    *        for the resource.
    * @param statusCode a long that contains a status code corresponding to the error,
    *        if available. This can be either a HTTP status or INET status code.  For a
    *        list of the possible HTTP status codes see java.net.HttpURLConnection. The
    *        list of INET status codes are attached as public members to this interface.
    * @return boolean that specifies whether to cancel the navigation to an error page
    *         and/or any further autosearch.  Return <code>false</code> to continue
    *         with navigation to an error page and/or autosearch.  Return <code>true</code>
    *         Cancel navigation to an error page and/or autosearch.
    * @see java.net.HttpURLConnection Http Status Codes in java.net.HttpURLConnection
    */
   public boolean navigateError (String url, String targetFrameName, int statusCode);
   
   /**
    * NewWindow2 event handler.
    * 
    * @param newIe the new hidden InternetExplorer window with no URL loaded.
    * @return true to cancel the navigation or false to allow it to continue.
    */
   public boolean newWindow2 (InternetExplorer newIe);
      
   /**
    * NewWindow3 event handler.
    * 
    * @param flags values from the NWMF enumeration
    * @param opener opener URL
    * @param opening opening URL
    * @return true to cancel the navigation or false to allow it to continue.
    */
   public boolean newWindow3 (int flags, String opener, String opening);
      
   /**
    * ProgressComplete event handler.
    * 
    * @param progress specifies the amount of total progress to show,
    *        or -1 when progress is complete.
    * @param progressMax specifies the maximum progress value.
    */
   public void progressChange (int progress, int progressMax);

   
   /** URL string is not valid. */
   public static final int INET_E_INVALID_URL = -2146697214;

   /** No session found. */
   public static final int INET_E_NO_SESSION = -2146697213;

   /** Unable to connect to server. */
   public static final int INET_E_CANNOT_CONNECT = -2146697212;

   /** Requested resource is not found. */
   public static final int INET_E_RESOURCE_NOT_FOUND = -2146697211;

   /** Requested object is not found. */
   public static final int INET_E_OBJECT_NOT_FOUND = -2146697210;

   /** Requested data is not available. */
   public static final int INET_E_DATA_NOT_AVAILABLE = -2146697209;

   /** Failure occurred during download. */
   public static final int INET_E_DOWNLOAD_FAILURE = -2146697208;

   /** Requested navigation requires authentication. */
   public static final int INET_E_AUTHENTICATION_REQUIRED = -2146697207;

   /** Required media not available or valid. */
   public static final int INET_E_NO_VALID_MEDIA = -2146697206;

   /** Connection timed out. */
   public static final int INET_E_CONNECTION_TIMEOUT = -2146697205;

   /** Request is invalid. */
   public static final int INET_E_INVALID_REQUEST = -2146697204;

   /** Protocol is not recognized. */
   public static final int INET_E_UNKNOWN_PROTOCOL = -2146697203;

   /** Navigation request has encountered a security issue. */
   public static final int INET_E_SECURITY_PROBLEM = -2146697202;

   /** Unable to load data from the server. */
   public static final int INET_E_CANNOT_LOAD_DATA = -2146697201;

   /** Unable to create an instance of the object. */
   public static final int INET_E_CANNOT_INSTANTIATE_OBJECT = -2146697200;

   /** Attempt to redirect the navigation failed. */
   public static final int INET_E_REDIRECT_FAILED = -2146697196;

   /** Navigation redirected to a directory. */
   public static final int INET_E_REDIRECT_TO_DIR = -2146697195;

   /** Unable to lock request with the server. */
   public static final int INET_E_CANNOT_LOCK_REQUEST = -2146697194;

   /** Reissue request with extended binding. */
   public static final int INET_E_USE_EXTEND_BINDING = -2146697193;

   /** Binding is terminated. */
   public static final int INET_E_TERMINATED_BIND = -2146697192;

   /** Permission to download is declined. */
   public static final int INET_E_CODE_DOWNLOAD_DECLINED = -2146697960;

   /** Result is dispatched. */
   public static final int INET_E_RESULT_DISPATCHED = -2146697704;

   /** Cannot replace a protected System File Protection (SFP) file. */
   public static final int INET_E_CANNOT_REPLACE_SFP_FILE = -2146697448;
   
}
