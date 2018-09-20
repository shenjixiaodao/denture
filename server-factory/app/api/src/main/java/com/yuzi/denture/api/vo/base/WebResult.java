/**
 * 
 */
package com.yuzi.denture.api.vo.base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WebResult<T> {
	//1表示成功，非1表示失败
	@ApiModelProperty(position=1,required = true, value="0表示成功，非0表示失败")
	private String code;
	//消息描述
	 @ApiModelProperty(position=2,value="消息描述")
	private String message;
	private T data;

	public WebResult() {
		code = WebConstants.RESULT_SUCCESS_CODE;
	}

	public static WebResult successResult(){
		WebResult result = new WebResult(){};
		result.setCode(WebConstants.RESULT_SUCCESS_CODE);
		return result;
	}

	public static WebResult failureResult(String errorCode, String msg){
		WebResult result = new WebResult(){};
		result.setCode(errorCode);
		result.setMessage(msg);
		return result;
	}

	public static WebResult failureResult(String msg){
		WebResult result = new WebResult(){};
		result.setCode(WebConstants.RESULT_FAIL_CODE);
		result.setMessage(msg);
		return result;
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
