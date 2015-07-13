package com.CTS.MS.MCID.SVN.JavaClient;

import java.io.File;
import java.util.Date;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class TestSVNClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	/*	SVNClientManager ourClientManager = SVNClientManager.newInstance();
	SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
	updateClient.setIgnoreExternals(false);*/
		
		 //final String url1 = "https://svne1.access.nsn.com/isource/svnroot/mocrrepository";
		 final String bracnhUrl = "https://svn./svn/bi_cdr_ui/branches/dec14_rel";
         final String destPath = "C:\\Practice_Code";
         int revisionNumber=348;

         SVNRepositoryFactoryImpl.setup();
         DAVRepositoryFactory.setup();
         SVNRepository repository = null;
         

     try{
         //initiate the reporitory from the url
         repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(bracnhUrl));

         //create authentication data
         ISVNAuthenticationManager authManager = 
                 SVNWCUtil.createDefaultAuthenticationManager("cog51017", "D1ngd0ng");
         repository.setAuthenticationManager(authManager);

         //output some data to verify connection
         System.out.println( "Repository Root: " + repository.getRepositoryRoot( true ) );
         System.out.println( "Repository Root: " + repository.getLocation() );
         System.out.println(  "Repository UUID: " + repository.getRepositoryUUID( true ) );

         //need to identify latest revision
         long latestRevision = repository.getLatestRevision();
         System.out.println(  "Repository Latest Revision: " + latestRevision);

         //create client manager and set authentication
         SVNClientManager ourClientManager = SVNClientManager.newInstance();
         ourClientManager.setAuthenticationManager(authManager);

         //use SVNUpdateClient to do the export
         SVNUpdateClient updateClient = ourClientManager.getUpdateClient( );
         updateClient.setIgnoreExternals( false );


	System.out.println("Export :"+updateClient.doExport( repository.getLocation(), new File(destPath), 
                 SVNRevision.create(revisionNumber), SVNRevision.create(latestRevision), 
                 null, true, SVNDepth.INFINITY));

     } catch (SVNException e) {
    	 System.out.println(e);
         e.printStackTrace();
     } finally {
         System.out.println("Done");
     }
		

	}

}
