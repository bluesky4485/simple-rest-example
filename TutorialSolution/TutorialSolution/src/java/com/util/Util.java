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
package com.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Put utils here.
 *
 * @author Jian
 */
public class Util {

    public static String generateHeaders(Object obj) {
        int length = obj.getClass().getFields().length;
        Field[] fieldList = obj.getClass().getClass().getFields();
        String str = new String();
        str += "<th>";
        for (int i = 0; i < length; i++) {
            str += "<td>" + fieldList[i] + "</td>";
        }
        str += "</th>";
        return str;
    }

    public static String tabulate(ArrayList<Object> obj) {
        
        ArrayList<Method> methods = new ArrayList<>();

        try {
            // Iterate through the real values by calling getters or to string.
            int length = obj.size(); 
            // Since we know all the object would be the same.
            for (PropertyDescriptor propertyDescriptor
                    : Introspector.getBeanInfo(obj.get(0).getClass(),Object.class).getPropertyDescriptors()) {
                methods.add(propertyDescriptor.getReadMethod());
            }
            
            //Now call each getter method lol
            for(int i = 0 ; i < length; i++){
                
            }
            
            return "";
        } catch (IntrospectionException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
