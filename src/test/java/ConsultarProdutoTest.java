import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

public class ConsultarProdutoTest {

  private AndroidDriver driver;

  private URL getUrl() {
    try {
      return new URL(
          "https://oauth-dwmedeiros1994-7b42a:e3d8b059-31d3-4ad4-9e28-f8ce0b55086c@ondemand.us-west-1.saucelabs.com:443/wd/hub");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @BeforeEach
  public void setUp() {
    Capabilities options = new BaseOptions()
        .amend("platformName", "Android")
        .amend("appium:platformVersion", "9.0")
        .amend("appium:deviceName", "Galaxy S9 FHD GoogleAPI Emulator")
        .amend("appium:deviceOrientation", "portrait")
        .amend("appium:app", "storage:filename=mda-2.2.0-25.apk")
        .amend("appium:appPackage", "com.saucelabs.mydemoapp.android")
        .amend("appium:appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity")
        .amend("appium:ensureWebviewsHavePages", true)
        .amend("appium:nativeWebScreenshot", true)
        .amend("sauce:options", Map.ofEntries(Map.entry("name", "Appium Desktop Session -- May 27, 2025 6:59 PM")))
        .amend("appium:newCommandTimeout", 3600)
        .amend("appium:connectHardwareKeyboard", true);

    driver = new AndroidDriver(this.getUrl(), options);
  }

  @Test
  public void sampleTest() {
    WebElement lblSecao = driver.findElement(AppiumBy.accessibilityId("title"));
    assertEquals("Products", lblSecao.getText());

    WebElement imgProduto = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView[@content-desc=\"Product Image\"])[1]"));
    imgProduto.click();

    WebElement lblTituloProduto = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV"));
    assertEquals("Sauce Labs Backpack", lblTituloProduto.getText());

    WebElement lblPrecoProduto = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/priceTV"));
    assertEquals("$ 29.99", lblPrecoProduto.getText());

    final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    var start = new Point(476, 1753);
    var end = new Point(497, 780);
    var swipe = new Sequence(finger, 1);
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
        PointerInput.Origin.viewport(), start.getX(), start.getY()));
    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
        PointerInput.Origin.viewport(), end.getX(), end.getY()));
    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(Arrays.asList(swipe));

    WebElement btnAdicionarCarrinho = driver.findElement(AppiumBy.accessibilityId("Tap to add product to cart"));
    btnAdicionarCarrinho.click();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

}
