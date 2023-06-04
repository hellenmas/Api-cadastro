package com.example.demo.service.impl

import com.example.demo.domain.Cadastro
import com.example.demo.repositories.CadastroRepository
import com.example.demo.request.CadastroRequest
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Optional

@ExtendWith(MockKExtension::class)
class CadastroServiceImplTest {

    @MockK
    private lateinit var repository: CadastroRepository

    @InjectMockKs
    private lateinit var service: CadastroServiceImpl

    @Test
    fun `should create`() {
        val fakeCadastro = buildTest2()
        val buildCadastro = buildTest()

        every { repository.save(any()) } returns buildCadastro

        service.create(fakeCadastro)

        verify(exactly = 1) { repository.save(any()) }
    }

    @Test
    fun `should return all`() {
        val fakeCadastro = listOf(buildTest(), buildTest())

        every { repository.findAll() } returns fakeCadastro

        val cadastro = service.getAll()

        assertEquals(fakeCadastro, cadastro)
        verify(exactly = 1) { repository.findAll() }
    }

    @Test
    fun `should return byId`() {
        val fakeCadastro = buildTest()

        every { repository.findById("123") } returns Optional.of(fakeCadastro)

        service.getById("123")

        verify(exactly = 1) { repository.findById("123") }
    }

    @Test
    fun `should update`() {
        val fakeCadastro = buildTest()

        every { repository.save(any()) } returns fakeCadastro

        service.update(buildTest2())

        verify(exactly = 1) { repository.save(any()) }
    }

    @Test
    fun `should delete`() {
        val fakeCadastro = buildTest()

        every { repository.deleteById("123") } returns Unit
        every { repository.existsById("123") } returns true
        service.delete("123")

        verify(exactly = 1) {
            repository.deleteById("123")
        }
    }

    fun buildTest(
        id: String = "112121",
        title: String = "teste",
        content: String = "teste",
        userId: String = "teste"
    ) = Cadastro(
        id = id,
        title = title,
        content = content,
        userId = userId
    )

    fun buildTest2(
        title: String = "teste",
        content: String = "teste",
        userId: String = "teste"
    ) = CadastroRequest(
        title = title,
        content = content,
        userId = userId
    )
}
