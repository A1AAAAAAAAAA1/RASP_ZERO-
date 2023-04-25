# RASP_ZERO-
Rasp(Runtime Application Self Protection) 配套的简单靶场。

技术栈: Javaagent + Javassist

启动方式：
1.idea配置好tomcat

2.VM Options 设置为：
-javaagent:C:\Users\****\RASP_zero.jar  （此处填写javaagent jar包路径）
![T}TZHW8C WQE F)LYB8GS5J](https://user-images.githubusercontent.com/94785056/234164463-859f0a45-b779-4dbb-b7ee-bebfc76baf31.png)
3.运行项目

![MI O PG)TCC%S@FJR_%)_(D](https://user-images.githubusercontent.com/94785056/234167616-5620faab-d758-4859-a3b9-58e1058befc0.png)

4.效果
  4个模块均为经典漏洞，可先不加载Rasp.jar 运行tomcat测试漏洞，之后添加VM-options 运行RASP_zero jar包查看Rasp的防护效果。
  
  mysql statement sql 注入
  ![HZJDD$2SQ5FISVUVFY LN%C](https://user-images.githubusercontent.com/94785056/234169036-8b9f9bf1-16f2-4ef1-b848-5bb563d87dc2.png)

  ![QQ$BG2A(WEL{{LOBF}%P8TS](https://user-images.githubusercontent.com/94785056/234168471-2f64d484-93a7-4612-ab74-a258eee06cdf.png)
  Runtime.exec() 命令执行
  ![image](https://user-images.githubusercontent.com/94785056/234169085-e62d0066-4402-496e-9c94-bdda2ade3575.png)
  ![44K`O)} `H59$49H@E61O8H](https://user-images.githubusercontent.com/94785056/234169196-aa8399eb-c150-478e-b28f-b81b4aab4e78.png)

  ProcessBuild命令执行
  ![KZ)PR`R2Y}0766 T8FK5QMT](https://user-images.githubusercontent.com/94785056/234169705-1de9ec7c-5462-4bf3-958d-438e8a124370.png)
  ![ZU72W9SDT%K)SMONEVVW Q2](https://user-images.githubusercontent.com/94785056/234169763-db35b98d-ad5a-4a24-89a9-0ab2a91ca60a.png)
  XML(javax.xml.parsers.DocumentBuilder外部实体注入
  ![IIP@09B2}W~{J701CQ6257Q](https://user-images.githubusercontent.com/94785056/234171011-97da2065-add6-47ff-8eb8-9a835ac4ac33.png)
  poc= <?xml version="1.0" encoding="ISO-8859-1"?><!DOCTYPE foo [   <!ELEMENT foo ANY >  <!ENTITY xxe SYSTEM "file:///c:/windows/win.ini" >]><foo>&xxe;</foo>
  ![LI82YN 0YDAXS KM~6@~S7W](https://user-images.githubusercontent.com/94785056/234171101-fc8974ae-a0b2-4fac-aa07-56e5e57a4cf9.png)

    文件上传木马防范，因为传统木马一般采用Runtime.exec() ProcessBuild 类进行命令执行，只要防护了这两个类的命令执行，就能对文件上传木马的命令执行进行阻断。
下面是一个传统的一句话木马。在本项目的位置：src/main/webapp/pages/backdoor.jsp （如直接下载本项目可能杀毒软件可能会误报，就是由此文件产生的）

代码：![}~U43{R(})S6R5ZP~FA~A9G](https://user-images.githubusercontent.com/94785056/234172115-21cc8d0b-651c-4c7d-89fa-2e62bf70090c.png)

访问：**/pages/backdoor.jsp?cmd=whoami
![8(NE~N$FKXX( B2W ~A1U{A](https://user-images.githubusercontent.com/94785056/234172193-5fd3fa85-25bc-491e-82e4-1441ac436a4e.png)
可以看到，命令执行被阻断。
![D@44$ L{3_TT8$C~JI@3GY3](https://user-images.githubusercontent.com/94785056/234172246-5f94c081-ae81-4b22-a5e7-e4413ce8e98c.png)

ps(Rasp技术对于webshell 或者内存马的检测相比较于传统的特征检测技术有着很大优势)。

待更新。。。。。。




  
  
