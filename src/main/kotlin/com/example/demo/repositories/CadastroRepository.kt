package com.example.demo.repositories
import com.example.demo.domain.Cadastro
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface CadastroRepository : CrudRepository<Cadastro, String> {

}