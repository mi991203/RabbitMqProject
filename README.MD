> 这是个maven项目。其中包含6个module，其中rabbitmq-try模块是测试模块，主要是测试各种用法，其他模块一起实现了一个外卖送餐消息流转。整个项目并
> 没有统一向springboot-start-aqmp迁移，只有rabbitmq-try模块部分代码实现了，其他代码还是使用com.rabbitmq.client.Channel原始API