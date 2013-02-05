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

import java.io.IOException;
import java.io.PrintWriter ;

import javax.servlet.http.HttpServlet ;
import javax.servlet.ServletConfig ;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

public class SimpleServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void init( ServletConfig config ) throws ServletException {
		super.init( config ) ;
	} // init
	
	public void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException {
		
		final String pathInfo = request.getPathInfo() ;
		PrintWriter out = new PrintWriter( response.getWriter() ) ;
		out.print( "Servlet called: " ) ;
		
		if( null == pathInfo ){
			out.println( "no extra path info" ) ;
		} else {
			out.println( "path info is \"" + pathInfo + "\"" ) ;
		}
		return ;
		
	} // doGet()
	
} // public class SimpleServlet
