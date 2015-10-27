package spms.bind;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Set;

import javax.servlet.ServletRequest;

public class ServletrequestDataBinder {

	public static Object bind( ServletRequest request, Class<?> dataType, String dataName) throws Exception  {
		System.out.println("[ServletrequestDataBinder]	bind ");
		if(isPrimitiveType(dataType)){
			return createValueObject(dataType, request.getParameter(dataName));
		}
		
		Set<String> paramNames = request.getParameterMap().keySet();
		Object obj = dataType.newInstance();
		Method m = null;
		
		
		for (String paramName : paramNames) {
			System.out.println("dataType : " + dataType + " , paramName : " + paramName);
			m = findSetter(dataType, paramName);
			if(m != null){
				m.invoke(obj, createValueObject(m.getParameterTypes()[0], request.getParameter(paramName)));
			}
		}
		return obj;
		
	}
	
	private static boolean isPrimitiveType (Class<?> dataType){
		
		if(dataType.getName().equals("int") || dataType == Integer.class ||
		   dataType.getName().equals("long") || dataType == Long.class ||
		   dataType.getName().equals("float") || dataType == Float.class ||
		   dataType.getName().equals("double") || dataType == Double.class ||
		   dataType.getName().equals("boolean") || dataType == Boolean.class || 
		   dataType == Date.class || dataType == String.class){
			System.out.println("[ServletrequestDataBinder]	isPrimitiveType -> return true");
			return true;
		} else {
			System.out.println("[ServletrequestDataBinder]	isPrimitiveType -> return false");
			return false;
		}
	}
	
	private static Object createValueObject (Class<?> dataType, String value) throws Exception {
		if(dataType.getName().equals("int") || dataType == Integer.class){
			return new Integer(value);
		} else if(dataType.getName().equals("float") || dataType == Float.class){
			return new Float(value);
		} else if(dataType.getName().equals("double") || dataType == Double.class){
			return new Double(value);
		} else if(dataType.getName().equals("long") || dataType == Long.class){
			return new Long(value);
		} else if(dataType.getName().equals("boolean") || dataType == Boolean.class){
			return new Boolean(value);
		} else if(dataType == Date.class){
			return java.sql.Date.valueOf(value);
		} else {
			return value;
		}
	}
	
	private static Method findSetter(Class<?> dataType, String name) {
		Method[] methods = dataType.getMethods();
		
		String propName = "";
		for (Method m : methods) {
			if(!m.getName().startsWith("set")){continue;}
			propName = m.getName().substring(3);
			if(propName.toLowerCase().equals(name.toLowerCase())){
				return m;
			}
		}
		return null;
	}
	
}
