# Getting Started

<span style="color:red;">Warning: This is a work in progress.
We are still working on improvements to this project.</span>

## [Github](https://github.com/mateuscesarglima/asCan-challenge-spring)
1. You need to access my github on the link above.
2. Clone project in your preferred folder.
3. build the project in your preferred IDE.
4. If you are using [VScode](https://code.visualstudio.com/download) you just need to open the file project and wait the build automatically.

## [Docker](https://www.docker.com/get-started/)
1. In the link above you can download the docker to run RabbitMQ image in your pc using docker
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

```
http://localhost:15672
```

5. you will see this screen:
   
    ![Screen image](/src/main/resources/assets/imgs/RabbitInitialScreen.png)

6. In the fields that are present you fill the **USERNAME** and **PASSWORD** that are in the configuration file "docker-compose.yml"

## [Postgres](https://www.postgresql.org/download/)
- In the link above you will download postgres
- You need to configure postgres according to what is in application.yml. If you want to apply other settings, you can make the necessary changes so that everything works normally.

## Run project
- By opening the java file and then running the project you will be able to send the HTTP request on the port 8080 using [POSTMAN](https://www.postman.com/downloads/) or any similar application.
- To see the requests that can be made to the API after starting the application, you can insert the following link in your browser of choice: 
- 
```
http://localhost:8080/swagger-ui/
```
- When you enter the above link, the following screen will appear:
![Screen image](/src/main/resources/assets/imgs/SwaggerScreen.png)
- How you can see, we have 4 controllers that we can use. So you can open the controllers to see endpoints.
- With the application running, you can use postman to perform the requests
- ***CREATING SUBSCRIPTION:*** 
  - **Method**: POST 
    - **URL**: localhost:8080/api/v1/subscription
    ```json
    {
        "user": {
            "name" : "Mateus Cesar"
        },
        "status" : {
            "status_name" : "PURSCHASED"
        }
    }
    ```
    ![Screen image](/src/main/resources/assets/imgs/postman_1.png)
- When you create a new subscription automatically you create a new user, new status and new eventHistory.
- So basically you just need to use subscription endpoint to create or update.
- You can use other requests like **GET All Subscription**, **GET All user**, **GET All status** and **GET All events**.
  
- ***GET All subscriptions:***
  - **Method**: GET
    - **URL**: localhost:8080/api/v1/subscription 
```json
[
    {
        "id": "60cf5594-3ca5-4bde-b0fe-c6a9b0729eb4",
        "user": {
            "id": "1bbd1333-ec9f-47d7-b9ff-625d75f0d40a",
            "name": "Mateus Cesar",
            "created_at": "19-07-2022 12:13:25"
        },
        "status": {
            "id": "369cfe6f-052c-4251-85c6-fd5b7cda0865",
            "status_name": "ACTIVE"
        },
        "created_at": "19-07-2022 12:13:25",
        "updated_at": null
    }
]
```
- So, how you can see we receive the subscriptions, user and status.
- and if you go to the pgAdmin you will see the persisted data.
- We are only using send and receive notification to create the data and persist

![Screen image](/src/main/resources/assets/imgs/pgadmim_1.png)
