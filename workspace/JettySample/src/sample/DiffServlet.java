package sample;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

public class DiffServlet extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Diff diff=new Diff();
		response.setContentType("text/html");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		String file1 = request.getParameter("input1");
		String file2 = request.getParameter("input2");
		out.println(">>>> Difference of file1.java & file2.java <br/>"); 	
		init(diff);
		storeLine(diff,file1,file2);
		computeDiff(diff);
	    out.println(""+diff.output);
		//out.println(file1+"<br/>"+file2);
	}
	
	public void init(Diff diff) {
		diff.oldinfo = new fileInfo();
		diff.newinfo = new fileInfo();
		diff.output ="";
	}
	
	public void storeLine(Diff diff,String file1 , String file2) {
		try {
		String linebuffer1=null,linebuffer2=null;
		JSONArray array1=new JSONArray(file1);
		JSONArray array2=new JSONArray(file2);
		for(int i=0;i<array1.length();i++) {
			linebuffer1=array1.get(i).toString();
			linebuffer1=linebuffer1.trim().replaceAll("( )+", " ");
	   	    if (!linebuffer1.isEmpty()) {
	           diff.storeline( linebuffer1, diff.oldinfo );
	   	    }
		}
		for(int i=0;i<array2.length();i++) {    
	        linebuffer2=array2.get(i).toString();
			linebuffer2=linebuffer2.trim().replaceAll("( )+", " ");
	        if (!linebuffer2.isEmpty()) {
	           diff.storeline( linebuffer2, diff.newinfo );
	        }
		}	
		} catch(JSONException j) {
		  throw new RuntimeException(j);
		}
	}
	
	public void computeDiff(Diff diff) {
		diff.blocklen = new int[(diff.oldinfo.maxLine > diff.newinfo.maxLine ? diff.oldinfo.maxLine
					: diff.newinfo.maxLine) + 2];
		diff.oldinfo.alloc();
		diff.newinfo.alloc();
		diff.transform();
		diff.printout();
	}
}
