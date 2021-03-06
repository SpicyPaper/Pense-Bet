package ch.arc.pensebet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	private static final String adminRole = "ADMIN";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
			jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder());
    }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()		
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/register").permitAll()

				.antMatchers("/search/{page}").authenticated()
				
				.antMatchers("/bet/create").authenticated()
				.antMatchers("/bet/{id}").authenticated()
				.antMatchers("/bet/user/waiting/{page}").authenticated()
				.antMatchers("/bet/user/active/{page}").authenticated()
				.antMatchers("/bet/user/ended/{page}").authenticated()
				.antMatchers("/bet/user/closed/{page}").authenticated()
				.antMatchers("/bet/user/created/active/{page}").authenticated()
				.antMatchers("/bet/user/created/ended/{page}").authenticated()
				.antMatchers("/bet/user/created/closed/{page}").authenticated()

				.antMatchers("/bet/admin/all/{page}").hasAuthority(adminRole)
				.antMatchers("/bet/{id}/delete").hasAuthority(adminRole)
				.antMatchers("/bet/moderator/all/{page}").hasAnyAuthority(adminRole, "MOD")
				.antMatchers("/bet/{id}/update").hasAnyAuthority(adminRole, "MOD")
				.and().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/",true)
				.usernameParameter("nickname")
				.passwordParameter("password")
				.and().logout()
		
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/").and().exceptionHandling()
		        .accessDeniedPage("/access-denied");
	}
    
    @Bean
	public AuthenticationSuccessHandler successHandler() {
	    SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
	    handler.setUseReferer(true);	 
	    return handler;
	}
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}