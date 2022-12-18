package command;
import javax.servlet.http.*;
public interface CommandAction {
	//인터페이스 메서드는 선언만 한다
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
	throws Throwable;
	
}
