package app.config;

import app.entity.Admin;
import app.entity.Client;
import app.entity.Employee;
import app.entity.Fee;
import app.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class Initial {

    private final ClientRepo clientRepo;
    private final EmployeeRepo employeeRepo;
    private final AdminRepo adminRepo;
    private final FeeRepo feeRepo;
    private final PasswordEncoder enc;

    @Bean
    public CommandLineRunner init(){
        return args -> {
            employeeRepo.save(new Employee("Employee", "username", enc.encode("123"), "EMPLOYEE"));
            adminRepo.save(new Admin("Admin", "admin", enc.encode("admin"), "ADMIN"));
            clientRepo.save(new Client("Elbrus Garayev", 20, "DFG123R", "CLIENT"));
            clientRepo.save(new Client("Valeh Jarchiyev", 21, "YTR456Y", "CLIENT"));
            clientRepo.save(new Client("Aytac Aliyeva", 20, "FGH456K", "CLIENT"));
            clientRepo.save(new Client("Aghgul Imanova", 20, "QWE345R", "CLIENT"));
            feeRepo.save(new Fee(600, 650, 0, clientRepo.findById(1L).get()));
            feeRepo.save(new Fee(600, 600, 1, clientRepo.findById(2L).get()));
            feeRepo.save(new Fee(500, 500, 2, clientRepo.findById(3L).get()));
            feeRepo.save(new Fee(450, 470, 0, clientRepo.findById(4L).get()));
        };
    }
}
