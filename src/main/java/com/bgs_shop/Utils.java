package com.bgs_shop;

import java.util.Collection;
import java.util.Iterator;



public class Utils {

	 public static String join(Collection<?> s, String delimiter) {
	     StringBuilder builder = new StringBuilder();
	     Iterator<?> iter = s.iterator();
	     while (iter.hasNext()) {
	         builder.append(iter.next());
	         if (!iter.hasNext()) {
	           break;                  
	         }
	         builder.append(delimiter);
	     }
	     return builder.toString();
	 }
	 
	 public static String join(Object[] array, String separator) {
	     
		 if (array.length == 0) return "";
		 
	     StringBuilder buffer = new StringBuilder();
	     
	     buffer.append(array[0]);
	     for (int i = 1; i < array.length; i++) {
	    	 buffer.append(separator);
	    	 buffer.append(array[i]);
	     }
	     return buffer.toString();
	 }
	
}
