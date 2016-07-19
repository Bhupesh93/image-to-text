package Helpers;


import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;


/**
 * Created by  Boobesh S  on 7/12/2016.
 */
public class RestClient {

    Client client;
    WebTarget webTarget;
    final String baseUri;
    Response clientResponse;
    Logger logger = LoggerFactory.getLogger(RestClient.class);

    public RestClient(String baseUri){
        this.baseUri = baseUri;
        logger.info("RestClient created with following base uri "+baseUri);
        client =  ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
    }

   public  Response getTextFromImage(String imagePath){
       String ocrURL = baseUri +"api/sync/ocrdocument/v1";
       logger.info("file to upload "+imagePath + "\n target url "+ocrURL);
       webTarget = client.target( ocrURL);
       File image = new File(imagePath);
       FormDataMultiPart multiPart = new FormDataMultiPart();
       if(image != null){
           multiPart.bodyPart(new FileDataBodyPart("file",image, MediaType.APPLICATION_OCTET_STREAM_TYPE));
           multiPart.field("apikey","db1b9ac8-434b-40cf-96df-7af8084c7ad1", MediaType.TEXT_PLAIN_TYPE);
       }
       clientResponse = webTarget.request(MediaType.MULTIPART_FORM_DATA_TYPE).post(Entity.entity(multiPart,MediaType.MULTIPART_FORM_DATA_TYPE));
        if(clientResponse.getStatus()>300){
            logger.error(" Bad Response from the Server "+baseUri +" Response : "+clientResponse);
            return clientResponse;
        };
      // System.out.println("Client response "+clientResponse.readEntity(String.class));
       return clientResponse;
   }
}
