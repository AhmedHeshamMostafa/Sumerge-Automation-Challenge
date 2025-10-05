package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generic {
    static private final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    static public String getElementText(WebDriver driver, By elementLocator){
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(7));
        WebElement element = waitTime.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        return element.getText();
    }

    static public void clickElement(WebDriver driver, By elementLocator){
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(12));
        WebElement element = waitTime.until(ExpectedConditions.elementToBeClickable(elementLocator));
        element.click();
    }

    static public void typeIntoTxtBx(WebDriver driver, By elementLocator, String txtToType){
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(7));
        WebElement element = waitTime.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        String typedTxt = element.getAttribute("value");
        assert typedTxt != null;
        if(typedTxt.isEmpty()){
            element.clear();
            element.sendKeys(txtToType);
        }
    }

    static public List<String> extractDates(String datesStr){
        // Match any month abbreviation followed by a space and a number
        Pattern pattern = Pattern.compile("(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d+");
        Matcher matcher = pattern.matcher(datesStr);

        List<String> datesList = new ArrayList<>();

        while (matcher.find()) {
            datesList.add(matcher.group());
        }

        return datesList;
    }


    static public boolean isDateCorrect(String dateAct, String dateExp){
        boolean dateCorrect = false;
        byte monthNumAct = 0;

        String[] dateActParts = dateAct.split(" ");
        String[] dateExpParts = dateExp.split("-");

        for(byte i=1; i<=12; i++){
            if(dateActParts[0].equalsIgnoreCase(months[i-1])){
                monthNumAct = i;
                break;
            }
        }
        if(monthNumAct == Integer.parseInt(dateExpParts[1])){
            if(Integer.parseInt(dateActParts[1]) == Integer.parseInt(dateExpParts[2])){
                dateCorrect = true;
            }
        }
        return dateCorrect;
    }


//    static public void debugDropdown(WebDriver driver) {
//        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        String[] selectors = new String[]{
//                ".hprt-nos-select.js-hprt-nos-select",
//                ".hprt-nos-select",
//                ".js-hprt-nos-select",
//                "select.hprt-nos-select",               // if it's a real <select>
//                "select.js-hprt-nos-select",
//                "[class*='hprt-nos-select']"
//        };
//
//        System.out.println("=== Running dropdown debug ===");
//
//        for (String sel : selectors) {
//            List<WebElement> elems = driver.findElements(By.cssSelector(sel));
//            System.out.println("Selector '" + sel + "' -> count: " + elems.size());
//            for (int i = 0; i < elems.size(); i++) {
//                WebElement e = elems.get(i);
//                try {
//                    System.out.println(" Element[" + i + "] displayed=" + e.isDisplayed() + " enabled=" + e.isEnabled()
//                            + " tag=" + e.getTagName());
//                    String outer = (String) js.executeScript("return arguments[0].outerHTML;", e);
//                    System.out.println("  outerHTML: " + (outer.length() > 400 ? outer.substring(0,400) + "..." : outer));
//                } catch (StaleElementReferenceException sere) {
//                    System.out.println("  Element stale when reading outerHTML");
//                } catch (Exception ex) {
//                    System.out.println("  Error reading element: " + ex.getMessage());
//                }
//            }
//        }
//
//        // Check presence inside iframes by iterating frames and trying simple selectors inside each
//        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
//        System.out.println("Total iframes on page: " + frames.size());
//        for (int i = 0; i < frames.size(); i++) {
//            try {
//                driver.switchTo().frame(i);
//                int totalFound = 0;
//                for (String sel : selectors) {
//                    totalFound += driver.findElements(By.cssSelector(sel)).size();
//                }
//                System.out.println(" In frame index " + i + " found candidate elements: " + totalFound);
//                driver.switchTo().defaultContent();
//            } catch (Exception e) {
//                System.out.println(" Could not access frame " + i + ": " + e.getMessage());
//                driver.switchTo().defaultContent();
//            }
//        }
//
//        // Shadow root detection for a likely host (use a coarse host selector)
//        try {
//            Object hasShadow = js.executeScript(
//                    "var el = document.querySelector('.hprt-nos-select, .js-hprt-nos-select, select');" +
//                            "if(!el) return null;" +
//                            "return el.shadowRoot != null;");
//            System.out.println("Shadow root present on candidate host? -> " + hasShadow);
//        } catch (Exception e) {
//            System.out.println("Shadow detect error: " + e.getMessage());
//        }
//
//        System.out.println("=== End debug ===");
//    }


}
