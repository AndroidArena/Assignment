import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {
//here we will try to fetch my repository "androidarena" and see if all tests passed using Mockk library - this will test our mock coroutines & viewmodel stability 
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    lateinit var mainViewModel: MainViewModel

    val dispatcher = Dispatchers.Unconfined

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(dispatcher, dispatcher, getRepositoriesUseCase)
    }

    @Test
    fun testFetchRepositories_Positive() {
        coEvery { getRepositoriesUseCase.execute(any()) } returns listOf(
            Repository("a", "name"),
            Repository("b", "name"),
            Repository("c", "name")
        )

        mainViewModel.repositoriesLiveData.observeForever {}

        mainViewModel.fetchRepositories("androidarena")

        assert(mainViewModel.repositoriesLiveData.value != null)
        assert(mainViewModel.repositoriesLiveData.value!!.status == LiveDataResult.STATUS.SUCCESS)
    }

    @Test
    fun testFetchRepositories_Negative() {
        coEvery { getRepositoriesUseCase.execute(any()) } coAnswers { throw Exception("No network") }

        mainViewModel.repositoriesLiveData.observeForever {}

        mainViewModel.fetchRepositories("androidarena")

        assert(mainViewModel.repositoriesLiveData.value != null)
        assert(mainViewModel.repositoriesLiveData.value!!.status == LiveDataResult.STATUS.ERROR)
    }

}
