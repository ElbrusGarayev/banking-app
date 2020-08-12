package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int maxMonthlyFeeAbility;

    private int lastFee;

    private int delayDaysCount;

    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    public Fee(int maxMonthlyFeeAbility, int lastFee, int delayDaysCount, Client client) {
        this.maxMonthlyFeeAbility = maxMonthlyFeeAbility;
        this.lastFee = lastFee;
        this.delayDaysCount = delayDaysCount;
        this.client = client;
    }
}
