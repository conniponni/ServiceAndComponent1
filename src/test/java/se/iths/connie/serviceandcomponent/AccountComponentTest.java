package se.iths.connie.serviceandcomponent;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.connie.serviceandcomponent.component.AccountComponent;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountComponentTest {
    private AccountComponent accountComponent;

    @BeforeEach
    void setup() {
        accountComponent = new AccountComponent();
    }

    @Test
    void initialBalanceShouldBeZero() {
        assertEquals(0,
                accountComponent.getBalance());
    }

    @Test
    void depositShouldIncreaseBalance() {
        accountComponent.deposit(100);
        assertEquals(100,
                accountComponent.getBalance());
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        accountComponent.deposit(200);
        accountComponent.withdraw(50);
        assertEquals(150,
                accountComponent.getBalance());
    }

    @Test
    void depositAndWithdrawCombined() {
        accountComponent.deposit(500);
        accountComponent.withdraw(200);
        accountComponent.deposit(100);
        assertEquals(400,
                accountComponent.getBalance());
    }
}
