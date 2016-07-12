
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import RestResource.Resource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 * Created by  Boobesh S  on 7/8/2016.
 */
public class MyApplication extends Application<MyConfiguration> {



    @Override
    public void run(MyConfiguration myConfiguration, Environment environment) throws Exception {

        Resource resource =  new Resource();
        environment.jersey().register(resource);
        environment.jersey().register(MultiPartFeature.class);
    }

    public static void main(String args[]) throws Exception {

        new MyApplication().run(args);

    }
}
