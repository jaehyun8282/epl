package controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sservice.CommandProcess;

@WebServlet(urlPatterns="*.co",
	initParams={@WebInitParam(name="config", // 이름 config
	value="/WEB-INF/command.properties")})   // 이름에 대한 값
public class Controller3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<>();
	public void init(ServletConfig config) throws ServletException { 
	   	String props = config.getInitParameter("config");
	   	// props : "/WEB-INF/command.properties"
	   	Properties pr = new Properties();
	   	// Java 11장 Properties클래스의 특징 키=값을 가진 Map을 구현
	    FileInputStream f = null;
	    try {
	          String configFilePath = 
	         		config.getServletContext().getRealPath(props);
	          // configFilePath 실제 사용할 위치에 있는 이름, eclipse
	          f = new FileInputStream(configFilePath);
	          pr.load(f);
	          // pr에는 command.properties라는 file의 데이터를 사용
	          // =앞에 있는 message.co을 key
	          // =뒤에 있는 service.Message을 값
	    } catch (IOException e) { throw new ServletException(e);
	     } finally {
	          if (f != null) try { f.close(); } catch(IOException ex) {}
	     }
	     Iterator<Object> keyIter = pr.keySet().iterator();
	     // message.co, hello.co
	     while( keyIter.hasNext() ) {
	          String command = (String)keyIter.next(); 
	          // command : message.co, hello.co
	          String className = pr.getProperty(command); 
	          // className : service.Message문자, service.Hello
	          try {
	               Class<?> commandClass=Class.forName(className);
	               // commandClass : service.Message 클래스 생성
	               Object commandInstance=commandClass.newInstance();
	               // commandInstance : service.Message객체 생성
	               commandMap.put(command, commandInstance);
	               // commnadMap에는
	               // key가 message.co, hello.co
	               // 값이 Message객체      Hello의 객체
	          } catch (Exception e) {
	               throw new ServletException(e);
	          }
	     }
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		String view = null;
	    CommandProcess com=null;
	    try { 
	    	  String command = request.getRequestURI();
	    	  // url : http://localhost:8080/ch13/message.co
	    	  // command, uri : /ch13/message.co
	    	  // request.getContextPath() : /ch13
	    	  // request.getContextPath().length()+1 : 6
		      command = command.substring( // 문자 일부만 추출
		    		 request.getContextPath().length()+1); 
		      // command : message.co
	          com = (CommandProcess)commandMap.get(command); 
	          System.out.println("command = "+command);
	          System.out.println("com= "+com);
	          view = com.requestPro(request, response);
	          // view는 "message" 문자
	    } catch(Throwable e) { throw new ServletException(e); }
	    // main.jsp를 실행하는 데 매개변수 pgm에 값을 대입
	    RequestDispatcher dispatcher =
	      	request.getRequestDispatcher(
	      		"template.jsp?menu="+view+".jsp");
	   dispatcher.forward(request, response);
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    		request.setCharacterEncoding("utf-8");
    		doGet(request, response);
	}
}