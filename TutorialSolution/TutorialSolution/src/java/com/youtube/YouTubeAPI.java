/**
 * *************************************************************************************
 * Title: youtube-api-samples Author: YouTube API Group Date: 2013 Availability:
 * https://code.google.com/p/youtube-api-samples/source/browse/#git%2Fsamples%2Fjava
 * https://developers.google.com/youtube/v3/docs/search/list
 * http://stackoverflow.com/questions/3732682/how-to-specify-movie-trailers-using-youtube-api
**************************************************************************************
 */
package com.youtube;

import java.util.Iterator;
import java.util.List;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author WUT
 */
public class YouTubeAPI {

    private static final String DEV_KEY = "AIzaSyC1R8xtoh4ePsPjomjS34EheQkz1PpA0ME";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final long NUMBER_OF_VIDEOS_RETURNED = 1;
    private static YouTube youtube;

    /**
     * Initializes YouTube object to search for videos on YouTube
     * (Youtube.Search.List). The program then prints the names and thumbnails
     * of each of the videos (only first 50 videos).
     *
     * @param usrQuery
     * @return
     */
    public static ArrayList<YouTubeInfo> configure(String usrQuery) {
        ArrayList<YouTubeInfo> results = new ArrayList<>();
        try {
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            })
                    .setApplicationName("fit5046-ass1")
                    .build();
            YouTube.Search.List search = youtube.search().list("id,snippet");
            search.setKey(DEV_KEY);
            search.setQ(usrQuery + "+trailer");
            search.setType("video");
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                return processResult(searchResultList.iterator(), usrQuery);
            }
        } catch (GoogleJsonResponseException e) {
            Logger.getLogger(YouTubeAPI.class.getName()).info(e.toString());
        } catch (IOException e) {
            Logger.getLogger(YouTubeAPI.class.getName()).info(e.toString());
        } catch (Throwable t) {
            Logger.getLogger(YouTubeAPI.class.getName()).info(t.toString());
        }
        return results;
    }

    private static ArrayList<YouTubeInfo> processResult(Iterator<SearchResult> iteratorSearchResults, String query) {
        ArrayList<YouTubeInfo> results = new ArrayList<>();
        if (!iteratorSearchResults.hasNext()) {
            return results;
        }
        while (iteratorSearchResults.hasNext()) {
            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                results.add(new YouTubeInfo(rId.getVideoId(), singleVideo.getSnippet().getTitle(), thumbnail.getUrl()));
            }
        }
        return results;
    }
}
