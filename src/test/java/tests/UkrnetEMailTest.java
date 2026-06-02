package tests;

import io.qameta.allure.Story;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UkrnetEMailTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(UkrnetEMailTest.class);

    @Test(priority = 1)
    @Story("Login functionality")
    @Description("User should be able to login with valid credentials")
    public void loginTest() {
        log.info("=== Test started: loginTest ===");
        Assert.assertEquals(inboxPage.getCurrentUrl(), inboxPage.getInboxPageUrl() + "123"
                , "Login was`t successful - urls were`t matching");
        log.info("=== loginTest passed successfully ===");
    }

    @Test(priority = 2)
    @Story("Draft functionality")
    @Description("User should be able to save email as draft")
    public void saveDraftTest() {
        log.info("=== Test started: saveDraftTest ===");
        inboxPage.createMessage();
        inboxPage.fillEmailForm();
        inboxPage.switchToDrafts();
        Assert.assertTrue(draftPage.mailIsDisplayed(), "Mail isn`t displayed on the draft page");
        draftPage.openTheMail();
        Assert.assertEquals(draftPage.getAddresseeText(), TestData.RECIPIENT
                , "Addressee does`t match the expected recipient");

        Assert.assertEquals(draftPage.getEmailTopicText(), TestData.SUBJECT
                , "Topic does`t match the expected subject");

        Assert.assertEquals(draftPage.getMailBodyInputText(), TestData.BODY
                , "MailBodyInput does`t match the expected body");
        draftPage.cancelTheMail();
        log.info("=== saveDraftTest passed successfully ===");
    }

    @Test(priority = 3)
    @Story("Send functionality")
    @Description("User should be able to send email from drafts")
    public void sendMailTest() {
        log.info("=== Test started: sendMailTest ===");
        inboxPage.createMessage();
        inboxPage.fillEmailForm();
        inboxPage.switchToDrafts();
        draftPage.openTheMail();

        draftPage.sendTheMail();
        inboxPage.switchToDrafts();
        Assert.assertTrue(draftPage.isDraftPageClear(), "Draft page still contains the mail");
        inboxPage.switchToSents();
        Assert.assertTrue(draftPage.mailIsDisplayed(), "Mail isn`t displayed on the sent page");
        log.info("=== sendMailTest passed successfully ===");
    }
}