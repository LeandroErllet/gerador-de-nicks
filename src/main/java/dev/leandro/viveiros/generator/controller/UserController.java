package dev.leandro.viveiros.generator.controller;

import dev.leandro.viveiros.generator.model.User;
import dev.leandro.viveiros.generator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller Rest para buscar informações dos usuários
 */
@RestController()
@CrossOrigin({"*"})
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/user/todos")
    public List<User> findAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/buscar-por-cpf/{cpf}")
    public List<User> findByCPF(@PathVariable("cpf") String cpf) {
        return userService.getAllUsers().stream()
                .filter(user -> user.getCpf().equalsIgnoreCase(cpf))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/buscar-por-nome/{nome}")
    public List<User> findByNome(@PathVariable("nome") String name) {
        return userService.getAllUsers().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }


}
