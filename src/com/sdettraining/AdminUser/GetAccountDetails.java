package com.sdettraining.AdminUser;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sdettraining.authorizationToken.AuthToken;
import com.sdettraining.basetest.BaseTest;
import com.sdettraining.loginTestPojo.LoginResponse;
import com.sdettraining.util.Util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAccountDetails extends BaseTest{

	
	public static void main(String[] args)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {

		File jsonFile = new File(System.getProperty("user.dir") + "/TestData/LoginData.json");
		applog.debug("*************Login Test Started***************");
		String authtoken = AuthToken.getToken();
		applog.debug(authtoken);
		Response resp1 = RestAssured

				.given().baseUri(Util.configReader("auth.url")).accept(ContentType.JSON).contentType(ContentType.JSON)
				.header("Authorization", "Bearer " + authtoken).body(jsonFile).when().post("/login");

		String sloginBody = resp1.body().asString();

		applog.debug(sloginBody);
		String sLoginBodyresponse = sloginBody.replace(Util.configReader("sRemoveLogin"), "");
		applog.debug(sLoginBodyresponse);
		LoginResponse loginResponse = obj.readValue(sLoginBodyresponse, LoginResponse.class);
		applog.debug("Success       :-" + loginResponse.getSuccess());
		applog.debug("User_id       :-" + loginResponse.getData().getUser_id());
		applog.debug("User_group_id :-" + loginResponse.getData().getUser_group_id());
		applog.debug("Username      :-" + loginResponse.getData().getUsername());
		applog.debug("Firstname     :-" + loginResponse.getData().getFirstname());
		applog.debug("Lastname      :-" + loginResponse.getData().getLastname());
		applog.debug("Email         :-" + loginResponse.getData().getEmail());
		applog.debug("Ip            :-" + loginResponse.getData().getIp());
		applog.debug("Status        :-" + loginResponse.getData().getStatus());
		applog.debug("Date_added    :-" + loginResponse.getData().getDate_added());
		applog.debug("User_group    :-" + loginResponse.getData().getUser_group());

		applog.debug("*************Login Test End***************");

		applog.debug("*************UserDetails Test Started***************");
		// Thread.sleep(70000);
		Response userDetailsReponse = RestAssured

				.given().baseUri("http://rest-api.upskills.in/api/rest_admin").accept(ContentType.JSON)
				.contentType(ContentType.JSON).header("Authorization", "Bearer " + authtoken).when().get("/user");

		String userDetailsString = userDetailsReponse.body().asString();
        System.out.println(userDetailsString);
		applog.debug(userDetailsString);
		
		String userDetailsStringOutput = userDetailsString.replace(Util.configReader("sRemoveLogin"), "");
		applog.debug(userDetailsStringOutput);
		System.out.println(userDetailsStringOutput);
		
		LoginResponse userDetailsStringOutput1 = obj.readValue(sLoginBodyresponse, LoginResponse.class);
		applog.debug("Success       :-" + userDetailsStringOutput1.getSuccess());
		applog.debug("User_id       :-" + userDetailsStringOutput1.getData().getUser_id());
		applog.debug("User_group_id :-" + userDetailsStringOutput1.getData().getUser_group_id());
		applog.debug("Username      :-" + userDetailsStringOutput1.getData().getUsername());
		applog.debug("Firstname     :-" + userDetailsStringOutput1.getData().getFirstname());
		applog.debug("Lastname      :-" + userDetailsStringOutput1.getData().getLastname());
		applog.debug("Email         :-" + userDetailsStringOutput1.getData().getEmail());
		applog.debug("Ip            :-" + userDetailsStringOutput1.getData().getIp());
		applog.debug("Status        :-" + userDetailsStringOutput1.getData().getStatus());
		applog.debug("Date_added    :-" + userDetailsStringOutput1.getData().getDate_added());
		applog.debug("User_group    :-" + userDetailsStringOutput1.getData().getUser_group());
		applog.debug("Success       :-" + userDetailsStringOutput1.getSuccess());
	
		applog.debug("*************UserDetails Test End***************");
	}

}
