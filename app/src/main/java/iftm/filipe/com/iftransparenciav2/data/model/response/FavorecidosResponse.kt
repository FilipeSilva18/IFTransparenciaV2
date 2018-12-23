package iftm.filipe.com.iftransparenciav2.data.model.response

class FavorecidosResponse(val draw: String,
                          val recordsTotal: String,
                          val recordsFiltered: String,
                          val data: List<Favorecido>
) {}

data class Favorecido(
        val favorecido: String,
        val codigoFavorecido: String,
        val valor: String
) {}