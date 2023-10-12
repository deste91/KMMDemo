//
//  PokemonPageDetailView.swift
//  PokedexTutorial
//
//  Created by Vincenzo Spadavecchia on 15/02/23.
//

import SwiftUI

struct PokemonPageDetailView: View {
    @StateObject var pokemonDetailState:PokemonPageDetailState
    @State var loading = true
    var i = 0
    var movesToView = Constant.movesToView
    @ViewBuilder
    var body: some View {
        ScrollView{
            AsyncImage(url: URL(string: pokemonDetailState.pokemonDetail.imageUrl)!){
                    imageState in
                    if let image = imageState.image{
                        
                        image.resizable().aspectRatio(contentMode: .fill)
                        
                    } else if let _ = imageState.error{
                        ProgressView().padding()

                            
                    }else {
                        Color.clear
                    }
            }
            .clipShape(RoundedRectangle(cornerRadius: 8.0))
                    .overlay{
                        RoundedRectangle(cornerRadius: 8.0).stroke(.white, lineWidth: 4)
                    }
                    .shadow(radius: 7)
                    .padding()
            
            VStack{
                HStack(alignment: .center){
                    Text(pokemonDetailState.pokemonDetail.name.capitalized)
                        .font((.system(size: 30)))
                        .bold()
                }
                ForEach(0..<pokemonDetailState.pokemonDetail.stats.count, id: \.self) { index in
                    HStack {
                        Text(pokemonDetailState.pokemonDetail.stats[index].name.capitalized)
                        Spacer()
                        Text(String(pokemonDetailState.pokemonDetail.stats[index].baseStat))
                            .font(Font.system(size: 20.0, weight: .bold))
                    }
                    .padding(.horizontal, 40)
                    .padding(.vertical, 10)
                }.task {
                    print(pokemonDetailState.pokemonDetail.stats)
                }
                
                Text("Moves").font(.system(size: 25)).bold()
                
                VStack{
                    ForEach(Array(stride(from: 0, to: movesToView, by: 2)),id: \.self){i in
                        HStack{
                            
                        }
                    }
                }
                }
        }.navigationTitle(pokemonDetailState.pokemonDetail.name.capitalized)
    }
    
}




