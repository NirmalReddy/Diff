package sample;

/*
 * 
 * This file is part of the sample code for the article,
 * "What is Jetty,"
 * http://www.onjava.com/pub/a/onjava/2006/06/14/what-is-jetty.html
 * by Ethan McCallum
 * 
 * This code was tested against Jetty 5.1.10 and Sun's JDK 1.5.0_03.
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.mortbay.jetty.Server;
import org.mortbay.xml.XmlConfiguration;
//import org.eclipse.jetty.xml.XmlConfiguration;

public class Step2Driver {

	private static final String LISTEN_ADDY = "localhost:7501" ;
	private static final String CONTEXT_PATH = "/embed" ;
	private static final String SERVLET_PATH = "/TryThis" ;
	
	public static void main( final String[] args ) {

		
		try{

			// load config from a file in the classpath
			final URL serviceConfig = Thread.currentThread().getContextClassLoader().getResource( "step2-service-config.xml" ) ;
			final XmlConfiguration serverFactory = new XmlConfiguration( serviceConfig ) ;
			
			// represents a container
			final Server service = (Server) serverFactory.newInstance() ;
				
				// instantiate a Server object based on the XML config file
			

			// start the container.  Notice that this call returns immediately;
			// Jetty handles the threading for you behind the scenes.  All you
			// have to do is hold on to the Server object such that you can 
			// shut it down later.
			
			service.start() ;
			System.out.println( "Server started" ) ;			
			
			
			// make a few calls to the servlet and print the output
			
			final URL[] targets = new URL[] {
				new URL( "http://" + LISTEN_ADDY + CONTEXT_PATH + SERVLET_PATH ) ,
				new URL( "http://" + LISTEN_ADDY + CONTEXT_PATH + SERVLET_PATH + "/foo" ) ,
				new URL( "http://" + LISTEN_ADDY + CONTEXT_PATH + SERVLET_PATH + "/bar" )
			} ;

			for( int ix = 0 ; ix != targets.length ; ++ix ){

				final URL url = targets[ix] ;
				
				System.out.println( "- - - URL Call " + ( 1 + ix ) + ": \"" + url + "\" - - -" ) ;
				
		        BufferedReader in = new BufferedReader( new InputStreamReader( url.openStream() ) );

		        String line ;
		        while( null != ( line  = in.readLine() ) ){
		        	System.out.println( line ) ;
		        }
		        in.close();

			}
			
			//service.wait(1000*60);
			// shutdown the container
			service.stop() ;
			
		}catch( Throwable t ){
			t.printStackTrace() ;
			
			// without the explicit exit() call, the container's threads would 
			// continue to run ad infinitum.  In a real app, the catch() block
			// would call Server.stop() in a do-nothing try/catch block, not
			// unlike one used for a JDBC ResultSet or Statement.
			System.exit( 1 ) ;
		}

	} // main()
	
} // public class Step1Driver