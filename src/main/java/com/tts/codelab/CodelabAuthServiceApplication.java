package com.tts.codelab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.tts.codelab.service.MongoUserDetailService;

@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CodelabAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodelabAuthServiceApplication.class, args);
    }

    @Configuration
    @EnableWebSecurity
    protected static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private MongoUserDetailService userDetailsService;

        @Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
            http
                .authorizeRequests().anyRequest().authenticated()
            .and()
                .csrf().disable();
            // @formatter:on
		}

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            // @formatter:off
            auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
            // @formatter:on
        }

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            // TODO Auto-generated method stub
            return super.authenticationManagerBean();
        }
    }
    
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServer extends AuthorizationServerConfigurerAdapter{
        
        private TokenStore tokenStore = new InMemoryTokenStore();
        
        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;
        
        @Autowired
        private MongoUserDetailService userDetailsService;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // @formatter:off
            clients
                .inMemory()
                    .withClient("browser")
                        .authorizedGrantTypes("refresh_token", "password")
                        .scopes("ui");
            // @formatter:on

        }
        
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            // @formatter:off
            endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
            // @formatter:on
        }
        
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            // @formatter:off
            security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
            // @formatter:on
        }
    }
}
