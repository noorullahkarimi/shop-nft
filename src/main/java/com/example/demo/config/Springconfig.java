package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.example.demo.models.Users;

@Configuration
public class Springconfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(NoOpPasswordEncoder.getInstance())
//				 .passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("select username,password, enabled from users where username = ?")
				.authoritiesByUsernameQuery("select username,authorities from authorities where username = ?");
	}

	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	 http.csrf().disable()
	 .authorizeRequests().antMatchers(
	 "/","/css/**","/js/**","/img/**","/loginUser/**","/users/**","/webfonts/**",
	 "/productsList/**","/collectionList/**","/artists/**"
	 ,"/loginCllients"
	 ).permitAll()
	 .antMatchers("/admin").hasAuthority("1")
	 .antMatchers("/admin/users/**").hasAuthority("2")
	 .antMatchers("/admin/products/**").hasAuthority("3")
	 .antMatchers("/admin/collections/**").hasAuthority("4")
	 .antMatchers("/admin/categoryies/**").hasAuthority("5")
	 .antMatchers("/admin/authorities/**").hasAuthority("6")
	 .anyRequest().authenticated().and().formLogin().loginPage("/loginCllients").usernameParameter("username").permitAll().and().logout().permitAll()
	 .and().exceptionHandling().accessDeniedPage("/NFTMarket/login.html");
	 }
}

