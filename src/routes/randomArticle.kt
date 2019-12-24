package com.akjaw.routes

import com.akjaw.errors.WikiError
import com.akjaw.util.WikiLanguage
import com.akjaw.util.isLanguageCorrect
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.randomArticle(){
    get("/random-article") {
        val language: WikiLanguage = call.request.queryParameters["language"] ?: throw WikiError.LanguageMissingError()
        if(!language.isLanguageCorrect()){
            throw WikiError.LanguageIncorrectError()
        }

        call.respond(mapOf("OK" to true))
    }
}