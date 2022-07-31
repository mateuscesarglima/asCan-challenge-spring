# Getting Started

<span style="color:red;">Warning: This is a work in progress.
We are still working on improvements to this project.</span>

# How to run locally
- First you need to install docker in your pc, follow instructions:
## [Docker](https://www.docker.com/get-started/)

- You can download the docker desktop to run the project in the link above.
- After install, probably the app will request you to install wsl, if you are having trouble with this, you can follow the instructions that are present [HERE](https://docs.microsoft.com/pt-br/windows/wsl/install)
- By default, your docker application will select the wsl option.

## Running project

- Open the project file in your preferred IDE
- Open terminal
- in the terminal type the following command:
```bash
cd docker
```
- And then type:

```bash
docker-compose -f .\docker-compose-prod.yml up -d
```
- After this command you will see the containers running on your docker desktop app, follow image:
   
![Screen image](/src/main/resources/assets/imgs/dockerApp.png)

- if the app container is not green like as image below
  
![docker image](/src/main/resources/assets/imgs/docker_2.JPG)

- you need to run one more time the following command:

```bash
docker-compose -f .\docker-compose-prod.yml up 
```
- or

```bash
docker-compose -f .\docker-compose-prod.yml up application
```
- I will work on to solve this problem, but it happens because the application container is going up at the same time as the rabbitmq container.

- by running project's containers [POSTMAN](https://www.postman.com/downloads/) 
 will be available for you to make http requests or any similar application.

- To see the requests that can be made to the API after starting the application, you can insert the following link in your browser of choice:

  ```
  http://localhost:8080/swagger-ui/
  ```

- When you enter in the above link, the following screen will appear:
  
  ![Screen image](/src/main/resources/assets/imgs/SwaggerScreen.png)
- How you can see, we have 4 controllers that we can use. So you can open the controllers to see endpoints.
- With the application running, you can use postman to perform the requests

## Database

- If you wanna see the data persistence, you need to download tool like [DBEAVER](https://dbeaver.io/download/) or [PGADMIN](https://www.pgadmin.org/download/).