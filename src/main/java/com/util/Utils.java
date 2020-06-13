package com.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrTokenizer;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Utils {
	
	private static Random random = new Random();
	private static String defaultLang = null;

	public static boolean str2bool(String s)
	{
		boolean flag = false;
		try
		{
			if (!"".equals(s))
				flag = Boolean.valueOf(s).booleanValue();
		}
		catch (Exception exception)
		{
			flag = false;
		}
		return flag;
	}

	public static String[] TokenizerStringNew(String s, String s1)
	{
		return (new StrTokenizer(s, s1)).getTokenArray();
	}

	public static ArrayList TokenizerString(String s, String s1)
	{
		return TokenizerString(s, s1, false);
	}

	public static ArrayList getSplitString(String as[], ArrayList arraylist)
	{
		int i = 1;
		String s = "";
		for (int j = 0; j < as.length; j++)
		{
			if (i == 1)
				s = as[j];
			else
				s = (new StringBuilder()).append(s).append(",").append(as[j]).toString();
			if (++i >= 999)
			{
				i = 1;
				arraylist.add(s);
				s = "";
			}
		}

		if (!"".equals(s))
		{
			boolean flag = true;
			arraylist.add(s);
			s = "";
		}
		return arraylist;
	}

	public static String toDecimalDigits(String aNumber,int precision)
	{
		String returnVal = aNumber;
		//判断数字是不是科学计数法，如果是需要对数据进行处理   QC#785941
		if(aNumber.contains("E")){
			BigDecimal db = new BigDecimal(returnVal);
			String tempValue = db.toPlainString();
			aNumber = tempValue;
			returnVal = tempValue;
		}
		try{
			if(null == aNumber || aNumber.equals("")){
				return  "";
			}
			int valInt = 0;
			if(aNumber.indexOf(".")>-1){
				String[] val = aNumber.split("\\.");
				valInt = val[val.length-1].length();
			}
			if(valInt != precision){
				if(valInt == 0){
					if(precision == 1){
						returnVal += ".0";
					}else if(precision == 2){
						returnVal += ".00";
					}else if(precision == 3){
						returnVal += ".000";
					}else if(precision == 4){
						returnVal += ".0000";
					}else if(precision == 5){
						returnVal += ".00000";
					}else if(precision == 6){
						returnVal += ".000000";
					}else if(precision == 7){
						returnVal += ".0000000";
					}else if(precision == 8){
						returnVal += ".00000000";
					}else if(precision == 9){
						returnVal += ".000000000";
					}else if(precision == 10){
						returnVal += ".0000000000";
					}else if(precision == 11){
						returnVal += ".00000000000";
					}else if(precision == 12){
						returnVal += ".000000000000";
					}else if(precision == 13){
						returnVal += ".0000000000000";
					}else if(precision == 14){
						returnVal += ".00000000000000";
					}else if(precision == 15){
						returnVal += ".000000000000000";
					}
				}else{
					int lengInt = precision-valInt;
					//判断添加小数位0的个数
					if(lengInt == 1 ){
						returnVal += "0";
					}else if(lengInt == 2){
						returnVal += "00";
					}else if(lengInt == 3){
						returnVal += "000";
					}else if(lengInt == 4){
						returnVal += "0000";
					}else if(lengInt == 5){
						returnVal += "00000";
					}else if(lengInt == 6){
						returnVal += "000000";
					}else if(lengInt == 7){
						returnVal += "0000000";
					}else if(lengInt == 8){
						returnVal += "00000000";
					}else if(lengInt == 9){
						returnVal += "000000000";
					}else if(lengInt == 10){
						returnVal += "0000000000";
					}else if(lengInt == 11){
						returnVal += "00000000000";
					}else if(lengInt == 12){
						returnVal += "000000000000";
					}else if(lengInt == 13){
						returnVal += "0000000000000";
					}else if(lengInt == 14){
						returnVal += "00000000000000";
					}else if(lengInt < 0){
                    /*if(precision == 1){
                        returnVal += ".0";
                    }else if(precision == 2){
                        returnVal += ".00";
                    }else if(precision == 3){
                        returnVal += ".000";
                    }else if(precision == 4){
                        returnVal += ".0000";
                    }  */
						//liuzy 上述做法会将123.45678转换成123.45678.00,算法改为四舍五入
						BigDecimal bd=new BigDecimal(returnVal);
						bd=bd.setScale(precision,BigDecimal.ROUND_HALF_UP);
						returnVal = bd.toString();
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return  returnVal;
	}
	
	public static BigDecimal toDecimal(String aNumber,int precision)
	{
		String returnVal = aNumber;
		//判断数字是不是科学计数法，如果是需要对数据进行处理   QC#785941
		if(aNumber.contains("E")){
			BigDecimal db = new BigDecimal(returnVal);
			String tempValue = db.toPlainString();
			aNumber = tempValue;
			returnVal = tempValue;
		}
		try{
			if(null == aNumber || aNumber.equals("")){
				return  BigDecimal.valueOf(Float.parseFloat("0"));
			}
			int valInt = 0;
			if(aNumber.indexOf(".")>-1){
				String[] val = aNumber.split("\\.");
				valInt = val[val.length-1].length();
			}
			if(valInt != precision){
				if(valInt == 0){
					if(precision == 1){
						returnVal += ".0";
					}else if(precision == 2){
						returnVal += ".00";
					}else if(precision == 3){
						returnVal += ".000";
					}else if(precision == 4){
						returnVal += ".0000";
					}else if(precision == 5){
						returnVal += ".00000";
					}else if(precision == 6){
						returnVal += ".000000";
					}else if(precision == 7){
						returnVal += ".0000000";
					}else if(precision == 8){
						returnVal += ".00000000";
					}else if(precision == 9){
						returnVal += ".000000000";
					}else if(precision == 10){
						returnVal += ".0000000000";
					}else if(precision == 11){
						returnVal += ".00000000000";
					}else if(precision == 12){
						returnVal += ".000000000000";
					}else if(precision == 13){
						returnVal += ".0000000000000";
					}else if(precision == 14){
						returnVal += ".00000000000000";
					}else if(precision == 15){
						returnVal += ".000000000000000";
					}
				}else{
					int lengInt = precision-valInt;
					//判断添加小数位0的个数
					if(lengInt == 1 ){
						returnVal += "0";
					}else if(lengInt == 2){
						returnVal += "00";
					}else if(lengInt == 3){
						returnVal += "000";
					}else if(lengInt == 4){
						returnVal += "0000";
					}else if(lengInt == 5){
						returnVal += "00000";
					}else if(lengInt == 6){
						returnVal += "000000";
					}else if(lengInt == 7){
						returnVal += "0000000";
					}else if(lengInt == 8){
						returnVal += "00000000";
					}else if(lengInt == 9){
						returnVal += "000000000";
					}else if(lengInt == 10){
						returnVal += "0000000000";
					}else if(lengInt == 11){
						returnVal += "00000000000";
					}else if(lengInt == 12){
						returnVal += "000000000000";
					}else if(lengInt == 13){
						returnVal += "0000000000000";
					}else if(lengInt == 14){
						returnVal += "00000000000000";
					}else if(lengInt < 0){
						BigDecimal bd=new BigDecimal(returnVal);
						bd=bd.setScale(precision,BigDecimal.ROUND_HALF_UP);
						return bd;
					}
				}
				BigDecimal bd=new BigDecimal(returnVal);
				bd=bd.setScale(precision,BigDecimal.ROUND_HALF_UP);
				return bd;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return  BigDecimal.valueOf(Float.parseFloat("0"));
	}

	public static ArrayList TokenizerString(String s, String s1, boolean flag)
	{
		s = null2String(s);
		s1 = null2String(s1);
		ArrayList arraylist = new ArrayList();
		for (StringTokenizer stringtokenizer = new StringTokenizer(s, s1, flag); stringtokenizer.hasMoreTokens(); arraylist.add(stringtokenizer.nextToken()));
		return arraylist;
	}

	public static char getSeparator_temp()
	{
		return '\003';
	}

	public static String[] TokenizerString2(String s, String s1)
	{
		return TokenizerString2(s, s1, false);
	}

	public static String[] TokenizerString2(String s, String s1, boolean flag)
	{
		ArrayList arraylist = TokenizerString(s, s1, flag);
		int i = arraylist.size();
		String as[] = new String[i];
		for (int j = 0; j < i; j++)
			as[j] = (String)arraylist.get(j);

		return as;
	}

	public static String add0(int i, int j)
	{
		long l = (long)Math.pow(10D, j);
		return String.valueOf(l + (long)i).substring(1);
	}


	public static void setCookie(HttpServletResponse httpservletresponse, String s, String s1, int i, String s2)
	{
		try
		{
			s1 = URLEncoder.encode(s1, "UTF-8");
		}
		catch (UnsupportedEncodingException unsupportedencodingexception)
		{
			unsupportedencodingexception.printStackTrace();
		}
		Cookie cookie = new Cookie(s, s1);
		cookie.setMaxAge(i);
		cookie.setDomain(s2);
		cookie.setPath("/");
		httpservletresponse.addCookie(cookie);
	}

	public static void setCookie(HttpServletResponse httpservletresponse, String s, String s1, int i)
	{
		try
		{
			s1 = URLEncoder.encode(s1, "UTF-8");
		}
		catch (UnsupportedEncodingException unsupportedencodingexception)
		{
			unsupportedencodingexception.printStackTrace();
		}
		Cookie cookie = new Cookie(s, s1);
		cookie.setMaxAge(i);
		cookie.setPath("/");
		httpservletresponse.addCookie(cookie);
	}

	public static void setCookie(HttpServletResponse httpservletresponse, String s, String s1)
	{
		setCookie(httpservletresponse, s, s1, -1);
	}

	public static String null2String(String s)
	{
		return s != null ? s : "";
	}

	public static String null2String(String s, String s1)
	{
		return s != null ? s : s1 != null ? s1 : "";
	}

	public static String null2s(String s, String s1)
	{
		return s != null && !s.equals("") ? s : s1 != null ? s1 : "";
	}

	public static String null2String(Object obj)
	{
		return obj != null ? obj.toString() : "";
	}

	public static String null2o(String s)
	{
		return s != null && !s.equals("") ? s : "0";
	}

	public static String convertInput2DB(String s)
	{
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		s = null2String(s);
		for (char ac[] = s.toCharArray(); i < ac.length;)
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("''");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
			if (c == '\r')
				stringbuffer.append("");
			else
				stringbuffer.append(c);
		}

		return stringbuffer.toString();
	}

	public static String convertInput2DB2(String s)
	{
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		s = null2String(s);
		for (char ac[] = s.toCharArray(); i < ac.length;)
		{
			char c = ac[i++];
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
			if (c == ' ')
				stringbuffer.append("&nbsp;");
			else
			if (c == '\r')
				stringbuffer.append("");
			else
				stringbuffer.append(c);
		}

		return stringbuffer.toString();
	}

	public static String convertInput2DB3(String s)
	{
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		s = null2String(s);
		for (char ac[] = s.toCharArray(); i < ac.length;)
		{
			char c = ac[i++];
			if (c == ' ')
				stringbuffer.append("&nbsp;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
			if (c == '\r')
				stringbuffer.append("");
			else
				stringbuffer.append(c);
		}

		return stringbuffer.toString();
	}

	public static String convertInput2DB4(String s)
	{
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		s = null2String(s);
		char ac[] = s.toCharArray();
		do
		{
			if (i >= ac.length)
				break;
			char c = ac[i++];
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("");
			else
			if (c != ' ')
				if (c == '\r')
					stringbuffer.append("");
				else
					stringbuffer.append(c);
		} while (true);
		return stringbuffer.toString();
	}

	public static String convertDB2Input(String s)
	{
		return StringReplace(s, "<br>", "\n");
	}

	public static String fromScreen(String s, int i)
	{
		if (s == null)
			s = null2String(s);
		s = fromBaseEncoding(s, i);
		s = toHtml(s);
		return s;
	}

	public static String fromScreenVoting(String s, int i)
	{
		if (s == null)
			s = null2String(s);
		s = fromBaseEncoding(s, i);
		s = toHtmlVoting(s);
		return s;
	}

	public static String fromScreen2(String s, int i)
	{
		if (s == null)
			s = null2String(s);
		s = fromBaseEncoding2(s, i);
		s = toHtml(s);
		return s;
	}

	public static String fromScreen3(String s, int i)
	{
		if (s == null)
			s = null2String(s);
		s = fromBaseEncoding(s, i);
		s = toHtml5(s);
		return s;
	}

	public static String fromScreen4(String s, int i)
	{
		if (s == null)
			s = null2String(s);
		s = fromBaseEncoding2(s, i);
		s = toExcelData(s);
		s = replaceHtml(s);
		return s;
	}

	
	public static String toExcelData(String s)
	{
		s = StringReplace(s, "&lt;", "<");
		s = StringReplace(s, "&gt;", ">");
		s = StringReplace(s, "<br>", "\n");
		s = StringReplace(s, "&quot;", "\"");
		s = StringReplace(s, "&nbsp;", " ");
		s = StringReplace(s, "&amp;", "&");
		s = StringReplace(s, "&ldquo;", "“");
		s = StringReplace(s, "&rdquo;", "”");
		s = StringReplace(s, "&lsquo;", "‘");
		s = StringReplace(s, "&rsquo;", "’");
		s = StringReplace(s, "&hellip;", "…");
		s = StringReplace(s, "&mdash;", "—");
		s = StringReplace(s, "&apos;", "'");
		return s;
	}

	public static String replaceHtml(String s)
	{
		s = s.replaceAll("\\&[a-zA-Z]{1,10};", "");
		s = s.replaceAll("initFlashVideo();", "");
		s = s.replaceAll("%nbsp", "");
		return s;
	}

	public static String toScreen(String s, int i)
	{
		return toScreen(s, i, "1");
	}

	public static String toScreen(String s, int i, String s1)
	{
		if (s == null)
			s = null2String(s);
		s = toBaseEncoding(s, i, s1);
		s = fromHtml(s);
		return s;
	}

	

	public static String toScreenToEdit(String s, int i)
	{
		if (s == null)
			s = null2String(s).trim();
		s = toBaseEncoding(s, i, "1");
		s = fromHtmlToEdit(s);
		return s;
	}

	public static String toBaseEncoding(String s, int i, String s1)
	{
		return s;
	}

	public static String fromBaseEncoding(String s, int i)
	{
		return s;
	}

	public static String fromBaseEncoding2(String s, int i)
	{
		return s;
	}

	public static String fromScreenForCpt(String s, int i)
	{
		if (s == null)
			s = null2String(s);
		s = fromBaseEncoding(s, i);
		s = toHtmlForCpt(s);
		return s;
	}

	public static String toHtmlForCpt(String s)
	{
		s = StringReplace(s, "<br>", "\n");
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("''");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
			if (c == ' ')
				stringbuffer.append("&nbsp");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtml(String s, boolean flag)
	{
		s = StringReplace(s, "<br>", "\n");
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtml(String s)
	{
		s = StringReplace(s, "<br>", "\n");
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("''");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toXmlText(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("&apos;");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtmlA(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		do
		{
			if (i >= ac.length)
				break;
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("''");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c != '\n' && c != '\r')
				stringbuffer.append(c);
		} while (true);
		return stringbuffer.toString();
	}

	public static String toExcel(String s)
	{
		s = StringReplace(s, "<br>", "\n");
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("''");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String forHtml(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
			if (c == '\r')
				stringbuffer.append("");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtmltextarea(String s)
	{
		s = StringReplace(s, "<br>", "\n");
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String spacetoHtml(String s)
	{
		s = StringReplace(s, "<br>", "\n");
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("''");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
			if (c == ' ')
				stringbuffer.append("&nbsp;");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtml10(String s)
	{
		String s1;
		if (s == null)
			s = null2String(s);
		s1 = s;
		s1 = StringReplace(s1, "<br>", "\n");
		s1 = StringReplace(s1, "'", "''");
		s1 = StringReplace(s1, "<", "&lt;");
		s1 = StringReplace(s1, ">'", "&gt;'");
		s1 = StringReplace(s1, "\"", "&quot;");
		s1 = StringReplace(s1, "\n", "<br>");
		return s1;
	}

	public static String toHtmlVoting(String s)
	{
		String s1;
		if (s == null)
			s = null2String(s);
		s1 = s;
		s1 = StringReplace(s1, "<br>", "\n");
		s1 = StringReplace(s1, "'", "'");
		s1 = StringReplace(s1, "<", "&lt;");
		s1 = StringReplace(s1, ">'", "&gt;'");
		s1 = StringReplace(s1, "\"", "&quot;");
		s1 = StringReplace(s1, "\n", "<br>");
		return s1;
	}


	public static String toHtml100(String s)
	{
		String s1;
		if (s == null)
			s = null2String(s);
		s1 = s;
		s1 = StringReplace(s1, "'", "''");
		return s1;
	}

	public static String toHtml2(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
			if (c == '\r')
				stringbuffer.append("<br>");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtml3(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
			if (c == '\'')
				stringbuffer.append("\\'");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtml4(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("\\'");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtml5(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("'");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtml6(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("''");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
			if (c == '\r')
				stringbuffer.append("<br>");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtml7(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("’");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("”");
			else
			if (c == '\n')
				stringbuffer.append("<br>");
			else
			if (c == '\r')
				stringbuffer.append("<br>");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtml8(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("\\'");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '"')
				stringbuffer.append("&quot;");
			else
			if (c == '\\')
				stringbuffer.append("\\\\");
			else
			if (c == '?')
				stringbuffer.append("\\?");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toHtmlForSplitPage(String s)
	{
		char ac[] = s.toCharArray();
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer();
		while (i < ac.length) 
		{
			char c = ac[i++];
			if (c == '\'')
				stringbuffer.append("\\'");
			else
			if (c == '<')
				stringbuffer.append("&lt;");
			else
			if (c == '>')
				stringbuffer.append("&gt;");
			else
			if (c == '&')
				stringbuffer.append("&amp;");
			else
				stringbuffer.append(c);
		}
		return stringbuffer.toString();
	}

	public static String toSqlForSplitPage(String s)
	{
		s = StringReplace(s, "\\'", "'");
		s = StringReplace(s, "&lt;", "<");
		s = StringReplace(s, "&gt;", ">");
		s = StringReplace(s, "&amp;", "&");
		return s;
	}

	public static String toHtmlMode(String s)
	{
		s = StringReplace(s, "''", "'");
		s = StringReplace(s, "&lt;", "<");
		s = StringReplace(s, "&gt;", ">");
		s = StringReplace(s, "&quot;", "\"");
		s = StringReplace(s, "<br>", "\n");
		return s;
	}

	public static String toHtmlMode2(String s)
	{
		s = StringReplace(s, "&amp;", "&");
		s = toHtmlMode(s);
		s = StringReplace(s, "&apos;", "'");
		return s;
	}

	public static String fromHtml(String s)
	{
		return s;
	}

	public static String fromHtmlToEdit(String s)
	{
		return StringReplace(s, "<br>", "");
	}

	public static String toHtmlForWorkflow(String s)
	{
		String s1;
		if (s == null)
			s = null2String(s);
		s1 = s;
		s1 = StringReplace(s1, "\n", "<br>");
		s1 = StringReplace(s1, "'", "''");
		s1 = StringReplace(s1, "&nbsp;", " ");
		return s1;
	}


	public static String toScreenForMode(String s)
	{
		String s1;
		ArrayList arraylist;
		if (s == null)
			s = null2String(s);
		s1 = s;
		arraylist = new ArrayList();
		String s2;
		for (; s1.indexOf("<a ") > -1; arraylist.add(s2))
		{
			s2 = s1.substring(s1.indexOf("<a "), s1.indexOf("</a>") + 4);
			s1 = (new StringBuilder()).append(s1.substring(0, s1.indexOf("<a "))).append("#").append(arraylist.size()).append("#").append(s1.substring(s1.indexOf("</a>") + 4)).toString();
		}

		s1 = StringReplace(s1, "<br>", "\n");
		s1 = StringReplace(s1, "<", "&lt;");
		s1 = StringReplace(s1, ">", "&gt;");
		s1 = StringReplace(s1, "\"", "&quot;");
		s1 = StringReplace(s1, " ", "&nbsp;");
		s1 = StringReplace(s1, "\n", "<br>");
		for (int i = 0; i < arraylist.size(); i++)
			s1 = s1.replaceFirst((new StringBuilder()).append("#").append(i).append("#").toString(), (String)arraylist.get(i));

		return s1;
	}

	public static String toScreenForWorkflowReadOnly(String s)
	{
		String s1;
		if (s == null)
			s = null2String(s);
		s1 = s;
		s1 = StringReplace(s1, "\n", "<br>");
		s1 = StringReplace(s1, "\"", "&quot;");
		s1 = StringReplace(s1, " ", "&nbsp;");
		return s1;
	}

	public static boolean contains(Object aobj[], Object obj)
	{
		if (aobj == null || obj == null)
			return false;
		for (int i = 0; i < aobj.length; i++)
			if (aobj[i] != null && aobj[i].equals(obj))
				return true;

		return false;
	}

	public static String extract(String s, String s1, String s2)
	{
		int i = s1 != null ? s.indexOf(s1) : 0;
		int j = s1 != null ? s1.length() : 0;
		int k = s2 != null ? s.indexOf(s2, i + j) : s.length();
		if (i == -1)
		{
			i = 0;
			j = 0;
		}
		if (k == -1)
			k = s.length();
		return s.substring(i + j, k);
	}

	public static String remove(String s, String s1)
	{
		int i = s.indexOf(s1);
		int j = s1.length();
		if (i != -1)
			return (new StringBuilder()).append(s.substring(0, i)).append(s.substring(i + j)).toString();
		else
			return s;
	}

	public static String replaceChar(String s, char c, char c1)
	{
		if (s == null)
			return s;
		char ac[] = s.toCharArray();
		for (int i = 0; i < ac.length; i++)
			if (ac[i] == c)
				ac[i] = c1;

		return String.valueOf(ac);
	}

	public static boolean isEmail(String s)
	{
		if (s == null || "".equals(s))
			return false;
		if (s.indexOf(";") >= 0 || s.indexOf(",") >= 0 || s.indexOf(">") >= 0 || s.indexOf("<") >= 0 || s.indexOf("[") >= 0 || s.indexOf("]") >= 0 || s.indexOf(")") >= 0 || s.indexOf("(") >= 0)
			return false;
		int i = 0;
		i = s.indexOf("@");
		if (i == -1)
			return false;
		return s.substring(i).indexOf(".") >= 0;
	}

	public static void swap(Object aobj[], int i, int j)
	{
		Object obj = aobj[i];
		aobj[i] = aobj[j];
		aobj[j] = obj;
	}

	public static String StringReplace(String s, String s1, String s2)
	{
		s = null2String(s);
		s1 = null2String(s1);
		s2 = null2String(s2);
		try
		{
			s = s.replace(s1, s2);
		}
		catch (Exception exception) { }
		return s;
	}

	public static String StringReplaceOnce(String s, String s1, String s2)
	{
		int i = s.indexOf(s1);
		if (i < 0)
			return s;
		else
			return (new StringBuilder()).append(s.substring(0, i)).append(s2).append(s.substring(i + s1.length())).toString();
	}

	public static String replaceRange(String s, String s1, String s2, String s3, boolean flag)
	{
		int i = -1;
		int j = -1;
		if (flag)
			i = s.indexOf(s1);
		else
			i = s.toLowerCase().indexOf(s1.toLowerCase());
		if (i == -1 || s == null || s1 == null || s2 == null || s3 == null)
			return s;
		if (flag)
			j = s.indexOf(s2, i);
		else
			j = s.toLowerCase().indexOf(s2.toLowerCase(), i);
		String s4 = null;
		if (j > -1)
			s4 = (new StringBuilder()).append(s.substring(0, i)).append(s3).append(s.substring(j + s2.length())).toString();
		else
			s4 = (new StringBuilder()).append(s.substring(0, i)).append(s3).append(s.substring(i + s1.length())).toString();
		return replaceRange(s4, s1, s2, s3, flag);
	}

	public static int getIntValue(String s)
	{
		return getIntValue(s, -1);
	}

	public static int getIntValue(String v, int def)
	{
		try {
			return Integer.parseInt(v);
		} catch (Exception ex) {
			return def;
		}
	}

	public static float getFloatValue(String s)
	{
		return getFloatValue(s, -1F);
	}

	public static float getFloatValue(String s, float f)
	{
		return Float.parseFloat(s);
	}

	public static double getDoubleValue(String s)
	{
		return getDoubleValue(s, -1D);
	}

	public static double getDoubleValue(String s, double d)
	{
		return Double.parseDouble(s);
	}

	public static String getPointValue(String s)
	{
		return getPointValue(s, 2);
	}

	public static String getIntValues(String s)
	{
		String s1 = s;
		if (s.indexOf(".") == -1)
			s1 = s;
		else
			s1 = s.substring(0, s.indexOf("."));
		return s1;
	}

	public static String getPointValue(String s, int i)
	{
		return getPointValue(s, 2, "-1");
	}

	public static String getPointValue2(String s, int i)
	{
		return getPointValue(s, i, "-1");
	}

	public static String getPointValue3(String s, int i, String s1)
	{
		String s2 = s;
		if (s2.indexOf(".") == -1)
			s2 = s2;
		else
			s2 = getPointValue(s, i, s1);
		return s2;
	}

	public static String getPointValue(String s, int i, String s1)
	{
		String s2;
		Double.parseDouble(s);
		s2 = s;
		if (s.indexOf("E") != -1)
			s2 = getfloatToString(s);
		if (s2.indexOf(".") == -1)
		{
			s2 = (new StringBuilder()).append(s2).append(".").toString();
			for (int j = 0; j < i; j++)
				s2 = (new StringBuilder()).append(s2).append("0").toString();

		} else
		if (s2.length() - s2.lastIndexOf(".") <= i)
		{
			for (int k = 0; k < (i - s2.length()) + s2.lastIndexOf(".") + 1; k++)
				s2 = (new StringBuilder()).append(s2).append("0").toString();

		} else
		{
			s2 = s2.substring(0, s2.lastIndexOf(".") + i + 1);
		}
		return s2;
	}

	public static String getFileidIn(String s)
	{
		return s;
	}

	public static String getFileidOut(String s)
	{
		return s;
	}

	public static String makeNavbar(int i, int j, int k, String s)
	{
		int l = 1;
		int i1 = k * 5;
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "Next";
		String s5 = "Previous";
		String s6 = "";
		if (s.indexOf("?") < 0)
			s6 = "?start=";
		else
			s6 = "&start=";
		Hashtable hashtable = new Hashtable();
		hashtable.put("action", s);
		l = i + 1;
		int j1;
		for (j1 = 0; j1 * i1 < l; j1++);
		l = (j1 - 1) * i1 + 1;
		if (i + 1 > i1)
			s1 = (new StringBuilder()).append("<a href='").append(s).append(s6).append(Math.max(1, l - i1)).append("'>[ ").append(s5).append(" ").append(i1).append(" ]</a>&nbsp;").toString();
		for (; l < j1 * i1 && l < j + 1; l += k)
		{
			hashtable.put("from", String.valueOf(l));
			hashtable.put("to", String.valueOf(Math.min(j, (l + k) - 1)));
			if (i + 1 >= l && i + 1 <= (l + k) - 1)
				s3 = (new StringBuilder()).append(s3).append(fillValuesToString("[$from - $to]&nbsp;", hashtable)).toString();
			else
				s3 = (new StringBuilder()).append(s3).append(fillValuesToString((new StringBuilder()).append("<a href='$action").append(s6).append("$from'>[$from - $to]</a>&nbsp;").toString(), hashtable)).toString();
		}

		if (j >= l)
			s2 = (new StringBuilder()).append("&nbsp;<a href='").append(s).append(s6).append(l).append("'>[ ").append(s4).append(" ").append(Math.min(i1, (j - l) + 1)).append(" ]</a>").toString();
		return (new StringBuilder()).append(s1).append(s3).append(s2).toString();
	}


	public static String makeNavbar2(int i, int j, int k, String s)
	{
		int l = 1;
		int i1 = (i - 1) * k + 1;
		int j1 = k * 5;
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "Next";
		String s5 = "Previous";
		String s6 = "";
		if (s.indexOf("?") < 0)
			s6 = "?pagenum=";
		else
			s6 = "&pagenum=";
		Hashtable hashtable = new Hashtable();
		hashtable.put("action", s);
		l = i1 + 1;
		int k1;
		for (k1 = 0; k1 * j1 < l; k1++);
		l = (k1 - 1) * j1 + 1;
		if (i1 + 1 > j1)
			s1 = (new StringBuilder()).append("<a href='").append(s).append(s6).append(Math.max(1, (l - j1) / k)).append("'>[ ").append(s5).append(" ").append(j1).append(" ]</a>&nbsp;").toString();
		for (; l < k1 * j1 && l < j + 1; l += k)
		{
			hashtable.put("from", String.valueOf(l));
			hashtable.put("to", String.valueOf(Math.min(j, (l + k) - 1)));
			hashtable.put("pagenum", String.valueOf(((l + k) - 1) / k));
			if (i1 + 1 >= l && i1 + 1 <= (l + k) - 1)
				s3 = (new StringBuilder()).append(s3).append(fillValuesToString("[$from - $to]&nbsp;", hashtable)).toString();
			else
				s3 = (new StringBuilder()).append(s3).append(fillValuesToString((new StringBuilder()).append("<a href='$action").append(s6).append("$pagenum'>[$from - $to]</a>&nbsp;").toString(), hashtable)).toString();
		}

		if (j >= l)
			s2 = (new StringBuilder()).append("&nbsp;<a href='").append(s).append(s6).append(l / k + 1).append("'>[ ").append(s4).append(" ").append(Math.min(j1, (j - l) + 1)).append(" ]</a>").toString();
		return (new StringBuilder()).append(s1).append(s3).append(s2).toString();
	}

	public static String makeNavbarUseFunc(int i, int j, int k, String s, String s1)
	{
		int l = 1;
		int i1 = (i - 1) * k + 1;
		int j1 = k * 5;
		String s2 = "";
		String s3 = "";
		String s4 = "";
		String s5 = "Next";
		String s6 = "Previous";
		String s7 = "";
		if (s.indexOf("?") < 0)
			s7 = "?pagenum=";
		else
			s7 = "&pagenum=";
		Hashtable hashtable = new Hashtable();
		hashtable.put("action", s);
		l = i1 + 1;
		int k1;
		for (k1 = 0; k1 * j1 < l; k1++);
		l = (k1 - 1) * j1 + 1;
		if (i1 + 1 > j1)
			s2 = (new StringBuilder()).append("<a href='").append(s).append(s7).append(Math.max(1, (l - j1) / k)).append("' onclick='").append(s1).append("'>[ ").append(s6).append(" ").append(j1).append(" ]</a>&nbsp;").toString();
		for (; l < k1 * j1 && l < j + 1; l += k)
		{
			hashtable.put("from", String.valueOf(l));
			hashtable.put("to", String.valueOf(Math.min(j, (l + k) - 1)));
			hashtable.put("pagenum", String.valueOf(((l + k) - 1) / k));
			if (i1 + 1 >= l && i1 + 1 <= (l + k) - 1)
				s4 = (new StringBuilder()).append(s4).append(fillValuesToString("[$from - $to]&nbsp;", hashtable)).toString();
			else
				s4 = (new StringBuilder()).append(s4).append(fillValuesToString((new StringBuilder()).append("<a href='$action").append(s7).append("$pagenum' onclick='").append(s1).append("'>[$from - $to]</a>&nbsp;").toString(), hashtable)).toString();
		}

		if (j >= l)
			s3 = (new StringBuilder()).append("&nbsp;<a href='").append(s).append(s7).append(l / k + 1).append("' onclick='").append(s1).append("'>[ ").append(s5).append(" ").append(Math.min(j1, (j - l) + 1)).append(" ]</a>").toString();
		return (new StringBuilder()).append(s2).append(s4).append(s3).toString();
	}

	public static String makeNavbar3(int i, int j, int k, String s)
	{
		int l = 1;
		int i1 = (i - 1) * k + 1;
		int j1 = k * 5;
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "Next";
		String s5 = "Previous";
		String s6 = "";
		if (s.indexOf("?") < 0)
			s6 = "?pagenum=";
		else
			s6 = "&pagenum=";
		Hashtable hashtable = new Hashtable();
		hashtable.put("action", s);
		l = i1 + 1;
		int k1;
		for (k1 = 0; k1 * j1 < l; k1++);
		l = (k1 - 1) * j1 + 1;
		if (i1 + 1 > j1)
			s1 = (new StringBuilder()).append("<a href='javascript:changePagePre(\"").append(s).append(s6).append(Math.max(1, (l - j1) / k)).append("\")'>[ ").append(s5).append(" ").append(j1).append(" ]</a>&nbsp;").toString();
		for (; l < k1 * j1 && l < j + 1; l += k)
		{
			hashtable.put("from", String.valueOf(l));
			hashtable.put("to", String.valueOf(Math.min(j, (l + k) - 1)));
			hashtable.put("pagenum", String.valueOf(((l + k) - 1) / k));
			if (i1 + 1 >= l && i1 + 1 <= (l + k) - 1)
				s3 = (new StringBuilder()).append(s3).append(fillValuesToString("[$from - $to]&nbsp;", hashtable)).toString();
			else
				s3 = (new StringBuilder()).append(s3).append(fillValuesToString((new StringBuilder()).append("<a href='javascript:changePageTo(\"$action").append(s6).append("$pagenum\")'>[$from - $to]</a>&nbsp;").toString(), hashtable)).toString();
		}

		if (j >= l)
			s2 = (new StringBuilder()).append("&nbsp;<a href='javascript:changePageNext(\"").append(s).append(s6).append(l / k + 1).append("\")'>[ ").append(s4).append(" ").append(Math.min(j1, (j - l) + 1)).append(" ]</a>").toString();
		return (new StringBuilder()).append(s1).append(s3).append(s2).toString();
	}

	public static String makeNavbarReverse(int i, int j, int k, String s)
	{
		int l = k * 5;
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int i1 = i + 1;
		Hashtable hashtable = new Hashtable();
		hashtable.put("action", s);
		String s4 = "Next";
		String s5 = "Previous";
		String s6 = "";
		if (s.indexOf("?") < 0)
			s6 = "?start=";
		else
			s6 = "&start=";
		int j1;
		for (j1 = 0; j1 * l < i1; j1++);
		i1 = (j1 - 1) * l + 1;
		if (i1 > l)
			s1 = (new StringBuilder()).append("<a href=").append(s).append(s6).append(Math.max(1, i1 - l)).append(">[ ").append(s4).append(" ").append(l).append(" ]</a>&nbsp;").toString();
		i++;
		for (int k1 = 0; k1 < k && i1 <= j; k1++)
		{
			hashtable.put("from", String.valueOf((j - i1) + 1));
			hashtable.put("to", String.valueOf(Math.max(1, ((j - i1) + 1 + 1) - k)));
			if (i >= i1 && i <= (i1 + k) - 1)
				s3 = (new StringBuilder()).append(s3).append(fillValuesToString("[$from - $to]&nbsp;", hashtable)).toString();
			else
				s3 = (new StringBuilder()).append(s3).append(fillValuesToString((new StringBuilder()).append("<a href='$action").append(s6).append(String.valueOf(i1)).append("'>[ $from - $to ]</a>&nbsp;").toString(), hashtable)).toString();
			i1 += k;
		}

		if (j > i1)
			s2 = (new StringBuilder()).append("&nbsp;<a href=").append(s).append(s6).append(String.valueOf(i1)).append(">[ ").append(s5).append(" ").append(Math.min((j - i1) + 1, 100)).append(" ]</a>").toString();
		return (new StringBuilder()).append(s1).append(s3).append(s2).toString();
	}

	public static String fillValuesToString(String s, Hashtable hashtable)
	{
		byte byte0 = 36;
		byte byte1 = 92;
		if (s == null || s.length() == 0 || hashtable == null)
			return s;
		char ac[] = s.toCharArray();
		char c1 = '\0';
		StringBuffer stringbuffer = new StringBuffer();
		char c = ac[c1];
		do
		{
			if (c == byte0)
			{
				String s1 = "";
				c1++;
				if (c1 >= ac.length)
					break;
				c = ac[c1];
				do
				{
					if (c != '_' && c != '-' && !Character.isLetterOrDigit(c))
						break;
					s1 = (new StringBuilder()).append(s1).append(c).toString();
					c1++;
					if (c1 >= ac.length)
						break;
					c = ac[c1];
				} while (true);
				if (s1.length() != 0)
				{
					String s2 = (String)hashtable.get(s1);
					if (s2 != null)
						stringbuffer.append(s2);
				}
				if (s1.length() != 0 && c == byte0)
					continue;
				if (c == byte1)
				{
					c1++;
					if (c1 >= ac.length)
						break;
					c = ac[c1];
					continue;
				}
				if (c1 >= ac.length)
					break;
			}
			stringbuffer.append(c);
			c1++;
			if (c1 >= ac.length)
				break;
			c = ac[c1];
		} while (true);
		return stringbuffer.toString();
	}

	public static String fillValuesToString2(String s, Hashtable hashtable)
	{
		byte byte0 = 36;
		byte byte1 = 92;
		if (s == null || s.length() == 0 || hashtable == null)
			return s;
		char ac[] = s.toCharArray();
		char c1 = '\0';
		StringBuffer stringbuffer = new StringBuffer();
		char c = ac[c1];
		do
		{
			if (c == byte0)
			{
				String s1 = "";
				c1++;
				if (c1 >= ac.length)
					break;
				c = ac[c1];
				do
				{
					if (!Character.isLetterOrDigit(c))
						break;
					s1 = (new StringBuilder()).append(s1).append(c).toString();
					c1++;
					if (c1 >= ac.length)
						break;
					c = ac[c1];
				} while (true);
				if (s1.length() != 0)
				{
					String s2 = (String)hashtable.get(s1);
					if (s2 != null)
						stringbuffer.append(s2);
				}
				if (s1.length() != 0 && c == byte0)
					continue;
				if (c == byte1)
				{
					c1++;
					if (c1 >= ac.length)
						break;
					c = ac[c1];
					continue;
				}
				if (c1 >= ac.length)
					break;
			}
			stringbuffer.append(c);
			c1++;
			if (c1 >= ac.length)
				break;
			c = ac[c1];
		} while (true);
		return stringbuffer.toString();
	}

	public static char getSeparator()
	{
		return '\002';
	}

	public static String addTime(String s, String s1)
	{
		if (s.equals("") || s1.equals(""))
			return "00:00";
		ArrayList arraylist = TokenizerString(s, ":");
		ArrayList arraylist1 = TokenizerString(s1, ":");
		int i = getIntValue((String)arraylist.get(0));
		int k = getIntValue((String)arraylist.get(1));
		int j = getIntValue((String)arraylist1.get(0));
		int l = getIntValue((String)arraylist1.get(1));
		int i1;
		int j1;
		if (k + l >= 60)
		{
			i1 = i + j + 1;
			j1 = (k + l) - 60;
		} else
		{
			i1 = i + j;
			j1 = k + l;
		}
		if (i1 < 10)
			if (j1 < 10)
				return (new StringBuilder()).append("0").append(i1).append(":").append("0").append(j1).toString();
			else
				return (new StringBuilder()).append("0").append(i1).append(":").append("").append(j1).toString();
		if (j1 < 10)
			return (new StringBuilder()).append("").append(i1).append(":").append("0").append(j1).toString();
		else
			return (new StringBuilder()).append("").append(i1).append(":").append("").append(j1).toString();
	}

	public static String subTime(String s, String s1)
	{
		if (s.equals("") || s1.equals(""))
			return "00:00";
		ArrayList arraylist = TokenizerString(s, ":");
		ArrayList arraylist1 = TokenizerString(s1, ":");
		int i = getIntValue((String)arraylist.get(0));
		int k = getIntValue((String)arraylist.get(1));
		int j = getIntValue((String)arraylist1.get(0));
		int l = getIntValue((String)arraylist1.get(1));
		int i1;
		int j1;
		if (k - l < 0)
		{
			i1 = i - j - 1;
			j1 = (k - l) + 60;
		} else
		{
			i1 = i - j;
			j1 = k - l;
		}
		if (i1 < 10)
			if (j1 < 10)
				return (new StringBuilder()).append("0").append(i1).append(":").append("0").append(j1).toString();
			else
				return (new StringBuilder()).append("0").append(i1).append(":").append("").append(j1).toString();
		if (j1 < 10)
			return (new StringBuilder()).append("").append(i1).append(":").append("0").append(j1).toString();
		else
			return (new StringBuilder()).append("").append(i1).append(":").append("").append(j1).toString();
	}

	public static String getFloatStr(String s, int i)
	{
		int j = s.indexOf(".");
		String s1 = "";
		if (j != -1)
			s1 = extract(s, ".", null);
		String s2 = extract(s, null, ".");
		if (s2.length() < i + 1)
			return s;
		String s3 = "";
		int k = s2.length() % i;
		s3 = s2.substring(0, k);
		s2 = s2.substring(k);
		int l = s2.length() / i;
		for (int i1 = 0; i1 < l; i1++)
			if (s3.equals("") || s3.equals("-"))
			{
				s3 = (new StringBuilder()).append(s3).append(s2.substring(0, i)).toString();
				s2 = s2.substring(i);
			} else
			{
				s3 = (new StringBuilder()).append(s3).append(",").append(s2.substring(0, i)).toString();
				s2 = s2.substring(i);
			}

		if (j != -1)
			return (new StringBuilder()).append(s3).append(".").append(s1).toString();
		else
			return s3;
	}

	public static String getRandom()
	{
		int i;
		for (i = 0x3b9aca00 + random.nextInt(0x3b9aca00); i == 0; i = 0x3b9aca00 + random.nextInt(0x3b9aca00));
		return String.valueOf(i);
	}

	public static int getPerpageLog()
	{
		return 10;
	}

	public static String getPortalPassword()
	{
		String s = "";
		boolean flag = false;
		for (int i = 0; i < 8; i++)
			if (!flag)
			{
				flag = true;
				char c = (char)(int)(Math.random() * 26D + 97D);
				s = (new StringBuilder()).append(s).append(c).toString();
			} else
			{
				flag = false;
				char c1 = (char)(int)(Math.random() * 10D + 48D);
				s = (new StringBuilder()).append(s).append(c1).toString();
			}

		return s;
	}

	public static String getCharString(int i)
	{
		String s = "";
		int j = getchars(i, 26, 0);
		for (int k = j; k > 0; k--)
		{
			if (k > 1)
			{
				int l = (new BigDecimal(Math.pow(26D, k - 1))).intValue();
				int j1 = i - l;
				int k1 = j1 % l;
				int l1 = j1 / l;
				i -= l;
				if (k1 != 0)
					l1++;
				if (l1 > 26)
				{
					l1 %= 26;
					if (l1 == 0)
						l1 = 26;
				}
				s = (new StringBuilder()).append(s).append((char)(l1 + 64)).toString();
				continue;
			}
			int i1 = i;
			if (i > 26)
				i1 = i % 26;
			if (i1 == 0)
				i1 = 26;
			s = (new StringBuilder()).append(s).append((char)(i1 + 64)).toString();
		}

		return s;
	}

	public static int getchars(int i, int j, int k)
	{
		int l = 1;
		int i1 = i / j;
		int j1 = i % j;
		if (i1 > 0)
			if (i1 > j)
			{
				if (j1 == 0)
					l += getchars(i1, j, i1 / j);
				else
					l += getchars(i1, j, 0);
			} else
			if (i1 > 1 || j1 > k)
				l++;
		return l;
	}

	public static int getCharInt(String s)
	{
		int i = s.length();
		int j = 0;
		for (int k = 0; k < i; k++)
			j += (s.charAt(i - (k + 1)) - 64) * (new BigDecimal(Math.pow(26D, k))).intValue();

		return j;
	}

	public static String getfloatToString(String s)
	{
		boolean flag = false;
		if (s.indexOf("-") != -1)
		{
			flag = true;
			s = s.substring(1, s.length());
		}
		int i = s.indexOf("E");
		if (i == -1)
			return s;
		int j = Integer.parseInt(s.substring(i + 1, s.length()));
		s = s.substring(0, i);
		i = s.indexOf(".");
		s = (new StringBuilder()).append(s.substring(0, i)).append(s.substring(i + 1, s.length())).toString();
		String s1 = s;
		if (s.length() <= j)
		{
			for (int k = 0; k < (j - s.length()) + 1; k++)
				s1 = (new StringBuilder()).append(s1).append("0").toString();

		} else
		{
			s1 = (new StringBuilder()).append(s1.substring(0, j + 1)).append(".").append(s1.substring(j + 1)).append("0").toString();
		}
		if (flag)
			s1 = (new StringBuilder()).append("-").append(s1).toString();
		return s1;
	}

	public static String getRequestHost(HttpServletRequest httpservletrequest)
	{
		return null2String(httpservletrequest.getHeader("Host")).trim();
	}

	public static int dayDiff(String s, String s1)
	{
		int i = 0;
		if (s.equals("") || s == null || s.length() < 10 || s1.equals("") || s1 == null || s1.length() < 10)
			return 0;
		String s2 = "";
		String s3 = "";
		byte byte0 = 1;
		if (s.compareTo(s1) <= 0)
		{
			s2 = s;
			s3 = s1;
		} else
		{
			s2 = s1;
			s3 = s;
			byte0 = -1;
		}
		int j = getIntValue(s2.substring(0, 4));
		int k = getIntValue(s2.substring(5, 7));
		int l = getIntValue(s2.substring(8, 10));
		Calendar calendar = Calendar.getInstance();
		calendar.set(j, k - 1, l);
		for (; s2.compareTo(s3) <= 0; s2 = (new StringBuilder()).append(add0(calendar.get(1), 4)).append("-").append(add0(calendar.get(2) + 1, 2)).append("-").append(add0(calendar.get(5), 2)).toString())
		{
			i++;
			calendar.add(5, 1);
		}

		return byte0 * i;
	}

	public static int monthDiff(String s, String s1)
	{
		boolean flag = false;
		if (s.equals("") || s == null || s.length() < 10 || s1.equals("") || s1 == null || s1.length() < 10)
		{
			return 0;
		} else
		{
			int j = getIntValue(s.substring(0, 4));
			int k = getIntValue(s.substring(5, 7)) - 1;
			int l = getIntValue(s1.substring(0, 4));
			int i1 = getIntValue(s1.substring(5, 7)) - 1;
			int i = i1 - k;
			i += 12 * (l - j);
			return i;
		}
	}

	public static int monthDiff2(String s, int i)
	{
		boolean flag = false;
		if (s.equals("") || s == null || s.length() < 10)
		{
			return 0;
		} else
		{
			int k = getIntValue(s.substring(5, 7));
			int j = i - k;
			return j;
		}
	}

	public static int yearDiff(String s, String s1)
	{
		boolean flag = false;
		if (s.equals("") || s == null || s.length() < 10 || s1.equals("") || s1 == null || s1.length() < 10)
		{
			return 0;
		} else
		{
			int j = getIntValue(s.substring(0, 4));
			int k = getIntValue(s1.substring(0, 4));
			int i = k - j;
			return i;
		}
	}

	public static int yearDiff2(String s, int i)
	{
		boolean flag = false;
		if (s.equals("") || s == null || s.length() < 10)
		{
			return 0;
		} else
		{
			int k = getIntValue(s.substring(0, 4));
			int j = i - k;
			return j;
		}
	}

	public static int timediff1(String s, String s1)
	{
		if (s.length() != 5 || s1.length() != 5)
		{
			return 0;
		} else
		{
			Calendar calendar = Calendar.getInstance();
			int i = getIntValue(s.substring(0, 2), 0);
			int j = getIntValue(s.substring(3, 5), 0);
			int k = getIntValue(s1.substring(0, 2), 0);
			int l = getIntValue(s1.substring(3, 5), 0);
			calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), i, j);
			Date date1 = calendar.getTime();
			long l1 = date1.getTime();
			calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), k, l);
			Date date2 = calendar.getTime();
			long l2 = date2.getTime();
			int i1 = (new Long((l1 - l2) / 60000L)).intValue();
			return i1;
		}
	}

	public static int timediff2(String s, String s1, String s2, String s3)
	{
		if (s.length() != 10 || s1.length() != 5 || s2.length() != 10 || s3.length() != 5)
		{
			return 0;
		} else
		{
			Calendar calendar = Calendar.getInstance();
			int i = getIntValue(s.substring(0, 4));
			int j = getIntValue(s.substring(5, 7));
			int k = getIntValue(s.substring(8, 10));
			int l = getIntValue(s2.substring(0, 4));
			int i1 = getIntValue(s2.substring(5, 7));
			int j1 = getIntValue(s2.substring(8, 10));
			int k1 = getIntValue(s1.substring(0, 2), 0);
			int l1 = getIntValue(s1.substring(3, 5), 0);
			int i2 = getIntValue(s3.substring(0, 2), 0);
			int j2 = getIntValue(s3.substring(3, 5), 0);
			calendar.set(i, j - 1, k, k1, l1);
			Date date1 = calendar.getTime();
			long l2 = date1.getTime();
			calendar.set(l, i1 - 1, j1, i2, j2);
			Date date2 = calendar.getTime();
			long l3 = date2.getTime();
			int k2 = (new Long((l2 - l3) / 60000L)).intValue();
			return k2;
		}
	}

	public static String makeNavbar4(int i, int j, int k, String s)
	{
		int l = 1;
		int i1 = k * 5;
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "Next";
		String s5 = "Previous";
		String s6 = "";
		if (s.indexOf("?") < 0)
			s6 = "?start=";
		else
			s6 = "&start=";
		Hashtable hashtable = new Hashtable();
		hashtable.put("action", s);
		l = i + 1;
		int j1;
		for (j1 = 0; j1 * i1 < l; j1++);
		l = (j1 - 1) * i1 + 1;
		if (i + 1 > i1)
			s1 = (new StringBuilder()).append("<a href='").append(s).append(s6).append(Math.max(1, l - i1)).append("'> ").append(s5).append(" ").append(i1).append(" </a>&nbsp;").toString();
		for (; l < j1 * i1 && l < j + 1; l += k)
		{
			int k1 = l / k + 1;
			hashtable.put("from", String.valueOf(k1));
			hashtable.put("linkfrom", String.valueOf(l));
			if (i + 1 >= l && i + 1 <= (l + k) - 1)
				s3 = (new StringBuilder()).append(s3).append(fillValuesToString(" $from &nbsp; ", hashtable)).toString();
			else
				s3 = (new StringBuilder()).append(s3).append(fillValuesToString((new StringBuilder()).append(" <a href='$action").append(s6).append("$linkfrom'>$from</a> &nbsp; ").toString(), hashtable)).toString();
		}

		if (j >= l)
			s2 = (new StringBuilder()).append("&nbsp;<a href='").append(s).append(s6).append(l).append("'> ").append(s4).append(" ").append(Math.min(i1, (j - l) + 1)).append(" </a>").toString();
		return (new StringBuilder()).append(s1).append(s3).append(s2).toString();
	}




	public static ArrayList arrayToArrayList(Object aobj[])
	{
		ArrayList arraylist = new ArrayList();
		if (aobj == null)
			return arraylist;
		for (int i = 0; i < aobj.length; i++)
			arraylist.add(aobj[i]);

		return arraylist;
	}


	public static String replaceString(String s, String s1, String s2)
	{
		if (s == null)
			return s;
		int i = 0;
		if ((i = s.indexOf(s1, i)) >= 0 && s2 != null)
		{
			char ac[] = s.toCharArray();
			char ac1[] = s2.toCharArray();
			int j = s1.length();
			StringBuffer stringbuffer = new StringBuffer(ac.length);
			stringbuffer.append(ac, 0, i).append(ac1);
			i += j;
			int k;
			for (k = i; (i = s.indexOf(s1, i)) > 0; k = i)
			{
				stringbuffer.append(ac, k, i - k).append(ac1);
				i += j;
			}

			stringbuffer.append(ac, k, ac.length - k);
			return stringbuffer.toString();
		} else
		{
			return s;
		}
	}

	public static String replaceString2(String s, String s1, String s2)
	{
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(s1, 2);
		Matcher matcher = pattern.matcher(s);
		StringBuffer stringbuffer = new StringBuffer();
		for (boolean flag = matcher.find(); flag; flag = matcher.find())
			matcher.appendReplacement(stringbuffer, s2);

		matcher.appendTail(stringbuffer);
		return stringbuffer.toString();
	}


	public static String getSubStr(String s, int i)
	{
		int j = s.length();
		if (j < i)
			return s;
		else
			return (new StringBuilder()).append(s.substring(0, i - 1)).append("...").toString();
	}

	public static String round(String s, int i)
	{
		BigDecimal bigdecimal;
		String s1 = s;
		if (s.indexOf(".") == -1)
			s1 = s;
		else
			s1 = (new StringBuilder()).append("").append(round2(s, i)).toString();
		bigdecimal = new BigDecimal(s1);
		bigdecimal.setScale(i);
		return bigdecimal.toPlainString();
	}

	public static double round2(String s, int i)
	{
		long l = 1L;
		double d = getDoubleValue(s);
		for (int j = i; j > 0; j--)
			l *= 10L;

		d *= l;
		long l1 = Math.round(d);
		return (double)l1 / (double)l;
	}

	public static java.util.List processTimeBySecond(String s, String s1, int i)
	{
		Calendar calendar = Calendar.getInstance();
		int j = 0;
		int k = 0;
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		int k1 = 0;
		int l1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		if (!"".equals(s) && null != s)
		{
			ArrayList arraylist = TokenizerString(s, "-");
			if (0 < arraylist.size())
				j = Integer.parseInt((String)arraylist.get(0));
			if (1 < arraylist.size())
				k = Integer.parseInt((String)arraylist.get(1));
			if (2 < arraylist.size())
				l = Integer.parseInt((String)arraylist.get(2));
		}
		if (!"".equals(s1) && null != s1)
		{
			ArrayList arraylist1 = TokenizerString(s1, ":");
			if (0 < arraylist1.size())
				i1 = Integer.parseInt((String)arraylist1.get(0));
			if (1 < arraylist1.size())
				j1 = Integer.parseInt((String)arraylist1.get(1));
			if (2 < arraylist1.size())
				k1 = Integer.parseInt((String)arraylist1.get(2));
		}
		ArrayList arraylist2 = new ArrayList();
		String s2 = "";
		String s3 = "";
		calendar.set(j, k - 1, l, i1, j1, k1);
		if (i / 0x15180 >= 1)
		{
			l1 = i / 0x15180;
			i %= 0x15180;
		}
		if (i / 3600 >= 1)
		{
			i2 = i / 3600;
			i %= 3600;
		}
		if (i / 60 >= 1)
		{
			j2 = i / 60;
			i %= 60;
		}
		k2 = i;
		calendar.add(5, l1);
		calendar.add(10, i2);
		calendar.add(12, j2);
		calendar.add(13, k2);
		s2 = (new StringBuilder()).append(add0(calendar.get(1), 4)).append("-").append(add0(calendar.get(2) + 1, 2)).append("-").append(add0(calendar.get(5), 2)).toString();
		s3 = (new StringBuilder()).append(add0(calendar.getTime().getHours(), 2)).append(":").append(add0(calendar.getTime().getMinutes(), 2)).append(":").append(add0(calendar.getTime().getSeconds(), 2)).toString();
		arraylist2.add(s2);
		arraylist2.add(s3);
		return arraylist2;
	}

	public static String processTimeBySecond(String s, int i)
	{
		Calendar calendar = Calendar.getInstance();
		int j = 0;
		int k = 0;
		int l = 0;
		String s1 = "";
		if (!"".equals(s) && null != s)
		{
			ArrayList arraylist = TokenizerString(s, ":");
			if (0 < arraylist.size())
				j = Integer.parseInt((String)arraylist.get(0));
			if (1 < arraylist.size())
				k = Integer.parseInt((String)arraylist.get(1));
			if (2 < arraylist.size())
				l = Integer.parseInt((String)arraylist.get(2));
		}
		calendar.set(11, j);
		calendar.set(12, k);
		calendar.set(13, l);
		calendar.add(13, i);
		s1 = (new StringBuilder()).append(add0(calendar.getTime().getHours(), 2)).append(":").append(add0(calendar.getTime().getMinutes(), 2)).append(":").append(add0(calendar.getTime().getSeconds(), 2)).toString();
		return s1;
	}

	public static void main(String args[])
	{
		System.out.println(numtochinese("340345339.80"));
	}

	public static int length2(String s)
	{
		return s.getBytes().length;
	}

	public static String passwordBuilder(int i)
	{
		String s = "";
		String as[] = {
			"2", "3", "4", "5", "6", "7", "8", "9", "a", "d", 
			"e", "f", "g", "h", "i", "j", "m", "n", "r", "t", 
			"u", "y", "A", "B", "D", "E", "F", "G", "H", "J", 
			"L", "M", "N", "Q", "R", "T", "Y"
		};
		Random random1 = new Random();
		do
		{
			if (s.length() >= i)
				break;
			String s1 = as[random1.nextInt(37)];
			if (s.indexOf(s1) == -1)
				s = (new StringBuilder()).append(s).append(s1).toString();
		} while (true);
		return s;
	}

	public static String passwordBuilderNo(int i)
	{
		String s = "";
		String as[] = {
			"2", "3", "4", "5", "6", "7", "8", "9"
		};
		Random random1 = new Random();
		do
		{
			if (s.length() >= i)
				break;
			String s1 = as[random1.nextInt(8)];
			if (s.indexOf(s1) == -1)
				s = (new StringBuilder()).append(s).append(s1).toString();
		} while (true);
		return s;
	}

	public static String passwordBuilderEn(int i)
	{
		String s = "";
		String as[] = {
			"a", "d", "e", "f", "g", "h", "i", "j", "m", "n", 
			"r", "t", "u", "y", "A", "B", "D", "E", "F", "G", 
			"H", "J", "L", "M", "N", "Q", "R", "T", "Y"
		};
		Random random1 = new Random();
		do
		{
			if (s.length() >= i)
				break;
			String s1 = as[random1.nextInt(29)];
			if (s.indexOf(s1) == -1)
				s = (new StringBuilder()).append(s).append(s1).toString();
		} while (true);
		return s;
	}

	public static String milfloatFormat(String s)
	{
		String s1 = s;
		String s3 = "";
		if (!s.equals(""))
		{
			String s2;
			if (s.indexOf(".") == -1)
			{
				s2 = s;
			} else
			{
				if (!s.substring(0, s.indexOf(".")).equals(""))
					s2 = s.substring(0, s.indexOf("."));
				else
					s2 = "0";
				s3 = s.substring(s.indexOf(".") + 1);
			}
			double d = getDoubleValue(s2);
			DecimalFormat decimalformat = new DecimalFormat("###,###");
			if (s.indexOf(".") == -1)
				s3 = (new StringBuilder()).append("").append(decimalformat.format(d)).toString();
			else
				s3 = (new StringBuilder()).append("").append(decimalformat.format(d)).append(".").append(s3).toString();
		}
		return s3;
	}

	public static String numtochineseOld(String s)
	{
		String s1 = "零壹贰叁肆伍陆柒捌玖";
		String s2 = "分角整圆拾佰仟万拾佰仟亿拾佰仟";
		String s3 = "";
		String s4 = "";
		if (s == null || s.equals(""))
			return "";
		s3 = s.trim();
		float f;
		try
		{
			f = getFloatValue(s3);
		}
		catch (Exception exception)
		{
			return "";
		}
		int i = 0;
		if (s3.indexOf(".") == -1)
			i = s3.length();
		else
			i = s3.indexOf(".");
		if (i > s2.length() - 3)
			return "";
		boolean flag = false;
		String s5 = "";
		String s7 = "";
		String s9 = "";
		for (int k = 0; k < s3.length() && k <= i + 2; k++)
			if (k != i)
			{
				int j = Integer.parseInt(String.valueOf(s3.charAt(k)));
				String s6 = s1.substring(j, j + 1);
				j = (i - k) + 2;
				String s8 = s2.substring(j, j + 1);
				s4 = s4.concat(s6).concat(s8);
			}

		if (i == s3.length() || i == s3.length() - 1)
			s4 = s4.concat("整");
		if (i == s3.length() - 2)
			s4 = s4.concat("零分");
		for (; s4.indexOf("零零") != -1; s4 = StringReplace(s4, "零零", "零"));
		s4 = StringReplace(s4, "零亿", "亿");
		s4 = StringReplace(s4, "亿万", "亿");
		s4 = StringReplace(s4, "零万", "万");
		s4 = StringReplace(s4, "零仟", "零");
		s4 = StringReplace(s4, "零佰", "零");
		s4 = StringReplace(s4, "零拾", "零");
		s4 = StringReplace(s4, "零元", "零");
		s4 = StringReplace(s4, "零角", "");
		s4 = StringReplace(s4, "零分", "");
		return s4;
	}

	public static String numtochinese(String s)
	{
		String s1 = "";
		String s2 = "";
		if (s == null || s.equals(""))
			return "";
		s1 = s.trim();
		s1 = StringReplace(s1, ",", "");
		double d;
		try
		{
			d = getDoubleValue(s1);
		}
		catch (Exception exception)
		{
			return "";
		}
		String as[] = {
			"角", "分"
		};
		String as1[] = {
			"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"
		};
		String as2[][] = {
			{
				"圆", "万", "亿"
			}, {
				"", "拾", "佰", "仟"
			}
		};
		String s3 = d >= 0.0D ? "" : "负";
		d = Math.abs(d);
		String s4 = "";
		String s5 = s1.replaceAll("^\\d+(\\.)?", "");
		for (int i = 0; i < as.length; i++)
			try
			{
				int j = getIntValue((new StringBuilder()).append("").append(s5.charAt(i)).toString(), 0);
				s4 = (new StringBuilder()).append(s4).append(as1[j]).append(as[i]).toString();
			}
			catch (Exception exception1) { }

		if (s4.equals("零角零分"))
			s4 = "";
		else
		if (s4.indexOf("零分") != -1)
			s4 = s4.substring(0, 2);
		if (s4.length() < 1)
			s4 = "整";
		double d1 = Math.floor(d);
		for (int k = 0; k < as2[0].length && d1 > 0.0D; k++)
		{
			String s6 = "";
			for (int l = 0; l < as2[1].length && d > 0.0D; l++)
			{
				s6 = (new StringBuilder()).append(as1[(int)(d1 % 10D)]).append(as2[1][l]).append(s6).toString();
				d1 /= 10D;
			}

			s4 = (new StringBuilder()).append(s6.replaceAll("(零.)*零$", "").replaceAll("^$", "零")).append(as2[0][k]).append(s4).toString();
		}

		return (new StringBuilder()).append(s3).append(s4.replaceAll("(零.)*零圆", "圆").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零圆整")).toString();
	}

	public static String encodeAnd(String s)
	{
		return StringReplace(s, "&", "&amp;");
	}

	public static String encodeJS(String s)
	{
		s = StringReplace(s, "\\", "\\\\");
		s = StringReplace(s, "/", "\\/");
		s = StringReplace(s, "'", "\\'");
		s = StringReplace(s, "\n", "\\n");
		s = StringReplace(s, "\r", "\\r");
		return s;
	}

	public static String getTxtWithoutHTMLElement(String s)
	{
		String s1 = "<[^<0-9a-z|^>]+>";
		String s2 = "<script>[^<|^>]+</script>";
		return s.replaceAll(s2, "").replaceAll(s1, "");
	}

	public static String urlAddPara(String s, String s1)
	{
		if (s.indexOf("Homepage.jsp") == -1)
			return s;
		if ("".equals(s))
			return "#";
		if (s.indexOf("?") != -1)
			return (new StringBuilder()).append(s).append("&").append(s1).toString();
		else
			return (new StringBuilder()).append(s).append("?").append(s1).toString();
	}

	public static String HTMLtoTxt(String s)
	{
		if (s == null || s.trim().equals(""))
		{
			return "";
		} else
		{
			String s1 = s.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "");
			return s1;
		}
	}

	public static boolean isPicture(String s, String s1)
	{
		if ("".equals(s))
			return false;
		String s2 = s.substring(s.lastIndexOf(".") + 1, s.length());
		String as[][] = {
			{
				"bmp", "0"
			}, {
				"dib", "1"
			}, {
				"gif", "2"
			}, {
				"jfif", "3"
			}, {
				"jpe", "4"
			}, {
				"jpeg", "5"
			}, {
				"jpg", "6"
			}, {
				"png", "7"
			}, {
				"tif", "8"
			}, {
				"tiff", "9"
			}, {
				"ico", "10"
			}
		};
		for (int i = 0; i < as.length; i++)
		{
			if (!"".equals(s1) && as[i][0].equals(s2.toLowerCase()) && as[i][1].equals(s1))
				return true;
			if ("".equals(s1) && as[i][0].equals(s2.toLowerCase()))
				return true;
		}

		return false;
	}

	public static int getListIndex(ArrayList arraylist, String s)
	{
		int i = -1;
		try
		{
			s = null2String(s);
			if (arraylist == null || arraylist.size() < 1)
			{
				i = -1;
			} else
			{
				int j = 0;
				do
				{
					if (j >= arraylist.size())
						break;
					String s1 = null2String((String)arraylist.get(j)).trim();
					if (s1.equalsIgnoreCase(s))
					{
						i = j;
						break;
					}
					j++;
				} while (true);
			}
		}
		catch (Exception exception)
		{
			i = -1;
		}
		return i;
	}

	public static String stringReplace4DocDspExt(String s)
	{
		String s1 = "";
		try
		{
			s1 = StringReplace(s, "\\", "\\\\");
			s1 = StringReplace(s1, "&lt;", "<");
			s1 = StringReplace(s1, "&gt;", ">");
			s1 = StringReplace(s1, "&quot;", "\"");
			s1 = StringReplace(s1, "\n", "\n");
			s1 = StringReplace(s1, "\r", "\r");
			s1 = StringReplace(s1, "\"", "\\\"");
			s1 = StringReplace(s1, "&#8226;", "\267");
		}
		catch (Exception exception)
		{
			s1 = s;
		}
		return s1;
	}

	public static String toScreenForJs(String s)
	{
		String s1;
		if (s == null)
			s = null2String(s);
		s1 = s;
		s1 = StringReplace(s1, "\\", "\\\\");
		s1 = StringReplace(s1, "\"", "\\\"");
		return s1;
	}

	public static String toScreenForJsBase(String s)
	{
		String s1;
		if (s == null)
			s = null2String(s);
		s1 = s;
		s1 = StringReplace(s1, "\"", "\\\"");
		return s1;
	}

	public static boolean isExt(String s)
	{
		boolean flag = false;
		if (s == null || s.trim().equals(""))
			return flag;
		if (s.equalsIgnoreCase("doc") || s.equalsIgnoreCase("xls") || s.equalsIgnoreCase("ppt") || s.equalsIgnoreCase("docx") || s.equalsIgnoreCase("xlsx") || s.equalsIgnoreCase("pptx") || s.equalsIgnoreCase("wps") || s.equalsIgnoreCase("et"))
			flag = true;
		return flag;
	}

	public static String convertDbInput(String s)
	{
		s = null2String(s);
		s = s.replace("'", "‘");
		return s;
	}


	public static String object2String(Object obj)
	{
		return obj != null ? obj.toString() : "";
	}

	public static boolean isExcuteFile(String s)
	{
		boolean flag = false;
		int i = s.lastIndexOf(".");
		if (i != -1)
		{
			String s1 = null2String(s.substring(i + 1));
			if (s1.equalsIgnoreCase("jsp") || s1.equalsIgnoreCase("php"))
				flag = true;
		}
		return flag;
	}

	public static String date(int i)
	{
		String s = "";
		SimpleDateFormat simpledateformat = null;
		if (i == 1)
			simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		else
		if (i == 2)
			simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else
		if (i == 3)
			simpledateformat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date1 = new Date();
		s = simpledateformat.format(date1);
		return s;
	}

	public static String getRandomString(int i)
	{
		StringBuffer stringbuffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer stringbuffer1 = new StringBuffer();
		Random random1 = new Random();
		int j = stringbuffer.length();
		for (int k = 0; k < i; k++)
			stringbuffer1.append(stringbuffer.charAt(random1.nextInt(j)));

		return stringbuffer1.toString();
	}

	public static String TrimComma(String s)
	{
		String s1 = "";
		String as[] = s.split(",");
		for (int i = 0; i < as.length; i++)
			if (!"".equals(as[i]))
				s1 = (new StringBuilder()).append(s1).append(as[i]).append(",").toString();

		int j = s1.length();
		int k = 0;
		int l = 0;
		char ac[];
		for (ac = s1.toCharArray(); k < j && ac[l + k] <= ','; k++);
		for (; k < j && ac[(l + j) - 1] <= ','; j--);
		return k <= 0 && j >= s1.length() ? s1 : s1.substring(k, j);
	}

	public static String sendRedirect(String s, String s1)
	{
		return sendRedirect(s, "0", "0", s1, "window");
	}

	public static String sendRedirect(String s, String s1, String s2, String s3, String s4)
	{
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append("<script type='text/javascript'>\n");
		if (!"".equals(s3))
			stringbuilder.append("parent.getParentWindow(window).parent.parent.refreshTree(").append(s1).append(",").append(s2).append(");\n");
		else
			stringbuilder.append("parent.parent.refreshTreeMain(").append(s1).append(",'").append(s2).append("');\n");
		stringbuilder.append(s4).append(".location.href='").append(s).append("'\n</script>");
		return stringbuilder.toString();
	}

	public static String getSubINClause(String s, String s1, String s2)
	{
		return getSubINClause(s, s1, s2, 999);
	}

	public static String getSubINClause(String s, String s1, String s2, int i)
	{
		String as[] = StringUtils.split(s, ",");
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		for (int j = 0; j < as.length; j++)
		{
			if (arraylist1.size() >= i)
			{
				String s5 = StringUtils.join(arraylist1, ',');
				arraylist.add((new StringBuilder()).append(s1).append(" ").append(s2).append(" (").append(s5).append(")").toString());
				arraylist1 = new ArrayList();
			}
			if (!StringUtils.isEmpty(as[j]))
				arraylist1.add(as[j]);
		}

		if (arraylist1.size() > 0)
		{
			String s3 = StringUtils.join(arraylist1, ',');
			arraylist.add((new StringBuilder()).append(s1).append(" ").append(s2).append(" (").append(s3).append(")").toString());
		}
		String s4 = "IN".equalsIgnoreCase(s2) ? " OR " : " AND ";
		return StringUtils.join(arraylist, s4);
	}

	public static String getIpAddr(HttpServletRequest httpservletrequest)
	{
		String s = httpservletrequest.getHeader("x-forwarded-for");
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
			s = httpservletrequest.getHeader("Proxy-Client-IP");
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
			s = httpservletrequest.getHeader("WL-Proxy-Client-IP");
		if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
			s = httpservletrequest.getRemoteAddr();
		if (s.indexOf(",") >= 0)
			s = s.substring(0, s.indexOf(","));
		return s;
	}

	public static String getByteNumString(String s, int i)
	{
		if ("".equals(s) || s == null)
			return "";
		int j = 0;
		String s1 = "";
		int k = 0;
		do
		{
			if (k >= s.length())
				break;
			byte abyte0[] = (new StringBuilder()).append(s.charAt(k)).append("").toString().getBytes();
			j += abyte0.length;
			if (j > i)
				break;
			s1 = (new StringBuilder()).append(s1).append(s.charAt(k)).toString();
			k++;
		} while (true);
		if (s.getBytes().length > i)
			s1 = (new StringBuilder()).append(s1).append("...").toString();
		return s1;
	}

	public static String formatMultiLang(String s, String s1)
	{
		s1 = null2String(s1);
		if (s == null || "".equals(s) || s.indexOf("~`~`") == -1)
			return s;
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(?<!value {0,3}= {0,3}('|\"))(~`~`(([\\d ]{1,2}[^`]*?`~`)+[\\d ]{1,2}[^`]*?)`~`~).*?", 2);
		Matcher matcher = pattern.matcher(s);
		StringBuffer stringbuffer = new StringBuffer();
		boolean flag = matcher.find();
		if (s1.equals(""))
			s1 = "7";
		for (; flag; flag = matcher.find())
		{
			String s2 = matcher.group(2);
			String s3 = transLang(s2, s1);
			matcher.appendReplacement(stringbuffer, s3);
		}

		matcher.appendTail(stringbuffer);
		return stringbuffer.toString();
	}

	public static String transLang(String s, String s1)
	{
		String s2 = s1;
		if (s1.length() < 2)
			s1 = (new StringBuilder()).append(s1).append(" ").toString();
		int i = s.indexOf("~`~`");
		int j = s.lastIndexOf("`~`~");
		if (i != -1 && j != -1 && i + 4 < j)
		{
			String as[] = s.substring(i + 4, j).split("`~`");
			String as1[] = as;
			int k = as1.length;
			for (int l = 0; l < k; l++)
			{
				String s3 = as1[l];
				if (s3.startsWith(s1))
					return s3.substring(s1.length());
				if (s3.startsWith(s2))
					return s3.substring(s2.length());
			}

		}
		return s;
	}

	public static boolean validateFileExt(String s, String as[])
	{
		if (s != null && as != null)
		{
			for (int i = 0; i < as.length; i++)
				if (s.toLowerCase().endsWith(as[i].toLowerCase()))
					return true;

			return false;
		} else
		{
			return true;
		}
	}

	public static boolean validateFileExt(String s)
	{
		String as[] = {
			"exe", "jsp", "class", "bat", "jar"
		};
		if (s != null && as != null)
		{
			for (int i = 0; i < as.length; i++)
				if (s.toLowerCase().endsWith(as[i].toLowerCase()))
					return false;

			return true;
		} else
		{
			return true;
		}
	}

	public static String arrayUnion(String as[], String as1[])
	{
		String s = "";
		int i = as.length;
		int j = as1.length;
		if (i > j)
		{
			for (int k = 0; k < i; k++)
			{
				for (int i1 = 0; i1 < j; i1++)
					if (as[k] == as1[i1])
						s = (new StringBuilder()).append(s).append(as[k]).append(",").toString();

			}

		} else
		{
			for (int l = 0; l < j; l++)
			{
				for (int j1 = 0; j1 < i; j1++)
					if (as1[l] == as[j1])
						s = (new StringBuilder()).append(s).append(as[l]).append(",").toString();

			}

		}
		s = (new StringBuilder()).append(s).append("0").toString();
		return s;
	}

	public static String getNumStr(double d)
	{
		String s = (new StringBuilder()).append("").append(d).toString();
		if ("".equals(s) || s == null)
			return "";
		int i = s.indexOf(".");
		String s1 = s.substring(i);
		String s2 = DecimalFormat.getInstance().format(d);
		String s3 = s2.replaceAll(",", "");
		if (".0".equals(s1) || ".00".equals(s1) || ".000".equals(s1) || ".0000".equals(s1))
			s3 = (new StringBuilder()).append(s3).append(s1).toString();
		if ("0".equals(s3))
			s3 = "";
		return s3;
	}

	public static String getNumStr(int i)
	{
		String s = (new StringBuilder()).append("").append(i).toString();
		if ("".equals(s) || s == null)
		{
			return "";
		} else
		{
			String s1 = DecimalFormat.getInstance().format(i);
			String s2 = s1.replaceAll(",", "");
			return s2;
		}
	}

	public static Map stringToMap(String s)
	{
		HashMap hashmap = new HashMap();
		String as[] = s.split("&");
		String as1[] = as;
		int i = as1.length;
		for (int j = 0; j < i; j++)
		{
			String s1 = as1[j];
			String as2[] = s1.split("=");
			if (as2.length < 2)
				continue;
			String s2 = null2String(as2[0]);
			String s3 = null2String(as2[1]);
			if (!"".equals(s2))
				hashmap.put(s2, s3);
		}

		return hashmap;
	}

}
