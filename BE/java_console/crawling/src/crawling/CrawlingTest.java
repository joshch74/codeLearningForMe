package crawling;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CrawlingTest {
	public static String id = "webdriver.chrome.driver";
	public static String path = "C:/chromedriver.exe";
	public static void main(String[] args) {
		System.setProperty(id, path);
		ChromeOptions options = new ChromeOptions();
		String url = "http://www.naver.com";
		options.setCapability("ignoreProtectedModeSetting", true);
		WebDriver driver = new ChromeDriver(options);
//		--------------------------------------------------- 셋팅 끝
		driver.get(url);
		try {Thread.sleep(2000);} catch (InterruptedException e) {}
		
		//네이버 페이지의 검색창 객체				By.id("아이디") : 아이디로 요소 검색
		WebElement searchInput = driver.findElement(By.id("query"));
		//검색창 클릭
		searchInput.click();
		//검색창에 "마스크" 문자열 보내기(쓰기)
		searchInput.sendKeys("마스크");
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		//검색버튼 객체로 받아오기(검색버튼의 id : search_btn)
		WebElement searchBtn = driver.findElement(By.id("search_btn"));
		//검색버튼 클릭
		searchBtn.click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		//현재 페이지에서 shop_guide_group이라는 클래스를 갖고있는 요소 찾아오기
		WebElement productList = driver.findElement(By.className("shop_guide_group"));
		
		//productList 객체에서 <ul> 요소를 찾고, 그 안에서 "box" 라는 클래스를 갖고 있는 요소들을 List로 받아온다.
		List<WebElement> listElements = productList.findElement(By.tagName("ul")).findElements(By.className("box"));
		
		for (WebElement element : listElements) {
			//리스트에 담겨있는 요소들을 꺼내요며 "title" 클래스를 갖고있는 요소의 텍스트 받아오기
			String prodname = element.findElement(By.className("title")).getText();
			//리스트에 담겨있는 요소들을 꺼내오며 "price"라는 클래스를 갖고있는 요소를 찾고 그 안의 <strong> 요소를 찾는다.
			//그 이후에 <strong> 태그 안에 있는 내용을 price 변수에 받아온다.
			String price = element.findElement(By.className("price")).findElement(By.tagName("strong")).getText();
			System.out.println(prodname +"\t"+ price+"원");
		}
	}
}
