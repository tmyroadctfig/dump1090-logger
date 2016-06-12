package tmyroadctfig.dump1090_logger;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

/**
 * The main class.
 */
public class Main
{
    /**
     * The logger.
     */
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Usage:");
            System.err.println("        dump1090_logger sourceUrl [-loop]");
            System.err.println("   e.g. dump1090_logger http://localhost:8080/dump1090/data.json");
        }

        String sourceUrl = args[0];

        boolean loop = args.length > 1 && "-loop".equals(args[1]);

        while (loop)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                // Ignore
            }

            try (CloseableHttpClient httpclient = HttpClientBuilder.create().build())
            {
                HttpGet httpget = new HttpGet(sourceUrl);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String responseBody = httpclient.execute(httpget, responseHandler);

                Gson gson = new Gson();
                List<AircraftInfo> aircraftInfoList = Arrays.asList(gson.fromJson(responseBody, AircraftInfo[].class));

                for (AircraftInfo aircraftInfo : aircraftInfoList)
                {
                    logger.info("Aircraft: " + aircraftInfo);
                }
            }
            catch (Exception e)
            {
                logger.error("Unexpected error", e);
            }
        }
    }
}
