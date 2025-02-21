package cmm.apps.adevinta.datasource_remote.mock

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.io.IOException

class MockServer {

    private val mockWebServer: MockWebServer = MockWebServer()

    @Throws(IOException::class)
    fun start(): String {
        mockWebServer.start()
        return mockWebServer.url("/").toString()
    }

    @Throws(IOException::class)
    fun shutdown() {
        mockWebServer.shutdown()
    }

    fun enqueue(code: Int, body: String): MockServer {
        val response = MockResponse()
            .setResponseCode(code)
            .setBody(body)
        enqueue(response)
        return this
    }

    @Throws(IOException::class)
    fun enqueueFile(code: Int, fileName: String): MockServer {
        val buffer = getStringFromFileName(fileName)
        enqueue(code, buffer)
        return this
    }

    private fun enqueue(response: MockResponse): MockServer {
        mockWebServer.enqueue(response)
        return this
    }

    @Throws(IOException::class)
    private fun getStringFromFileName(fileName: String): String {
        val `in` = this.javaClass.classLoader!!.getResourceAsStream(fileName)
        val buffer = `in`.source().buffer()

        return buffer.readUtf8()
    }
}