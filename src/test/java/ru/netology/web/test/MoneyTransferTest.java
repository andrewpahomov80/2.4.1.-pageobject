package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
  @Test
  void shouldTransferMoneyFromSecondToFirstCard() {
    open("http://localhost:9999");
    val loginPage = new LoginPage();
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    val dashboardPage = verificationPage.validVerify(verificationCode);
    val firstCardBalance = dashboardPage.getFirstCardBalance();
    val secondCardBalance = dashboardPage.getSecondCardBalance();
    val transferPageFirstCard = dashboardPage.firstCard();
    val value = 3800;
    val secondCardNumber = DataHelper.getSecondCardNumber();
    transferPageFirstCard.transfer(value, secondCardNumber);
    val firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance();
    val secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
    val resultFirstCardBalance = firstCardBalanceAfterTransfer - firstCardBalance;
    val resultSecondCardBalance = secondCardBalance - secondCardBalanceAfterTransfer;
    Assertions.assertEquals(resultFirstCardBalance, value);
    Assertions.assertEquals(resultSecondCardBalance, value);
  }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
      open("http://localhost:9999");
      val loginPage = new LoginPage();
      val authInfo = DataHelper.getAuthInfo();
      val verificationPage = loginPage.validLogin(authInfo);
      val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
      val dashboardPage = verificationPage.validVerify(verificationCode);
      val firstCardBalance = dashboardPage.getFirstCardBalance();
      val secondCardBalance = dashboardPage.getSecondCardBalance();
      val transferPageSecondCard = dashboardPage.secondCard();
      val value = 4300;
      val firstCardNumber = DataHelper.getFirstCardNumber();
      transferPageSecondCard.transfer(value, firstCardNumber);
      val firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance();
      val secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
      val resultFirstCardBalance = firstCardBalance - firstCardBalanceAfterTransfer;
      val resultSecondCardBalance = secondCardBalanceAfterTransfer - secondCardBalance;
      Assertions.assertEquals(resultFirstCardBalance, value);
      Assertions.assertEquals(resultSecondCardBalance, value);
  }

  @Test
  void shouldTransferMoneyFromFirstToSecondCardOverLimit() {
    open("http://localhost:9999");
    val loginPage = new LoginPage();
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    val dashboardPage = verificationPage.validVerify(verificationCode);
    val firstCardBalance = dashboardPage.getFirstCardBalance();
    val secondCardBalance = dashboardPage.getSecondCardBalance();
    val transferPageSecondCard = dashboardPage.secondCard();
    val value = 50000;
    val firstCardNumber = DataHelper.getFirstCardNumber();
    transferPageSecondCard.overLimit(value, firstCardNumber);
  }
}

