package com.problem.two.liquibases;

import com.problem.two.liquibases.entity.a.EntityA;
import com.problem.two.liquibases.entity.a.EntityARepository;
import com.problem.two.liquibases.entity.b.EntityBRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("ab")
class TwoLiquibasesApplicationTests {

	@Autowired
	private EntityARepository entityARepository;

	@Autowired
	private EntityBRepository entityBRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createEntityA() {
		final EntityA entityA = new EntityA();
		entityA.fieldA = "ABCD";
		assertThat(entityARepository.save(entityA)).hasNoNullFieldsOrProperties();
	}

	@Test
	public void findEntityB() {
		assertThat(entityBRepository.findById(1L));
	}

}
