package tr.ozaytunctan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.ozaytunctan.dto.AuthenticationRequestDto;
import tr.ozaytunctan.dto.AuthenticationResponseDto;
import tr.ozaytunctan.dto.ResponseErrorMessageDto;
import tr.ozaytunctan.security.JwtProvider;
import tr.ozaytunctan.service.spec.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private UserService userService;

	@PostMapping(path = { "/authenticate" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()));

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username and password", e);
		}

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				"", "");
		UserDetails user = this.userService.loadUserByUsername(authenticationRequestDto.getUsername());

		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		String token = jwtProvider.generateToken(user);

		return ResponseEntity.ok(new AuthenticationResponseDto(token, jwtProvider.extractExpired(token)));
	}

	@PostMapping(path = { "/hi" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> hi() {

		return ResponseEntity.ok().body("hi");
	}

	@ExceptionHandler(value = { BadCredentialsException.class })
	public ResponseEntity<ResponseErrorMessageDto> badCredentialsException(BadCredentialsException ex) {
		ResponseErrorMessageDto error = new ResponseErrorMessageDto();
		error.setCode(HttpStatus.UNAUTHORIZED.value()+"");
		error.setStatus("Error");
		error.setMessage(ex.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

	}

}
