## Camunda

#### 一、Camunda简介

> Camunda Platform 是一个基于 Java 的框架。主要组件是用 Java 编写的，我们主要致力于为 Java 开发人员提供他们需要的工具，以便在 JVM 上设计、实现和运行业务流程和工作流。不过，我们还希望让非 java 开发人员可以使用流程引擎技术。这就是为什么 Camunda Platform 还提供一个 REST API，它允许您构建连接到远程进程引擎的应用程序。
>
> Camunda Platform 既可以作为独立的进程引擎服务器使用，也可以嵌入到定制的 Java 应用程序中。可嵌入性要求是 Camunda 平台中许多架构决策的核心。

#### 二、Process Engine Architecture

![img](../../../../images/typora-images/process-engine-architecture.png)

> **Public API** :面向服务的 API，允许 Java 应用程序与流程引擎交互。流程引擎的不同职责（即流程存储库、运行时流程交互、任务管理……）被分成单独的服务。



> **BPMN2.0Core Engine** : 这是流程引擎的核心。它具有一个用于图结构的轻量级执行引擎(PVM-Process Virtual Machine)、一个将 BPMN 2.0 XML 文件转换为 Java 对象的 BPMN 2.0解析器和一组 BPMN 行为实现(提供诸如 Gateways 或 Service Tasks 等 BPMN 2.0构造的实现)



> **Job Executor**: 作业执行器负责处理异步后台工作，如进程中的定时器或异步延续



> **The Persistence Layer** :流程引擎的特点是有一个持久层，负责将流程实例状态持久化到关系数据库



![img](../../../../images/typora-images/api.services.png)

​                                                        **ProcessEngine**

|      |        引擎类        |            作用            |
| ---- | :------------------: | :------------------------: |
| 1    |  RespositoryService  |        操作流程定义        |
| 2    |    HistoryService    |       查询历史表数据       |
| 3    |     TaskService      |          操作任务          |
| 4    |   IdentityService    |       操作用户或者组       |
| 5    |    RuntimeService    |        操作流程实列        |
| 6    |     FormService      |        操作流程表单        |
| 7    | AuthorizationService |        授权相关服务        |
| 8    |  ManagementService   | 执行cmd与以及job相关的服务 |
| 9    | ExternalTaskService  |     外部任务相关的服务     |
| 10   |     CaseService      |        CMMN相关操作        |
| 11   |     FiterService     |       过滤相关的服务       |
| 12   |   DecisionService    |        DMN相关操作         |

#### 三、流程引擎使用场景

**（一）嵌入式流程引擎**

![img](../../../../images/typora-images/embedded-process-engine.png)

> 在这种情况下，流程引擎作为应用程序库添加到自定义应用程序中。通过这种方式，流程引擎可以很容易地在应用程序生命周期中启动和停止。可以在共享数据库上运行多个嵌入式进程引擎



**(二) 共享的，容器管理的过程引擎**

![img](../../../../images/typora-images/shared-process-engine.png)

> 在这种情况下，流程引擎在运行时容器(Servlet 容器，Application Server，...)内启动。流程引擎是作为容器服务提供的，可以由部署在容器内的所有应用程序共享。这个概念可以与 JMS 消息队列相比较，后者由运行时提供，可以由所有应用程序使用。流程部署和应用程序之间存在一对一的映射: 流程引擎跟踪应用程序部署的流程定义，并将执行委托给相关的应用程序。



**（三）独立(远程)进程引擎服务器**

![img](../../../../images/typora-images/standalone-process-engine.png)

> 在这种情况下，流程引擎作为网络服务提供。在网络上运行的不同应用程序可以通过远程通信通道与进程引擎进行交互。使流程引擎可以远程访问的最简单方法是使用内置的 REST API。不同的通信通道(如 soapwebservices 或 JMS)是可能的，但需要由用户实现。

#### 四、Camunda集群

![img](../../../../images/typora-images/clustered-process-engine.png)

> 为了提供扩展或故障转移功能，流程引擎可以分布到集群中的不同节点。然后，每个流程引擎实例必须连接到共享数据库。
