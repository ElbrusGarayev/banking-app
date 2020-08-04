package app.service;

import app.entity.Client;
import app.entity.Fee;
import app.repository.ClientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepo clientRepo;

    public List<Client> getAll(int amount, int duration){
        return clientRepo.findAll().stream()
                .filter(client -> client.getFee().getMaxMonthlyFeeAbility() >= (float) amount / duration)
                .collect(Collectors.toList());
    }
}
