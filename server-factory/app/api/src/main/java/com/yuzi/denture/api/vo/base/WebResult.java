/**
 * 
 */
package com.yuzi.denture.api.vo.base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;

@ApiModel
public class WebResult<T> {
	//1表示成功，非1表示失败
	@ApiModelProperty(position=1,required = true, value="0表示成功,2表示登录过期，非0表示失败")
	private String code;
	//消息描述
	 @ApiModelProperty(position=2,value="消息描述")
	private String message;
	private T data;

	public WebResult() {
		this(ResponseCode.SUCCESS);
	}

	public WebResult(ResponseCode responseCode) {
		code = responseCode.code();
		message = responseCode.msg();
	}

	public static WebResult success(){
		return new WebResult(ResponseCode.SUCCESS);
	}

	public static WebResult expired() {
		return new WebResult(ResponseCode.TOKEN_EXPIRED);
	}

	public static WebResult failure() {
		return new WebResult(ResponseCode.FAILURE);
	}

	public static WebResult failure(String errorCode, String msg){
		WebResult result = new WebResult(){};
		result.setCode(errorCode);
		result.setMessage(msg);
		return result;
	}

	public static WebResult failure(String msg){
		WebResult result = new WebResult(){};
		result.setCode(ResponseCode.FAILURE.code());
		result.setMessage(msg);
		return result;
	}

	public static <T> WebResult<T> execute(Action function, String exceptionLog, Logger logger) {
		WebResult<T> result = WebResult.success();
		try {
			function.doAction(result);
		}  catch (Exception e) {
			logger.warn(exceptionLog+": {}",e);
			result.setCode(ResponseCode.FAILURE.code());
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public interface Action {
		void doAction(WebResult result);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
