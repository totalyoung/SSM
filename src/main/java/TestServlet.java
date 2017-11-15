import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;


public class TestServlet extends HttpServlet{

	private long modTime = System.currentTimeMillis()/1000*1000;
	
	@Override
	public void doGet(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	throws IOException{
	
		PrintWriter printWriter = httpServletResponse.getWriter();
		printWriter.print("hello");
		//EntityResolver
		DocumentBuilderFactory.newInstance();
		ServletContext ser = httpServletRequest.getServletContext();
				
	}
	
	public void doPost(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
			throws IOException{
		doGet(httpServletRequest,httpServletResponse);
	}
	
	public long getLastModified(HttpServletRequest httpServletRequest){
		return modTime;
	}
	
	
	
	
 
}
