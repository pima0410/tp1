package com.sdzee.form;

import javax.servlet.http.HttpServletRequest;

public class FormUtil {
    public static String getParam( HttpServletRequest request, String nomParam ) {
        String temp = request.getParameter( nomParam );

        if ( temp == null || temp.trim().isEmpty() ) {
            return null;
        }
        return temp.trim();

    }
}
