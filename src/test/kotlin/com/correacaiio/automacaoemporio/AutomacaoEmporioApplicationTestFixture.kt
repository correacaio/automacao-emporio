package com.correacaiio.automacaoemporio

import io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import org.openqa.selenium.By
import org.openqa.selenium.By.xpath
import org.openqa.selenium.PageLoadStrategy.NORMAL
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.time.Duration

object AutomacaoEmporioApplicationTestFixture {

    private val TIMEOUT: Duration = Duration.ofSeconds(30)

    fun emporioDaCerveja(): WebDriver {
        val browser = chromeWebDriver()
        browser.get(url())
        return browser
    }

    fun telaInicial(): WebDriver {
        val browser = emporioDaCerveja()
        browser.findElement(verificacaoIdadeSimBtnElement()).click()

        return browser
    }

    fun chromeWebDriver(): WebDriver {
        chromedriver().setup()

        val driver = ChromeDriver(
            ChromeOptions().apply { setPageLoadStrategy(NORMAL) }
        )

        driver.manage().apply {
            timeouts().implicitlyWait(TIMEOUT)
            timeouts().pageLoadTimeout(TIMEOUT)
            window().maximize()
        }

        return driver
    }

    fun url(): String =
        "https://www.emporiodacerveja.com.br/"

    fun urlCervejas(): String =
        "https://www.emporiodacerveja.com.br/cervejas"

    fun urlMarcas(): String =
        "https://www.emporiodacerveja.com.br/marcas"

    fun verificacaoIdadeTexto(): String =
        "Você tem 18 anos ou mais?\n" +
            "O consumo de bebidas alcoólicas é proibido para menores de 18 anos.\n" +
            "NÃO\n" +
            "SIM"

    fun verificacaoIdadeElement(): By =
        xpath("//*[@id=\"modal-close-outside-age-gate\"]/div")

    fun verificacaoIdadeNaoBtnElement(): By =
        xpath("//*[@id=\"modal-close-outside-age-gate\"]/div/div/div[2]/div[3]/button[1]")

    fun verificacaoIdadeSimBtnElement(): By =
        xpath("//*[@id=\"modal-close-outside-age-gate\"]/div/div/div[2]/div[3]/button[2]")

    fun consumidorResponsavelUrl(): String =
        "https://www.ambev.com.br/ESG/consumo-responsavel/"

    fun consumidorResponsavelTitle(): String =
        "Consumo Responsável - Ambev"

    fun emporioDaCervejaTitle(): String =
        "Empório da Cerveja"

    fun emporioDaCervejaCervejasTitle(): String =
        "Todas as Cervejas"

    fun emporioDaCervejaMarcasTitle(): String =
        "Marcas"

    fun abaCervejas(): By =
        xpath("//*[@id=\"menu-item-category-cerveja\"]")

    fun abaMarcas(): By =
        xpath("//*[@id=\"menu-item-category-marcas\"]")
}
