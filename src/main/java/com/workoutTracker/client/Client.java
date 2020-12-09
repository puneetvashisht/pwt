package com.workoutTracker.client;

import java.util.Scanner;
import com.workoutTracker.*;
import com.workoutTracker.Entity.Role;
import com.workoutTracker.Entity.User;
import com.workoutTracker.Exception.ValidationException;
import com.workoutTracker.Repository.UserRepository;

public class Client {

	
	public static void main(String[] args) throws ValidationException {
		
		UserRepository objRepository=new UserRepository();
		
		Scanner inp = new Scanner(System.in);
		System.out.println("1).Add User \n2).Login User \n3).Edit User \n4).Delete User \n5).Find User \n6).Exit");
		int value =inp.nextInt();
		switch (value) {
		case 1:
			System.out.println("Enter your Email:");
			String email = inp.nextLine();inp.next();
			System.out.println("Enter your password:");
			String password = inp.nextLine();inp.next();
			System.out.println("Enter your height:");
			int height = inp.nextInt();
			System.out.println("Enter your weight:");
			int weight = inp.nextInt();
			System.out.println("Enter your Role:(Admin/Trainer/User):");
			String role = inp.nextLine();inp.next();
			Role roleobj = new Role(role);
			User userobj = new User(email,password,height,weight,roleobj);
			objRepository.addUser(userobj);
			break;
			
		case 2:
			System.out.println("Enter your Email:");
			String email1 =inp.nextLine();inp.next();
			System.out.println("Enter your Password");
			String password1 = inp.nextLine();inp.next();
			objRepository.loginTest(email1, password1);
			break;	
		case 3:
			System.out.println("Enter new email");
//			System.out.println("Enter the id of user");
//			int id =
			String st =inp.nextLine();inp.next();
			//objRepository.editUser(3,st);
			break;
		case 4:
			System.out.println("Enter the user to be deleted");
			String email2 = inp.nextLine();inp.next();
			//objRepository.deleteUser(email2);
			break;
		case 5:
			System.out.println("Enter the user to be Found");
			String email3 = inp.nextLine();inp.next();
			objRepository.findUser(email3);
			break;
		case 6:
			System.exit(0);
			
			
		default:
			break;
		}
	}
}
