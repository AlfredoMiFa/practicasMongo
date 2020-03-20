package com.mistborn.practicas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mistborn.practicas.dao.UsuariosDAO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PracticasApplicationTests {
	
	@Autowired
	private UsuariosDAO dao;

	@Test
	public void contextLoads() {
		dao.insertUsers();
	}

}
