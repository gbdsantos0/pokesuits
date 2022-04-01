package com.dbc.pokesuits.enums;

public enum NomesRegras {
    ADMIN(1), LEAGUE_CHAMPION(2), USER(3);

    private Integer idRegra;

    NomesRegras(Integer idRegra) {
        this.idRegra = idRegra;
    }

    public Integer getIdRegra() {
        return idRegra;
    }
}
