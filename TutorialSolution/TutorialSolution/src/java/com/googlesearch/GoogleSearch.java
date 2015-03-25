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
package com.googlesearch;

import com.rest.RestConnection;
import com.rest.RestResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jian
 */
public class GoogleSearch {

    public static final String API_KEY = "AIzaSyC1R8xtoh4ePsPjomjS34EheQkz1PpA0ME";
    public static final String SEARCH_ID_cx = "016016692328025561500:ql40nyq7tu0";

    public static ArrayList<GoogleResult> configure(String query) {
        ArrayList<GoogleResult> results = new ArrayList<>();
        try {
            RestConnection conn = new RestConnection("https://www.googleapis.com/customsearch/v1?key=" + API_KEY
                    + "&cx=" + SEARCH_ID_cx + "&q=" + query.replace(" ", "%20"));
            RestResponse response;
            response = conn.get();
            JSONObject json = new JSONObject(response.getDataAsString());
            JSONArray items = json.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject result = items.getJSONObject(i);
                results.add(new GoogleResult(result.getString("title"), result.getString("snippet"), result.getString("link")));
            }
            return results;
        } catch (JSONException | IOException ex) {
        }
        return results;
    }
}
