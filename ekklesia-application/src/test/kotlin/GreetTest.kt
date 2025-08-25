import com.kcanoe.ekklesia.api.greet
import com.kcanoe.ekklesia.models.GreetView
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GreetTest {
    @Test
    fun `greet returns a correct GreetView object`() {
        val expectedGreetView = GreetView(
            handle = "kCanoe",
            greeting = "Welcome to Ekklesia."
        )

        val actualGreetView = greet()

        assertEquals(expectedGreetView, actualGreetView)
    }
}
