package com.example.demo.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

@RestController
public class Main {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/hellow")
	public String hellow() {
		return "hellow";
	}
	
	@GetMapping("/tianqi")
	public String tianqi(@PathParam(value = "city") Long city) {
		System.out.println("1231");
		System.out.println(restTemplate);
//		return null;
		return restTemplate.getForObject("http://localhost:7900/simple/" + city, String.class);
	}
}
