个人毕业设计

SpringBoot + MyBatis + MyBatis Generator

系统运行环境：<br/>
①	JDK1.8<br/>
②	MySql5.6 端口3306 用户名root 密码root<br/>
③	Redis5.0.0 端口6379 密码bookstore<br/>
④	MongoDB4.0.0 端口27017 用户名root 密码******(注：6个星号)<br/>
⑤	FastDFS5.05 + Nginx1.8 tracker端口22122 Nginx端口8888<br/>

登录说明：<br/>
系统前台登录地址：http://localhost:8080/<br/>
系统前台登录账号：yangwanhao<br/>
系统前台登录密码：yangwanhao<br/>
系统后台登录地址：http://localhost:8080/admin/page/login<br/>
系统前台登录账号：admin<br/>
系统前台登录密码：admin<br/>

其他说明：<br/>
①	系统启动时需要使用8080端口，请保证8080端口不被占用<br/>
②	数据库名字应为bookshop<br/>
③	如果要测试订单支付功能，请务必保证项目运行在公网服务器上，且需要将配置文件application-commit.yml中的 
127.0.0.1:8080替换为当前服务器的IP+端口号，否则会收不到支付宝的支付回调。<br/>
