package com.correacaiio.automacaoemporio

import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.abaCervejas
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.abaMarcas
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.consumidorResponsavelTitle
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.consumidorResponsavelUrl
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.emporioDaCerveja
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.emporioDaCervejaCervejasTitle
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.emporioDaCervejaMarcasTitle
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.emporioDaCervejaTitle
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.telaInicial
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.url
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.urlCervejas
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.urlMarcas
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.verificacaoIdadeElement
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.verificacaoIdadeNaoBtnElement
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.verificacaoIdadeSimBtnElement
import com.correacaiio.automacaoemporio.AutomacaoEmporioApplicationTestFixture.verificacaoIdadeTexto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class AutomacaoEmporioApplicationTest : BehaviorSpec({

    given("Em um navegador") {

        `when`("Acesso o link do Emporio da Cerveja") {

            then("visualizo o tela de verificacao de idade") {
                val browser = emporioDaCerveja()
                val verificacaoIdade = browser.findElement(verificacaoIdadeElement())

                verificacaoIdade.isDisplayed shouldBe true
                verificacaoIdade.isEnabled shouldBe true
                verificacaoIdade.text shouldBe verificacaoIdadeTexto()

                browser.quit()
            }
        }
    }

    given("Na tela de verificacao de idade") {

        `when`("clico no botao NAO") {

            then("devo ser direcionado a tela de Consumidor Responsavel") {
                val browser = emporioDaCerveja()
                val btnNao = browser.findElement(verificacaoIdadeNaoBtnElement())

                btnNao.click()

                delay(3.seconds)

                browser.currentUrl shouldBe consumidorResponsavelUrl()
                browser.title shouldBe consumidorResponsavelTitle()

                browser.quit()
            }
        }

        `when`("clico no botao SIM") {

            then("devo ser direcionado a tela principal do Emporio da Cerveja") {
                val browser = emporioDaCerveja()
                val btnSim = browser.findElement(verificacaoIdadeSimBtnElement())

                btnSim.click()

                delay(3.seconds)

                browser.currentUrl shouldBe url()
                browser.title shouldBe emporioDaCervejaTitle()

                browser.quit()
            }
        }
    }

    given("Na tela inicial do Emporio da Cerveja") {

        `when`("clico na aba `Cervejas`") {

            then("devo ser direcionado a tela de cervejas") {
                val browser = telaInicial()
                val abaCervejas = browser.findElement(abaCervejas())

                abaCervejas.click()

                delay(3.seconds)

                browser.currentUrl shouldBe urlCervejas()
                browser.title shouldBe emporioDaCervejaCervejasTitle()

                browser.quit()
            }
        }

        `when`("clico na aba `Marcas`") {

            then("devo ser direcionado a tela de marcas") {
                val browser = telaInicial()
                val abaMarcas = browser.findElement(abaMarcas())

                abaMarcas.click()

                delay(3.seconds)

                browser.currentUrl shouldBe urlMarcas()
                browser.title shouldBe emporioDaCervejaMarcasTitle()

                browser.quit()
            }
        }
    }
})
