import SealedClassTest.MyEntity.MyEntitySubtypeA
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class SealedClassTest {

    sealed class MyEntity {
        data class MyEntitySubtypeA(val id: String) : MyEntity()
        data class MyEntitySubtypeB(val id: String) : MyEntity()
    }

    interface MyRepository {
        fun findById(id: String): MyEntity
    }

    private val repository = mockk<MyRepository>()

    @Test
    fun `should be able to mock sealed classes`() {
        every { repository.findById(any()) } returns MyEntitySubtypeA(randomUUID().toString())
    }
}
