package pl.merito.DaniilMylenko.wizytowka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.merito.DaniilMylenko.wizytowka.ui.theme.WizytowkaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runZadanie2()
        runZadanie3()

        enableEdgeToEdge()
        setContent {
            WizytowkaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Daniil",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Student(
    val name: String,
    val punkty: Int,
    val grupa: String
)

fun Student.czyZaliczyl(): Boolean = punkty >= 50

fun runZadanie2() {
    println("=== ZADANIE 2 ===")
    val studenti = listOf(
        Student("Anna", 75, "Group A"),
        Student("Jan", 40, "Group A"),
        Student("Piotr", 90, "Group B"),
        Student("Ewa", 45, "Group B")
    )

    val pogrupowani = studenti.groupBy { it.grupa }
    pogrupowani.forEach { (grupa, lista) ->
        val srednia = lista.map { it.punkty }.average()
        println("Średnia w grupie $grupa: $srednia")
    }

    val zaliczeni = studenti.filter { it.czyZaliczyl() }
    println("Studentzi, którzy zaliczyli: ${zaliczeni.map { it.name }}")
}

data class Produkt(
    val nazwa: String,
    val cena: Double,
    val ilosc: Int
)

class Koszyk {
    private val produkty = mutableListOf<Produkt>()

    fun dodaj(produkt: Produkt) {
        produkty.add(produkt)
    }

    fun usun(nazwa: String) {
        produkty.removeAll { it.nazwa == nazwa }
    }

    fun suma(): Double {
        val sumaCalkowita = produkty.sumOf { it.cena * it.ilosc }
        return when {
            sumaCalkowita > 200.0 -> sumaCalkowita * 0.90
            else -> sumaCalkowita
        }
    }
}

fun runZadanie3() {
    println("=== ZADANIE 3 ===")
    val koszyk = Koszyk()

    koszyk.dodaj(Produkt("Książka", 50.0, 2))
    koszyk.dodaj(Produkt("Myszka", 120.0, 1))

    println("Suma z rabatem: ${koszyk.suma()} zł")

    koszyk.usun("Myszka")
    println("Suma po usunięciu Myszki: ${koszyk.suma()} zł")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WizytowkaTheme {
        Greeting("Daniil")
    }
}