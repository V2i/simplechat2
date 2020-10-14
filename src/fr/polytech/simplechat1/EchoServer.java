package fr.polytech.simplechat1;import java.io.*;import fr.polytech.simplechat1.common.ChatIF;import ocsf.server.*;/** * This class overrides some of the methods in the abstract  * superclass in order to give more functionality to the server. * * @author Dr Timothy C. Lethbridge * @author Dr Robert Lagani&egrave;re * @author Fran&ccedil;ois B&eacute;langer * @author Paul Holden * @version July 2000 */public class EchoServer extends AbstractServer implements ChatIF {  //Class variables *************************************************    /**   * The default port to listen on.   */  final public static int DEFAULT_PORT = 5555;    //Constructors ****************************************************    /**   * Constructs an instance of the echo server.   *   * @param port The port number to connect on.   */  public EchoServer(int port)   {    super(port);  }    //Instance methods ************************************************    /**   * This method handles any messages received from the client.   *   * @param msg The message received from the client.   * @param client The connection from which the message originated.   */  public void handleMessageFromClient(Object msg, ConnectionToClient client) {    System.out.println("Message received: " + msg + " from " + client);    this.sendToAllClients(msg);  }      /**   * This method overrides the one in the superclass.  Called   * when the server starts listening for connections.   */  protected void serverStarted()  {    System.out.println      ("Server listening for connections on port " + getPort());  }    /**   * This method overrides the one in the superclass.  Called   * when the server stops listening for connections.   */  protected void serverStopped()  {    System.out.println      ("Server has stopped listening for connections.");  }    //Class methods ***************************************************    /**   * This method is responsible for the creation of    * the server instance (there is no UI in this phase).   *   */  public static void main(String[] args)   {    int port = 0; //Port to listen on    try {      port = Integer.parseInt(args[0]); //Get port from command line    } catch(Throwable t) {      port = DEFAULT_PORT; //Set port to 5555    }	    EchoServer sv = new EchoServer(port);        try {      sv.listen(); //Start listening for connections    } catch (Exception ex) {      System.out.println("ERROR - Could not listen for clients!");    }  }  protected void clientConnected(ConnectionToClient client) {    System.out.println("SERVEUR > Un client s'est connecte : " + client);  }  synchronized protected  void clientDisconnected(ConnectionToClient client) {    System.out.println("SERVEUR > Un client s'est deconnecte : " + client);  }  @Override  public void display(String message) {    System.out.println("SERVEUR MSG > " + message);  }}//End of EchoServer class