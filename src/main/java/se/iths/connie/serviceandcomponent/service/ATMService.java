package se.iths.connie.serviceandcomponent.service;


import org.springframework.stereotype.Service;
import se.iths.connie.serviceandcomponent.component.AccountComponent;
import se.iths.connie.serviceandcomponent.exception.InsufficientFundsException;
import se.iths.connie.serviceandcomponent.exception.InvalidAmountException;
import se.iths.connie.serviceandcomponent.exception.MaxWithdrawalExceededException;

@Service
public class ATMService {
    private final AccountComponent accountComponent;
    private static final int MAX_WITHDRAWAL = 1000;

    public ATMService(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Amount must be greater than 0"
            );
        }
        accountComponent.deposit(amount);
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Amount must be greater than 0"
            );
        }

        if (amount > MAX_WITHDRAWAL) {
            throw new MaxWithdrawalExceededException(
                    "Amount exceeds max withdrawal"
            );
        }
        int balance = accountComponent.getBalance();
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds"
            );
        }
        accountComponent.withdraw(amount);
    }

    public int getBalance() {
        return accountComponent.getBalance();
    }
}