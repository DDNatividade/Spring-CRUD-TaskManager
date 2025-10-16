package com.FirstCrudSpring.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpS) throws Exception {
        return httpS
        //Cuando nos loggeamos solo con ususario y contraseña
         .httpBasic(Customizer.withDefaults())
         
         //Configuración de la página de login de la web 
         //Cumple la función del post en el formulario.
		.formLogin(form -> form
			    .loginPage("/")                // <- Esta página muestra tu formulario "login.html"
			    .loginProcessingUrl("/login")  // <- Aquí se envían los datos (POST)
			    .defaultSuccessUrl("/user", true) // <- A dónde ir tras autenticarse
			    .failureUrl("/?error")          // <- aquí define qué parámetro se añade cuando hay un fallo. Dicho parámetro
			//podremos hacer uso de el en el formulario.
			    .usernameParameter("email") // aquí le dices que use "email" en vez de "username" para validar en el formulario
			    // de lo contrario usa "userName"
			    .permitAll()
			    )
		
		//Caso de logout
		.logout(logout -> logout
			    .logoutUrl("/logout")
			    .logoutSuccessUrl("/?logout")
			    .permitAll()
			    )
         
       //Permiso por cada endpoint
         .authorizeHttpRequests(http -> {
             // Configurar los endpoints publicos 
             http.requestMatchers(HttpMethod.GET, "/").permitAll();

             // Cofnigurar los endpoints privados
             http.requestMatchers(HttpMethod.POST, "/user/**").hasAnyRole("ADMIN", "USER");
             http.requestMatchers(HttpMethod.PATCH, "/auth/patch").hasAnyAuthority("REFACTOR");

             // Configurar el resto de endpoint - NO ESPECIFICADOS ANTES
             http.anyRequest().denyAll(); //También podemos usar .Autenticate(). Si el usuario es seguro permite el acceso 
         })
         .build();
            
    }
	
	@SuppressWarnings("deprecation")
	@Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailService){
		
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        
        //Devolvemos el valor
        return provider;
	}
	
	@Bean
    public PasswordEncoder passwordEncoder(){
    	
    	//Encriptación de la contraseña
        return new BCryptPasswordEncoder();
    }	
	
	
}
