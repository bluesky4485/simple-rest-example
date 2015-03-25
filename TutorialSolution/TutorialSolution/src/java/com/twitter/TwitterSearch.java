/**
 * Taken and adapted from
 * http://stackoverflow.com/questions/13545936/twitter4j-search-for-public-tweets
 */
package com.twitter;

import com.rest.RestConnection;
import com.rest.RestResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jian
 */
public class TwitterSearch {

    public static final String CONSUMER_KEY = "blPSTGe1ExI8dJDNrxoBZwYgB";
    public static final String CONSUMER_SECRET = "aGXpwaKLBzTyTh1SIx0147XtylNXAda7r8aNHGNCO1oqa2PgkW";
    public static final String OAUTH_TOKEN = "2841280556-EmyXKRefbzWO691Uyv3Ib3yZeqnRKnW66C7r1UF";
    public static final String OAUTH_TOKEN_SECRET = "7lDAtfHBReIEPsviVTrrqhFFXQKnRZB71665qgjdsxCaC";

    public static ArrayList<Status> configure(String query) {

        ArrayList<Status> results = new ArrayList<>();
        try {
            OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY,
                    CONSUMER_SECRET);
            consumer.setTokenWithSecret(OAUTH_TOKEN, OAUTH_TOKEN_SECRET);
            //need to get the value for query variable from the user
            query = query.replaceAll(" ", "%20");
            String URL = "https://api.twitter.com/1.1/search/tweets.json?q=" + query;
            System.out.println("--->" + consumer.sign(URL));
            String[][] pathParams = new String[][]{};
            String[][] queryParams = new String[][]{};
            RestConnection conn = new RestConnection(consumer.sign(URL), pathParams,
                    queryParams);
            RestResponse response = conn.get();
            String strResponse = response.getDataAsString();
            JSONObject json = new JSONObject(strResponse);
            JSONArray arrResults = json.getJSONArray("statuses");
            // Iterate the array lol
            for (int i = 0 ; i < arrResults.length(); i++){
                JSONObject temp = arrResults.getJSONObject(i);
                JSONObject userObj = temp.getJSONObject("user");
                String username = userObj.getString("screen_name");
                results.add( new Status(temp.getString("created_at"),username,temp.getString("text")));
            }
            return results;
        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException | IOException | JSONException ex) {
        }
        return results;
    }
}
