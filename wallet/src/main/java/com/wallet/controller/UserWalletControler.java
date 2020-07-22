package com.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;
import com.wallet.response.Response;
import com.wallet.service.UserWalletService;

@RestController
@RequestMapping(path = "user-wallet") // nome do end-point

public class UserWalletControler {

	@Autowired
	private UserWalletService service;
	
	@PostMapping
	public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody  UserWalletDTO dto, BindingResult result) {
		
		Response<UserWalletDTO> response = new Response<UserWalletDTO>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(response);
		}
		
		UserWallet uw = service.save(this.convertDtotoEntity(dto));
		
		response.setData(this.convertEntitytoDto(uw));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	public UserWallet convertDtotoEntity(UserWalletDTO dto) {
		UserWallet uw = new UserWallet();
		uw.setId(dto.getId());
		
		User us = new User();
		us.setId(dto.getUsers());
		uw.setUsers(us);
		
		Wallet w = new Wallet();
		w.setId(dto.getWallet());
		uw.setWallet(w);
		
		return uw;
	}
	
	public UserWalletDTO convertEntitytoDto(UserWallet uw) {
		
		UserWalletDTO dto = new UserWalletDTO();
	    dto.setId(uw.getId());
		dto.setUsers(uw.getUsers().getId());
		dto.setWallet(uw.getWallet().getId());
		
		
		return dto;
		
	}

	
}
