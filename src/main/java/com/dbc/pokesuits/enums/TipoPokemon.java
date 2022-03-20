package com.dbc.pokesuits.enums;

public enum TipoPokemon {
    BUG(1), DARK(2), DRAGON(3), ELECTRIC(4), FAITY(5), FIGHTING(6), FIRE(7), FLYING(8), GHOST(9), GRASS(10), 
    GROUND(11), ICE(12), NORMAL(13), POISON(14), PSYCHIC(15), ROCK(16), STEEL(17), WATER(18);
	
	private TipoPokemon(int valor) {this.valor = valor;}
	private int valor;
	public int getValor() {return valor;}
}
