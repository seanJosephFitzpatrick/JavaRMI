package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class StringServiceImpl extends UnicastRemoteObject implements StringService{

	private static final long serialVersionUID = 1L;
	private Algorithms algorithms;
        private Resultator resultator;
	
	public StringServiceImpl() throws RemoteException{
		algorithms = new Algorithms();
	}

	public Resultator compare(String a, String b, String algorithm) throws RemoteException {
            resultator = new ResultatorIMPL(); 
	    switch(algorithm){
                case "Damerau-Levenshtein Distance":
                	resultator.setResult(String.valueOf((algorithms.DamerauLevenshtein(a, b))));
                case "Levenshtein Distance":
                	resultator.setResult(String.valueOf((algorithms.Levenshtein(a,b))));
                case "Hamming Distance":
                	resultator.setResult(String.valueOf((algorithms.HammingDistance(a, b))));              
		}
        resultator.setProcessed();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultator;
}
}
