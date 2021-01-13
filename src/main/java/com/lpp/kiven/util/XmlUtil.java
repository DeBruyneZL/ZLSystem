package com.lpp.kiven.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * XML工具类
 * @author suyunlong
 *
 */
public class XmlUtil {
	/**
	 * 解析XML转换为Object
	 * @param strXML xml字符串
	 * @param elementName 解析根标签名
	 * @param className 类名全路径（包名+类名）
	 * @return
	 */
	public static List<Object> parseObject(String strXML,String elementName,String className){
		List<Object> list=new ArrayList<Object>();
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=null;
		try{
			builder=factory.newDocumentBuilder();
			Document doc=builder.parse(new ByteArrayInputStream(strXML.getBytes("utf-8"))); 
			NodeList nodelist=doc.getElementsByTagName(elementName);
			for(int i=0;i<nodelist.getLength();i++){
				Node node=nodelist.item(i);
				NodeList chlist=node.getChildNodes();
				Object bean=Class.forName(className).newInstance();
				Class<?> cls=bean.getClass();
		    	Method methods[]=cls.getDeclaredMethods();
		    	Field fields[]=cls.getDeclaredFields();
				for(int j=0;j<chlist.getLength();j++)
				{
					Node chnode=chlist.item(j);
					if(chnode instanceof Element)
					{
						String nodeName=chnode.getNodeName();
						System.out.println(nodeName+","+chnode.getTextContent());
						if (nodeName.indexOf(":")!= -1) {
							nodeName=nodeName.split(":")[1];
						}
						System.out.println(nodeName+","+chnode.getTextContent());
						for(Field field:fields)
			    		{
							String fieldName=field.getName();
							if(fieldName.equals(nodeName)){
								String fldtype=field.getType().getSimpleName();
				    			String setMethod=pareSetName(fieldName);
				    			if(!checkMethod(methods,setMethod))
				    			{
				    				continue;
				    			}
				    			Object value=chnode.getTextContent();
				    			Method method=cls.getMethod(setMethod,field.getType());
				    			if(null != value)
				    			{
				    				if("String".equals(fldtype))
				    				{
				    					method.invoke(bean,value.toString());  
				                    }
				    				else if("Date".equals(fldtype))
				    				{
				    					Date temp=parseDate(value.toString());
				    					method.invoke(bean,temp);
				                    }
				    				else if("Integer".equals(fldtype) || "int".equals(fldtype))
				    				{
				    					Integer intval=Integer.parseInt(value.toString());
				    					method.invoke(bean,intval);  
				                    }
				    				else if("Long".equalsIgnoreCase(fldtype))
				    				{
				    					Long temp=Long.parseLong(value.toString());
				    					method.invoke(bean,temp);
				    				}
				    				else if(fldtype.equalsIgnoreCase("Float"))
				    				{
				    					Float f=Float.parseFloat(value.toString());
				    					method.invoke(bean,f);
				    				}
				    				else if("Double".equalsIgnoreCase(fldtype))
				    				{
				    					Double temp=Double.parseDouble(value.toString());
				    					method.invoke(bean,temp);  
				                    }
				    				else if("Boolean".equalsIgnoreCase(fldtype))
				    				{
				    					Boolean temp=Boolean.parseBoolean(value.toString());
				    					method.invoke(bean,temp);
				    				}
				    				else 
				    				{
				    					System.out.println("not supper type"+fldtype);  
				                    }  
				    			}
				    			break;
							}
			    		}
					}
				}
				list.add(bean);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	/**  
     * 拼接某属性set 方法  
     * @param fldname  
     * @return  
     */
    public static String pareSetName(String fldname)
    {
    	if(null==fldname || "".equals(fldname))
    	{
    		return null;  
        }
        String pro="set"+fldname.substring(0,1).toUpperCase()+fldname.substring(1);
        return pro;
    }
    /**  
     * 判断该方法是否存在  
     * @param methods  
     * @param met  
     * @return  
     */
    public static boolean checkMethod(Method methods[],String met)
    {
    	if(null != methods)
    	{
    		for(Method method:methods)
    		{
    			if(met.equals(method.getName()))
    			{
    				return true;  
                }
            }
        }
    	return false;
    }
    /** 
     * 格式化string为Date 
     * @param datestr 
     * @return date 
     */
    public static Date parseDate(String datestr)
    {
    	if(null==datestr || "".equals(datestr))
    	{  
            return null;  
        }  
        try
        {
        	String fmtstr=null;  
            if(datestr.indexOf(':')>0)
            {
            	fmtstr="yyyy-MM-dd HH:mm:ss";  
            }
            else
            {
            	fmtstr="yyyy-MM-dd";
            }
            SimpleDateFormat sdf=new SimpleDateFormat(fmtstr);
            return sdf.parse(datestr);
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        	return null;  
        }  
    }
}
