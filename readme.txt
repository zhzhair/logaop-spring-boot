spring-boot-aop封装（windows10+jdk8+idea+spring-boot2.1.5）
项目url：https://github.com/zhzhair/logaop-spring-boot.git
1.根据spring aop和spring boot封装自动打印运行时间，入参，出参的注解
	log-spring-boot：封装的一般方法和controller类的日志打印注解（@LogForConsumer和@LogForController）；
	exercise：引用封装好的jar包依赖实现一般方法和controller类的日志打印功能；
	pers：封装的jar包依赖，直接放到maven仓库就可以用；
	nolog-exercise：全局打印接口日志，加注解@NotAutoLog实现对应的接口不打印日志。
