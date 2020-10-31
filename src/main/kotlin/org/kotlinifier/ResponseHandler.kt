package org.kotlinifier

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.InputStream
import java.io.OutputStream

@JsonIgnoreProperties(ignoreUnknown = true)
data class QueryStringParameters(val name: String?)

@JsonIgnoreProperties(ignoreUnknown = true)
data class HandlerInput(val queryStringParameters: QueryStringParameters?)

data class HandlerOutput(val names: List<String>)

class ResponseHandler {

    private val mapper = jacksonObjectMapper()

    private val kotlinifier = Kotlinifier()

    fun handler(input: InputStream, output: OutputStream) {
        val inputData = mapper.readValue<HandlerInput>(input)
        val kotlinizedWords =
            if (inputData.queryStringParameters?.name != null) {
                kotlinifier.kotlinify(inputData.queryStringParameters.name)
            } else {
                kotlinifier.getRandomWords()
            }
        mapper.writeValue(output, HandlerOutput(kotlinizedWords))
    }

}
