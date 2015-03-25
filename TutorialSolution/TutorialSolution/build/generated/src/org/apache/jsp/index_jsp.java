package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.net.URLEncoder;
import com.controller.SearchController;
import com.twitter.TwitterSearch;
import com.youtube.YouTubeAPI;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootflat.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/star.css\">\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css\">\n");
      out.write("        <title>FIT5046 - Tutorial Solutions</title>\n");
      out.write("    </head>\n");
      out.write("    <body style=\"background-color: rgb(241, 242, 246);\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <h1 class=\"text-center\">Google Custom Search</h1>\n");
      out.write("            <div class=\"well well-sm\">\n");
      out.write("                <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/index.jsp\" method=\"post\">\n");
      out.write("                    <div class=\"input-group form-search\">\n");
      out.write("                        <input class=\"form-control search-query\" type=\"text\" placeholder=\"Please enter search term\" name=\"submit\" value=\"\" onsubmit=\"\" autofocus> \n");
      out.write("                        <span class=\"input-group-btn\">\n");
      out.write("                            <button type=\"submit\" name=\"submit\" class=\"btn btn-primary\" data-type=\"last\">Search</button>\n");
      out.write("                        </span>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"result\"></div>\n");
      out.write("            ");
 String search = (String) request.getParameter("submit");
                if (request.getParameter("submit") != null) {
                    String query = URLEncoder.encode(search, "UTF-8");
                    //String query = search.replaceAll(" ", "%20");
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
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
