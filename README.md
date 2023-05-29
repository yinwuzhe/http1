## Docker环境安装
```shell
//运行Docker开发环境
# docker run -it ksimple/java8-mvn-nodejs-npm-python3 bash

//进入doker,clone代码仓库
root@f034fd594c8e:/# git clone https://github.com/yinwuzhe/http1
root@f034fd594c8e:/# cd http1

//编译打包运行
root@f034fd594c8e:/http1# mvn clean package
root@f034fd594c8e:/http1# java -jar target/http1-1.0-SNAPSHOT.jar
```

## 本地环境安装
> 要搭建一个Java开发环境，包括Maven（包管理工具、并且编译运行）和IDE（用于编写代码），可以按照以下步骤进行：
>
> 1.  下载Java Development Kit（JDK）：访问Oracle官网下载JDK并按照下载的安装程序进行安装。
> 2.  下载Maven：访问Apache Maven官网下载Maven并按照下载的安装程序进行安装。
> 3.  配置Maven环境变量：将Maven的bin目录添加到系统环境变量中。
> 4.  下载IDE：可以选择Eclipse或IntelliJ IDEA等Java IDE，访问官方网站下载并按照下载的安装程序进行安装。
> 5.  配置IDE：打开IDE并安装Java开发插件，然后配置Maven路径和Java路径。
      >     完成以上步骤后，你就可以开始使用Java开发环境进行编程了。
>
> 安装配置环境是每个开发者的入门过程，有时候会遇到各种问题和困难。如果你在这个过程中遇到了任何问题可以寻求帮助。