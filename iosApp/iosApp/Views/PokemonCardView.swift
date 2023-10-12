//
//  PokemonCardView.swift
//  iosApp
//
//  Created by Davide Destefanis on 12/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PokemonCardView: View{
    @StateObject var state: PokemonCardDetails
    
    var body: some View{
        NavigationLink(destination:PokemonPageDetailView(pokemonDetailState: PokemonPageDetailState(pokemonDetail: state.pokemondetailExended))){
            VStack(alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/) {
                AsyncImage(url: URL(string: state.pokemondetailExended.imageUrl)!){
                    imageState in
                    if let image = imageState.image {
                        image.resizable().aspectRatio(contentMode: .fill)
                    } else if let _ = imageState.error{
                        ProgressView().padding()
                    } else {
                        Color.clear
                    }
                }
                .cornerRadius(8)
                .frame(width: 100, height: 100)
                .task {
                    print(URL(string: state.pokemondetailExended.imageUrl)!)
                }
                
                Text(state.pokemondetailExended.name.capitalized)
                    .tint(.black)
                    .font(Font.system(size: 20.0, weight: .bold))
            
            }
        }
    }
}
