package ie.gmit.sw;

import java.util.LinkedList;
import java.util.Map;
import java.rmi.RemoteException;

public class Worker implements Runnable{
	
	
	private Resultator result;
	private StringService stringService;
	private LinkedList<Request> InQueue;
	private Map<String, Resultator> OutQueue;

	public Worker(LinkedList<Request> inQueue, Map<String, Resultator> outQueue, StringService str) {
		this.InQueue = inQueue;
		this.stringService = str;
		this.OutQueue = outQueue;
	}

	
	public void run() {
		//Take From InQueue
		Request request = InQueue.poll();

		try {
			
			System.out.println("Task No : " + request.getTaskNumber() + " is being processed");
			
			//Sleep for 8 seconds
			Thread.sleep(8000);
			
			result = stringService.compare(request.getStr1(), request.getStr2(), request.getAlgorithim());
			
			//Add to HashMap
			OutQueue.put(request.getTaskNumber(), result);
			
			
		} catch (RemoteException e) {		
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
