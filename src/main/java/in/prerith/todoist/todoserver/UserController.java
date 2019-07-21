package in.prerith.todoist.todoserver;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/listUsers")
	    public List<User> listUsers() {
	        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
	    }
	 @PostMapping("/updateUser")
	    public User updateUser(@RequestBody User inputUser) {
	        User updatedUser = userRepository.save(inputUser);
	        return updatedUser;
	    }

	    @PostMapping("/createUser")
	    public User createUser(@RequestBody User inputUser) {
	        inputUser.setUserId(UUID.randomUUID().toString());
	        userRepository.save(inputUser);
//	        User.add(inputUser);
	        return inputUser;
	    }

	 
	}


