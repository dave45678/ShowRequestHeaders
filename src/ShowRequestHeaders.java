
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowRequestHeaders")
public class ShowRequestHeaders extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowRequestHeaders() {
        super();
    }

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		printOutput(request,response);
	}

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		printOutput(request,response);
	}
	
	private void printOutput(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String title = "Showing Request Headers";
		out.println("<html><head><title>" + title + "</title></head>"
				+ "<body bgcolor=\"#aed6f1\">\n" + "<h1 align=\"center\">" + title
				+ "</h1>\n" + "<b>Request method: </b>" + request.getMethod()
				+ "<br>\n" + "<b>Request URI: </b>" + request.getRequestURI()
				+ "<br>\n" + "<b>Request Protocol: </b>"
				+ request.getProtocol() + "<br><br>\n"
				+ "<table border=\"1\" align=\"center\">\n"
				+ "<tr bgcolor=\"#ffad00\">\n"
				+ "<th>Header Name<th>Header Value");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			out.println("<tr><td>" + headerName);
			out.println("    <td>" + request.getHeader(headerName));
		}
		// Read from request
	    StringBuilder buffer = new StringBuilder();
	    BufferedReader reader;

		reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }

	    String data = buffer.toString();
		
		out.println("<tr><td>request variables:</td><td>" + data + "</td></tr>");
		out.println("</table>\n");
		out.println("</body></html>");
	}

}
