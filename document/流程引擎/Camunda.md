## Camunda



#### 工作流简介

> 工作流（Workflow），指“业务过程的部分或整体在计算机应用环境下的自动化”。是对工作流程及其各操作步骤之间业务规则的抽象、概括描述。在计算机中，工作流属于计算机支持的协同工作（CSCW）的一部分。后者是普遍地研究一个群体如何在计算机的帮助下实现协同工作的。
> 工作流主要解决的主要问题是：为了实现某个业务目标，利用计算机在多个参与者之间按某种预定规则自动传递文档、信息或者任务。



#### Camunda简介

> Camunda Platform 是一个基于 Java 的框架。主要组件是用 Java 编写的，我们主要致力于为 Java 开发人员提供他们需要的工具，以便在 JVM 上设计、实现和运行业务流程和工作流。不过，我们还希望让非 java 开发人员可以使用流程引擎技术。这就是为什么 Camunda Platform 还提供一个 REST API，它允许您构建连接到远程进程引擎的应用程序。
>
> Camunda Platform 既可以作为独立的进程引擎服务器使用，也可以嵌入到定制的 Java 应用程序中。可嵌入性要求是 Camunda 平台中许多架构决策的核心。

![流程引擎组件构建](../../../../images/typora-images/流程引擎组件构建.png)

#### Process Engine Architecture

![img](../../../../images/typora-images/process-engine-architecture.png)

> **Public API** :面向服务的 API，允许 Java 应用程序与流程引擎交互。流程引擎的不同职责（即流程存储库、运行时流程交互、任务管理……）被分成单独的服务。



> **BPMN2.0Core Engine** : 这是流程引擎的核心。它具有一个用于图结构的轻量级执行引擎(PVM-Process Virtual Machine)、一个将 BPMN 2.0 XML 文件转换为 Java 对象的 BPMN 2.0解析器和一组 BPMN 行为实现(提供诸如 Gateways 或 Service Tasks 等 BPMN 2.0构造的实现)



> **Job Executor**: 作业执行器负责处理异步后台工作，如进程中的定时器或异步延续



> **The Persistence Layer** :流程引擎的特点是有一个持久层，负责将流程实例状态持久化到关系数据库



![img](../../../../images/typora-images/api.services.png)

​                                                        **ProcessEngine**

|      |             引擎类             |            作用            |
| ---- | :----------------------------: | :------------------------: |
| 0    | **ProcessEngineConfiguration** |       流程引擎配置类       |
| 1    |     **RespositoryService**     |        操作流程定义        |
| 2    |       **HistoryService**       |       查询历史表数据       |
| 3    |        **TaskService**         |          操作任务          |
| 4    |        IdentityService         |       操作用户或者组       |
| 5    |       **RuntimeService**       |        操作流程实列        |
| 6    |          FormService           |        操作流程表单        |
| 7    |      AuthorizationService      |        授权相关服务        |
| 8    |       ManagementService        | 执行cmd与以及job相关的服务 |
| 9    |      ExternalTaskService       |     外部任务相关的服务     |
| 10   |          CaseService           |        CMMN相关操作        |
| 11   |          FiterService          |       过滤相关的服务       |
| 12   |        DecisionService         |        DMN相关操作         |

#### 流程引擎使用场景

**（一）嵌入式流程引擎**

![img](../../../../images/typora-images/embedded-process-engine.png)

> 在这种情况下，流程引擎作为应用程序库添加到自定义应用程序中。通过这种方式，流程引擎可以很容易地在应用程序生命周期中启动和停止。可以在共享数据库上运行多个嵌入式进程引擎



**(二) 共享的，容器管理的过程引擎**

![img](../../../../images/typora-images/shared-process-engine.png)

> 在这种情况下，流程引擎在运行时容器(Servlet 容器，Application Server，...)内启动。流程引擎是作为容器服务提供的，可以由部署在容器内的所有应用程序共享。这个概念可以与 JMS 消息队列相比较，后者由运行时提供，可以由所有应用程序使用。流程部署和应用程序之间存在一对一的映射: 流程引擎跟踪应用程序部署的流程定义，并将执行委托给相关的应用程序。



**（三）独立(远程)进程引擎服务器**

![img](../../../../images/typora-images/standalone-process-engine.png)

> 在这种情况下，流程引擎作为网络服务提供。在网络上运行的不同应用程序可以通过远程通信通道与进程引擎进行交互。使流程引擎可以远程访问的最简单方法是使用内置的 REST API。不同的通信通道(如 soapwebservices 或 JMS)是可能的，但需要由用户实现。



#### Camunda流程引擎术语

