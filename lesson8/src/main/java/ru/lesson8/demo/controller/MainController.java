package ru.lesson8.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.lesson8.demo.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class MainController {

    private List<User> users = new ArrayList<>() {{
        add(new User(1L, "user1"));
        add(new User(2L, "user2"));
        add(new User(3L, "user3"));
    }};

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping("/")
    public ModelAndView getMainPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("users", users);
        return model;
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}