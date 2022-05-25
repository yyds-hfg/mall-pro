package com.hacker.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * 流程请求对象
 *
 * @Author: Zero
 * @Date: 2022/5/25 11:44
 * @Description:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="ProcessRequest-流程提交参数对象")
@Data
public class ProcessRequest {

	@ApiModelProperty(value="流程定义ID")
	private String	processDefId;
	
	@ApiModelProperty(value="流程定义Key")
	private String	processDefKey;
	
	@ApiModelProperty(value="流程发起者")
	private String	starter;
	
	@ApiModelProperty(value="流程标题")
	private	String	title;
	
	@ApiModelProperty(value="外部业务系统数据主键标识值")
	private String businessKey;
	
	@ApiModelProperty(value="流程变量键值对")
	private Map<String, Object> variables;
   
	public static String REJECT_TO_START ="1";
	public static String REJECT_TO_LAST ="2";
	public static String REJECT_TO_TARGET ="3";
   
	public static String WITHDRAW_TO_START ="1";
	public static String WITHDRAW_TO_TARGET ="2";
	
}
