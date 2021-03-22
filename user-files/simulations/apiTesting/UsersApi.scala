package apiTesting

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import utils.dem

class UsersApi extends Simulation {

  val httpProtocol = http
    .baseUrl("https://reqres.in/api") 
  
  val header = Map(
    "Content-Type" -> "application/json"
  )

  val csvFeeder = csv("create-user2.csv").queue

  object ListOfUsers {
    val listOfUsers = exec(
      http("List of users")
      .get("/users")
      .headers(header)
      .queryParam("page", dem.page)
      .check(status.is(200))
    )
  }

  object SuccessfullyLogin {
    val successfullyLogin = exec(
      http("Successfully login")
      .post("/login")
      .headers(header)
      .body(ElFileBody("successfully-login.json"))
      .asJson
      .check(status.is(200))
    )
  }

  object UnsuccessfullyLogin {
    val unsuccessfullyLogin = exec(
      http("Unsuccessfully login")
      .post("/login")
      .headers(header)
      .body(ElFileBody("unsuccessfully-login.json"))
      .asJson
      .check(status.is(400))
    )
  }

  object CreateUser {
    val createUser = exec(
      http("Create user")
      .post("/users")
      .headers(header)
      .body(ElFileBody("create-user.json"))
      .asJson
      .check(status.is(201))
      .check(jsonPath("$.name").saveAs("name"))
      .check(jsonPath("$.job").saveAs("job"))
    )
  }

  object CreateUserCsv {
    val createUserCsv = exec(
      http("Create user CSV")
      .post("/users")
      .headers(header)
      .body(StringBody("""{"name": "${name}", "job": "${job}"}"""))
      .asJson
      .check(status.is(201))
    )
  }

  object UpdateUser {
    val updateUser = exec(
      http("Update user")
      .put("/users")
      .headers(header)
      .body(ElFileBody("update-user.json"))
      .asJson
      .check(status.is(200))
    )
  }
  
  val basicTestingScn = scenario("Testing").exec(
    ListOfUsers.listOfUsers,
    SuccessfullyLogin.successfullyLogin,
    UnsuccessfullyLogin.unsuccessfullyLogin,
    CreateUser.createUser,
    UpdateUser.updateUser
  )

  val createUserTestingScn = scenario("Testing create user")
    .feed(csvFeeder)
    .exec(CreateUserCsv.createUserCsv)


  setUp(
    basicTestingScn.inject(constantUsersPerSec(dem.nbInicialUsers).during(dem.myTestDuration)),
    createUserTestingScn.inject(constantUsersPerSec(dem.nbInicialUsers).during(dem.myTestDuration))
  ).protocols(httpProtocol)
}