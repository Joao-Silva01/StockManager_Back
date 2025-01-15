package com.dev.StockManager.entities.enums;

public enum Category {
    ALIMENTOS_E_BEBIDAS(1,"Alimentos e Bebidas"),
    ROUPAS_E_ACESSORIOS(2,"Roupas e Acessórios"),
    MOVEIS_E_DECORACAO(3,"Móveis e Decoração"),
    PRODUTOS_DE_LIMPEZA(4,"Produtos de Limpeza"),
    BRINQUEDOS_E_JOGOS(5,"Brinquedos e Jogos");

    private Integer cod;
    private String description;

    Category(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static Category validEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(Category x : Category.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: "+ cod);
    }
}
