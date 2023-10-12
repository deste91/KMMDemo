//
//  PokemonDetailsVM.swift
//  iosApp
//
//  Created by Davide Destefanis on 11/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class PokemonPageDetailState: ObservableObject{
    @Published var pokemonDetail: Pokemon
    @Published var moveDetail: [Pokemon.Move]
    
    var repo : PokemonSharedRepo = PokemonSharedRepo()
    
    init(pokemonDetail: Pokemon) {
        self.pokemonDetail = pokemonDetail
        self.moveDetail = []
    }
}

class PokemonCardDetails: ObservableObject{
    
    @Published var pokemondetailExended:Pokemon
    
    init(pokemonDetail: Pokemon) {
        self.pokemondetailExended = pokemonDetail
    }

    
}

