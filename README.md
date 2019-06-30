# config 一个采用类json格式的配置
## 特点
* 在纯Java中实现，没有依赖项
* 支持三种格式的文件：Java属性，JSON和人性化的JSON超集
* 合并所有格式的多个文件
* 可以从文件，URL或类路径加载
* 对“嵌套”的良好支持（将配置的任何子树视为与整个配置相同）
* 用户可以使用Java系统属性覆盖配置， java -Dmyapp.foo.bar=10
* 支持使用其框架和库配置应用程序，所有这些都来自单个文件，例如 application.conf
* 解析持续时间和大小设置，“512k”或“10秒”
* 转换类型，所以如果你要求一个布尔值，并且值是字符串“yes”，或者你要求一个浮点数并且值是一个int，它就会计算出来。
* JSON超集功能：
	* 注解（comment）
	* 包含（include）
	* 替换（"foo" : ${bar}，"foo" : Hello ${who}）
	* 属性符号（a.b=c）
	* 较少繁琐，更宽松的语法
	* 替换环境变量（logdir=${HOME}/logs）
基于Hocon的配置，宽松的语法，
# 在原始项目上
* 新加部分api
* 数组以及配置采用有序集合形式，保证了配置的循序
* 支持配置合并
# 案例
```json
MVC:{
	SERVER:{
			TOMCAT:[
				{ ## 默认调配器
					class:org.apache.catalina.servlets.DefaultServlet,
					priority:2,
					signlton:true,
					attribute:"*",
					service:javax.servlet.Servlet
				},
				{ ## 默认JSP调配器
					class:org.apache.jasper.servlet.JspServlet,
					priority:1,
					signlton:true,
					attribute:".jsp",
					service:javax.servlet.Servlet
				},
			]
		}
	}
```
```json
###################### plugin
plugins:[
		## Fragment
		"com.YaNan.frame.hibernate.database.fragment.ForEachFragment",
		"com.YaNan.frame.hibernate.database.fragment.IFFragment",
		"com.YaNan.frame.hibernate.database.fragment.IncludeFragment",
		"com.YaNan.frame.hibernate.database.fragment.SelectorFragment",
		"com.YaNan.frame.hibernate.database.fragment.TrimFragment",
		"com.YaNan.frame.hibernate.database.fragment.SqlFragment",
		{ ## 数据库
			class:com.YaNan.frame.hibernate.database.HibernateContextInit,##组件类
			priority:0,##优先级
			signlton:true,##是否启用单例模式
			attribute:"DB",
			service:"javax.servlet.Servlet,*",
			field:{location:{Resource:"classpath:hibernate.xml"}}
		},
	]
####################### global configure
```
本想支持yml语法，但不喜欢依赖缩进的代码块方式。放弃了
## 新增Api [原始api](https://lightbend.github.io/config/latest/api/index.html)
* 是否允许key为空（null），原始项目不支持key为null的配置，原始项目中，当尝试获取某个key时，如果key不存在，会抛出异常。
	* void allowKeyNull(boolean allowNull),
	* void allowKeyNull();
* 是否使用类型验证（null），原始项目中，当尝试获取某个key是，会对类型进行判断，此时如果类型不匹配，则会抛出异常
 	* void enableTypeVerify(boolean isVerify);
 	* void enableTypeVerify();
 	* void disableTypeVerify();
* 是否允许值为空（null），原始项目不支持value不存在的情况，如果尝试获取某个值为空的key时，会抛出异常
	* void allowValueNull(boolean allowNull);
	* void allowValueNull();
* 判断集合是否会否是一个对象（Object 或 config）的结合
	* boolean isConfigList(String path);
* 判断某个路径的值是否是一个集合（数组）
	* boolean isList(String path);
* 获取当前配置对象的所有子配置的集合（Set）
	* Set<Entry<String, Config>> configEntrySet();
* 获取当前配置对象的所有简单对象的集合（简单对象为非Config的对象，如String，boolean，int，long等）
	* Set<Entry<String, Object>> simpleObjectEntrySet();
* 获取路径的配置值类型
	* ConfigValueType getType(String path);
* 获取某个值为集合的路径的值集合，返回值中包含包裹类型（此包裹类型非java原始包裹类型，为config专有）
 	* List<? extends Object> getValueList(String path);
* 获取某个值为集合的路径的值结合，返回值中简单类型去掉包裹类型
	* List<? extends Object> getValueListUnwrapper(String path);
* 合并配置，有时候，可能需要对配置进行合并
	void merge(Config config);
## 开始使用

* 安装
	* 引入jar包的方式，到 [这里](https://github.com/tja414312570/plugin.configure/tree/master/target) 下载对应jar包
	* pom依赖
```xml
	<dependency>
		<groupId>com.github.tja414312570</groupId>
		<artifactId>plugin.config</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>
```
# 传送门
[原始项目](https://github.com/lightbend/config)
