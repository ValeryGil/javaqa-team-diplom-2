package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {

    @Test // Перевод отрицательной суммы с кредитного счета на сберегательный.
    public void shouldTransferNegativeAmountFromCreditAccountToSavingAccount() {

        CreditAccount creditAccount = new CreditAccount(
                4_000,
                5_000,
                15
        );
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                7_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(creditAccount, savingAccount, -1_000);

        Assertions.assertEquals(4_000, creditAccount.getBalance());
        Assertions.assertEquals(2_000, savingAccount.getBalance());
    }

    @Test // Перевод положительной суммы с кредитного счета на сберегательный.
    public void shouldTransferPositiveAmountFromCreditAccountToSavingAccount() {

        CreditAccount creditAccount = new CreditAccount(
                4_000,
                5_000,
                15
        );
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                7_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(creditAccount, savingAccount, 1_000);

        Assertions.assertEquals(3_000, creditAccount.getBalance());
        Assertions.assertEquals(3_000, savingAccount.getBalance());
    }

    @Test // Перевод нулевой суммы с кредитного счета на сберегательный.
    public void shouldTransferZeroAmountFromCreditAccountToSavingAccount() {

        CreditAccount creditAccount = new CreditAccount(
                4_000,
                5_000,
                15
        );
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                7_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(creditAccount, savingAccount, 0);

        Assertions.assertEquals(4_000, creditAccount.getBalance());
        Assertions.assertEquals(2_000, savingAccount.getBalance());
    }

    @Test // Перевод суммы больше, чем величина кредитного лимита, с кредитного счета на сберегательный.
    public void shouldTransferAmountOverCreditLimitFromCreditAccountToSavingAccount() {

        CreditAccount creditAccount = new CreditAccount(
                2_000,
                3_000,
                15
        );
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                9_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(creditAccount, savingAccount, 4_000);

        Assertions.assertEquals(3_000, creditAccount.getCreditLimit());
        Assertions.assertEquals(6_000, savingAccount.getBalance());
    }

    @Test // Перевод суммы равной величине кредитного лимита с кредитного счета на сберегательный.
    public void shouldTransferAmountCreditLimitFromCreditAccountToSavingAccount() {

        CreditAccount creditAccount = new CreditAccount(
                2_000,
                3_000,
                15
        );
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                9_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(creditAccount, savingAccount, 3_000);

        Assertions.assertEquals(3_000, creditAccount.getCreditLimit());
        Assertions.assertEquals(5_000, savingAccount.getBalance());
    }

    @Test // Превышение суммы максимального баланса после осуществления операции перевода
    // с кредитного счета на сберегательный.
    public void shouldGetSumMoreThanMaxBalanceAfterTransfer() {

        CreditAccount creditAccount = new CreditAccount(
                2_000,
                10_000,
                15
        );
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                9_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(creditAccount, savingAccount, 8_000);

        Assertions.assertEquals(10_000, creditAccount.getCreditLimit());
        Assertions.assertEquals(2_000, savingAccount.getBalance());
    }

    @Test // Перевод отрицательной суммы со сберегательного счета на кредитный.
    public void shouldTransferNegativeAmountFromSavingAccountToCreditAccount() {

        SavingAccount savingAccount = new SavingAccount(
                5_000,
                1_000,
                7_000,
                15
        );
        CreditAccount creditAccount = new CreditAccount(
                4_000,
                5_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(savingAccount, creditAccount, -1_000);

        Assertions.assertEquals(5_000, savingAccount.getBalance());
        Assertions.assertEquals(4_000, creditAccount.getBalance());
    }

    @Test // Перевод положительной суммы со сберегательного счета на кредитный.
    public void shouldTransferPositiveAmountFromSavingAccountToCreditAccount() {

        SavingAccount savingAccount = new SavingAccount(
                5_000,
                1_000,
                7_000,
                15
        );
        CreditAccount creditAccount = new CreditAccount(
                4_000,
                5_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(savingAccount, creditAccount, 1_000);

        Assertions.assertEquals(4_000, savingAccount.getBalance());
        Assertions.assertEquals(5_000, creditAccount.getBalance());
    }

    @Test // Перевод нулевой суммы со сберегательного счета на кредитный.
    public void shouldTransferZeroAmountFromSavingAccountToCreditAccount() {

        SavingAccount savingAccount = new SavingAccount(
                5_000,
                1_000,
                7_000,
                15
        );
        CreditAccount creditAccount = new CreditAccount(
                4_000,
                5_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(savingAccount, creditAccount, 0);

        Assertions.assertEquals(5_000, savingAccount.getBalance());
        Assertions.assertEquals(4_000, creditAccount.getBalance());
    }

    @Test // Перевод суммы на кредитный счет с остатком на сберегательном счете суммы равной минимальному баласу.
    public void shouldTransferAmountMinBalanceFromSavingAccountToCreditAccount() {

        SavingAccount savingAccount = new SavingAccount(
                5_000,
                1_000,
                7_000,
                15
        );
        CreditAccount creditAccount = new CreditAccount(
                4_000,
                15_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(savingAccount, creditAccount, 4_000);

        Assertions.assertEquals(1_000, savingAccount.getBalance());
        Assertions.assertEquals(8_000, creditAccount.getBalance());
    }

    @Test // Перевод суммы на кредитный счет с остатком на сберегательном счете меньше суммы минимального баласа.
    public void shouldTransferAmountLessMinBalanceFromSavingAccountToCreditAccount() {

        SavingAccount savingAccount = new SavingAccount(
                5_000,
                1_000,
                7_000,
                15
        );
        CreditAccount creditAccount = new CreditAccount(
                4_000,
                5_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(savingAccount, creditAccount, 4_500);

        Assertions.assertEquals(5_000, savingAccount.getBalance());
        Assertions.assertEquals(4_000, creditAccount.getBalance());
    }

    @Test // Перевод суммы равной максимальному баласу со сберегательного счета на кредитный.
    public void shouldTransferAmountMaxBalanceFromSavingAccountToCreditAccount() {

        SavingAccount savingAccount = new SavingAccount(
                7_000,
                0,
                7_000,
                15
        );
        CreditAccount creditAccount = new CreditAccount(
                4_000,
                5_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(savingAccount, creditAccount, 7_000);

        Assertions.assertEquals(0, savingAccount.getBalance());
        Assertions.assertEquals(11_000, creditAccount.getBalance());
    }

    @Test // Перевод суммы больше максимального баласа со сберегательного счета на кредитный.
    public void shouldTransferAmountOverMaxBalanceFromSavingAccountToCreditAccount() {

        SavingAccount savingAccount = new SavingAccount(
                5_000,
                1_000,
                7_000,
                15
        );
        CreditAccount creditAccount = new CreditAccount(
                4_000,
                5_000,
                15
        );

        Bank bank = new Bank();
        bank.transfer(savingAccount, creditAccount, 8_000);

        Assertions.assertEquals(5_000, savingAccount.getBalance());
        Assertions.assertEquals(4_000, creditAccount.getBalance());
    }
}
