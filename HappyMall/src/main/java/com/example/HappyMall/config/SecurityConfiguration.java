package com.example.HappyMall.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.HappyMall.Handler.CustomerLoginSuccessHandler;

//import com.example.HappyMall.Handler.CustomerLoginSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private CustomerLoginSuccessHandler customerLoginSuccessHandler;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    

    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/shoppingcart/**").permitAll()
//                .antMatchers("/admin/**").permitAll()
//                .antMatchers("/products/**").permitAll()
//                .antMatchers("/search/**").permitAll()


//                .antMatchers("/vendor").permitAll()
//                .antMatchers("/vendors").permitAll()
                .antMatchers("/admin/**").hasAuthority("4")
                .antMatchers("/vendor/**").hasAuthority("2")
//			    .antMatchers("/shoppingcart/**").hasAuthority("1")
			    //.antMatchers("/index/**").hasAuthority("1")
//			    .antMatchers("/customer/**").hasAuthority("3")
			    //.antMatchers("/home/**").hasAnyAuthority("VENDOR_USER","ADMIN","END_USER","CUSTOMER_USER")
                //.antMatchers("/admin/**").hasAnyAuthority("VENDER_USER","ADMIN")
                .anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .successHandler(customerLoginSuccessHandler)
                //.defaultSuccessUrl("/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.
//                authorizeRequests()
//                .antMatchers("/h2-console").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/register").permitAll()
//                .antMatchers("/shoppingcart").permitAll()
//                .antMatchers("/admin/**").permitAll()
////                .antMatchers("/vender/**").hasAuthority("VENDER_USER")
////				.antMatchers("/endUser/**").hasAuthority("SITE_USER")
//                .antMatchers("/home/**").hasAnyAuthority("VENDER_USER","ADMIN","SITE_USER").anyRequest()
////                .antMatchers("/admin/**").hasAnyAuthority("VENDER_USER","ADMIN").anyRequest()
//                .authenticated().and().csrf().disable()
//                .formLogin()
//                .loginPage("/login").failureUrl("/login?error=true")
//                .successHandler(customerLoginSuccessHandler)
//                .defaultSuccessUrl("/home")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/").and().exceptionHandling()
//                .accessDeniedPage("/access-denied");
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/h2-console/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
