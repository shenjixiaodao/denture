## 项目结构图

![](https://github.com/shenjixiaodao/denture/blob/master/server/docs/project_struct.png)

## 层次依赖图

项目做了清晰的分层，并且已经构建好了层次依赖，不建议打破依赖！！！
为了规范项目，便于后续迭代，层次依赖必须遵从以下**原则**：


1. **domain是核心领域层**，所有业务逻辑都应该定义在这里，包括model定义,service层提供的业务服务，repository持久化或者聚合查询的接口;


2. api层定义了http接口相关(controller);biz层(service层)定义了service接口的实现逻辑，和应用模块之间的交互细节；data层定义了数据持久化、序列化、数据缓存等数据相关的实现细节。


3. 在一个应用模块里(这里模块不是指maven的module，而是如factory模块和clinic模块的区分)，domain层作为应用的核心，所有其它层涉及业务相关逻辑，都是依赖只依赖domian层；例如biz、data、api之间绝对不相互依赖。**这样做的目的是为了聚合业务核心**。


4. 每一个应用模块应该相互隔离；


5. 应用模块之间的业务交互，只允许在biz层依赖另一个模块的domain，并**通过service来进行交互**；

*依赖结构如下图所示：*

![](https://github.com/shenjixiaodao/denture/blob/master/server/docs/project_dependency.png)