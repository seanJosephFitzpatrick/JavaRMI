package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ResultatorIMPL extends UnicastRemoteObject implements Resultator {

    private static final long serialVersionUID = 1L;
    private boolean processed;
    private String result;
    private String str1;
    private String str2;

    ResultatorIMPL() throws RemoteException{
        super();
    }
    
    ResultatorIMPL(String s1, String s2) throws RemoteException{
    	this.str1 = s1;
    	this.str2 = s2;
    }
    
    public String getResult() throws RemoteException {
        return result;
    }
    
    public void setResult(String result) throws RemoteException
    {
        this.result = result;
    }
    
    public boolean isProcessed() throws RemoteException{
        return processed = true;
    }
    
    public void setProcessed() throws RemoteException{
        this.processed = processed;
    }
}