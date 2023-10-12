//
//  PokemonListView.swift
//  iosApp
//
//  Created by Davide Destefanis on 11/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PokemonListView: View {
    @StateObject var state:PokemonListState
    let columns = [GridItem(.flexible()), GridItem(.flexible())]

    var body: some View {
        VStack {
            LazyVGrid(columns: columns) {
                ForEach(0..<state.pokemonDetailExended.count, id: \.self){ index in
                    PokemonCardView(state: PokemonCardDetails(pokemonDetail: state.pokemonDetailExended[index]))
                }
            }
            Spacer()
        }
        .padding()
        .navigationTitle("Pokemon")
        .navigationBarTitleDisplayMode(.inline)
        .task {
            if state.fetch {
                await self.state.fetchList()
                state.fetch.toggle()
            }
        }
    }
}

#Preview {
    PokemonListView(state: PokemonListState())
}
