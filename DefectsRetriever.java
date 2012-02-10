package it.unibz.emse;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/* Nicolò Paternoster, Carmine Giardino. EMSE Students
 * Software Metrics Course
 * Free University Of Bolzano
 * 2010/2011
 * 
 * Usage : DefectsRetriever <URL/To/BugZilla> <ProductName> <from AAAA-MM-GG> <to AAAA-MM-GG> > outputFileName.csv
 * 
 * */


public class DefectsRetriever {
	public static final String PRODUCT_NAME = "Defect Occurrences Retriever - 0.01 " ;
	public static final String USAGE_HELP = "DefectsRetriever <URL/To/BugZilla> <ProductName> <from AAAA-MM-GG> <to AAAA-MM-GG> > outputFileName.csv \n"+
	" f.i.   java -jar DefectsRetriever.jar https://bugzilla.mozilla.org addons.mozilla.org 2010-01-01 2010-11-01 > mozilla.csv";

	public static URL searchURI=null;

	public static InputStream is = null;
	public static DataInputStream dis;
	public static String s;


	public static URL getUrl(String bugzillaPath,String productName,String from,String to) throws MalformedURLException
	{
		// Working Example: https://bugzilla.mozilla.org/buglist.cgi?bug_status=RESOLVED&bug_status=VERIFIED&bug_status=CLOSED&chfield=%5BBug%20creation%5D&chfieldfrom=2010-01-01&chfieldto=2010-11-11&columnlist=opendate&product=addons.mozilla.org&query_format=advanced&resolution=FIXED&resolution=WORKSFORME&resolution=EXPIRED&resolution=MOVED&ctype=csv

		String url = bugzillaPath+"/"+
		"buglist.cgi?bug_status=RESOLVED&bug_status=VERIFIED&bug_status=CLOSED&chfield=%5BBug%20creation%5D"+
		"&chfieldfrom="+from+"&chfieldto="+to+
		"&columnlist=opendate"+
		"&product="+productName+"&query_format=advanced"
		+"&resolution=FIXED&resolution=WORKSFORME&resolution=EXPIRED&resolution=MOVED&ctype=csv";

		//System.out.println(url);

		return new URL(url);
	}
	public static void main(String[] args)
	{
		if (args.length!=4)
			System.out.println(PRODUCT_NAME+"**********************************\n\nWrong Sintax. " +
					"\n\n**********************************\n\n" +
					"Use Instead : \n\n##########################\n"+USAGE_HELP +
			"\n\n##########################\n\n");
		else
		{
			//Some checks here
			try {
				searchURI = getUrl(args[0],args[1],args[2],args[3]);
				is = searchURI.openStream();
				dis = new DataInputStream(new BufferedInputStream(is));
				while ((s = dis.readLine()) != null) {
					System.out.println(s);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				try {
					is.close();
				} catch (IOException ioe) {
				}
			}
		}
	}

}
