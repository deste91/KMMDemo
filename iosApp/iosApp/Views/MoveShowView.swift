//
//  moveShow.swift
//  PokedexTutorial
//
//  Created by Vincenzo Spadavecchia on 20/02/23.
//

import SwiftUI

struct MoveShowView: View {
    var name:String
    var pw:Int
    var middle: String?
    var color: Color?
    var body: some View {
        
        HStack{
            Spacer()

            Text(name).font(.system(size: 20)).bold()
            if let inMiddle = middle {
                Text(inMiddle + ":")
            }else{
                Text(":")
            }
            
            Text(String(pw)).font(.system(size: 20)).foregroundColor(color ?? .black)
            Spacer()
        }
    }
}



