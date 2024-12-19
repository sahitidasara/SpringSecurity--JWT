package com.example.carrentalproject.security.jwt;

public class JwtResponse {

	private String token;

    private long expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", expiresIn=" + expiresIn + "]";
	}
    
    
}
