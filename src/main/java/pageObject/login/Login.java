package pageObject.login;

import ConstantGlobal.Constants;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.wrapper.Wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.opentelemetry.api.common.AttributeType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfvoType.FORMULA;

public class Login extends Wrapper {


    public Login(WebDriver driver) {
        super(driver);
    }

    private WebDriver driver;

    @FindBy(id = Constants.USERNAME)
    private WebElement inputUserName;
    @FindBy(id = Constants.PASSWORD)
    private WebElement inputPassword;
    @FindBy(id = Constants.BUTTON_LOGIN)
    private WebElement inputButtonLogin;




    public void userLogin() {
       sendKeys(inputUserName, Constants.USER_VI);
       sendKeys(inputPassword, Constants.PASS_QA);
       clickButton(inputButtonLogin);
    }


    public void prueba() throws InterruptedException{
        Thread.sleep(3000);
        try {
            // Abre el archivo Excel
            FileInputStream file = new FileInputStream(new File("D:\\Automatizacion_Selenuimtest\\Usuario.xlsx"));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Recorre las filas del archivo Excel
            Row row = sheet.getRow(0);

            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();

            // Validar que la fila no sea nula



            // Encuentra los elementos de usuario y contraseña
            WebElement usernameField = driver.findElement(By.id(Constants.USERNAME));
            WebElement passwordField = driver.findElement(By.id(Constants.PASSWORD));
            WebElement loginButton = driver.findElement(By.id(Constants.BUTTON_LOGIN));

            // Ingresa las credenciales
            usernameField.sendKeys(username);
            passwordField.sendKeys(password);
            loginButton.click();

            // Espera y valida el resultado
            Thread.sleep(2000); // Espera para que la página cargue, ajustar según sea necesario
            String currentUrl = driver.getCurrentUrl();

            // Aquí puedes agregar lógica para validar si el login fue exitoso
            if (currentUrl.equals("https://clientes-qa.confuturo.cl/")) {
                System.out.println("Login exitoso para: " + username);
            } else {
                System.out.println("Login fallido para: " + username);
            }



            workbook.close();
            file.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return ""; // Manejar celda vacía
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Formato de fecha
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula(); // Manejar fórmulas si es necesario
            default:
                return "";
        }
    }

}
