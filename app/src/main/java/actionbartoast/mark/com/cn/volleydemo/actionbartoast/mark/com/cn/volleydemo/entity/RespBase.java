package actionbartoast.mark.com.cn.volleydemo.actionbartoast.mark.com.cn.volleydemo.entity;

public class RespBase {
	private int code;
	private String Msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}
	
	public boolean isSuccess() {
		return code == 0;
	}
}
