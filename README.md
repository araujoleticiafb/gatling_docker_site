
# Load Testing with Gatling 

## About the project
The idea behind this project is to execute load testing using Gatling with Docker. Also, a static site, which contains all results, is generated. Thinking about usability, the site supports this point of view become easier to access the default result that Gatling creates.

* Static Site - Inicial Page:

[![Product Name Screen Shot 1][product-screenshot1]]
* Static Site - Page after clicking on Inicial Page:

[![Product Name Screen Shot 2][product-screenshot2]]
* The default result generated by Gatling - After clicking on index.html:

[![Product Name Screen Shot 3][product-screenshot3]]

## Running with Docker
Download and install [Docker](https://www.docker.com/products/docker-desktop).

### Build
```sh
$ docker build -t YOUR_IMAGE_NAME .
```
### Run
```sh
$ docker run -it -p 8080:80 -e JAVA_OPTS="-DinicialUsers=USER_NUMBER -DtestDuration==DURATION" --rm -v $(pwd)/conf:/opt/gatling/conf \
-v $(pwd)/user-files:/opt/gatling/user-files \
-v $(pwd)/results:/opt/gatling/results \
-v $(pwd)/results/:/opt/www/files \
YOUR_IMAGE_NAME
```
> **NOTE:** Replace **YOUR_IMAGE_NAME**,  **USER_NUMBER (number)**  and  **DURATION (number)**.

### Some Docker command line
| Command line | Explanation |
| ------ | ------ |
| docker system prune -a | Clear all dependecies |
| docker ps -a | List all running and stopped containers |
| docker rm ID_CONTAINER | Remove a container |
| docker stop ID_CONTAINER | Stop a container |
| docker imagens | List all imagens |
| docker rmi ID_IMAGE | Remove an imagem |
| docker exec -it ID_CONTAINER bash | Login a container |

> **NOTE 1:** Use **docker ps -a** to show the ID_CONTAINER.

> **NOTE 2:** Use **docker images** to show the ID_IMAGE.

## Running local  

1. Clone repository on Git;
2. Execute the command on the terminal:

```sh
$ brew install scala
```
3. Set JDK.

### Run

Go to folder `bin` and execute:

```sh
For Linux/macOS: sh gatling.sh -s PACKAGE_NAME.CLASS_NAME
```

```sh
For Windows: gatling.bat -s PACKAGE_NAME.CLASS_NAME
```
> **NOTE 1:** -s PACKAGE_NAME.CLASS_NAME is used to running Gatling without user interaction.

> **NOTE 2:** To change quantity of request and duration you have to setup in the code.

## Setting results
Go to `conf/gatling.conf`

## Simulation setup
| Injection | Explanation |
| ------ | ------ |
| atOnceUsers(nbUsers) | Injects a given number of users at once. |
| rampUsers(nbUsers) over(duration) | Injects a given number of users distributed evenly on a time window of a given duration. |
| constantUsersPerSec(rate) during(duration) | Injects users at a constant rate, defined in users per second, during a given duration. Users will be injected at regular intervals. |

For more simulation setup access https://gatling.io/docs/current/general/simulation_setup/

<!-- MARKDOWN LINKS & IMAGES -->
[product-screenshot1]: images/inicial_page_site.png
[product-screenshot2]: images/page_2.png
[product-screenshot3]: images/default_result_gatling.png