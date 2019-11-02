package tr.ozaytunctan.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class AuthenticationResponseDto implements Serializable {

	private String token;

	private Date expireDate;
	
	
	public AuthenticationResponseDto() {
	}


	public AuthenticationResponseDto(String token, Date expireDate) {
		super();
		this.token = token;
		this.expireDate = expireDate;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Date getExpireDate() {
		return expireDate;
	}


	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}




}
