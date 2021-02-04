# JiraDockerProject

This project is the jira regression tests implemented in a cross-platform testing used
docker container platform technology. In my project I use chrome and firefox debug containers.

# Concepts Included:

Page Object pattern
Keyword driven testing
Commonly used test utility classes

Selenium Webdriver: Chrome and Chromedriver (UI tests use Chrome by default, can be changed in config)
Java
Maven

# Reporting
 
TestScript contains 5 tests, from which 5 passes.


####1. Pull the docker images with the next commands:

- $ docker pull selenium/node-chrome-debug
- $ docker pull selenium/node-firefox-debug

####2. Then we need to be connected to a Grid Hub.

####3. Verify the images on the system by using:
- $ docker images

####4. Update your docker-compose.yml file:

```version: "3"
 services:
   hub:
     image: selenium/hub
     ports:
       - "4444:4444"
     environment:
       GRID_MAX_SESSION: 16
       GRID_BROWSER_TIMEOUT: 3000
       GRID_TIMEOUT: 3000
   chrome:
     image: selenium/node-chrome-debug
     container_name: web-automation_chrome
     depends_on:
       - hub
     environment:
       HUB_PORT_4444_TCP_ADDR: hub
       HUB_PORT_4444_TCP_PORT: 4444
       NODE_MAX_SESSION: 4
       NODE_MAX_INSTANCES: 4
     volumes:
       - /dev/shm:/dev/shm
     ports:
       - "9001:5900"
     links:
       - hub
   firefox:
     image: selenium/node-firefox-debug
     container_name: web-automation_firefox
     depends_on:
       - hub
     environment:
       HUB_PORT_4444_TCP_ADDR: hub
       HUB_PORT_4444_TCP_PORT: 4444
       NODE_MAX_SESSION: 2
       NODE_MAX_INSTANCES: 2
     volumes:
       - /dev/shm:/dev/shm
     ports:
       - "9002:5900"
     links:
       - hub
```
You need to download VNC viewer: (https://www.realvnc.com/en/connect/download/viewer/)
Open the VNC viewer and please proceed with installation steps.

####5. Time to setup infrastructure!
- $ docker-compose -f /path/to/docker-compose.yml up -d

####6. To verify if containers are up, use the following command:
- $ docker ps

####7.
Enter your <ip address of your container>:<container port address> in vnc viewer search tab. In my case, it is 0.0.0.0:9001 and 0.0.0.0:9002.

####8.
After this, vnc viewer will prompt for password. Default password is secret.
VNC viewer is now connected to your docker container. Repeat the same steps for firefox container.

####9. Time to run tests!
- $ mvn clean test -Dsurefire.suiteXmlFiles=Testng.xml

As you can see, both the tests ran in chrome and firefox debug containers in parallel mode. 
Time used by single browser runs are similar to the ones in non-debug mode. Whereas, in parallel, same tests take almost twice the time.

####10. Tear Down the infrastructure.
- $ docker-compose -f /path/to/docker-compose.yml down

Containers will be stopped. Grid network will be destroyed.
- $ docker ps





