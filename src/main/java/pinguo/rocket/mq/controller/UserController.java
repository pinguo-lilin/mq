package pinguo.rocket.mq.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pinguo.rocket.mq.entity.User;
import pinguo.rocket.mq.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Resource
	UserService uService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model)
	{
		List<User> userList = uService.listUsers();
		for(User user : userList){
			System.out.println("id:"+user.getId());
			System.out.println("name:"+user.getName());
			System.out.println("age"+user.getAge());
			System.out.println("desc"+user.getContent());
		}
		
		model.addAttribute("title", "用户列表");
		model.addAttribute("userList", userList);
		
		return "user_list";
	}
}