- **Process Definition**

  >  Process Definition即流程定义。Process Definition定义了流程的结构，或者说定义了业务活动的执行过程。Camunda bpm使用bpmn2.0作为其流程定义的主要建模语言。

  

- **Process Instance**

  > Process Instance即流程实例。流程实例是流程定义的单独执行，流程定义和流程实例是一对多关系。流程实例与流程定义的关系与面向对象编程中对象与类的关系相同（在这种类比中，流程实例扮演对象的角色，流程定义扮演类的角色）。流程定义设计完成后，发布到BPM，通过流程引擎解析流程定义，发起一次流程即创建了一个流程实例，比如：创建了一个“请假流程”，这是一个流程定义，张三发起了一次请假流程，即创建了一个流程实例，李四也发起了一次请假，就是创建了另一个流程实例，这两个实例均基于流程定义创建生成。



- **Execution**

  > Execution即流程执行实例，如果流程实例包含多个执行路径（例如，在并行网关之后），则会同时产生多个执行实例，即execution, 通过excutionId能够区分流程实例内的当前活动路径。如下流程图，“receive payment”和“ship order”节点同时运行，即有两个execution在运行。

![img](../../../../images/typora-images/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d4ejI1OA==,size_16,color_FFFFFF,t_70#pic_center.png)



- **Activity Instance**

  > Activity Instance即活动实例，活动实例概念与执行概念类似，但采用了不同的视角。虽然可以将执行想象为在流程中移动的令牌，但活动实例表示活动（任务、子流程等）的单个实例。因此，活动实例的概念更面向状态。



- **Process Variable**

  > Process Variable即流程变量，流程变量在整个工作流中扮演很重要的作用，是业务和流程引擎之间交互信息的载体，业务可以把数据放到流程变量里传递给流程引擎，流程引擎也可以把信息放到流程变量给传递给业务，流程变量最常见的用途有路由条件表达式、流程执行事件参数等。例如：请假流程中有请假天数、请假原因等一些参数都为流程变量的范围。流程变量的作用域范围是流程实例，也就是说各个流程实例的流程变量是不相互影响的。



- **Cockpit**

  > Camunda Platform Cockpit 是一个用于监控和操作的 Web 应用程序。它提供对已部署的 BPMN 流程和 DMN 决策的访问，允许搜索正在运行和已结束的实例并在这些实例上执行操作。



- **Tasklist**

  > Tasklist即任务列表，也就是待办任务。当流程节点是人工任务类型时，才可产生任务列表。



- **Admin**

  > Admin 是一个应用程序，它允许您通过引擎的身份服务和引擎的授权服务来配置用户和组。此外，您可以将 Camunda Admin 连接到您的 LDAP 系统。



- 

| 名称              | 中文名称    | 描述                              |
| ----------------- | ----------- | --------------------------------- |
| Definition ID     | 流程定义id  | 一类流程实例具有相同的流程定义id  |
| Definition Key    | 流程定义key | 一类流程实例具有相同的流程定义key |
| Deployment ID     | 流程部署id  | 一个流程部署一次具有不同的部署Id  |
| ProcessInstanceId | 流程实例Id  | 一个流程实例具有唯一的流程实例Id  |
| Business Key      | 业务key     | 一个流程实例应该具有唯一的业务key |







#### Demo示列



以请假流程为例

> 1. 定义流程：【管理员】新建流程、设计流程模型、并设置用户任务的审批人，最终发布流程
> 2. 发起流程：【员工】选择流程，并发起流程实例
> 3. 审批流程：【审批人】接收到流程任务，审批结果为通过或不通过

![流程引擎定义流程](../../../../images/typora-images/流程引擎定义流程.png)



> 根据业务需要，业务通过建立独立的数据库表（业务表）记录申请信息，而流程引擎只负责推动流程的前进或者结束。两者需要进行**双向**的关联：
>
> - 每一条业务表记录，通过它的流程实例的编号( `process_instance_id` )指向对应的流程实例
> - 每一个流程实例，通过它的业务键( `BUSINESS_KEY_` ) 指向对应的业务表记录。

[Camunda表单](https://docs.camunda.org/manual/latest/user-guide/task-forms/)

![Diagram 1](../../../../images/typora-images/Diagram 1.jpg)



> 根据业务创建前端相对应的业务实现
>
>  PROCESS_KEY    静态变量：是业务对应的流程定义Key的编号 ,同一个流程的版本升级是不可以不改变流程定义的。
>
> 

![1654742587579](../../../../images/typora-images/1654742587579.png)





> 流程走完之后，配置监听器自动去更改请假状态

![1654745214908](../../../../images/typora-images/1654745214908.png)