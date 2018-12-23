package iftm.filipe.com.iftransparenciav2.data.model.response

data class AuxilioRequest(
        val draw: String,
        val recordsTotal: String,
        val recordsFiltered: String,
        val data: List<Auxilio>
) {}

data class Auxilio(
        val data: String,
        val documento: String,
        val documentoResumido: String,
        val subtitulo: String,
        val localizadorGasto: String,
        val fase: String,
        val especie: String,
        val favorecido: String,
        val codigoFavorecido: String,
        val nomeFavorecido: String,
        val ufFavorecido: String,
        val valor: String,
        val ug: String,
        val uo: String,
        val orgao: String,
        val orgaoSuperior: String,
        val grupo: String,
        val elemento: String,
        val modalidade: String,
        val favorecidoIntermediario: Boolean
) {}