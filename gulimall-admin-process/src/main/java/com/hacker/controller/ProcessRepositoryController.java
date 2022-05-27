package com.hacker.controller;

import com.hacker.result.R;
import com.hacker.service.ProcessRepositoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Zero
 * @Date: 2022/5/24 13:34
 * @Description:
 */
@RestController
@Api(tags = "流程定义控制器")
@RequestMapping("/repository")
public class ProcessRepositoryController {

    @Autowired
    private ProcessRepositoryService processRepositoryService;

    @ApiOperation("获取最新的流程定义信息")
    @GetMapping("/getProcessDefinition/{definitionKey}")
    public R<?> getProcessDefinition(@NotBlank(message = "流程定义Key不能为空")
                                     @PathVariable String definitionKey) {
        return R.run(() -> processRepositoryService.getProcessDefinition(definitionKey));
    }

    @ApiOperation("得到部署的流程")
    @GetMapping("/getDeployProcess")
    public R<?> getDeployProcess() {
        return R.run(() -> processRepositoryService.getDeploymentInfo());
    }

}

