package chavez.alma.pokedexlist.model.domain

data class Pokemon(val name: String,
                   val number: Number,
                   val type: String,
                   val description: String,
                   val height: Float,
                   val weight: Float,
                   val favorite: Boolean,
                   val ability: String,
                   val image: Int
    )
