package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test // Пополнение счета при нулевом балансе.
    public void shouldAddToZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test // Пополнение счета при положительном балансе.
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                100,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_100, account.getBalance());
    }

    @Test // Пополнение счета при отрицательном балансе.
    public void shouldAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(-3_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test // Пополнение счета на нулевую величину.
    public void shouldAddZeroAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test // Пополнение счета на отрицательную величину.
    public void shouldAddNegativeAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(-500);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test // Оплата покупки когда сумма баланса больше, чем сумма покупки.
    public void shouldPayWhenBalanceMoreThanAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(700);

        Assertions.assertEquals(300, account.getBalance());
    }

    @Test // Оплата покупки когда сумма покупки больше, чем сумма баланса.
    public void shouldPayWhenAmountMoreThanBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(1_700);

        Assertions.assertEquals(-700, account.getBalance());
    }

    @Test // Оплата покупки когда суммы покупки и баланса равны.
    public void shouldPayWhenAmountAndBalanceSame() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(1_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test // Оплата покупки когда суммы покупки отрицательная.
    public void shouldPayNegativeAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(-800);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test // Оплата покупки при превышении кредитного лимита.
    public void shouldPayWhenAmountMoreThanCreditLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                15
        );

        account.pay(2_500);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test // Расчет процентов при положительном балансе.
    public void shouldCalculationYearChangeWhenPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                3_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test // Расчет процентов при отсутствие средств на балансе.
    public void shouldCalculationYearChangeWhenZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                3_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test // Расчет процентов при отрицательном балансе.
    public void shouldCalculationYearChangeWhenNegativeBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                3_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(-300, account.yearChange());
    }

    @Test // Проверка выдачи исключения при отрицательной ставке кредита.
    public void shouldCheckExceptionNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    3_000,
                    -15
            );
        });
    }

    @Test // Проверка выдачи исключения при нулевой ставке кредита.
    public void shouldCheckExceptionZeroRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    3_000,
                    0
            );
        });
    }

    @Test // Проверка выдачи исключения при отрицательном кредитном лимите.
    public void shouldCheckExceptionNegativeCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    -3_000,
                    15
            );
        });
    }

    @Test // Проверка выдачи исключения при отрицательном балансе.
    public void shouldCheckExceptionNegativeBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -1_000,
                    3_000,
                    15
            );
        });
    }

    @Test // Проверка выдачи исключения при корректных параметрах.
    public void shouldCheckExceptionPositiveParameters() {
        CreditAccount account = new CreditAccount(
                1_000,
                3_000,
                15
        );

        Assertions.assertEquals(1_000, account.getBalance());
        Assertions.assertEquals(3_000, account.getCreditLimit());
        Assertions.assertEquals(15, account.getRate());
    }
}
