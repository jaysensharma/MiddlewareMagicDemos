package client;
import java.io.*;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
 
public class RestEasyClient
{
      public static void main(String ar[]) throws Exception
       {
        String url="http://localhost:8080/RestServiceWithCDI/test/customers/all";
        MediaType mediaType=MediaType.APPLICATION_XML_TYPE;
        String result = "";
 
        try
        {
            ClientRequest request = new ClientRequest(url);
            request.accept(mediaType);
            ClientResponse<String> response = request.get(String.class);
            if (response.getStatus() != 200)
            {
                System.out.println("***** BAD RESPONSE *****");
                throw new RuntimeException("Request Processing Failed with HTTP status: "+ response.getStatus());
            }
 
            BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
            String output = null;
            while ((output = br.readLine()) != null)
            {
                result = result+output+"\n";
            }

            result=result.replaceAll("><",">\n<");
            System.out.println("\n\n********** RESULT **********\n"+result);
        }
        catch (Exception e)
        {
            System.err.println("\n\t Exception Occured: "+e);
            e.printStackTrace();
        }
    }
 
}
