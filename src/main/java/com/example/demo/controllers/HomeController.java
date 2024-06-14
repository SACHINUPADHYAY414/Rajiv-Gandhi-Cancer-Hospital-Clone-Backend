package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import com.example.demo.entities.Gender;
import com.example.demo.entities.JwtRequest;
import com.example.demo.entities.JwtResponse;
import com.example.demo.entities.LoginPatient;
import com.example.demo.entities.PasswordResponse;
import com.example.demo.entities.PatientDetails;
import com.example.demo.entities.RegisterPatient;
import com.example.demo.entities.State;
import com.example.demo.entities.Title;
import com.example.demo.entities.User;
import com.example.demo.security.JwtHelper;
import com.example.demo.services.CityServices;
import com.example.demo.services.CountryServices;
import com.example.demo.services.GenderServices;
import com.example.demo.services.PatientDetailServices;
import com.example.demo.services.RegisterPatientServices;
import com.example.demo.services.StateServices;
import com.example.demo.services.TitleServices;
import com.example.demo.services.TokenService;
import com.example.demo.services.UserServices;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class HomeController {

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private UserServices userServices;

	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

	@Autowired
	private TitleServices titleServices;

	@Autowired
	private StateServices stateServices;

	@Autowired
	private GenderServices genderServices;

	@Autowired
	private CityServices cityServices;

	@Autowired
	private CountryServices countryServices;

	@Autowired
	private RegisterPatientServices registerPatientServices;

	@Autowired
	private PatientDetailServices patientDetailServices;

	@Autowired
    private JwtHelper helper;

//<---------------------------------------------------------------------------------------------------------------------------------->


//<---------------------------------------------------------------------------------------------------------------------------------->
//	City Controller
//<---------------------------------------------------------------------------------------------------------------------------------->

	@GetMapping("/getlistcities")
	public ResponseEntity<List<City>> getlistcities(@RequestParam String stateId){
		return ResponseEntity.status(HttpStatus.CREATED).body(cityServices.getCities(stateId));
	}

	@PostMapping("/savelistcities")
	public ResponseEntity<List<City>> savelistcities(@RequestBody List<City> cities){
		return ResponseEntity.status(HttpStatus.CREATED).body(cityServices.addCity(cities));
	}

//<---------------------------------------------------------------------------------------------------------------------------------->
//	Country Controller
//<---------------------------------------------------------------------------------------------------------------------------------->

	@GetMapping("/getlistcountries")
	public ResponseEntity<List<Country>> getlistcountries(){
		return ResponseEntity.status(HttpStatus.CREATED).body(countryServices.getCountriesList());
	}

	@PostMapping("/savelistcountries")
	public List<Country> savelistcountries(@RequestBody List<Country> countrs){
		return countryServices.addCountriesList(countrs);
	}

//<---------------------------------------------------------------------------------------------------------------------------------->
//	Gender Controller
//<---------------------------------------------------------------------------------------------------------------------------------->

	@GetMapping("/getgenders")
	public ResponseEntity<List<Gender>> getgenders(){
		List<Gender> genders = genderServices.getGenders();
		return ResponseEntity.ok(genders);
	}


	@PostMapping("/addgenders")
    public ResponseEntity<String> addgenders(@Validated @RequestBody Gender gender) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(genderServices.addGenders(gender));
    }

//<---------------------------------------------------------------------------------------------------------------------------------->
//	State Controller
//<---------------------------------------------------------------------------------------------------------------------------------->

	@GetMapping("/getliststates")
	public ResponseEntity<List<State>> getliststates(@RequestParam String countryId){
		return ResponseEntity.status(HttpStatus.CREATED).body(stateServices.getState(countryId));
	}

	@PostMapping("/saveliststates")
	public ResponseEntity<List<State>> saveliststates(@RequestBody List<State> states){
		return ResponseEntity.status(HttpStatus.CREATED).body(stateServices.saveState(states));
	}

//<---------------------------------------------------------------------------------------------------------------------------------->
//	Title Controller
//<---------------------------------------------------------------------------------------------------------------------------------->

	@GetMapping("/getTitles")
	public ResponseEntity<List<Title>> gettitles(){
		List<Title> genders = titleServices.getTitles();
		return ResponseEntity.ok(genders);
	}

	@PostMapping("/addTitle")
    public ResponseEntity<String> addgenders(@Validated @RequestBody Title title) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(titleServices.addTitle(title));
    }

//<---------------------------------------------------------------------------------------------------------------------------------->
//	User Controller
//<---------------------------------------------------------------------------------------------------------------------------------->

	@PostMapping("/updateUserPassword")
	public ResponseEntity<String> updateUserPassword(@RequestBody User user) {
		String password = user.getPassword();
		if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?#&])[A-Za-z\\d@$!%*#?&]{8,30}$")) {
			return ResponseEntity.status(HttpStatus.CREATED).body(userServices.updatePassword(user.getUsername(),user.getPassword()));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Password length must be atleast 6 characters long and atmost 30 characters long."
					+ "\nPassword must have at least 1 big character, 1 small character, 1 number and 1 special character.");
		}
	}


//<---------------------------------------------------------------------------------------------------------------------------------->
//	login/register
//<---------------------------------------------------------------------------------------------------------------------------------->

	@PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		System.out.println(request.getUsername());
		System.out.println(request.getPassword());


        this.doAuthenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        System.out.println(token);

        tokenService.saveToken(token);
        tokenService.revokeAllTokens(tokenService.getUserIdFromToken(token),token);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token).build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

	@GetMapping("/getLoginPatients")
	public ResponseEntity<List<LoginPatient>> getLoginPatients (HttpServletRequest request){
		String requestHeader = request.getHeader("Authorization");
		String token = requestHeader.substring(7);
		System.out.println(request.getHeader("Authorization"));
		System.out.println(Long.valueOf(helper.getUsernameFromToken(token)));
		return new ResponseEntity<>(registerPatientServices.getLoginPatients(Long.valueOf(helper.getUsernameFromToken(token))),  HttpStatus.OK);
	}

	@PostMapping("/register")
    public ResponseEntity<PasswordResponse> register(@Validated @RequestBody RegisterPatient registerPatient) {
		System.out.println(registerPatient);

		PasswordResponse passwordResponse = new PasswordResponse();
		passwordResponse.setUserExists(registerPatientServices.regPatient(registerPatient));
        return new ResponseEntity<>(passwordResponse,  HttpStatus.OK);
    }

	@GetMapping("/patientDetails")
	public ResponseEntity<PatientDetails> patientdetails(@RequestBody PatientDetails patientDetails) {
		System.out.println(patientDetails.getUserId());
		PatientDetails pds = patientDetailServices.getDataFromUserId(patientDetails.getUserId());
		patientDetails = pds;
		return new ResponseEntity<>(patientDetails,  HttpStatus.OK);
	}

//<---------------------------------------------------------------------------------------------------------------------------------->
//	Functions
//<---------------------------------------------------------------------------------------------------------------------------------->

	private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password!!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

//<---------------------------------------------------------------------------------------------------------------------------------->
}
