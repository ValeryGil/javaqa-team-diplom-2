package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // 1_Геттеры.
    SavingAccount account = new SavingAccount(
            40_000,
            30_000,
            50_000,
            5);

    // 1.1_Получение начального баланса.
    @Test
    public void shouldGetInitialBalance() {
        int expected = 40_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 1.2_Получение минимального баланса.
    @Test
    public void shouldGetMinBalance() {
        int expected = 30_000;
        int actual = account.getMinBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 1.3_Получение максимального баланса.
    @Test
    public void shouldGetMaxBalance() {
        int expected = 50_000;
        int actual = account.getMaxBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 1.4_Получение процента.
    @Test
    public void shouldGetPercent() {
        int expected = 5;
        int actual = account.getRate();

        Assertions.assertEquals(expected, actual);
    }

    // 2_Метод создания нового объекта.

    // 2.1_Должно выкидываться исключение в случае, если минимальный баланс больше максимального:
    @Test
    public void shouldAnExceptionMustBeThrownWhenMinGreaterMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    20_000,
                    100_000,
                    50_000,
                    3);
        });
    }

    // 2.2_Должно выкидываться исключение в случае, если процент отрицательный:
    @Test
    public void shouldAnExceptionMustBeThrownWhenNegativePercent() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    20_000,
                    10_000,
                    50_000,
                    -3);
        });
    }

    // 2.3_Должно выкидываться исключение в случае, если начальный баланс меньше минимального:
    @Test
    public void shouldAnExceptionMustBeThrownWhenInitialLessMin() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    20_000,
                    30_000,
                    50_000,
                    3);
        });
    }

    // 2.4_Должно выкидываться исключение в случае, если начальный баланс больше максимального:
    @Test
    public void shouldAnExceptionMustBeThrownWhenInitialGreaterMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    70_000,
                    30_000,
                    50_000,
                    3);
        });
    }

    // 2.5_Должно выкидываться исключение в случае, если минимальный баланс отрицательный:
    @Test
    public void shouldAnExceptionMustBeThrownWhenMinNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    10_000,
                    -30_000,
                    70_000,
                    3);
        });
    }

    // 3_Метод оплаты.

    // 3.1_При покупке на 0 рублей должна возвращаться "ложь":
    @Test
    public void shouldReturnFalseForNullAmountPay() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        boolean expected = false;
        boolean actual = account.pay(0);

        Assertions.assertEquals(expected, actual);
    }

    // 3.2_При покупке на 0 рублей новый баланс равен:
    @Test
    public void shouldReturnNewBalanceForNullAmountPay() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        account.pay(0);

        int expected = 50_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 3.3_При покупке на отрицательное число рублей должна возвращаться "ложь":
    @Test
    public void shouldReturnFalseForNegativeAmountPay() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        boolean expected = false;
        boolean actual = account.pay(-10_000);

        Assertions.assertEquals(expected, actual);
    }

    // 3.4_При покупке на отрицательное число рублей новый баланс равен:
    @Test
    public void shouldReturnNewBalanceForNegativeAmountPay() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        account.pay(-10_000);

        int expected = 50_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 3.5_При покупке на положительное число рублей должна возвращаться "истина":
    @Test
    public void shouldReturnTrueForPositiveAmountPay() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        boolean expected = true;
        boolean actual = account.pay(10_000);

        Assertions.assertEquals(expected, actual);
    }

    // 3.6_При покупке на положительное число рублей новый баланс равен:
    @Test
    public void shouldReturnNewBalanceForPositiveAmountPay() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        account.pay(10_000);

        int expected = 40_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 3.7_При покупке на положительное число рублей и новом балансе равном минимальному должна возвращаться "истина":
    @Test
    public void shouldReturnTrueForPositiveAmountWhenNewEqualsMin() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        boolean expected = true;
        boolean actual = account.pay(40_000);

        Assertions.assertEquals(expected, actual);
    }

    // 3.8_При покупке на положительное число рублей и новом балансе равном минимальному:
    @Test
    public void shouldReturnNewBalanceForPositiveAmountWhenNewEqualsMin() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        account.pay(40_000);

        int expected = 10_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 3.9_При покупке на положительное число рублей и новом балансе меньше минимального должна возвращаться "ложь":
    @Test
    public void shouldReturnFalseForPositiveAmountWhenNewLessMin() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        boolean expected = false;
        boolean actual = account.pay(45_000);

        Assertions.assertEquals(expected, actual);
    }

    // 3.10_При покупке на положительное число рублей и новом балансе меньше минимального:
    @Test
    public void shouldReturnNewBalanceForPositiveAmountWhenNewLessMin() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        account.pay(45_000);

        int expected = 50_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 4_Метод пополнения.

    // 4.1_При пополнении на 0 рублей должна возвращаться "ложь":
    @Test
    public void shouldReturnFalseForNullAmountAdd() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        boolean expected = false;
        boolean actual = account.add(0);

        Assertions.assertEquals(expected, actual);
    }

    // 4.2_При пополнении на 0 рублей новый баланс равен:
    @Test
    public void shouldReturnNewBalanceForNullAmountAdd() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        account.add(0);

        int expected = 50_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 4.3_При пополнении на отрицательное число рублей должна возвращаться "ложь":
    @Test
    public void shouldReturnFalseForNegativeAmountAdd() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        boolean expected = false;
        boolean actual = account.add(-10_000);

        Assertions.assertEquals(expected, actual);
    }

    // 4.4_При пополнении на отрицательное число рублей новый баланс равен:
    @Test
    public void shouldReturnNewBalanceForNegativeAmountAdd() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        account.add(-10_000);

        int expected = 50_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 4.5_При пополнении на положительное число рублей должна возвращаться "истина":
    @Test
    public void shouldReturnTrueForPositiveAmountAdd() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        boolean expected = true;
        boolean actual = account.add(10_000);

        Assertions.assertEquals(expected, actual);
    }

    // 4.6_При пополнении на положительное число рублей новый баланс равен:
    @Test
    public void shouldReturnNewBalanceForPositiveAmountAdd() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        account.add(10_000);

        int expected = 60_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 4.7_При пополнении на положительное число рублей и новом балансе равном максимальному должна возвращаться "истина":
    @Test
    public void shouldReturnTrueForPositiveAmountWhenNewEqualsMax() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        boolean expected = true;
        boolean actual = account.add(50_000);

        Assertions.assertEquals(expected, actual);
    }

    // 4.8_При пополнении на положительное число рублей и новом балансе равном максимальному:
    @Test
    public void shouldReturnNewBalanceForPositiveAmountWhenNewEqualsMax() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        account.add(50_000);

        int expected = 100_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 4.9_При пополнении на положительное число рублей и новом балансе больше максимального должна возвращаться "ложь":
    @Test
    public void shouldReturnFalseForPositiveAmountWhenNewGreaterMax() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        boolean expected = false;
        boolean actual = account.add(55_000);

        Assertions.assertEquals(expected, actual);
    }

    // 4.10_При пополнении на положительное число рублей и новом балансе больше максимального:
    @Test
    public void shouldReturnNewBalanceForPositiveAmountWhenNewGreaterMax() {
        SavingAccount account = new SavingAccount(
                50_000,
                10_000,
                100_000,
                5);

        account.add(55_000);

        int expected = 50_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 5_Метод расчета процентов на остаток.

    // 5.1_Процент на остаток от 40_000 при 5%:
    @Test
    public void shouldCalculateYearChange() {
        int expected = 2_000;
        int actual = account.yearChange();

        Assertions.assertEquals(expected, actual);
    }

}