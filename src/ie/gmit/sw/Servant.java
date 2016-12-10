package ie.gmit.sw;

//import ie.gmit.sw;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;;

public class Servant {

    public static void main(String[] args) throws Exception {

        StringService ss = new StringServiceImpl();
        
        //System.setProperty("java.rmi.server.hostname","localhost");

        //Start the RMI regstry on port 1099
        LocateRegistry.createRegistry(1099);

        //Bind our remote object to the registry with the human-readable name "MyStringCompareService"
        Naming.rebind("StringService", ss);

        //Print a nice message to standard output
        System.out.println("listening on port 1099.");
    }
}