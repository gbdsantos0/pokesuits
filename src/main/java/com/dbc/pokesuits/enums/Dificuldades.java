package com.dbc.pokesuits.enums;

public enum Dificuldades {
    FACIL(30),MEDIO(15),DIFICIL(5);

    private int chance;

    Dificuldades(int chance){
        this.chance = chance;
    }

    public int getChance() {
        return chance;
    }
}
