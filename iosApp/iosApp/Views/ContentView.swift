import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greet()

	var body: some View {
        TabView {
            NavigationStack {
                PokemonListView(state: PokemonListState())
            }.tabItem {
                VStack {
                    Image("MyPokemon")
                        .renderingMode(.template)
                        .resizable()
                        .scaledToFit()
                        .foregroundColor(.red)
                    Spacer()
                    Text("My Pokemon")
                        .lineLimit(1)
                        .minimumScaleFactor(0.5)
                }
            }
            
            Text("TODO")
                .tabItem {
                    VStack {
                        Image("Battles")
                            .renderingMode(.template)
                            .resizable()
                            .scaledToFit()
                            .foregroundColor(.red)
                        Spacer()
                        Text("Battles")
                            .lineLimit(1)
                            .minimumScaleFactor(0.5)
                    }
                }
            
            Text("TODO")
                .tabItem {
                    VStack {
                        Image("Infirmary")
                            .renderingMode(.template)
                            .resizable()
                            .scaledToFit()
                            .foregroundColor(.red)
                        Spacer()
                        Text("Infirmary")
                            .lineLimit(1)
                            .minimumScaleFactor(0.5)
                    }
                }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
