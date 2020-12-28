package com.sprint2.personalworkout.services.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.sprint2.personalworkout.entity.Role;
import com.sprint2.personalworkout.entity.User;
import com.sprint2.personalworkout.services.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class UserServiceImplTest {

	Role role;
	User user;

	@MockBean
	UserService userService;

	@SuppressWarnings("deprecation")
	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeEach
	void setUp() throws Exception {
		role = new Role("trainer");
		user = new User(1, "murali@gmail.com", "Murali@123", 180, 76, role);
	}

	@AfterEach
	void tearDown() throws Exception {
		user = null;
		role = null;
	}

	@Test
	void testAddUser() {
		userService.addUser(user);
		verify(userService, times(1)).addUser(user);
		assertEquals("murali@gmail.com", user.getEmail());
	}

	@Test
	void testLogin() {
		userService.login(user);
		verify(userService, times(1)).login(user);
		assertEquals("murali@gmail.com", user.getEmail());
	}

	@Test
	void testFindUserById() {
		userService.findUserById(1);
		verify(userService, times(1)).findUserById(1);
		assertEquals("murali@gmail.com", user.getEmail());
	}

	@Test
	void testFindByEmail() {
		userService.findByEmail("murali@gmail.com");
		verify(userService, times(1)).findByEmail("murali@gmail.com");
		assertEquals("murali@gmail.com", user.getEmail());
	}

	@Test
	void testFindAll() {
		List<User> userList = new ArrayList<>();
		userList.add(user);
		when(userService.findAll()).thenReturn(userList);
		userService.findAll();
		verify(userService, times(1)).findAll();
		assertEquals(1, userList.size());
		assertNotNull(userList);
	}

	@Test
	void testDeleteUser() {
		userService.deleteUser(2);
		verify(userService, times(1)).deleteUser(2);
		assertNotNull(user);
	}

	@Test
	void testUpdateUser() {
		user.setHeight(190);
		userService.updateUser(user);
		verify(userService, times(1)).updateUser(user);
		assertEquals(190, user.getHeight());
	}
}
