这是一个使用dubbo提供库存服务的应用。

请求过来时候，首先进入的是 GtsProviderFilter , 如果上游系统传递了xid，则在这个filter就把xid设置进了txc的context。

然后会进入 StockAPIImpl 这个类，调用StockService 的addStock()方法，  如果此时 addStock() 方法有@TxcTransaction注解，就会把上一步上游传递进本系统，并且已经设置好的xid替换。


但是本系统addStock()方法  必须加上这个注解的，因为 有些上游系统可能不会传递xid过来。有些会传递xid


所以能不能在处理处理@TxcTransaction注解的地方，先判断下当前ThreadLocal有没有xid，有的话就不需要新生成了