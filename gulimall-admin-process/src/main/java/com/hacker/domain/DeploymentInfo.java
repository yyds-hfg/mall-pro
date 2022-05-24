package com.hacker.domain;

import cn.hutool.core.date.DateUtil;
import lombok.Builder;
import lombok.Data;
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ResourceEntity;
import org.camunda.bpm.engine.repository.Deployment;

import java.util.Date;
import java.util.Map;

/**
 * @Author: Zero
 * @Date: 2022/5/24 16:28
 * @Description:
 */
@Data
@Builder
public class DeploymentInfo {

    protected String deploymentId;

    protected String deploymentName;

    protected String deploymentTime;

    protected boolean isNew;

    protected String source;

    public static DeploymentInfo getInstance(Deployment deployment) {
        DeploymentEntity deploymentEntity = (DeploymentEntity) deployment;
        return DeploymentInfo.builder()
                .deploymentId(deploymentEntity.getId())
                .deploymentName(deploymentEntity.getName())
                .deploymentTime(DateUtil.format(deploymentEntity.getDeploymentTime(),"yyyy-MM-dd hh:mm:ss"))
                .isNew(deploymentEntity.isNew())
                .source(deploymentEntity.getSource())
                .build();
    }
}
