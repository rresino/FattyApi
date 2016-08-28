package com.rresino.api;

import com.rresino.api.com.rresino.api.data.generator.EventGenerator;
import com.rresino.api.com.rresino.api.domain.MyDataSource;

import javax.activation.MimeType;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by rresino on 25/08/2016.
 */
public class FattyServlet extends HttpServlet {

    final private static int FILE_BUFFER_SIZE = 128;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyDataSource dataSource = new MyDataSource(EventGenerator.generateEvents(10000));

        // Set response content type
        response.setContentType("text/html");

        ServletOutputStream out = response.getOutputStream();

        response.setContentType("text/html");
        out.write(("<!DOCTYPE HTML> \n<html>\n  <head>\n    " +
                "<title>Fatty Api</title>\n  </head>\n  <body>\n").getBytes());

        out.write("<ul>".getBytes());
        while (dataSource.hasNext()) {
            out.write(("<li>"+dataSource.next().toString()+"</li>").getBytes());
        }
        out.write("</ul>".getBytes());
        out.write("  </body>\n</html>\n".getBytes());

        // do the following in a finally block:
        out.close();
    }
}
