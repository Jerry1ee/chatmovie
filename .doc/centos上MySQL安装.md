# mysql centos 安装

### 一、安装YUM Repo
1、由于CentOS 的yum源中没有mysql，需要到mysql的官网下载yum repo配置文件。
下载命令：

wget https://dev.mysql.com/get/mysql57-community-release-el7-9.noarch.rpm

2、然后进行repo的安装：
rpm -ivh mysql57-community-release-el7-9.noarch.rpm

执行完成后会在/etc/yum.repos.d/目录下生成两个repo文件mysql-community.repo mysql-community-source.repo

### 二、使用yum命令即可完成安装
注意：必须进入到 /etc/yum.repos.d/目录后再执行以下脚本

1、安装命令：
yum install mysql-server

2、启动msyql：
systemctl start mysqld #启动MySQL

3、获取安装时的临时密码（在第一次登录时就是用这个密码）：
grep 'temporary password' /var/log/mysqld.log

4、倘若没有获取临时密码，则
4.1、删除原来安装过的mysql残留的数据

rm -rf /var/lib/mysql

4.2.再启动mysql

systemctl start mysqld #启动MySQL

### 三、登录：
1、方式一（已验证）：
mysql -u root -p

然后输入密码（刚刚获取的临时密码）

2、方式二（未验证）：
进入mysql数据库

root@test:/home# mysql -uroot -proot   <uroot是用户名，proot是密码>

如：

root@test:/home# mysql -root -XXXX

3、若登录不了，则进行以下配置，跳过登录验证
3.1、重置密码的第一步就是跳过MySQL的密码认证过程，方法如下：

3.2、vim /etc/my.cnf(注：windows下修改的是my.ini)

在文档内搜索mysqld定位到[mysqld]文本段：

/mysqld(在vim编辑状态下直接输入该命令可搜索文本内容)

在[mysqld]后面任意一行添加“skip-grant-tables”用来跳过密码验证的过程，如下图所示：



3.3、保存文档并退出：

#:wq

3.4、接下来我们需要重启MySQL：

### 四、登录成功后修改密码
1、注意：这里会进行密码强度校验（密码设置时必须包含大小写字母、特殊符号、数字，并且长度大于8位）
2、如不满足以上条件则会报错，如下图：
密码策略问题异常信息：

ERROR 1819 (HY000): Your password does not satisfy the current policy requirements



 

3、解决办法：
3.1、查看 mysql 初始的密码策略，

 

输入语句 “ SHOW VARIABLES LIKE 'validate_password%'; ” 进行查看，

如下图：



3.2、首先需要设置密码的验证强度等级，设置 validate_password_policy 的全局参数为 LOW 即可，

输入设值语句 “ set global validate_password_policy=LOW; ” 进行设值，

3.3、当前密码长度为 8 ，如果不介意的话就不用修改了，按照通用的来讲，设置为 6 位的密码，设置 validate_password_length 的全局参数为 6 即可，

输入设值语句 “ set global validate_password_length=6; ” 进行设值，

3.4、现在可以为 mysql 设置简单密码了，只要满足六位的长度即可，

输入修改语句 “ ALTER USER 'root'@'localhost' IDENTIFIED BY '123456'; ” 可以看到修改成功，表示密码策略修改成功了！！！

3.5、注：在默认密码的长度最小值为 4 ，由 大/小写字母各一个 + 阿拉伯数字一个 + 特殊字符一个，

只要设置密码的长度小于 3 ，都将自动设值为 4 ，

3.6、关于 mysql 密码策略相关参数；

1）、validate_password_length  固定密码的总长度；

2）、validate_password_dictionary_file 指定密码验证的文件路径；

3）、validate_password_mixed_case_count  整个密码中至少要包含大/小写字母的总个数；

4）、validate_password_number_count  整个密码中至少要包含阿拉伯数字的个数；

5）、validate_password_policy 指定密码的强度验证等级，默认为 MEDIUM；

关于 validate_password_policy 的取值：

LOW：只验证长度；

1/MEDIUM：验证长度、数字、大小写、特殊字符；

2/STRONG：验证长度、数字、大小写、特殊字符、字典文件；

6）、validate_password_special_char_count 整个密码中至少要包含特殊字符的个数；

 

### 五、修改密码
1、方式一（已验证）：
ALTER USER 'root'@'localhost' IDENTIFIED BY '@abcd123456'; 

2、方式二（未验证）
set password=password("yourpassword"); 

六、开启远程控制
MySQL默认是没有开启远程控制的，必须添加远程访问的用户，即默认是只能自己访问，别的机器是访问不了的。

1、方式一（已验证）：
　   1.1、连接服务器: mysql -u root -p

　　1.2、看当前所有数据库：show databases;

　　1.3、进入mysql数据库：use mysql;

　　1.4、查看mysql数据库中所有的表：show tables;

　　1.5、查看user表中的数据：select Host, User,Password from user;

　　1.6、修改user表中的Host:   update user set Host='%' where User='root';  

                说明： % 代表任意的客户端,可替换成具体IP地址。

　　1.7、最后刷新一下：flush privileges;

       1.8、注意：一定要记得在写sql的时候要在语句完成后加上" ; "

2、方式二（未验证）：
1、使用 grant 命令
grant all privileges on 数据库名.表名 to 创建的用户名(root)@"%" identified by "密码";

2、格式说明：
数据库名.表名 如果写成*.*代表授权所有的数据库 flush privileges; #刷新刚才的内容

如：

grant all privileges on *.* to root@"113.123.123.1" identified by "123456789";

@ 后面是访问mysql的客户端IP地址（或是 主机名） % 代表任意的客户端，如果填写 localhost 为本地访问（那此用户就不能远程访问该mysql数据库了）

### 七、其他配置
1、设置安全选项：
mysql_secure_installation

2、关闭MySQL
systemctl stop mysqld 

3、重启MySQL
systemctl restart mysqld 

4、查看MySQL运行状态
systemctl status mysqld 

5、设置开机启动
systemctl enable mysqld 

6、关闭开机启动
systemctl disable mysqld 

7、配置默认编码为utf8：
vi /etc/my.cnf #添加 [mysqld] character_set_server=utf8 init_connect='SET NAMES utf8'

其他默认配置文件路径： 

配置文件：/etc/my.cnf 日志文件：/var/log//var/log/mysqld.log 服务启动脚本：/usr/lib/systemd/system/mysqld.service socket文件：/var/run/mysqld/mysqld.pid

8、查看版本
select version();
————————————————
版权声明：本文为CSDN博主「不甘于平凡的溃败」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/wohiusdashi/article/details/89358071

1.在用Navicat配置远程连接Mysql数据库时遇到如下报错信息，这是由于Mysql配置了不支持远程连接引起的。



2.在安装Mysql数据库的主机上登录root用户：

mysql -u root -p



3.依次执行如下命令：

use mysql;
 
select host from user where user='root';
可以看到当前主机配置信息为localhost.



4.将Host设置为通配符%

update user set host = '%' where user ='root';
Host设置了“%”后便可以允许远程访问。



5.Host修改完成后记得执行flush privileges使配置立即生效

flush privileges;


6.使用navicat 成功连接至mysql
————————————————
版权声明：本文为CSDN博主「B-W」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/h985161183/article/details/82218710


密码 Mysql123..
