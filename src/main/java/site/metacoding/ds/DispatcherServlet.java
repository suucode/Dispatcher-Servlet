package site.metacoding.ds;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {//소켓구현완료
	
	@Override //부모(HttpServlet의 메서드 재정의)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doProcess 요청됨");
		String httpMethod =req.getMethod();
		//System.out.println(httpMethod);
		String identifier = req.getRequestURI();
		//System.out.println(identifier);
		
		//공통 로직 처리
		UserController c = new UserController();
		
		Method[] methodList = c.getClass().getDeclaredMethods();
		for (Method method : methodList) { //methodList의 크기만큼 돈다 -> 오른쪽엔 컬렉션을 넣는다
			//System.out.println(method.getName());
			Annotation annotation =
					method.getDeclaredAnnotation(RequestMapping.class);
			RequestMapping requestMapping = (RequestMapping) annotation; //다운캐스팅
			try {
				//System.out.println(requestMapping.value());
				if(identifier.equals(requestMapping.value())) {
					method.invoke(c);//invoke는 호출, 메모리에 뜬 주소와 파라미터를 넣는데 우린 파라미터가 없어서 주소만!
				}
			} catch (Exception e) {
				System.out.println(method.getName()+"은 어노테이션이 없습니다.");
			}
		}
	}
}
