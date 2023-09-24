package com.gainsight.casestudy.Config;

import com.gainsight.casestudy.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig
{
    @Bean
    public PasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        return new CustomUserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return http.csrf().disable()
                //.authorizeHttpRequests().requestMatchers("/employee/id/**","/Users").permitAll().and()
                .authorizeHttpRequests().requestMatchers("/getAllProducts","/getProductById/**","/Users/**","/getProductByName/**").permitAll().and()
                .authorizeHttpRequests().requestMatchers("/deleteProduct/**","/addProduct","/showAllOrders","/showAllOrdersToAdmin/{status}","/markOrderAsDelivered/{orderId}","/doPayment","/order/{orderId}").hasAuthority("ROLE_ADMIN").and()
                .authorizeHttpRequests().requestMatchers("/getProductDetails/{isSingleProductCheckout}/**","/cart/**","/cart/getCartDetails").hasAuthority("ROLE_USER").and()
                .authorizeHttpRequests().requestMatchers("/placeOrder/{isCartCheckout}").hasAuthority("ROLE_USER").and().httpBasic().and().build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setPasswordEncoder(encoder());
        dap.setUserDetailsService(userDetailsService());
        return dap;
    }


}
