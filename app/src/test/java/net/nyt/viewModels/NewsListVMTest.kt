package net.nyt.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.runBlocking
import net.nyt.models.MostPopular
import net.nyt.network.Apis
import net.nyt.network.RetrofitBuilder
import net.nyt.nytsample.MockResponseFileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection


class NewsListVMTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    private lateinit var viewModel: NewsListVM
    private lateinit var mockWebServer: MockWebServer
    private lateinit var client: Apis

    @Mock
    private lateinit var apiMostPopularObserver: Observer<MostPopular>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = NewsListVM()
        viewModel.getMostPopularNews(1).observeForever(apiMostPopularObserver)

        mockWebServer = MockWebServer()
        mockWebServer.start()
        client = RetrofitBuilder.instant
    }

    @Test
    fun `fetch details and check response Code 200 returned`() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("most_popular_success_response.json").content)
        mockWebServer.enqueue(response)

        val actualResponse = client.getMostPopularNews(1)

        assertEquals(response.toString().contains("200"), actualResponse.status == "OK")
    }


    @After
    fun tearDown() {
        viewModel.getMostPopularNews(1).removeObserver(apiMostPopularObserver)
        mockWebServer.shutdown()
    }
}