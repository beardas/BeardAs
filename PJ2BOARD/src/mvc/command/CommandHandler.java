package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

		// CommandHandler �������̽��� �����Ѵ�.
public interface CommandHandler {
		// ��� ��ɾ� �ڵ鷯 Ŭ������ �������� �����ؾ� �ϴ� process �޼��� ����
		// ��ɾ� �ڵ鷯 Ŭ������ process �޼��带 �̿��ؼ� �˸��� ���� �ڵ带 �����ϰ�,
		// ����� ������ JSP�� URI�� ������.
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
	
}

