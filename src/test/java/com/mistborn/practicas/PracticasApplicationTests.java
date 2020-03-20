package com.mistborn.practicas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mistborn.practicas.dao.UsuariosDAO;
import com.mistborn.practicas.entity.Usuarios;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PracticasApplicationTests {
	
	private static final Logger log = LoggerFactory.getLogger(PracticasApplicationTests.class);
	
	@Autowired
	private UsuariosDAO dao;

	@Test
	public void contextLoads() {
		long total = 10000000;
		Usuarios usuario = dao.getUsuario("Don Juan 5095021", total);
		ObjectMapper mp = new ObjectMapper();
		try {
			log.info("Usuario: {}",mp.writeValueAsString(usuario));
		} catch (JsonProcessingException e) {
			log.info("Exception: ",e);
		}
	}

}
