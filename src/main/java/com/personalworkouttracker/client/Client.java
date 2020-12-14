package com.personalworkouttracker.client;

import java.util.List;
import java.util.Scanner;
import com.personalworkouttracker.entity.Role;
import com.personalworkouttracker.entity.User;
import com.personalworkouttracker.exception.ValidationException;
import com.personalworkouttracker.repository.UserRepository;
import com.personalworkouttracker.service.UserService;

public class Client {
	public static void main(String[] args) throws ValidationException {

		UserRepository objRepository = new UserRepository();
		UserService userService = new UserService();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1).Add User \n2).Login User \n3).Edit User \n4).Delete User \n5).Find User \n6).ViewAllUser \n7).Exit");
			System.out.println("Enter your choice");
			int value = scanner.nextInt();
			switch (value) {
			case 1:
				System.out.println("Enter your Email:");
				String email = scanner.next();
				System.out.println("Enter your password:");
				String password = scanner.next();
				System.out.println("Enter your height:");
				int height = scanner.nextInt();
				System.out.println("Enter your weight:");
				int weight = scanner.nextInt();
				System.out.println("Enter your Role:(Admin/Trainer/User):");
				String role = scanner.next();
				Role roleobj = new Role(role);
				User userobj = new User(email, password, height, weight, roleobj);
				userService.add(userobj);
				break;

			case 2:
				System.out.println("Enter your Email:");
				String email1 = scanner.next();
				System.out.println("Enter your Password");
				String password1 = scanner.next();
				objRepository.loginTest(email1, password1);
				break;

			case 3:
				System.out.println("Enter your id");
				int id = scanner.nextInt();
				System.out.println("Enter your new Email");
				String newEmail = scanner.next();
				userService.editUser(id, newEmail);
				break;

			case 4:
				System.out.println("Enter the user to be deleted");
				String email2 = scanner.next();
				System.out.println("enter the user id");
				int iddd = scanner.nextInt();
				objRepository.deleteUser(iddd, email2);
				break;
				
			case 5:
				System.out.println("Enter the user to be Found");
				String email3 = scanner.next();
				System.out.println(objRepository.findUser(email3));
				break; 
				
			case 6:
				List<User> userList = objRepository.findAllUser();
				for (User u : userList) 
				{
					System.out.println(u);
				}
				break;

			case 7:
				System.exit(0);
				
			default:
				break;
			}
		}		
	}
}
