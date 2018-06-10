# RMI String Comparison Service.          

![image](https://user-images.githubusercontent.com/9217947/41128931-2fd0bf68-6ab0-11e8-909f-3d72af79b234.PNG)

4th year Software Development project. The project is a dynamic Web Application using string comparison algorithms to compare two text strings. This is achieved using the Java Remote Method Invocation (RMI) API which provides a mechanism to create distributed applications in java. Java RMI allows a machine (JVM) to invoke methods on another machine (JVM). The first machine (JVM) looks up the method it wants to invoke in the RMI Registry and sends the two strings. The method is invoked on the second machine (JVM) and the two strings are compared and the result returned to the first machine (JVM).

Languages, Technologies and Implementation Architecture used for this project:
Java, [Java RMI](https://docs.oracle.com/javase/tutorial/rmi/), [Java Server Pages](http://www.oracle.com/technetwork/java/index-jsp-138231.html), Eclipse.

## Algorithms Implemented.      

[Hamming Distance][https://en.wikipedia.org/wiki/Hamming_distance]         
[Levenshtein Distance][https://en.wikipedia.org/wiki/Levenshtein_distance]   
[Damerau-Levenshtein Distance][https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance]    

## Getting Started

Download and extract the zip folder here [JavaRMI](https://github.com/seanJosephFitzpatrick/JavaRMI/archive/master.zip) 

### Prerequisites

Eclipse JEE Neon. You can download here [Eclipse JEE Neon](https://www.genuitec.com/eclipse-neon/)       
* **Select** Operating System --> Eclipse IDE for Java EE Developers                    
Java 8. You can download the latest version of Java here [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)   
Apache-tomcat-9.0.0.M13. You can download Apache Tomcat here [Apache Tomcat](https://tomcat.apache.org/download-90.cgi)        
* **Select** version 9 --> Binary Distribution --> zip                              

### Installing

Insall Java JDK/JRE                
Video tutorial of How to Install Java JDK/JRE here [Install Java](https://www.youtube.com/watch?v=FzKcJK68z2k)      

Install Eclipse Neon JEE             
Video tutorial of How to Install Eclipse Neon on Windows 10 here [Install Eclipse Neon JEE](https://www.youtube.com/watch?v=TJ8aGFqI9x0)    

Insall Apache Tomcat               
Video tutorial of How to Install Apache Tomcat on Windows here [Install Apache Tomcat](https://www.youtube.com/watch?v=th2fXIwyw4M) 

## Deployment

There are two methods for deployment. Running the application using the string-service.jar and comparator.war files is the fastest as you just need java and Apache installed.

* **Eclipse**          
Launch Eclipse and set the workspace as the root folder that was extracted from the GitHub project. Select the folder using the Eclipse popup window browse button. Create a new project File tab --> New --> Other --> Web --> Dynamic Web Project and name it **JavaRMI**. This is the name of the folder containing the project, Eclipse will pull in the files into the created project. You can see a video of how to add sever to eclipse project [Here](https://www.youtube.com/watch?v=2kIiSeY71oQ).               
Once the project and server is set up in eclipse, right click on the JavaRMI project --> Properties --> Targeted Runtimes and tick the box for Apache tomcat 9. Click Apply and ok.      

To run the application in Eclipse   
1) right click on Servant.java in the ie.gmit.sw package and select --> **run as --> java application**.           
2) right click on the server in the servers tab and start server.            
3) right click on index.jsp in the webcontent folder and **run on server**.                    

* **Apache Tomcat**         
Place the string-service.jar and comparator.war file into the apche-tomcat/webapps folder. The string-service.jar can be placed in any folder, place in the same folder for convenience. Navigate into the apcha-tomcat/webapps folder using the command line.     
Run the **string-service.jar** with the following command: **java –cp ./string-service.jar ie.gmit.sw.Servant**.     

Navigate into the apache-tomcat/bin folder using a seperate Command Line. Run the comparator.war with the following command **startUp.bat**. 

Open a browser and type **localhost:8080/comparator** into the address bar.          

1) Select Algoritm from the drop-down menu        
2) Enter a text string into each input box         
3) Click the compare button 

## Built With

* [Eclipse JEE Neon](https://www.genuitec.com/eclipse-neon/)  - Eclipse Java EE IDE for Web Developers. Version: Neon.1a Release (4.6.1)
* [Apache Tomcat](https://tomcat.apache.org/download-90.cgi) - Apache Tomcat 9
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - JDK/JRE                    

## Authors

[Sean Fitzpatrick](https://github.com/seanJosephFitzpatrick)            

## License


This project is licensed under the [MIT](https://github.com/seanJosephFitzpatrick/JavaRMI/blob/master/LICENSE) License
