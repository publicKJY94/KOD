package controller.util;

public class ActionForward {
	private String path; // 어디로 가야하는지 == 경로
	private boolean redirect; // 어떻게 갈지 == 리다이렉트 or 포워드
	
	public ActionForward() {
		/* [정현진]
		 * super(); 부모클래스 생성자가 생략되어있음 
		 * -> extends를 사용하여 상속받은 클래스가 없는데 어떤 클래스를 상속받았다는 거니 ?
		 * -> 특정클래스를 상속받지 않았을 때는 자동으로 자바의 최상위 클래스인 Object 클래스를 상속받게 됨
		 */
	}

	public String getPath() { // 경로
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() { // 데이터 전달 여부
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
}

/*
 * 
 */
