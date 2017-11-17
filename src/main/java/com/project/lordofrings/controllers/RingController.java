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
import com.project.lordofrings.models.Ring;
import com.project.lordofrings.models.Role;
import com.project.lordofrings.models.User;
import com.project.lordofrings.services.RingService;
import com.project.lordofrings.services.UserService;

@Controller
public class RingController{
	private UserService userService;
	private RingService ringService;

	public RingController(UserService userService, RingService ringService){
		this.userService = userService;
		this.ringService = ringService;
	}
	
	@RequestMapping("/dashboard")
	public String index(Principal principal, Model model){
		String username = principal.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		model.addAttribute("rings", ringService.findAll());
		return "index";
	}
	@PostMapping("/bind")
	public String bind(Principal principal, @RequestParam("ring") long ringId, RedirectAttributes flash){
		String username = principal.getName();
		User user = userService.findByUsername(username);
		// List<Ring> userRings = user.getRing();
		// userRings.add(ring);
		// user.setRing(userRings);
		// userService.updateUser(user);
		Ring ring = ringService.getRingById(ringId);
		ring.setUser(user);
		ringService.updateRing(ring);
		return "redirect:/dashboard";
	}
}
