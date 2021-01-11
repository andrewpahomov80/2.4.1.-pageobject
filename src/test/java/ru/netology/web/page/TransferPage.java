package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $(byText("Пополнение карты"));
    private SelenideElement buttonFirstCard = $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0] [class=button__text]");
    private SelenideElement amount = $("[data-test-id=amount]");
    private SelenideElement from = $("[data-test-id=from]");
    private SelenideElement actionTransfer = $("[data-test-id=action-transfer]");

    public TransferPage() { heading.shouldBe(visible); }

    public DashboardPage firstCard(DataHelper.Cards cards) {
        buttonFirstCard.click();
        amount.setValue("250");
        from.setValue(cards.getCardNum());
        actionTransfer.click();
        return new DashboardPage();
    }
}
