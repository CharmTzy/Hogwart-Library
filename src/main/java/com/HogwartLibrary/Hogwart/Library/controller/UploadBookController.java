package com.HogwartLibrary.Hogwart.Library.controller;

import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.HogwartLibrary.Hogwart.Library.dbaccess.ImageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UploadBookController {
	@RequestMapping(method = RequestMethod.POST, path = "/uploadImage/book", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadNormalBookImage(@RequestParam("image") MultipartFile imageFile) {
		
		boolean condition = false;
		try {
				String base64ImageData = Base64.getEncoder().encodeToString(imageFile.getBytes());
				String key = "book/";
				
				ImageRequest imagerequest = new ImageRequest();
				imagerequest.setImage_data(base64ImageData);
				imagerequest.setImage_name(imageFile.getOriginalFilename());
				imagerequest.setKey(key);
				
				ObjectMapper obj = new ObjectMapper();
				String json = obj.writeValueAsString(imagerequest);
				
				HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(json, headers);

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.postForEntity("https://qdqfasa7zk.execute-api.us-east-1.amazonaws.com/V1", entity, String.class);

                // Handle the response if needed
                if (response.getStatusCode() == HttpStatus.OK) {
                   	condition = true;
                } else {
                    condition = false;
                }
			}
			catch(Exception e) { 
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(false);
			}
		
		return ResponseEntity.ok().body(condition);
	}
}
