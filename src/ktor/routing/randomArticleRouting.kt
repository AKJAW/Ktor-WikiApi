package ktor.routing

import wiki_media.request.RandomArticleRequest
import io.ktor.application.call
import io.ktor.http.Parameters
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.core.inject
import org.koin.ktor.ext.inject
import wiki_media.error.WikiError
import wiki_media.language.LanguageTransformer

fun Routing.randomArticle(){
    val languageTransformer: LanguageTransformer by inject()

    get("/random-article") {
        val languageParameter = call.request.queryParameters["language"] ?: throw WikiError.LanguageMissingError()
        val language = languageTransformer.transform(languageParameter)

        val randomArticleRequest = RandomArticleRequest(language)

        //Set header to json
        call.respond(randomArticleRequest.makeRequest())
    }
}