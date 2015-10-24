import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URISyntaxException;


public class TestNode {
	private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
	
	public static void main( String[] args ) throws URISyntaxException
    {
        //checkDatabaseIsRunning();
        
        RetriveMoviesForGivenYear( "MATCH (n)-[r:RELATED_TO]-(n1) WHERE  has(n.movie) and n.year >= "+ args[0]+" and n.year<= "+args[1]+" and not(has(n1.movie)) return n as Movie,r as Relationship,n1 as Actor" );
        SearchForGivenActor("MATCH (n)-[r:RELATED_TO]-(n1) WHERE  not(has(n.movie)) and n.name = '"+args[2]+"'  return n as ActorName,r as Relationship,n1 as MoviesforActor");
        SearchForGivenGenre("MATCH (n)-[r:RELATED_TO]-(n1) WHERE  has(n.movie) and n.genre = '"+args[3]+"' and not(has(n1.movie)) return n as Movie,r as Relationship,n1 as Actor");
    }
	 private static void checkDatabaseIsRunning()
	    {
	        // START SNIPPET: checkServer
	        WebResource resource = Client.create()
	                .resource( SERVER_ROOT_URI );
	        ClientResponse response = resource.get( ClientResponse.class );

	        System.out.println( String.format( "GET on [%s], status code [%d]",
	                SERVER_ROOT_URI, response.getStatus() ) );
	        response.close();
	        // END SNIPPET: checkServer
	        
	    }
	 private static void RetriveMoviesForGivenYear(String query) {
	        // START SNIPPET: queryAllNodes
	        final String txUri = SERVER_ROOT_URI + "transaction/commit";
	        WebResource resource = Client.create().resource( txUri );

	        String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
	        ClientResponse response = resource
	                .accept(MediaType.APPLICATION_JSON)
	                .type( MediaType.APPLICATION_JSON )
	                .entity( payload )
	                .post( ClientResponse.class );
	        
	        System.out.println( String.format(
	                "POST [%s] to [%s], status code [%d], returned data: "
	                        + System.lineSeparator() + "%s",
	                payload, txUri, response.getStatus(),
	                response.getEntity( String.class ) ) );
	        
	        response.close();
	        // END SNIPPET: queryAllNodes
	    }
	 private static void SearchForGivenActor(String query) {
	        // START SNIPPET: queryAllNodes
	        final String txUri = SERVER_ROOT_URI + "transaction/commit";
	        WebResource resource = Client.create().resource( txUri );

	        String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
	        ClientResponse response = resource
	                .accept(MediaType.APPLICATION_JSON)
	                .type( MediaType.APPLICATION_JSON )
	                .entity( payload )
	                .post( ClientResponse.class );
	        
	        System.out.println( String.format(
	                "POST [%s] to [%s], status code [%d], returned data: "
	                        + System.lineSeparator() + "%s",
	                payload, txUri, response.getStatus(),
	                response.getEntity( String.class ) ) );
	        
	        response.close();
	        // END SNIPPET: queryAllNodes
	    }
	 private static void SearchForGivenGenre(String query) {
	        // START SNIPPET: queryAllNodes
	        final String txUri = SERVER_ROOT_URI + "transaction/commit";
	        WebResource resource = Client.create().resource( txUri );

	        String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
	        ClientResponse response = resource
	                .accept(MediaType.APPLICATION_JSON)
	                .type( MediaType.APPLICATION_JSON )
	                .entity( payload )
	                .post( ClientResponse.class );
	        
	        System.out.println( String.format(
	                "POST [%s] to [%s], status code [%d], returned data: "
	                        + System.lineSeparator() + "%s",
	                payload, txUri, response.getStatus(),
	                response.getEntity( String.class ) ) );
	        
	        response.close();
	        // END SNIPPET: queryAllNodes
	    }
}
