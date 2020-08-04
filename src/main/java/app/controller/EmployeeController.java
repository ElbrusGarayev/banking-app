package app.controller;

import app.entity.Client;
import app.entity.Fee;
import app.repository.FeeRepo;
import app.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@RestController
public class EmployeeController {

    private final ClientService clientService;

    /**
     * http://localhost:8080/clients?amount=6000&duration=10
     */

    @GetMapping("clients")
    List<Client> handleUsers(@RequestParam int amount,@RequestParam  int duration){
        //duration is month
        List<Client> clients = clientService.getAll(amount, duration);
        clients.sort(Comparator.comparingInt(o -> o.getFee().getDelayDaysCount()));
        return clients;
    }
}
