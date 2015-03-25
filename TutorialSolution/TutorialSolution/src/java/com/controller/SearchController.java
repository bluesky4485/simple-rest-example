/*
 * The MIT License
 *
 * Copyright 2015 Jian.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.controller;

import com.googlesearch.GoogleResult;
import com.googlesearch.GoogleSearch;
import com.twitter.Status;
import com.twitter.TwitterSearch;
import com.youtube.YouTubeAPI;
import com.youtube.YouTubeInfo;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONException;

/**
 * This search controller is just to parse all the returned files and display
 * them at the intended location.
 *
 * @author Jian
 */
public class SearchController {

    public static String displayGoogleSearch(String query) throws JSONException {
        String str = "<h3 class=\"text-center\"><i class=\"fa fa-google\">&nbsp;</i>Google Custom Search</h3>";
        ArrayList<GoogleResult> results = GoogleSearch.configure(query);

        if (results.isEmpty()) {
            str += "<div class=\"alert alert-info\">No search results found.</div>";
            return str;
        }
        if (results.size() > 5) {
            results.subList(5, results.size()).clear();
        }
        str += "<div class=\table-responsive\">";
        str += "<table class=\"table table-striped\">";
        str += "<tr><th>Title</th><th>Snippet</th><th>Link</th>";
        str = results.stream().map((result) -> "<tr><td>" + result.getTitle() + "</td><td>" + result.getSnippet() + "</td><td>" + result.getLink() + "</td></tr>").reduce(str, String::concat);
        str += "</table>";
        return str;
    }

    public static String displayYouTube(String query) {
        ArrayList<YouTubeInfo> results = YouTubeAPI.configure(query);
        String str = " <h3 class=\"text-center\"><i class=\"fa fa-youtube-square\">&nbsp;</i>Youtube Video</h3>";
        if (results.isEmpty()) {
            str += "<div class=\"alert alert-info\">No search results found.</div>";
            return str;
        }
        for (YouTubeInfo status : results) {
            str += "<div class=\"embed-responsive embed-responsive-16by9\">";
            str += "<iframe class=\"embed-responsive-item\" src=https://www.youtube.com/embed/" + status.getId() + "></iframe>";
            str += "</div><div class=\"text-center\">";
            str += "<strong>" + status.getTitle() + "&nbsp;</strong><br /><br /></div>";
        }
        return str;
    }

    public static String displayTweets(String query) {
        StringBuilder sb = new StringBuilder();
        sb.append(" <h3 class=\"text-center\"><i class=\"fa fa-twitter-square\"></i>&nbsp;Comments from Twitter</h3>");
        List<Status> tweet = TwitterSearch.configure(query);
        if (tweet.isEmpty()) {
            sb.append("<div class=\"alert alert-info\">No Tweets results found.</div>");
            return sb.toString();
        }

        // Reduce the number of tweets via jsp
        if (tweet.size() > 5) {
            tweet.subList(5, tweet.size()).clear();
        }

        sb.append("<div class=\"table-responsive\">");
        sb.append("<table class=\"table table-striped\">");
        sb.append("<tr><th>Created At</th><th>User</th><th>Text</th></tr>");
        for (Status status : tweet) {
            sb.append("<tr><td>");
            sb.append(status.getCreatedAt());
            sb.append("</td><td>");
            sb.append(status.getUserName());
            sb.append("</td><td>");
            sb.append(status.getText());
            sb.append("</td></tr>");
        }
        sb.append("</table></div>");
        return sb.toString();
    }

}
