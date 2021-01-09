package ar.ungs.shop.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.ungs.shop.repositories.UsersDao;

@Profile("qa")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DatabaseSeederServiceImplTest {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private DatabaseSeederService seeder;

	@Test
	void testDependenciesAreNotNull() {
		Assertions.assertNotNull(seeder);
		Assertions.assertNotNull(usersDao);
	}
	
	@Test
	void testSeedDbFromYamelFile() {
		seeder.seedDatabase();
		final int NUMBER_OF_EXPECTEDS_USERS = 3;
		Assertions.assertEquals(usersDao.findAll().size(), NUMBER_OF_EXPECTEDS_USERS);
	}
}
