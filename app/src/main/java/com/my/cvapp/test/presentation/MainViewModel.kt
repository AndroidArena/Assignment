import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.Exception


class MainViewModel(
    mainDispacher : CoroutineDispatcher,
    ioDispacher : CoroutineDispatcher,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val job = SupervisorJob()

    val repositoriesLiveData = MutableLiveData<LiveDataResult<List<Repository>>>()

    private val uiScope = CoroutineScope(mainDispacher + job)

    val ioScope = CoroutineScope(ioDispacher + job)

    fun fetchRepositories(user: String) {

        uiScope.launch {
            repositoriesLiveData.value = LiveDataResult.loading()

            try {
                val data = ioScope.async {
                    return@async getRepositoriesUseCase.execute(user)
                }.await()

                repositoriesLiveData.value = LiveDataResult.success(data)
            } catch (e: Exception) {
                repositoriesLiveData.value = LiveDataResult.error(e)
            }


        }

    }

    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }

}