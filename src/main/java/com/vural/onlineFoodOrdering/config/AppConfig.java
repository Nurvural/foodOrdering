package com.vural.onlineFoodOrdering.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//JWT durumsuz kimlik doğrulama için kullanıldığından oturum oluşturmayı devre dışı bırakır.
		//HTTP isteklerinin yetkilendirilmesini konfigüre eder.
				.authorizeHttpRequests(
						Authorize -> Authorize.requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER", "ADMIN") ///api/admin/ ile başlayan tüm yolları yalnızca RESTAURANT_OWNER veya ADMIN rollerine sahip kullanıcıların erişimine açar.
								.requestMatchers("/api/**").authenticated() ///api/ ile başlayan tüm yolları yalnızca kimliği doğrulanmış kullanıcılara erişime açar.
								.anyRequest().permitAll())//Diğer tüm yollar ise herkese açıktır.
				.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)//JwtTokenValidator sınıfından oluşturulan filtreyi, temel HTTP kimlik doğrulama filtresinden önce ekler. Bu, isteklerin önce JWT token'ı kullanılarak doğrulanmasını sağlar.
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigrationSource()));//CORS konfigürasyonunu ayarlar.
		return null;

	}

	private CorsConfigurationSource corsConfigrationSource() {
		
		return new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {//Belirli bir HTTP isteği için CORS konfigürasyonu sağlar.
				CorsConfiguration configuration = new CorsConfiguration();
				configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));// CORS'a izin verilen kaynakları (domain'leri) bir dizi olarak ayarlar.
				configuration.setAllowedMethods(Collections.singletonList("*"));// Bu satır, CORS'a izin verilen HTTP metodlarını bir dizi olarak ayarlar.
				configuration.setAllowCredentials(true);//Bu satır, CORS isteklerinde kimlik bilgilerinin (çerezler gibi) gönderilmesine izin verir. 
				configuration.setAllowedHeaders(Collections.singletonList("*"));
				configuration.setExposedHeaders(Arrays.asList("Authorization"));
				configuration.setMaxAge(3600L);
				return configuration;
			}
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();//Bu metod, şifreleri hash'lemek için bir BCryptPasswordEncoder bean'i oluşturur ve döndürür. Spring Security, kullanıcı şifrelerini veritabanında güvenli bir şekilde saklamak için şifre hash'lemeyi kullanır.
    
	}
}
