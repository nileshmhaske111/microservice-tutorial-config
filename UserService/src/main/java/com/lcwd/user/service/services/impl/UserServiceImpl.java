package com.lcwd.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import com.lcwd.user.service.services.exceptions.ResourcenotFoundException;
import com.lcwd.user.service.services.external.services.HotelService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	//private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
		
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow( ()-> new ResourcenotFoundException("User with given Id is jot found on server !: "+userId));
		String url = "http://RATINGSERVICE/ratings/users/"+user.getUserId();
		Rating[] ratingsOfUser = restTemplate.getForObject(url, Rating[].class);
		logger.info("{} ",ratingsOfUser);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		List<Rating> ratingList = ratings.stream().map(rating->{
//		ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
		Hotel hotel = hotelService.getHotel(rating.getHotelId());	
//		logger.info("response status code: {} ", forEntity.getStatusCodeValue());
		rating.setHotel(hotel);
		return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

}
