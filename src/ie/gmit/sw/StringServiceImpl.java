package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import ie.gmit.stringAlgo.*;


public class StringServiceImpl extends UnicastRemoteObject implements StringService{

	private static final long serialVersionUID = 1L;
    private Resultator resultator;
    private Levenshtein levenshtein;
    private DamerauLevenshtein damerauLevenshtein;
    private HammingDistance hammingDistance;
	
	public StringServiceImpl() throws RemoteException{
		levenshtein = new Levenshtein(); 
		damerauLevenshtein = new DamerauLevenshtein();
		hammingDistance = new HammingDistance();
	}

	public Resultator compare(String str1, String str2, String algorithm) throws RemoteException {
		resultator = new ResultatorIMPL(); 
	    switch(algorithm){
                case "Damerau-Levenshtein Distance":
                	resultator.setResult(String.valueOf(damerauLevenshtein.distance(str1, str2)));
                case "Levenshtein Distance":
                	resultator.setResult(String.valueOf(levenshtein.distance(str1, str2)));
                case "Hamming Distance":
                	resultator.setResult(String.valueOf(hammingDistance.distance(str1, str2)));             
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
