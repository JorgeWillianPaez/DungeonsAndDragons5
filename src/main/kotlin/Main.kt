package org.example

import org.example.character.classes.IClasse
import org.example.character.races.IRaca
import org.example.personagem.Personagem
import org.example.classes.Barbaro
import org.example.racas.*

fun main() {
    println("CRIAÇÃO DE PERSONAGEM:")
    println("--------------------------------------")

    var racaEscolhida: IRaca? = null
    var classeEscolhida: IClasse? = null

    val possiveisRacas = arrayOf(
        Anao(),
        Draconato(),
        Elfo(),
        Gnomo(),
        Halfing(),
        Humano(),
        MeioElfo(),
        MeioOrc(),
        Tiefling()
    )

    val possiveisClasses = arrayOf(
        Barbaro()
    )

    var personagem: Personagem?

    print("Primeiramente, insira o nome do seu personagem: ")
    val nomePersonagem = readlnOrNull()

    println("--------------------------------------")

    println("Escolha sua raça:")

    while (racaEscolhida == null) {
        val opcoesDisponiveis = mutableListOf<Int>()

        println("Opção | Raça")
        for((index, raca) in possiveisRacas.withIndex()){
            opcoesDisponiveis.add(index + 1)
            println("${index + 1}     | ${raca.nome}")
        }

        print("Digite a opção: ")
        val opcao = readln().toInt()

        if (opcao !in opcoesDisponiveis) {
            println("Opção indisponível! Tente novamente...")
            println("--------------------------------------")
        } else {
            racaEscolhida = possiveisRacas[opcao - 1]
        }
    }

    println("-----------------------------------")
    println("Escolha sua classe:")

    while (classeEscolhida == null) {
        val opcoesDisponiveis = mutableListOf<Int>()

        println("Opção | Classe")
        for((index, classe) in possiveisClasses.withIndex()){
            opcoesDisponiveis.add(index + 1)
            println("${index + 1}     | ${classe.nome}")
        }

        print("Digite a opção: ")
        val opcao = readln().toInt()

        if (opcao !in opcoesDisponiveis) {
            println("Opção indisponível! Tente novamente...")
            println("--------------------------------------")
        } else {
            classeEscolhida = possiveisClasses[opcao - 1]
        }
    }

    personagem = Personagem(racaEscolhida, classeEscolhida)
    personagem.nome = nomePersonagem.toString()

    println("-----------------------------------")
    println("Escolha a forma como deseja distribuir seus atributos:")
    var opcaoDistribuicao = 0
    val opcoesDisponiveisDistribuicao = arrayOf<Int>(1, 2, 3)

    while (opcaoDistribuicao !in opcoesDisponiveisDistribuicao) {
        println("Opção | Descrição")
        println("1     | Distribuir 27 pontos iniciais.")
        println("2     | Utilizar os dados (4d6).")
        println("3     | Utilizar os valores pré definidos (15, 14, 13, 12, 10, 8).")

        print("Digite a opção: ")
        opcaoDistribuicao = readln().toInt()

        if (opcaoDistribuicao !in opcoesDisponiveisDistribuicao) {
            println("Opção indisponível! Tente novamente...")
            println("--------------------------------------")
        }
    }

    println("--------------------------------------")

    when (opcaoDistribuicao) {
        1 -> personagem.distribuirPontosIniciais()
        2 -> personagem.rolarDadosIniciais()
        3 -> personagem.distribuirPreDefinidosIniciais()
    }

    personagem = personagem.raca.aplicarProficienciaInicial(personagem)
    personagem.calcularVidaInicial()

    println("Parabéns, seu personagem está criado e pronto para jogar!")
    println("Nome: ${personagem.nome} | Raça: ${personagem.raca.nome} | Classe: ${personagem.classe.nome}")
    println("Vida do personagem: ${personagem.vida}")
    println("******* Atributos *******")
    println("Força: ${personagem.forca.nivelAtual} | Modificador atual = ${personagem.forca.buscarModificadorAtual()}")
    println("Destreza: ${personagem.destreza.nivelAtual} | Modificador atual = ${personagem.destreza.buscarModificadorAtual()}")
    println("Constituição: ${personagem.constituicao.nivelAtual} | Modificador atual = ${personagem.constituicao.buscarModificadorAtual()}")
    println("Inteligência: ${personagem.inteligencia.nivelAtual} | Modificador atual = ${personagem.inteligencia.buscarModificadorAtual()}")
    println("Sabedoria: ${personagem.sabedoria.nivelAtual} | Modificador atual = ${personagem.sabedoria.buscarModificadorAtual()}")
    println("Carisma: ${personagem.carisma.nivelAtual} | Modificador atual = ${personagem.carisma.buscarModificadorAtual()}")
}