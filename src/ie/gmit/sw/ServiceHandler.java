package ie.gmit.sw;

import java.io.*;
import java.rmi.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.*;


import javax.servlet.*;
import javax.servlet.http.*;

public class ServiceHandler extends HttpServlet {
	private String remoteHost = null;
	private static long jobNumber = 0;
	private static LinkedList<Request> InQueue;
	private static Map<String, Resultator> OutQueue;
	private static ExecutorService executor;
	private final int EXECUTOR_POOL_SIZE = 10;
	private boolean checkProcessed = false;
	private String distance = "";

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
		

		InQueue = new LinkedList<Request>();
		OutQueue = new HashMap<String, Resultator>();
		executor = Executors.newFixedThreadPool(EXECUTOR_POOL_SIZE);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringService ss = null;
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Initialise some request varuables with the submitted form info. These are local to this method and thread safe...
		String algorithm = req.getParameter("cmbAlgorithm");
		String str1 = req.getParameter("txtS");
		String str2 = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");
		
		
		
		try {
			ss = (StringService) Naming.lookup("rmi://"+remoteHost+":1099/StringService");
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			
			//Reset checkProcessed to false
			checkProcessed = false;
			
			// Create a new Request OBJECT
			Request request = new Request(algorithm, str1, str2 , taskNumber);

			// Add Task to in-queue
			InQueue.add(request);

			// Pass the Request Obj to a Worker Class (Thread)
			Runnable worker = new Worker(InQueue, OutQueue, ss);

			// Execute the Worker(fixed_pool_size)
			executor.execute(worker);
			
			PrintWriter writer = resp.getWriter();
			writer.println("<h3>Hello from doget</h3>");
		
			jobNumber++;
			
		}else{
			// ELSE - Check outQueue for finished job

						// Check HashMap for
						if (OutQueue.containsKey(taskNumber)) {
							
							Resultator result = OutQueue.get(taskNumber);

							System.out.println("\nChecking Status of Task No:" + taskNumber);

							checkProcessed = result.isProcessed();

							// Check to see if result is Processed
							if (checkProcessed == true) {
								// Remove the processed item from Map by taskNumber
								OutQueue.remove(taskNumber);
								//Get the Distance of the Current Task
								distance = result.getResult();

								System.out.println("\nTask " + taskNumber + "Removed from OutQueue");
								System.out.println("Distance Between String (" + str1 + ") and String (" + str2 + ") = " + distance);
							}
			}
		}
		
		
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + str1);
		out.print("<br>String <i>t</i> : " + str2);
		out.print("<br>This servlet should only be responsible for handling client request and returning responses. Everything else should be handled by different objects.");
		out.print("Note that any variables declared inside this doGet() method are thread safe. Anything defined at a class level is shared between HTTP requests.");				
		out.print("</b></font>");

		out.print("<P> Next Steps:");	
		out.print("<OL>");
		out.print("<LI>Generate a big random number to use a a job number, or just increment a static long variable declared at a class level, e.g. jobNumber.");	
		out.print("<LI>Create some type of an object from the request variables and jobNumber.");	
		out.print("<LI>Add the message request object to a LinkedList or BlockingQueue (the IN-queue)");			
		out.print("<LI>Return the jobNumber to the client web browser with a wait interval using <meta http-equiv=\"refresh\" content=\"10\">. The content=\"10\" will wait for 10s.");	
		out.print("<LI>Have some process check the LinkedList or BlockingQueue for message requests.");	
		out.print("<LI>Poll a message request from the front of the queue and make an RMI call to the String Comparison Service.");			
		out.print("<LI>Get the <i>Resultator</i> (a stub that is returned IMMEDIATELY by the remote method) and add it to a Map (the OUT-queue) using the jobNumber as the key and the <i>Resultator</i> as a value.");	
		out.print("<LI>Return the result of the string comparison to the client next time a request for the jobNumber is received and the <i>Resultator</i> returns true for the method <i>isComplete().</i>");	
		out.print("</OL>");	
		
		//put an if statement here to stop page refresh
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"cmbAlgorithm\" type=\"hidden\" value=\"" + algorithm + "\">");
		out.print("<input name=\"txtS\" type=\"hidden\" value=\"" + str1 + "\">");
		out.print("<input name=\"txtT\" type=\"hidden\" value=\"" + str2 + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		//this refreshes the page
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
				
		//You can use this method to implement the functionality of an RMI client
		
		//
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}