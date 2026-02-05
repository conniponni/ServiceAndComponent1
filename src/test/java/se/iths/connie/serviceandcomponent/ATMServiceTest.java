package se.iths.connie.serviceandcomponent;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.connie.serviceandcomponent.component.AccountComponent;
import se.iths.connie.serviceandcomponent.exception.InsufficientFundsException;
import se.iths.connie.serviceandcomponent.exception.InvalidAmountException;
import se.iths.connie.serviceandcomponent.exception.MaxWithdrawalExceededException;
import se.iths.connie.serviceandcomponent.service.ATMService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ATMServiceTest {

    @Mock
    private AccountComponent accountComponent;

    @InjectMocks
    private ATMService atmService;

    @Test
    void depositValid() {
        atmService.deposit(100);
        verify(accountComponent)
                .deposit(100);
    }

    @Test
    void depositInvalid() {
        assertThrows(
                InvalidAmountException.class,
                () -> atmService.deposit(0)
        );
    }

    @Test
    void withdrawInvalidAmount() {
        assertThrows(
                InvalidAmountException.class,
                () -> atmService.withdraw(0)
        );
    }

    @Test
    void withdrawOverMax() {
        assertThrows(
                MaxWithdrawalExceededException.class,
                () -> atmService.withdraw(2000)
        );
    }

    @Test
    void withdrawInsufficientFunds() {
        when(accountComponent.getBalance())
                .thenReturn(100);
        assertThrows(
                InsufficientFundsException.class,
                () -> atmService.withdraw(200)
        );
    }

    @Test
    void withdrawValid() {
        when(accountComponent.getBalance())
                .thenReturn(500);
        atmService.withdraw(200);
        verify(accountComponent)
                .withdraw(200);
    }
}