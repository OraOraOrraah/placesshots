# Welcome to Tomcat7 on CloudBees

This is a "ClickStart" that gets you going with a Maven - Log4j - Tomcat 7 "seed" project starting point. You can launch it here:

<a href="https://grandcentral.cloudbees.com/?CB_clickstart=https://raw.github.com/CloudBees-community/tomcat7-log4j-clickstart/master/clickstart.json"><img src="https://d3ko533tu1ozfq.cloudfront.net/clickstart/deployInstantly.png"/></a>

This will setup a continuous deployment pipeline - a CloudBees Git repository, a Jenkins build compiling and running the test suite (on each commit).
Should the build succeed, this seed app is deployed on a Tomcat 7 container.

# CloudBees Tomcat 7 container

Tomcat 7 container is available on CloudBees thanks to the [tomcat7-clickstack](https://github.com/CloudBees-community/tomcat7-clickstack). Documentation is available [here](https://developer.cloudbees.com/bin/view/RUN/Tomcat7).

# How to deploy a web application on a Tomcat7 ClickStack

You can deploy your web application on the tomcat7 clickstack using the [CloudBees SDK](https://developer.cloudbees.com/bin/view/RUN/BeesSDK) "`app:deploy`" command.

```
bees app:deploy -a myaccount/tomcat7-log4j-clickstart -t tomcat7 ./target/tomcat7-log4j-clickstart-1.0-SNAPSHOT.war
```

* "`-a myaccount/tomcat7-log4j-clickstart`": name of the CloudBees account and of the application. The application will be accessible on the URL http://tomcat7-log4j-clickstart.myaccount.cloudbees.net/
* "`-t tomcat7`": identifier of the tomcat7 clickstack
* "`./target/tomcat7-log4j-clickstart-1.0-SNAPSHOT.war`": path to the war file.
You only need to set the "`-R`", "`-t`" and "`-D`" settings once - they will be remembered for subsequent deploys.

# How to troubleshoot Log4j initialisation

### Set `log4j.debug=true` system property

#### Command

```
$ bees config:set -P log4j.debug=true -a myapp
```

#### Response

```
Application config parameters for myaccount/myapp: saved
Application Parameters:
  log4j.debug=true
```

### Restart application

$ bees app:restart myapp
Are you sure you want to restart this application [myaccount/myapp]: (y/n) y
application restarted - myaccount/myapp

### Check the console


```
$ bees app:tail myapp
Aug 14, 2013 10:02:59 AM com.staxnet.appserver.utils.AppServerConfiguration readAppServerConfig
INFO: loading app config: /var/genapp/apps/b2ed200d/appserver.xml
...
Aug 14, 2013 10:03:01 AM org.apache.catalina.startup.Embedded start
INFO: Starting tomcat server
Aug 14, 2013 10:03:02 AM org.apache.catalina.core.StandardEngine start
INFO: Starting Servlet Engine: Apache Tomcat/6.0.35
log4j: Trying to find [log4j.xml] using context classloader WebappClassLoader
  context:
  delegate: false
  repositories:
    /WEB-INF/classes/
----------> Parent Classloader:
java.net.URLClassLoader@2002ff65
.
log4j: Trying to find [log4j.xml] using WebappClassLoader
  context:
  delegate: false
  repositories:
    /WEB-INF/classes/
----------> Parent Classloader:
java.net.URLClassLoader@2002ff65
 class loader.
log4j: Trying to find [log4j.xml] using ClassLoader.getSystemResource().
log4j: Trying to find [log4j.properties] using context classloader WebappClassLoader
  context:
  delegate: false
  repositories:
    /WEB-INF/classes/
----------> Parent Classloader:
java.net.URLClassLoader@2002ff65
.
log4j: Using URL [file:/mnt/genapp/apps/b2ed200d/staxcat/install/webapp.war/WEB-INF/classes/log4j.properties] for automatic log4j configuration.
log4j: Reading configuration from URL file:/mnt/genapp/apps/b2ed200d/staxcat/install/webapp.war/WEB-INF/classes/log4j.properties
log4j: Parsing for [root] with value=[INFO, stdout].
log4j: Level token is [INFO].
log4j: Category root set to INFO
log4j: Parsing appender named "stdout".
log4j: Parsing layout options for "stdout".
log4j: Setting property [conversionPattern] to [%d [%t] %-5p %c - %m%n].
log4j: End of parsing for "stdout".
log4j: Parsed "stdout" options.
log4j: Finished configuring.
Aug 14, 2013 10:03:06 AM org.apache.coyote.http11.Http11Protocol init
INFO: Initializing Coyote HTTP/1.1 on http-8216
Aug 14, 2013 10:03:06 AM org.apache.coyote.http11.Http11Protocol start
INFO: Starting Coyote HTTP/1.1 on http-8216
...
```

# Application troubleshooting changing Log4j log level at runtime

## Enable Log4j JMX management adding cloudbees-log4j-extras to your web application

See [cloudbees-log4j-extras](https://github.com/CloudBees-community/cloudbees-log4j-extras).

Please note that there are alternatives to cloudbees-log4j-extras to manage Log4j via JMX.

### Add cloudbees-log4j-extras to your classpath

Maven dependency:

```xml
<dependency>
    <groupId>com.cloudbees.extras</groupId>
    <artifactId>cloudbees-log4j-extras</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Register the Log4jConfigurer JMX MBean

#### Servlet 3+ containers with `@Web...` annotations discovery enabled

Servlet 3+ containers (Tomcat 7+, Glassfish 3+, ...) will automatically discover the
`com.cloudbees.log4j.jmx.Log4jConfigurerWebListener` class that is annotated with `@WebListener`
if you set `metadata-complete="false"` in `web.xml`.

web.xml fragment with `metadata-complete="false"`

```xml
<web-app
        xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
        version="3.0" metadata-complete="false">
   ...
</web-app>
```

#### Servlet 2.5 containers and manual ServletContextListener declaration.

On Servlet 2.5 containers (Tomcat 6, ...) and when `@Web...` annotation discovery is disabled, you must declare
the `Log4jConfigurerWebListener` class as a Servlet Context Listener.

web.xml fragment with `<listener>`

```xml
<web-app ...>
    <listener>
        <listener-class>com.cloudbees.log4j.jmx.Log4jConfigurerWebListener</listener-class>
    </listener>
</web-app>
```

#### Check the initialisation of the Log4jConfigurer JMX MBean

Start your application locally and check with VisualVM that the JMX MBean
`com.cloudbees:type=Log4jConfigurer,context=/,name=Log4jConfigurer` appears in the MBean registry of your container.


### Deploy your application with the Log4jConfigurer JMX MBean on CloudBees

Deploy your application with cloudbees-log4j-extras


### Invoke remotely the `Log4jConfigurer.setLoggerLevel(logger, level)` JMX Operation with the jmx_invoker control script


#### Set Log4j Logger Level

##### Command

```
$ bees app:instance:invoke -a myapp --script jmx_invoker \
    --args="-on com.cloudbees:type=Log4jConfigurer,* -op setLoggerLevel org.springframework DEBUG"
```

##### Response

```
Exit code: 0
Invoke operation com.cloudbees:type=Log4jConfigurer,context=/,name=Log4jConfigurer:setLoggerLevel(org.springframework, DEBUG): void
```

#### Get Log4j Logger Effective Level

##### Command

```
$ bees app:instance:invoke -a myapp --script jmx_invoker \
    --args="-on com.cloudbees:type=Log4jConfigurer,* -op getLoggerEffectiveLevel org.springframework"
```

##### Response

```
Exit code: 0
Invoke operation com.cloudbees:type=Log4jConfigurer,context=/,name=Log4jConfigurer:getLoggerEffectiveLevel(org.springframework): DEBUG
```

#### Get Log4j Configuration

##### Command

```
$ bees app:instance:invoke -a myapp --script jmx_invoker \
    --args="-on com.cloudbees:type=Log4jConfigurer,* -op printLog4jEffectiveConfiguration"
```

##### Response

```
Exit code: 0
Get attribute value com.cloudbees:type=Log4jConfigurer,context=/,name=Log4jConfigurer:Log4jConfiguration: log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.follow=false
log4j.appender.stdout.immediateFlush=true
log4j.appender.stdout.target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.contentType=text/plain
log4j.appender.stdout.layout.conversionPattern=%d [%t] %-5p %c - %m%n
log4j.rootLogger=INFO, stdout
log4j.logger.org.springframework=DEBUG
```

### Documentation

* [jmx_invoker control script](http://developer.cloudbees.com/bin/view/RUN/CloudBees_JMX_Invoker)
* [Log4jConfigurer JMX Mbean](https://github.com/CloudBees-community/cloudbees-log4j-extras)

## How to use a specific Log4j configuration file on CloudBees servers

Here is a proposal to use a specific configuration file on CloudBees platform.

1. add a Log4j configuration file `src/main/resources/log4j-production.properties` next to the default `src/main/resources/log4j.properties`
2. Use the system property `log4j.configuration` to specify this alternate log4j configuration file
      bees config:set -a myapp -P log4j.configuration=log4j-production.properties


You can verify that this `log4j-production.properties` configuration file is used:

* Enabling Log4j initialization debugging with the `bees config:set -a myapp -P log4j.debug=true` and restarting the app  (`bees app:restart -a myapp`)
* Retrieving the Log4j configuration with the `jmx_invoker` control script (see above).




