package com.jto.lola

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
//@EnableWebSecurity
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
class SecurityConfiguration() {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .authorizeHttpRequests { auth ->
                //auth.anyRequest().authenticated()
                auth
                    .requestMatchers(HttpMethod.GET, "/conversations/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/conversations/**").hasAnyRole("USER")
            }
            .httpBasic(withDefaults()).cors().and().csrf().disable()
        return http.build()
    }

    /*@Bean
    fun userDetailsService(): InMemoryUserDetailsManager? {
        val user: UserDetails = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }*/

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser("guest").password("{noop}pass")
            .roles("GUEST")
            .and()
            .withUser("user1").password("{noop}pass")
            .roles("USER", "GUEST")
            .and()
            .withUser("admin").password("{noop}pass")
            .roles("ADMIN", "USER", "GUEST")
    }
}
    /*override fun configure(http: HttpSecurity) {
        http
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/bankconnections").hasAnyRole("ADMIN", "USER", "GUEST")
            .antMatchers(HttpMethod.POST, "/bankconnections/{\\d+}/H00{\\d+}/**").hasAnyRole("USER", "GUEST")
            .antMatchers(HttpMethod.GET, "/bankconnections/{\\d+}/H00{\\d+}/**").hasAnyRole("USER", "GUEST")
            .antMatchers(HttpMethod.POST, "/bankconnections").hasRole("USER")
            .antMatchers(HttpMethod.PUT, "/bankconnections/{\\d+}").hasRole("USER")
            .antMatchers(HttpMethod.DELETE, "/bankconnections/{\\d+}").hasAnyRole("ADMIN", "USER")
            .antMatchers(HttpMethod.GET, "/banks/**").hasAnyRole("ADMIN", "USER", "GUEST")
            .antMatchers(HttpMethod.POST, "/banks/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/banks/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/banks/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/banks/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/user").hasAnyRole("ADMIN", "USER", "GUEST")
            .antMatchers(HttpMethod.GET, "/user/settings").hasAnyRole("ADMIN", "USER", "GUEST")
            .antMatchers(HttpMethod.PUT, "/user/settings").hasAnyRole("ADMIN", "USER", "GUEST")
            .and()
            .cors().and()
            .csrf().disable()
            .formLogin().disable();
        //http.httpBasic().and().authorizeRequests().antMatchers("/users", "/").permitAll().anyRequest().authenticated()
    }

     */