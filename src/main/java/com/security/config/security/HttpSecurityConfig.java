package com.security.config.security;

import com.security.config.security.filter.JwtAuthenticationFilter;
import com.security.persistence.util.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider daoAuthProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthorizationManager<RequestAuthorizationContext> authorizationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SecurityFilterChain filterChain = http
                //.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement( sessionMagConfig -> sessionMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( auth -> {
                    auth.anyRequest().access(authorizationManager);
                })
                .exceptionHandling(exceptionConfig ->{
                    exceptionConfig.authenticationEntryPoint(authenticationEntryPoint);
                    exceptionConfig.accessDeniedHandler(accessDeniedHandler);
                })
                .build();
        return filterChain;
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {

        /*  public authorization  */
        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

        authReqConfig.anyRequest().authenticated();
    }

    private static void buildRequestMatchersV2(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
        /*  products authorization  */

//        authReqConfig.requestMatchers(HttpMethod.GET,"/product").hasAuthority(RolePermission.READ_ALL_PRODUCTS.name());
//        authReqConfig.requestMatchers(HttpMethod.GET,"/product/{productId}").hasAuthority(RolePermission.READ_ONE_PRODUCT.name());
//        authReqConfig.requestMatchers(HttpMethod.POST,"/product").hasAuthority(RolePermission.CREATE_ONE_PRODUCT.name());
//        authReqConfig.requestMatchers(HttpMethod.PUT,"/product/{productId}").hasAuthority(RolePermission.UPDATE_ONE_PRODUCT.name());
//        authReqConfig.requestMatchers(HttpMethod.PUT,"/product/{productId}/disabled").hasAuthority(RolePermission.DISABLE_ONE_PRODUCT.name());

        authReqConfig.requestMatchers(HttpMethod.GET,"/product")
                .hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/product/{productId}")
                .hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/product").hasRole(RoleEnum.ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/product/{productId}")
                .hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/product/{productId}/disabled").hasRole(RoleEnum.ADMINISTRATOR.name());

        /*  categories authorization  */
//        authReqConfig.requestMatchers(HttpMethod.GET,"/category").hasAuthority(RolePermission.READ_ALL_CATEGORIES.name());
//        authReqConfig.requestMatchers(HttpMethod.GET,"/category/{categoryId}").hasAuthority(RolePermission.READ_ONE_CATEGORY.name());
//        authReqConfig.requestMatchers(HttpMethod.POST,"/category").hasAuthority(RolePermission.CREATE_ONE_CATEGORY.name());
//        authReqConfig.requestMatchers(HttpMethod.PUT,"/category/{categoryId}").hasAuthority(RolePermission.UPDATE_ONE_CATEGORY.name());
//        authReqConfig.requestMatchers(HttpMethod.PUT,"/category/{categoryId}/disabled").hasAuthority(RolePermission.DISABLE_ONE_CATEGORY.name());

        authReqConfig.requestMatchers(HttpMethod.GET,"/category")
                .hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/category/{categoryId}")
                .hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/category").hasRole(RoleEnum.ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/category/{categoryId}")
                .hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/category/{categoryId}/disabled").hasRole(RoleEnum.ADMINISTRATOR.name());

        /*  auth authorization  */

//        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/profile").hasAuthority(RolePermission.READ_MY_PROFILE.name());

        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/profile")
                .hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name(), RoleEnum.CUSTOMER.name());


        /*  public authorization  */
        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

        authReqConfig.anyRequest().authenticated();
    }
}
