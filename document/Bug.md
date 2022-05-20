##  

#### 解决Idea中Lombok问题

> Idea编写代码时，使用@Getter、@Setter、@Slf4j等lombok的注解，代码编译阶段无问题，编写代码并不会报错，单运行代码时报错调用get、set方法时会报错：
>
>解决办法：
>
>在File | Settings | Build, Execution, Deployment | Compiler | VM options设置 -Djps.track.ap.dependencies=false

#### 阿波罗配置中心启动报错问题

2022-05-20 16:55:50.037 INFO 7440 --- [main] c.c.f.f.i.p.DefaultServerProvider        : Environment is set to null.
Because it is not available in either (1) JVM system property 'env', (2) OS env variable 'ENV' nor (3) property 'env'
from the properties InputStream.

> 在启动的激活配置文件上 vm.options -Denv=DEV
