package com.dbc.pokesuits.model.objetos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Mochila {
//    private ArrayList<Pokemon> bag;
    private int idMochila;
    @NotNull
    @Size(min=0, max=99)
    private int quantidadeGreatBalls;
    @NotNull
    @Size(min=0, max=99)
    private int quantidadeHeavyBalls;
    @NotNull
    @Size(min=0, max=99)
    private int quantidadeMasterBalls;
    @NotNull
    @Size(min=0, max=99)
    private int quantidadeNetBalls;
    @NotNull
    @Size(min=0, max=99)
    private int quantidadePokeBalls;

//    public Mochila() {
//        this.bag = new ArrayList<>();
//        this.quantidadeGreatBalls = 0;
//        this.quantidadeHeavyBalls = 0;
//        this.quantidadeMasterBalls = 0;
//        this.quantidadeNetBalls = 0;
//        this.quantidadePokeBalls = 0;
//    }


    /*private Optional<Pokemon> pesquisarPorNome(String nomePokemon){
        return this.bag.stream().filter( p -> p.getNome().equalsIgnoreCase(nomePokemon)).findFirst(); 
    }
    //CIRAR
    public void adicionarPokemom(Pokemon p){
        this.bag.add(p);
    }

    //CIRAR VARIOS
    public void adicionarPokemons(List<Pokemon> p){
        this.bag.addAll(p);
    }

    //ATUALIZAR
    public void atualizarApelidoPokemon(Scanner scanner){
        System.out.println("Qual pokemon deseja renomear:");
        this.imprimir();
        System.out.println();
        Optional<Pokemon> pokemon = this.pesquisarPorNome(scanner.nextLine());

        if(pokemon.isPresent()){
            System.out.println("Digite um novo Nome: ");
            Pokemon p = pokemon.get();
            this.bag.remove(p);
            p.setNome(scanner.nextLine());
            this.bag.add(p);
        }else{
            System.out.println("Este pokemon não existe!! ");
        }
    }

    //REMOVER
    public Integer removerPokemom(Scanner scanner){
        System.out.println("qual pokemon você deseja assassinar friamente: ");
        this.imprimir();
        System.out.println();
        Optional<Pokemon> pokemon = this.pesquisarPorNome(scanner.nextLine());

        if(pokemon.isPresent()){
            this.bag.remove(pokemon.get());
            System.out.println("Este pokemon \"retirado\"!! ");
            return pokemon.get().getId();
        }else{
            System.out.println("Este pokemon não existe!! ");
        }
		return null;
    }*/
    //MOSTRAR
    public void imprimir() {
//        this.bag.forEach(p -> System.out.println("======================\n" + p + "\n============================\n"));
    }

    //getter
    /*public List<Pokemon> getBag() {
        //retorna uma lista não modificavel;
        return Collections.unmodifiableList(this.bag);
    }*/

//    public int getIdMochila() {
//        return idMochila;
//    }
//
//    public int getQuantidadeGreatBalls() {
//        return quantidadeGreatBalls;
//    }
//
//    public int getQuantidadeHeavyBalls() {
//        return quantidadeHeavyBalls;
//    }
//
//    public int getQuantidadeMasterBalls() {
//        return quantidadeMasterBalls;
//    }
//
//    public int getQuantidadeNetBalls() {
//        return quantidadeNetBalls;
//    }
//
//    public int getQuantidadePokeBalls() {
//        return quantidadePokeBalls;
//    }
//
//    //setters
//    public void setIdMochila(int idMochila) {
//        this.idMochila = idMochila;
//    }
//
//    public boolean setQuantidadeGreatBalls(int quantidadeGreatBalls) {
//        if(quantidadeGreatBalls>=0 && quantidadeGreatBalls<=99){
//            this.quantidadeGreatBalls = quantidadeGreatBalls;
//            return true;
//        }else{
//            System.out.println("Não é possível modificar a quantidade para valores negativos ou maiores que 99");
//            return false;
//        }
//    }
//
//    public boolean setQuantidadeHeavyBalls(int quantidadeHeavyBalls) {
//        if(quantidadeHeavyBalls>=0 && quantidadeHeavyBalls<=99){
//            this.quantidadeHeavyBalls = quantidadeHeavyBalls;
//            return true;
//        }else{
//            System.out.println("Não é possível modificar a quantidade para valores negativos ou maiores que 99");
//            return false;
//        }
//    }
//
//    public boolean setQuantidadeMasterBalls(int quantidadeMasterBalls) {
//        if(quantidadeMasterBalls>=0 && quantidadeMasterBalls<=99){
//            this.quantidadeMasterBalls = quantidadeMasterBalls;
//            return true;
//        }else{
//            System.out.println("Não é possível modificar a quantidade para valores negativos ou maiores que 99");
//            return false;
//        }
//    }
//
//    public boolean setQuantidadeNetBalls(int quantidadeNetBalls) {
//        if(quantidadeNetBalls>=0 && quantidadeNetBalls<=99){
//            this.quantidadeNetBalls = quantidadeNetBalls;
//            return true;
//        }else{
//            System.out.println("Não é possível modificar a quantidade para valores negativos ou maiores que 99");
//            return false;
//        }
//    }
//
//    public boolean setQuantidadePokeBalls(int quantidadePokeBalls) {
//        if(quantidadePokeBalls>=0 && quantidadePokeBalls<=99){
//            this.quantidadePokeBalls = quantidadePokeBalls;
//            return true;
//        }else{
//            System.out.println("Não é possível modificar a quantidade para valores negativos ou maiores que 99");
//            return false;
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "Mochila{" +
//                "idMochila=" + idMochila +
//                ", quantidadeGreatBalls=" + quantidadeGreatBalls +
//                ", quantidadeHeavyBalls=" + quantidadeHeavyBalls +
//                ", quantidadeMasterBalls=" + quantidadeMasterBalls +
//                ", quantidadeNetBalls=" + quantidadeNetBalls +
//                ", quantidadePokeBalls=" + quantidadePokeBalls +
//                '}';
//    }
}








