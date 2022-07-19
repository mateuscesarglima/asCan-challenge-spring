# Getting Started

<span style="color:red;">Warning: This is a work in progress.
We are still working on improvements to this project.</span>

##  [Github](https://github.com/mateuscesarglima/asCan-challenge-spring)
1. You need to access my github on the link above.
2. Clone project in your preferred folder.
3. build the project in your preferred IDE.
4. If you are using [VScode](https://code.visualstudio.com/download) you just need to open the file project and wait the build automatically.

## [Docker](https://www.docker.com/get-started/)
1. On the link above you can download the docker to run RabbitMQ image in your pc using docker
2. You need to create the following rabbit configuration in any file that you want. You can put the file name like this **docker-compose.yml**:
```yml
services:
  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    restart: always
    ports:
      - 5672:5672
      - 15672:15672
    volumes:
      - ./dados:/var/lib/rabbitmq/
    environment:
      - RABBITMQ_DEFAULT_USER=admin
      - RABBITMQ_DEFAULT_PASS=123456
```
3. using the terminal of your choice, you must navigate to the folder that contains the file and type the following command:


```bash
docker-compose up
```

4. This command above start the rabbit config on the port 15672, so if you go to the browser and fill on search bar the following link:

```bash
http://localhost:15672
```
5. you will see this screen:
  
    ![Screen image](/src/main/resources/assets/imgs/RabbitInitialScreen.png)

6. In the fields that are present you fill the **USERNAME** and **PASSWORD** that are in the configuration file "docker-compose.yml"




   
