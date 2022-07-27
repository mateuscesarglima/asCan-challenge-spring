# Getting Started

<span style="color:red;">Warning: This is a work in progress.
We are still working on improvements to this project.</span>

## [Github](https://github.com/mateuscesarglima/asCan-challenge-spring)

1. You need to access my github on the link above.
2. Clone project in your preferred folder.
3. build the project in your preferred IDE.
4. If you are using [VScode](https://code.visualstudio.com/download) you just need to open the file project and wait the build automatically.

## Application
- After you cloned the application, you need to open file project and build the project.
- inside the main directory you need to run the following command on terminal:
```bash
mvn install -DskipTests
```

## [Docker](https://www.docker.com/get-started/)

1. In the link above you can download the docker to run RabbitMQ, Postgres and the appplication image in your pc using docker.

2. using the terminal of your choice, you must navigate to the folder that contains the file and type the following commands:

```bash
cd docker
```

```bash
docker-compose -f .\docker-compose-prod.yml up 
```
3. After this command you will see the containers running on your docker desktop app, follow image:
   
![Screen image](/src/main/resources/assets/imgs/dockerApp.png)

4. If "application-prod" is not running, you need to run one more time the command:
```bash
docker-compose -f .\docker-compose-prod.yml up 
```
5.  I will work on to solve this problem, but this happens because the application container is going up at the same time as the rabbitmq container.

6. This command above start the rabbit config on the port 15672, application on the port 8080 and Postgres on port 5432.

## Run project

- by running project's containers [POSTMAN](https://www.postman.com/downloads/) 
 will be available for you to make http requests or any similar application.
- To see the requests that can be made to the API after starting the application, you can insert the following link in your browser of choice:

```
http://localhost:8080/swagger-ui/
```

- When you enter the above link, the following screen will appear:
  ![Screen image](/src/main/resources/assets/imgs/SwaggerScreen.png)
- How you can see, we have 4 controllers that we can use. So you can open the controllers to see endpoints.
- With the application running, you can use postman to perform the requests
- **_CREATING SUBSCRIPTION:_**
  - **Method**: POST
    - **URL**: localhost:8080/api/v1/subscription
    ```json
    {
      "user": {
        "name": "Mateus Cesar"
      },
      "status": {
        "status_name": "PURSCHASED"
      }
    }
    ```
    ![Screen image](/src/main/resources/assets/imgs/postman_1.png)
- When you create a new subscription automatically you create a new user, new status and new eventHistory.
- So basically you just need to use subscription endpoint to create or update.
- You can use other requests like **GET All Subscription**, **GET All user**, **GET All status** and **GET All events**.
- **_GET All subscriptions:_**
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

- **_GET All Users:_**
  - **Method**: GET
    - **URL**: localhost:8080/api/v1/user

```json
[
  {
    "id": "1ad307df-0e6f-4856-b90b-34ec6d19f008",
    "name": "Mateus Cesar",
    "created_at": "19-07-2022 03:24:04"
  }
]
```

- **_GET All Events:_**
  - **Method**: GET
    - **URL**: localhost:8080/api/v1/events

```json
[
  [
    {
      "id": "c08c2533-6f11-4640-bf95-c2eb3769b333",
      "subscription": {
        "id": "889d36fc-c458-4a81-8ea2-fea662f5782e",
        "user": {
          "id": "1337c141-1d35-41df-9605-ba6edae21195",
          "name": "Mateus Cesar",
          "created_at": "19-07-2022 03:14:36"
        },
        "status": {
          "id": "1a3eabbe-dc21-438e-a98b-4053307c6184",
          "status_name": "ACTIVE"
        },
        "created_at": "19-07-2022 03:14:36",
        "updated_at": null
      },
      "type": "SUBSCRIPTION_PURCHASED",
      "created_at": "19-07-2022 03:14:36"
    }
  ]
]
```
