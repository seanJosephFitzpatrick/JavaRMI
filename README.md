#An Asynchronous RMI String Comparison Service. 


##Sean Fitzpatrick

##Introduction
A Java RMI [Remote Method Invocation] [https://en.wikipedia.org/wiki/Java_remote_method_invocation] and JSP [Java Server Pages] [https://en.wikipedia.org/wiki/JavaServer_Pages] Dynamic Web Application using string comparison algorithms to compare two text strings.


##Running the Program

###Set up the Service
```
Run the "string-service.jar" with the following command: java –cp ./string-service.jar ie.gmit.sw.Servant          
```

###Set up Web Application
```
Apache-tomcat-9.0.0.M13 was used for the development of this application.     
Place the "comparator.war" file into the apache-tomcat/webapps folder.        
cd into the apache-tomcat/bin and run the "startUp.bat" file.         
```

##User Guide
1) Select Algoritm from the drop-down menu        
2) Enter a text string into each input box         
3) Click the compare button           



#Algorithms Implemented.
[Hamming Distance][https://en.wikipedia.org/wiki/Hamming_distance]        
[Levenshtein Distance][https://en.wikipedia.org/wiki/Levenshtein_distance]         
[Damerau-Levenshtein Distance][https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance] 





