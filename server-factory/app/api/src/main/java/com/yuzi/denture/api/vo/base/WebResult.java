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
	private String resultCode;
	//消息描述
	 @ApiModelProperty(position=2,value="消息描述")
	private String message;
	@ApiModelProperty(position=3,value="错误码")
	private String errorCode;
	private T data;

	public static WebResult successResult(){
		WebResult result = new WebResult(){};
		result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
		return result;
	}

	public static WebResult failureResult(String errorCode, String msg){
		WebResult result = new WebResult(){};
		result.setResultCode(WebConstants.RESULT_FAIL_CODE);
		result.setErrorCode(errorCode);
		result.setMessage(msg);
		return result;
	}

	public static WebResult failureResult(String msg){
		WebResult result = new WebResult(){};
		result.setResultCode(WebConstants.RESULT_FAIL_CODE);
		result.setMessage(msg);
		return result;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
