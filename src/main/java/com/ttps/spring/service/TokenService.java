package com.ttps.spring.service;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
	final static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String generarToken(String email, int segundos) {
		Date exp = new Date(System.currentTimeMillis() + segundos * 1000);
		return Jwts.builder().setSubject(email).signWith(key).setExpiration(exp).compact();
	}
	
	public static boolean validar(String token) {
		String prefix = "Bearer";
		try {
			if (token.startsWith(prefix)) {
				token = token.substring(prefix.length()).trim();
			}
			Claims claims = Jwts.parser()
			.setSigningKey(key)
	        .parseClaimsJws(token).getBody();
			System.out.println("ID: " + claims.getId());
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Issuer: " + claims.getIssuer());
            System.out.println("Expiration: " + claims.getExpiration());
			/*
			 .
			 * Copyright 2022 the original author or authors.
			 *
			 * Licensed under the Apache License, Version 2.0 (the "License");
			 * you may not use this file except in compliance with the License.
			 * You may obtain a copy of the License at
			 *
			 *      https://www.apache.org/licenses/LICENSE-2.0
			 *
			 * Unless required by applicable law or agreed to in writing, software
			 * distributed under the License is distributed on an "AS IS" BASIS,
			 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
			 * See the License for the specific language governing permissions and
			 * limitations under the License.
			 */
            return true;
		}catch(ExpiredJwtException exp){
			return false;
		}catch(JwtException e){
			System.out.println(e);
			return false;
		}
	}
}
