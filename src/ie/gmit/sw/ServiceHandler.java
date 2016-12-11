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

	private static final long serialVersionUID = 1L;
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
		
		//Initialize some request variables with the submitted form info. These are local to this method and thread safe...
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

			// Execute the Worker(EXECUTOR_POOL_SIZE)
			executor.execute(worker);
			
			jobNumber++;
			
		}else{
			// ELSE - Check outQueue for finished job

				// Check HashMap
				if (OutQueue.containsKey(taskNumber)) {
				
					Resultator result = OutQueue.get(taskNumber);
	
					//console print
					System.out.println("\nChecking Status of Task No : " + taskNumber);
					
					checkProcessed = result.isProcessed();
					
					// if checkProcessed is True
					if (checkProcessed == true) {
						// Remove the processed item from HashMap by taskNumber
						OutQueue.remove(taskNumber);
						//Get the Result of the Current Task
						distance = result.getResult();
						
						//Console print
						System.out.println("Task : " + taskNumber + " Removed from OutQueue");
						System.out.println("\nDistance Between String (" + str1 + ") and String (" + str2 + ") = " + distance);
						
						out.print("<font color=\"#993333\"><b>");
						out.print("<br><br><center><h1>Job#: " + taskNumber + " has been processed</h1><center>");
						out.print("<br>Distance was calculated as: " + distance);
						out.print("<br>Algorithm: " + algorithm);		
						out.print("<br>String <i>one</i> : " + str1);
						out.print("<br>String <i>two</i> : " + str2);
						
				}
			}
		}
		
		if(!checkProcessed){
			
			out.print("<font color=\"#993333\"><b>");
			out.print("<br><br><center><h1>Processing request for Job#: " + taskNumber + "</h1><center>");
			out.print("<br><h3>Please wait....</h3><br>");
		
			out.print("RMI Server is located at " + remoteHost);
			out.print("<br>Algorithm: " + algorithm);		
			out.print("<br>String <i>one</i> : " + str1);
			out.print("<br>String <i>two</i> : " + str2);
			
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
		}
		
		//You can use this method to implement the functionality of an RMI client
	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}

}