package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StringService extends Remote {
	
    public Resultator compare(String str1, String str2, String algo) throws RemoteException;
    
}
