package com.mistborn.practicas.dao;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mistborn.practicas.entity.Usuarios;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UsuariosDAO {
	
	private static final Logger log = LoggerFactory.getLogger(UsuariosDAO.class);
	
	@Autowired
	@Qualifier(value = "primaryMongoTemplate")
	private MongoTemplate mongoTemplate;
	
	public void insertUsers() {
		log.info("UsuariosDAO [insertUsers]");
		Random ran = new Random();
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		long inicio = System.currentTimeMillis();
		for(int i = 0;i<10000000;i++) {
			int random1 = ran.nextInt(1000000);
			StringBuilder sb = new StringBuilder();
			sb.append(random1);
			int count = 10;
			while(count-- != 0) {
				int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
				sb.append(ALPHA_NUMERIC_STRING.charAt(character));
			}
			Usuarios user = new Usuarios();
			user.set_id(sb.toString());
			if(i<5000000) {
				user.setActivo(true);
				user.setAp("Android"+i);
			}
			else {
				user.setActivo(false);
				user.setAp("IOS"+i);
			}
			user.setAlias("user"+random1);
			user.setCelular(String.valueOf(random1));
			user.setDv(String.valueOf((random1+1)));
			user.setNombre("Juanito "+i);
			user.setUserName("Don Juan "+i);
			mongoTemplate.save(user);
		}
		log.info("Terminamos en: {}",(System.currentTimeMillis()-inicio));
	}
	
}
