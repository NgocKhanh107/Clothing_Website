package com.duan.service;

import java.util.ArrayList;
import java.util.List;

import com.duan.entities.Users;
import com.duan.repositories.RoleDao;
import com.duan.repositories.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private userRepo userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users appUser = this.userDao.findByEmail(username);
			
		if(appUser == null) {
			System.out.println("User not found! "+username);
			throw new UsernameNotFoundException("User " + username + " was not found in database");
		}
		else {
			System.out.println("User found! "+username);
		}
		
	//	System.out.println("Found User: "+appUser);
		
//		List<String> roleNames = this.roleDao.findRoleNameByUser_Id(appUser.getId());
		List<GrantedAuthority> grandList = new ArrayList<GrantedAuthority>();
		
//		if(roleNames!=null) {
//			for(String role: roleNames) {
//				GrantedAuthority authority = new SimpleGrantedAuthority(role);
//				grandList.add(authority);
//			}
//		}
		
		UserDetails userDetails = (UserDetails) new User(appUser.getEmail(), appUser.getPassword(), grandList);
		
		return userDetails;
	}

}
