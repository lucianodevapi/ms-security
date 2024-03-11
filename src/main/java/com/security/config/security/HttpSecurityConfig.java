package com.security.config.security;

import com.security.config.security.filter.JwtAuthenticationFilter;
import com.security.persistence.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider daoAuthProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SecurityFilterChain filterChain = http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement( sessionMagConfig -> sessionMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(HttpSecurityConfig::buildRequestMatchers)
                .build();
        return filterChain;
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
        /*  products authorization  */

//        authReqConfig.requestMatchers(HttpMethod.GET,"/product").hasAuthority(RolePermission.READ_ALL_PRODUCTS.name());
//        authReqConfig.requestMatchers(HttpMethod.GET,"/product/{productId}").hasAuthority(RolePermission.READ_ONE_PRODUCT.name());
//        authReqConfig.requestMatchers(HttpMethod.POST,"/product").hasAuthority(RolePermission.CREATE_ONE_PRODUCT.name());
//        authReqConfig.requestMatchers(HttpMethod.PUT,"/product/{productId}").hasAuthority(RolePermission.UPDATE_ONE_PRODUCT.name());
//        authReqConfig.requestMatchers(HttpMethod.PUT,"/product/{productId}/disabled").hasAuthority(RolePermission.DISABLE_ONE_PRODUCT.name());

        authReqConfig.requestMatchers(HttpMethod.GET,"/product")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/product/{productId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/product").hasRole(Role.ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/product/{productId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/product/{productId}/disabled").hasRole(Role.ADMINISTRATOR.name());

        /*  categories authorization  */
//        authReqConfig.requestMatchers(HttpMethod.GET,"/category").hasAuthority(RolePermission.READ_ALL_CATEGORIES.name());
//        authReqConfig.requestMatchers(HttpMethod.GET,"/category/{categoryId}").hasAuthority(RolePermission.READ_ONE_CATEGORY.name());
//        authReqConfig.requestMatchers(HttpMethod.POST,"/category").hasAuthority(RolePermission.CREATE_ONE_CATEGORY.name());
//        authReqConfig.requestMatchers(HttpMethod.PUT,"/category/{categoryId}").hasAuthority(RolePermission.UPDATE_ONE_CATEGORY.name());
//        authReqConfig.requestMatchers(HttpMethod.PUT,"/category/{categoryId}/disabled").hasAuthority(RolePermission.DISABLE_ONE_CATEGORY.name());

        authReqConfig.requestMatchers(HttpMethod.GET,"/category")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/category/{categoryId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/category").hasRole(Role.ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/category/{categoryId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/category/{categoryId}/disabled").hasRole(Role.ADMINISTRATOR.name());

        /*  auth authorization  */

//        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/profile").hasAuthority(RolePermission.READ_MY_PROFILE.name());

        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/profile")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name(), Role.CUSTOMER.name());


        /*  public authorization  */
        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

        authReqConfig.anyRequest().authenticated();
    }
}
