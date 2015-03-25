<%--*
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
--> %>
<%-- 
    Document   : index
    Created on : Mar 16, 2015, 8:06:32 PM
    Author     : Jian
--%>
<%@page import="com.controller.SearchController"%>
<%@page import="com.twitter.TwitterSearch"%>
<%@page import="com.youtube.YouTubeAPI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/bootflat.min.css">
        <link rel="stylesheet" type="text/css" href="css/star.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <title>FIT5046 - Tutorial Solutions</title>
    </head>
    <body style="background-color: rgb(241, 242, 246);">
        <div class="container">
            <h1 class="text-center">Google Custom Search</h1>
            <div class="well well-sm">
                <form action="${pageContext.request.contextPath}/index.jsp" method="post">
                    <div class="input-group form-search">
                        <input class="form-control search-query" type="text" placeholder="Please enter search term" name="submit" value="" onsubmit="" autofocus> 
                        <span class="input-group-btn">
                            <button type="submit" name="submit" class="btn btn-primary" data-type="last">Search</button>
                        </span>
                    </div>

                </form>
            </div>
            <div class="result"></div>
            <% String search = (String) request.getParameter("submit");
                if (request.getParameter("submit") != null) {
                    String query = search.replaceAll(" ", "%20");
                    out.println("<div class=\"well well-lg\">");
                    out.println(SearchController.displayGoogleSearch(query));
                    out.println("</div>");
                    out.println("<div class=\"well well-lg\">");
                    out.println(SearchController.displayTweets(query));
                    out.println("</div>");
                    out.println("<div class=\"well well-lg\">");
                    out.println(SearchController.displayYouTube(query));
                    out.println("</div>");
                    
                }
            %>
        </div>
    </body>
</html>
