package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class E2ETest {

	WebDriver driver;

	@Given("^I have login page opened$")
	public void i_have_login_page_opened() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/miro-assignment/driver/chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("https://miro.com/login/"); //open login page in Chrome  browser
		driver.manage().window().maximize();
		System.out.print("checking start page...");

		//check if it loads
		try {
			driver.findElement(By.id("email"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch (NullPointerException e) {
			System.out.print("NullPointerException caught");
		}
	}

	@When("I enter valid login {string} and password {string}")
	public void i_enter_valid_login_and_password(String login,String password) throws Throwable {
		System.out.print("checking login...");

		driver.findElement(By.id("email")).sendKeys(login);   //enter login info
		driver.findElement(By.id("password")).sendKeys(password);         //enter password
		driver.findElement(By.cssSelector("body > div.overlay-signup > div > div.overlay-signup__form > div.overlay-signup__form-container > div > div > form > button")).click();
	}

	@Then("^I see board select menu$")
	public void i_see_board_select_menu() throws Exception {
		driver.findElement(By.className("user-profile"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Then("^I create new board$")
	public void i_create_new_board() throws Exception {
		System.out.print("creating new board");
		if(driver.findElement(By.className("serviceCard__tile-3JdsV")).isEnabled()) {
			driver.findElement(By.className("serviceCard__tile-3JdsV")).click();
		}
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("body > board-will-be-shared > div > div > div > div > div.rtb-modal-content__actions.rtb-modal-actions > button.rtb-btn.rtb-btn--primary.rtb-btn--medium")).click();
		driver.findElement(By.cssSelector("#react-modals-container > div > div > button > div")).click();
	}

	@Then("^I create new sticker$")
	public void i_create_new_sticker() throws Exception {
		System.out.print("creating new sticker");
		driver.findElement(By.cssSelector("#canvasContainer > div:nth-child(2) > div:nth-child(3) > div > div.board-toolbar__customized-box > div.toolbar__item.toolbar__item--customized.AT__toolbar--STICKERS.toolbar__item--stickers")).click();
		driver.findElement(By.cssSelector("#board-canvas-container > div.canvas-fixed-container")).click();
		driver.findElement(By.cssSelector("#widgetsOverlay > div.editor-container.sticker-editor-container > div > div > div")).sendKeys("Sample Sticker");
	}

	@Then("I share board with {string}")
	public void i_share_board(String email2) throws Exception {
		System.out.print("Sharing Board");
		driver.findElement(By.cssSelector("#canvasContainer > div:nth-child(2) > div.board-top > div.board-top__right > div.board-top-right-panel.board-panel--transparent.backdrop-blur.board-panel--hidden-top > div.board-top-share-button")).click();
		driver.findElement(By.cssSelector("body > div.rtb-modal--center.rtb-modal--small.rename-board-modal.effect-fadein.effect-scale.md-centered.rtb-modal.md-show > div > ng-transclude > div.rtb-modal-container__content.rtb-modal-content > div.rtb-modal-content__actions.rtb-modal-actions > button.rtb-btn.rtb-btn--primary.rtb-btn--medium")).click();
		driver.findElement(By.className("email-input__input")).sendKeys(email2);
		driver.findElement(By.cssSelector("body > div.rtb-modal.rtb-modal--center.share-modal.effect-fadein.effect-scale.md-centered.md-show > div > div > share-content > div > div > div.rtb-scroll-wrapper.custom-message-input.rtb-scroll-wrapper--y-scroll.share-content__custom-message > div.rtb-scroll.no-prevent-default > div")).click();
		driver.findElement(By.cssSelector("body > div.rtb-modal.rtb-modal--center.share-modal.effect-fadein.effect-scale.md-centered.md-show > div > div > share-content > div > div > div.share-content__buttons-panel > button.rtb-btn.rtb-btn--primary.rtb-btn--medium")).click();
	}

	@Then("^I open login page$")
	public void i_open_login_page() throws Exception {
		driver.get("https://miro.com/login/"); //open login page in Chrome  browser
		driver.manage().window().maximize();
		System.out.print("checking start page...");

		//check if it loads
		try {
			driver.findElement(By.id("email"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch (NullPointerException e) {
			System.out.print("NullPointerException caught");
		}
	}

	@Then("I Login with user2 {string} and password {string}")
	public void i_login_with_user2(String email2,String password2) throws Exception {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email2);   //enter login info
		driver.findElement(By.id("password")).sendKeys(password2);         //enter password
		driver.findElement(By.cssSelector("body > div.overlay-signup > div > div.overlay-signup__form > div.overlay-signup__form-container > div > div > form > button")).click();
	}

	@Then("^I verify sticker on shared board$")
	public void i_verfiy_sticker_on_shared_board() throws Exception {
		driver.findElement(By.cssSelector("#router-container-wrapper > div > div.dashboard > div.dashboard__inner > div.dashboard__columns > div.dashboard__content > div.boards-scroller.boards-scroller--with-pin > div > ng-transclude > div.boards-scroller__list-wrapper > div.boards-grid > div:nth-child(2) > div > div > div > div.board-brick__preview > div")).click();
		driver.findElement(By.cssSelector("#board-canvas-container > div.canvas-fixed-container")).isDisplayed();
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File("screenshot" + java.time.LocalTime.now() + ".png");
        FileUtils.copyFile(screenshot, destination);
	}
	
	@Then("i close driver")
	public void i_close_driver() throws Exception {
	    driver.close();
	}
	
	}
