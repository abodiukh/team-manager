package com.levi9.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.levi9.domain.User;
import com.levi9.domain.UserRole;
import com.levi9.domain.Verification;
import com.levi9.dto.UserDTO;
import com.levi9.exceptions.EmailExistsException;
import com.levi9.listeners.events.OnRegistrationCompleteEvent;
import com.levi9.services.UserService;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/")
public class BaseController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        boolean authorized = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            authorized = true;
        }
        model.addAttribute("authorized", authorized);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {
        if (logout != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            return "redirect:/";
        }

        return "login";

    }

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String registration(@Valid UserDTO userDTO, Errors errors, BindingResult result, WebRequest request, Model model) {
        List<String> invalidMessages = new ArrayList<>();
        if (!result.hasErrors()) {
            try {
                User registered = userService.addUser(userDTO);
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
            } catch (EmailExistsException e) {
                invalidMessages.add(e.getMessage());
            } catch (Exception e) {
                invalidMessages.add("Invalid email");
                userService.deleteUser(userDTO.getId());
            }
        } else {
            for (ObjectError error : errors.getAllErrors()) {
                invalidMessages.add(error.getDefaultMessage());
            }
        }
        if (!invalidMessages.isEmpty()) {
            model.addAttribute("errors", errors);
        } else {
            return "redirect:/";
        }
        return "signup";
    }

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token,
                                                 WebRequest request, UriComponentsBuilder uriBuilder) {
        Verification verification = userService.getVerificationToken(token);
        if (verification == null) {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }

        User user = verification.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verification.getExpirationDate().getTime() - cal.getTime().getTime()) <= 0) {
            return new ResponseEntity<>("Token was expired", HttpStatus.BAD_REQUEST);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEnabled(true);
        userDTO.setRoles(user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toSet()));
        userService.updateUser(userDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/").build().toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.SEE_OTHER);
    }

}

