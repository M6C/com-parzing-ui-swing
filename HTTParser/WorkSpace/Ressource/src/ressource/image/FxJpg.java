package ressource.image;

import java.net.*;
import com.sun.image.codec.jpeg.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class FxJpg {

  public FxJpg() {
  }

  /**
   * Write a liste of image from an url to a destination path
   * @param url
   * @param lstImage
   * @param szDestinationPath
   */
  public static void writeFromUrl( URL url, Vector lstImage, String szDestinationPath )
  {
    for( int i=0 ; i<lstImage.size() ; i++ )
    {
      Vector item = (Vector)lstImage.elementAt(i);
      String szName = (String)item.elementAt(0);
      Vector data = (Vector)item.elementAt(1);
      for( int j=0 ; j<data.size() ; j++ )
      {
        String link = (String)data.elementAt(j);
        try
        {
          URL imgUrl = new URL(url,link);
          writeFromUrl(imgUrl, szDestinationPath);
        }
        catch(MalformedURLException ex)
        {
          //TRACE
          ex.printStackTrace();
        }
        catch (IOException ex)
        {
          //TRACE
          ex.printStackTrace();
        }
        catch (Exception ex)
        {
          //TRACE
          ex.printStackTrace();
        }
      }
    }
  }

  /**
   * Write an Jpg Image from an url to a destination path
   * @param url
   * @param szDestPath
   */
  public static void writeFromUrl(URL url, String szDestPath)
  {
    try
    {
      int iSlash = url.getFile().lastIndexOf('/');
      String szFileName = szDestPath.concat( url.getFile().substring(iSlash+1) );
      JPEGImageDecoder enc = JPEGCodec.createJPEGDecoder(url.openStream());
      BufferedImage bufImg = enc.decodeAsBufferedImage();
      try
      {
        File theFile = new File(szFileName);
        JPEGCodec.createJPEGEncoder(new FileOutputStream(theFile)).encode(bufImg);
      }
      catch (java.io.IOException ex)
      {
        //TRACE
        ex.printStackTrace();
      }
    }
    catch(MalformedURLException ex)
    {
      //TRACE
      ex.printStackTrace();
    }
    catch (IOException ex)
    {
      //TRACE
      ex.printStackTrace();
    }
  }
}