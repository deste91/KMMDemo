//
//  MyPokemonListVM.swift
//  iosApp
//
//  Created by Davide Destefanis on 11/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class PokemonListState: ObservableObject {
    var fetch = true
    @Published public var pokemonDetailExended: [Pokemon]
    
    private var repo : PokemonSharedRepo = PokemonSharedRepo()
    
    init() {
        self.pokemonDetailExended = []
    }
    
    @MainActor
    func fetchList() async{
        do{
            try await repo.fetchPokemonsTeam().forEach{element in
                self.pokemonDetailExended.append(element)
            }
        } catch {
            print(error.localizedDescription)
        }
    }
}
