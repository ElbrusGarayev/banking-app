package app.security.service;

import app.entity.Admin;
import app.entity.Employee;
import app.repository.AdminRepo;
import app.repository.EmployeeRepo;
import app.security.entity.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepo employeeRepo;
    private final AdminRepo adminRepo;

    public static UserDetails mapper_to_my_ud(Employee employee) {
        return new CustomUserDetails(
                employee.getId(),
                employee.getUsername(),
                employee.getPassword(),
                employee.getRoles()
        );
    }

    public static UserDetails mapper_to_my_ud(Admin admin) {
        return new CustomUserDetails(
                admin.getId(),
                admin.getUsername(),
                admin.getPassword(),
                admin.getRoles()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> user1 = employeeRepo.findByUsername(username)
                .map(CustomUserDetailsService::mapper_to_my_ud);
        Optional<UserDetails> user2 = adminRepo.findByUsername(username)
                .map(CustomUserDetailsService::mapper_to_my_ud);
        if (user1.isPresent())
            return user1.get();
        if (user2.isPresent())
            return user2.get();
        throw new UsernameNotFoundException(username);
    }
}
