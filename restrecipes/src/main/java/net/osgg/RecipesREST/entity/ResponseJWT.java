package net.osgg.RecipesREST.entity;

import java.io.Serializable;

public class ResponseJWT implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public ResponseJWT(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}