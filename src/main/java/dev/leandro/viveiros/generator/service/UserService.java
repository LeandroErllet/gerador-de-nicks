package dev.leandro.viveiros.generator.service;

import dev.leandro.viveiros.generator.model.User;
import dev.leandro.viveiros.generator.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço com criação e lista de usuários
 */

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // para captarar Exceptions sem precisar de try catch
    @SneakyThrows
    // criar usuario
    public void createUser(String name, String cpf) {
        // verificar se existe um usuario criado com este CPF
        val userOptional = userRepository.findByCpf(cpf);
        if(userOptional.isPresent()) {
            throw new Exception("já existe um usuario com este CPF");
        }
        // criar um novo usuario e salvar no banco
        val user = new User();
        user.setCpf(cpf);
        user.setName(name);
        userRepository.save(user);
    }

    /**
     * pegar lista de usuários
     *
     * @return Uma lista com todos os usuários
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
