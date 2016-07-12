package RestResource;


import Helpers.FileHelper;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

/**
 * Created by  Boobesh S  on 7/8/2016.
 */
@Path("/")
public class Resource {

    String tempDirectory = null;
    Logger logger = LoggerFactory.getLogger(Resource.class);
    public Resource(){
        tempDirectory = System.getProperty("user.dir")+"\\"+"temp";
        File tempDir = new File(tempDirectory);
        if(!tempDir.exists()){
            logger.info("Temp directory does not exist . Creating temp directory ....");
            tempDir.mkdir();
        }
        logger.info("Uploaded files will be stored in this directory "+ tempDirectory);
    }

    @Path("/upload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(@FormDataParam("file")InputStream fileInputStream, @FormDataParam("file")FormDataContentDisposition fileMetaData) throws IOException {
        logger.debug("upload called with this file " +fileMetaData.getFileName());
        String uploadedPath = tempDirectory + "\\" +fileMetaData.getFileName();
        FileHelper.saveFileToLocation(fileInputStream,uploadedPath);
        return Response.ok().status(Response.Status.ACCEPTED).build();
    }

}
