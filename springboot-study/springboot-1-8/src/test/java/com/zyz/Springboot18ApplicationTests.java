package com.zyz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot18ApplicationTests {

	/**
	 o：organization（组织-公司）
	 ou：organization unit（组织单元-部门）
	 c：countryName（国家）
	 dc：domainComponent（域名）
	 sn：surname（姓氏）
	 cn：common name（常用名称）
	 */
	@Autowired
	private PersonRepository personRepository;

	@Test
	public void findAll() throws Exception {

		personRepository.findAll().forEach(p -> {
			System.out.println(p);
		});

	}

	@Test
	public void save() throws Exception {
		Person person = new Person();
		person.setUid("uid:1");
		person.setSuerName("AAA");
		person.setCommonName("aaa");
		person.setUserPassword("123456");
		personRepository.save(person);

		personRepository.findAll().forEach(p -> {
			System.out.println(p);
		});
	}

}
