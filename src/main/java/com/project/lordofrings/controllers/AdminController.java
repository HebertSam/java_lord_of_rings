package com.project.lordofrings.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.lordofrings.models.Guild;
import com.project.lordofrings.models.Ring;
import com.project.lordofrings.models.Role;
import com.project.lordofrings.models.User;
import com.project.lordofrings.services.RingService;
import com.project.lordofrings.services.UserService;

@Controller
public class AdminController{
	private UserService userService;
	private RingService ringService;

	public AdminController(UserService userService, RingService ringService){
		this.userService = userService;
		this.ringService = ringService;
	}
	
	@RequestMapping("/admin/forge")
	public String index(){
		return "forge";
	}
	@PostMapping("/admin/forge")
	public String forge(Principal principal, @RequestParam("name") String name, RedirectAttributes flash){
		Ring ring = new Ring(name);
		ringService.createRing(ring);
		return "redirect:/dashboard";
	}
	@RequestMapping("/admin/fool_creator")
	public String foolCreator(@Valid @ModelAttribute("guild") Guild guild, Principal principal, Model model){
		List<User> allUsers = userService.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("guilds", ringService.getAllGuilds());
		for (int i = 0; i< allUsers.size(); i++){
			System.out.println(allUsers.get(i).getusername());
			System.out.println(allUsers.get(i).getGuilds().size());
			for (int j = 0; i< allUsers.get(i).getGuilds().size(); i++){
				System.out.println("in second loop");
				if(allUsers.get(i).getGuilds().get(j) == null){
					System.out.println("no guilds");
				}
				System.out.println(allUsers.get(i).getGuilds().get(j).getName());
			}
		}
		return "foolCreator";
	}
	@PostMapping("/admin/createGuild")
	public String createGuild(@Valid @ModelAttribute("guild") Guild guild, BindingResult result, Model model, RedirectAttributes flash){
		if (result.hasErrors()){
			flash.addFlashAttribute("error", result.getAllErrors());
			return "redirect:/admin/fool_creator";
		}
		ringService.createGuild(guild);
		return "redirect:/admin/fool_creator";
	}
	@PostMapping("/admin/joinGuild")
	public String joinGuild(@RequestParam("user") long userId, @RequestParam("guild") long guildId, RedirectAttributes flash){
		User user = userService.findById(userId);
		Guild guild = ringService.findbyId(guildId);
		if (guild.getUsers().contains(user)){
			System.out.println("hit if 1");
			flash.addFlashAttribute("guildError", "user is already in selected guild");
			return "redirect:/admin/fool_creator";
		}
		if (guild.getUsers().size() >= guild.getSize()){
			System.out.println("hit if 2");
			flash.addAttribute("guildError", "guild is already full");
			return "redirect:/admin/fool_creator";
		}
		guild.getUsers().add(user);
		ringService.updateGuild(guild);
		System.out.println(guild.getUsers().size());
		return "redirect:/admin/fool_creator";
	}
	@RequestMapping("/admin/destroy/{id}")
	public String destroy(@PathVariable("id") long id){
		userService.destroyUser(id);
		return "redirect:/admin/fool_creator";
	}
	@RequestMapping("/admin/makeAdmin/{id}")
	public String makeAdmin(@PathVariable("id") long id){
		User user = userService.findById(id);
		Role admin = userService.getRole("ROLE_ADMIN").get(0);
		user.getRoles().add(admin);
		userService.updateUser(user);
		return "redirect:/admin/fool_creator";
	}
}
