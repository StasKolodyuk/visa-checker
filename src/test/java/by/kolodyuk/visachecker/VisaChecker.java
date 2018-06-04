package by.kolodyuk.visachecker;

import com.deathbycaptcha.Captcha;
import com.deathbycaptcha.SocketClient;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;


public class VisaChecker {

    @Test
    public void userCanLoginByUsername() throws Exception {

        // Configuration.baseUrl = "https://google.com";

        // open("https://online.vfsglobal.com/Global-Appointment/");
        open("https://online.vfsglobal.com/Global-Appointment/Account/RegisteredLogin?q=shSA0YnE4pLF9Xzwon/x/ASnHZRMROGDyz5YljrTPrmD7weWKDzHm/9+x4kyou3T7EbygDK+7ECJT8O+dWpxGw==");
        // $("#EmailId").setValue("staskolodyuk@gmail.com");
        $("#EmailId").setValue("lalala");

        $("#Password").setValue("lalalal");

        Screenshot screenshot = new AShot()
                .takeScreenshot(getAndCheckWebDriver(), $("#CaptchaImage"));

        SocketClient client = new SocketClient("tedwantsmore", "money1003");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(screenshot.getImage(), "png", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        Captcha captcha = client.decode(is);

        System.out.println(captcha.text);

        $("#CaptchaInputText").setValue(captcha.text.toUpperCase());

        $(".submitbtn").click();

        $(By.linkText("Schedule Appointment")).click();
        System.out.println($("#LocationError").getText());

        $("#LocationId").selectOptionContainingText("Minsk");

        System.out.println($("#LocationError").getText());
    }


}
