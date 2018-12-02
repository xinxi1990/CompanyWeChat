# 简介
基于企业微信项目进行接口测试

# 项目结构

Base:封装基类,任何case都要继承此类,进行一些初始化操作

Case:按照企业微信模块划分

Config:隐私密钥配置

ReportListenter:报告监听

Tools:常用工具封装


# 运行命令
在根目录下执行:
mvn test or mvn install

mvn test > request.log

# 生产模版
```$xslt
https://segmentfault.com/a/1190000011768799
```


# 报告
在test-output目录下生成报告




