package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;

@CrossOrigin(origins = "*")
@RestController
public class AdminController {
	
	@Autowired
	AdminRepository adminRepository;
	

	@GetMapping("/admin")
	public ResponseEntity<Object>  getAdmin(){
		try {
			List<Admin> admins = adminRepository.findAll();
			return new ResponseEntity<>(admins,HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/admin")
	public ResponseEntity<Object> addAdmin(@RequestBody Admin body) {
		try {

			
			Admin admin =  adminRepository.save(body);
			

			return new ResponseEntity<>(admin, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/admin/{adminId}")
	public ResponseEntity<Object> getAdminDetail(@PathVariable Integer adminId) {
		
		try {
			Optional<Admin> admin = adminRepository.findById(adminId);
			if(admin.isPresent()) {
				return new ResponseEntity<>(admin,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Admin Not Found",HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("admin/{adminId}")
	public ResponseEntity<Object> updateAdmin(@PathVariable Integer adminId,@RequestBody Admin body) {
		try {Optional<Admin> admin =  adminRepository.findById(adminId);
		
		if (admin.isPresent()) {
			Admin adminEdit = admin.get();
			adminEdit.setAdminfirstName(body.getAdminfirstName());
			adminEdit.setAdminlastName(body.getAdminlastName());
			adminEdit.setAdminId(body.getAdminId());
			
			
			adminRepository.save(adminEdit);
			
			return new ResponseEntity<>(adminEdit,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Admin Not Found.",HttpStatus.BAD_REQUEST);
		}
	
		}catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
		
	
	@DeleteMapping("admin/{adminId}")
	public ResponseEntity<Object> deleteadmin(@PathVariable Integer adminId) {
		try {
			Optional<Admin> admin =  adminRepository.findById(adminId);
		
			if (admin.isPresent()) {
			
				adminRepository.delete(admin.get());
				return new ResponseEntity<>("Delete Admin Success.",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Admin Not Found.",HttpStatus.BAD_REQUEST);
		}
		}catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	  @PostMapping("/admin/login")  
	    public ResponseEntity<Object> loginAdmin(@RequestBody Admin body) {
	        try {
	            Optional<Admin> adminFound = adminRepository.findByAdminUsername(body.getAdminUsername());

	            if (adminFound.isPresent() && adminFound.get().getAdminPassword().equals(body.getAdminPassword())) {
	                adminFound.get().setAdminPassword(null);
	                return new ResponseEntity<>(adminFound, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Invalid Credentials.", HttpStatus.UNAUTHORIZED);
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            return new ResponseEntity<>("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}