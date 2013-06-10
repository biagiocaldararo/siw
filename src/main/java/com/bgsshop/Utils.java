package com.bgsshop;

import java.util.Collection;
import java.util.Iterator;

import com.bgsshop.mvc.NotFoundException;
import com.bgsshop.persistence.DAO;
import com.bgsshop.persistence.DAOFactory;
import com.bgsshop.persistence.PersistenceException;



public class Utils {

//	public static <T> T getObjectOr404(Class<T> clazz, String id) {
//		DAO<T> dao = DAOFactory.getDAOFactory().getDAO(clazz);
//		try {
//			return dao.findOne("id", Long.parseLong(id));
//		} catch (NumberFormatException | PersistenceException e) {
//			e.printStackTrace();
//			throw new NotFoundException("Prodotto inesistente");
//		}
//	}
	
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
